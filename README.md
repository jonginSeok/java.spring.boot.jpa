#System architecture overview

##관리자/사용자 분리, 권한그룹에 따른 메뉴·버튼 노출 제어, PostgreSQL 기반의 RBAC(Role-Based Access Control)로 구성합니다. 
백엔드는 Spring Boot + Spring Security로 인증/인가를 처리하고, 화면 단위로 “허용된 액션 목록”을 내려주어 프런트가 버튼을 숨깁니다.
• 플랫폼: JDK 24, Spring Boot 3.x, Spring Security 6.x, PostgreSQL
• 인증: JWT 기반 로그인 (세션리스)
• 인가: 역할(Role) + 권한(Permission) + 권한그룹(RoleGroup)로 RBAC 구성
• UI 제어: 사용자에게 특정 화면ID(page_key)에 대한 허용 액션(action_key: SAVE, QUERY, EXPORT_XLS, IMPORT_XLS 등) 집합을 API로 제공

Rbac 모델과 권한 전략
권한을 “메뉴 접근”과 “버튼/액션” 두 층으로 분리합니다. 메뉴는 네비게이션 접근 통제, 액션은 화면 내 기능 제어에 사용합니다.
• Label: 사용자
	- User는 하나 이상의 RoleGroup에 속하며, 직접 Role를 가질 수도 있습니다.
• Label: 권한 단위
	- Permission은 “리소스 + 액션”을 표현합니다. 예: resource=“inventory”, action=“SAVE”
• Label: 역할
	- Role은 여러 Permission을 묶은 추상화입니다. 예: ROLE_INVENTORY_MANAGER
• Label: 권한그룹
	- RoleGroup은 프로젝트/조직별 묶음. User ↔ RoleGroup ↔ Role ↔ Permission 체인으로 확장성 확보.
• Label: 메뉴
	- Menu는 계층 구조(parent_id). 각 Menu에 접근에 필요한 최소 Role 또는 Permission을 연결합니다.
• Label: 화면 액션
	- PageAction은 특정 page_key에서 사용할 수 있는 버튼/기능 키 목록입니다. Role/Permission과 매핑하여 노출/비노출을 제어합니다.


PostgreSQL Ddl 설계
아래 스키마는 확장 가능한 RBAC를 위해 정규화했으며, 메뉴/페이지별 액션 제어까지 포함합니다.
[SQL]
-- 1) 사용자/계정
CREATE TABLE app_user (
  id            BIGSERIAL PRIMARY KEY,
  username      VARCHAR(100) UNIQUE NOT NULL,
  email         VARCHAR(255) UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  is_active     BOOLEAN NOT NULL DEFAULT TRUE,
  is_admin      BOOLEAN NOT NULL DEFAULT FALSE,  -- 최상위 관리자 플래그
  created_at    TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at    TIMESTAMP NOT NULL DEFAULT NOW()
);

-- 2) 권한(Permission)
CREATE TABLE permission (
  id           BIGSERIAL PRIMARY KEY,
  resource_key VARCHAR(100) NOT NULL,      -- e.g., "inventory", "user", "order"
  action_key   VARCHAR(100) NOT NULL,      -- e.g., "VIEW", "QUERY", "SAVE", "IMPORT_XLS", "EXPORT_XLS"
  description  VARCHAR(255),
  UNIQUE (resource_key, action_key)
);

-- 3) 역할(Role)
CREATE TABLE role (
  id          BIGSERIAL PRIMARY KEY,
  role_key    VARCHAR(100) UNIQUE NOT NULL,  -- e.g., "ROLE_INVENTORY_MANAGER"
  name        VARCHAR(100) NOT NULL,
  description VARCHAR(255)
);

-- 4) 역할-권한 매핑
CREATE TABLE role_permission (
  role_id       BIGINT NOT NULL REFERENCES role(id) ON DELETE CASCADE,
  permission_id BIGINT NOT NULL REFERENCES permission(id) ON DELETE CASCADE,
  PRIMARY KEY (role_id, permission_id)
);

-- 5) 권한그룹(RoleGroup)
CREATE TABLE role_group (
  id          BIGSERIAL PRIMARY KEY,
  group_key   VARCHAR(100) UNIQUE NOT NULL,  -- e.g., "RG_SEOUL_BRANCH"
  name        VARCHAR(100) NOT NULL,
  description VARCHAR(255)
);

-- 6) 권한그룹-역할 매핑
CREATE TABLE role_group_role (
  role_group_id BIGINT NOT NULL REFERENCES role_group(id) ON DELETE CASCADE,
  role_id       BIGINT NOT NULL REFERENCES role(id) ON DELETE CASCADE,
  PRIMARY KEY (role_group_id, role_id)
);

-- 7) 사용자-권한그룹 매핑
CREATE TABLE user_role_group (
  user_id       BIGINT NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
  role_group_id BIGINT NOT NULL REFERENCES role_group(id) ON DELETE CASCADE,
  PRIMARY KEY (user_id, role_group_id)
);

-- 8) 사용자-직접 역할 매핑 (선택)
CREATE TABLE user_role (
  user_id BIGINT NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
  role_id BIGINT NOT NULL REFERENCES role(id) ON DELETE CASCADE,
  PRIMARY KEY (user_id, role_id)
);

-- 9) 메뉴
CREATE TABLE menu (
  id             BIGSERIAL PRIMARY KEY,
  menu_key       VARCHAR(100) UNIQUE NOT NULL,   -- e.g., "MENU_INVENTORY"
  name           VARCHAR(100) NOT NULL,
  path           VARCHAR(255),                    -- 프런트 라우트
  parent_id      BIGINT REFERENCES menu(id) ON DELETE SET NULL,
  display_order  INT NOT NULL DEFAULT 0,
  is_enabled     BOOLEAN NOT NULL DEFAULT TRUE
);

-- 10) 메뉴 접근에 필요한 역할/권한 정의
CREATE TABLE menu_required_role (
  menu_id BIGINT NOT NULL REFERENCES menu(id) ON DELETE CASCADE,
  role_id BIGINT NOT NULL REFERENCES role(id) ON DELETE CASCADE,
  PRIMARY KEY (menu_id, role_id)
);

CREATE TABLE menu_required_permission (
  menu_id      BIGINT NOT NULL REFERENCES menu(id) ON DELETE CASCADE,
  permission_id BIGINT NOT NULL REFERENCES permission(id) ON DELETE CASCADE,
  PRIMARY KEY (menu_id, permission_id)
);

-- 11) 화면(Page)와 액션 정의
CREATE TABLE page (
  id        BIGSERIAL PRIMARY KEY,
  page_key  VARCHAR(120) UNIQUE NOT NULL,   -- e.g., "PAGE_INVENTORY_LIST"
  name      VARCHAR(120) NOT NULL,
  menu_id   BIGINT REFERENCES menu(id) ON DELETE SET NULL
);

CREATE TABLE page_action (
  id             BIGSERIAL PRIMARY KEY,
  page_id        BIGINT NOT NULL REFERENCES page(id) ON DELETE CASCADE,
  action_key     VARCHAR(120) NOT NULL,     -- e.g., "SAVE", "QUERY", "EXPORT_XLS", "IMPORT_XLS"
  description    VARCHAR(255),
  UNIQUE (page_id, action_key)
);

-- 12) 페이지 액션과 권한/역할 매핑 (버튼 노출 제어의 핵심)
CREATE TABLE page_action_permission (
  page_action_id BIGINT NOT NULL REFERENCES page_action(id) ON DELETE CASCADE,
  permission_id  BIGINT NOT NULL REFERENCES permission(id) ON DELETE CASCADE,
  PRIMARY KEY (page_action_id, permission_id)
);

CREATE TABLE page_action_role (
  page_action_id BIGINT NOT NULL REFERENCES page_action(id) ON DELETE CASCADE,
  role_id        BIGINT NOT NULL REFERENCES role(id) ON DELETE CASCADE,
  PRIMARY KEY (page_action_id, role_id)
);

-- 13) 감사/로그 (선택)
CREATE TABLE audit_log (
  id          BIGSERIAL PRIMARY KEY,
  user_id     BIGINT REFERENCES app_user(id),
  event       VARCHAR(200) NOT NULL,
  details     JSONB,
  occurred_at TIMESTAMP NOT NULL DEFAULT NOW()
);

초기 시드 데이터 예시:
[SQL]
-- 예시 권한
INSERT INTO permission(resource_key, action_key, description) VALUES
('inventory', 'VIEW', '재고 화면 보기'),
('inventory', 'QUERY', '재고 조회'),
('inventory', 'SAVE', '재고 저장'),
('inventory', 'EXPORT_XLS', '재고 엑셀 다운로드'),
('inventory', 'IMPORT_XLS', '재고 엑셀 업로드');

-- 예시 역할
INSERT INTO role(role_key, name, description) VALUES
('ROLE_INVENTORY_VIEWER', 'Inventory Viewer', '재고 보기/조회'),
('ROLE_INVENTORY_MANAGER', 'Inventory Manager', '재고 저장/엑셀');

-- 역할-권한 매핑
-- Viewer: VIEW, QUERY
INSERT INTO role_permission(role_id, permission_id)
SELECT r.id, p.id FROM role r, permission p
WHERE r.role_key = 'ROLE_INVENTORY_VIEWER' AND p.resource_key='inventory' AND p.action_key IN ('VIEW','QUERY');

-- Manager: SAVE, EXPORT_XLS, IMPORT_XLS (+ VIEW, QUERY 포함해도 됨)
INSERT INTO role_permission(role_id, permission_id)
SELECT r.id, p.id FROM role r, permission p
WHERE r.role_key = 'ROLE_INVENTORY_MANAGER' AND p.resource_key='inventory' AND p.action_key IN ('VIEW','QUERY','SAVE','EXPORT_XLS','IMPORT_XLS');

-- 메뉴/페이지/액션
INSERT INTO menu(menu_key, name, path, display_order) VALUES ('MENU_INVENTORY', '재고관리', '/inventory', 10);

INSERT INTO page(page_key, name, menu_id)
SELECT 'PAGE_INVENTORY_LIST', '재고 목록', m.id FROM menu m WHERE m.menu_key='MENU_INVENTORY';

INSERT INTO page_action(page_id, action_key, description)
SELECT p.id, k.action_key, k.description
FROM page p
JOIN (VALUES ('QUERY','조회'), ('SAVE','저장'), ('EXPORT_XLS','엑셀 다운로드'), ('IMPORT_XLS','엑셀 업로드')) AS k(action_key, description)
ON TRUE
WHERE p.page_key='PAGE_INVENTORY_LIST';

-- 페이지 액션 ↔ 권한 매핑
INSERT INTO page_action_permission(page_action_id, permission_id)
SELECT pa.id, pm.id
FROM page_action pa
JOIN permission pm ON pm.resource_key='inventory' AND pm.action_key = pa.action_key
WHERE pa.page_id = (SELECT id FROM page WHERE page_key='PAGE_INVENTORY_LIST');

-- 메뉴 접근 제한 (Viewer 이상 접근 허용)
INSERT INTO menu_required_role(menu_id, role_id)
SELECT (SELECT id FROM menu WHERE menu_key='MENU_INVENTORY'), r.id
FROM role r
WHERE r.role_key IN ('ROLE_INVENTORY_VIEWER', 'ROLE_INVENTORY_MANAGER');


Spring boot 구현 핵심
프로젝트 의존성
• Label: 필수
	- spring-boot-starter-web, spring-boot-starter-security, 
		spring-boot-starter-data-jpa, postgresql, 
		jjwt(또는 spring-boot-starter-oauth2-resource-server)
• Label: 선택
	- flyway 또는 liquibase(DDL/마이그레이션), mapstruct(매핑), springdoc-openapi

엔티티 및 JPA 매핑 예시
[JAVA]
@Entity
@Table(name = "app_user")
public class AppUser {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;
  private String email;
  private String passwordHash;
  private boolean isActive;
  private boolean isAdmin;

  @ManyToMany
  @JoinTable(name="user_role",
      joinColumns=@JoinColumn(name="user_id"),
      inverseJoinColumns=@JoinColumn(name="role_id"))
  private Set<Role> roles = new HashSet<>();

  @ManyToMany
  @JoinTable(name="user_role_group",
      joinColumns=@JoinColumn(name="user_id"),
      inverseJoinColumns=@JoinColumn(name="role_group_id"))
  private Set<RoleGroup> roleGroups = new HashSet<>();
}

@Entity
@Table(name="role")
public class Role {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String roleKey;
  private String name;

  @ManyToMany
  @JoinTable(name="role_permission",
      joinColumns=@JoinColumn(name="role_id"),
      inverseJoinColumns=@JoinColumn(name="permission_id"))
  private Set<Permission> permissions = new HashSet<>();
}

@Entity
@Table(name="permission")
public class Permission {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String resourceKey;
  private String actionKey; // VIEW, QUERY, SAVE, EXPORT_XLS, IMPORT_XLS
}


Security 설정 (JWT + 권한 매핑)
[JAVA]
@Configuration
@EnableMethodSecurity // @PreAuthorize 사용
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/auth/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/menus/**").authenticated()
        .anyRequest().authenticated()
      )
      .oauth2ResourceServer(oauth -> oauth.jwt()); // 또는 커스텀 JWT 필터

    return http.build();
  }

  @Bean
  public JwtAuthenticationConverter jwtAuthenticationConverter(UserAuthorityService authorityService) {
    return token -> {
      // 토큰에서 username 추출 후 DB에서 Role/Permission 집합 로드
      String username = token.getClaimAsString("sub");
      Collection<GrantedAuthority> authorities = authorityService.loadAuthorities(username);
      var auth = new JwtAuthenticationToken(token, authorities);
      return auth;
    };
  }
}


권한 로딩 서비스 예시:
[JAVA]
@Service
public class UserAuthorityService {

  private final UserRepository userRepo;

  public UserAuthorityService(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  public Collection<GrantedAuthority> loadAuthorities(String username) {
    AppUser u = userRepo.findByUsername(username).orElseThrow();
    Set<String> auths = new HashSet<>();

    // 관리자면 모든 권한 부여
    if (u.isAdmin()) {
      auths.add("ROLE_ADMIN");
      auths.add("PERM_ALL");
      return auths.stream().map(SimpleGrantedAuthority::new).toList();
    }

    // 직접 Role
    u.getRoles().forEach(r -> auths.add(r.getRoleKey()));

    // RoleGroup → Role
    u.getRoleGroups().forEach(g -> g.getRoles().forEach(r -> auths.add(r.getRoleKey())));

    // Role → Permission
    u.getRoles().forEach(r -> r.getPermissions().forEach(p -> auths.add(permissionKey(p))));
    u.getRoleGroups().forEach(g -> g.getRoles().forEach(r ->
        r.getPermissions().forEach(p -> auths.add(permissionKey(p)))
    ));

    return auths.stream().map(SimpleGrantedAuthority::new).toList();
  }

  private String permissionKey(Permission p) {
    // "PERM_inventory:SAVE" 형태로 부여
    return "PERM_" + p.getResourceKey() + ":" + p.getActionKey();
  }
}



메서드 보안과 커스텀 PermissionEvaluator
버튼 단위 제어를 위해 화면(page_key)와 action_key를 검사하는 서비스 메서드를 제공합니다.
[JAVA]
@Component("authz")
public class PageAuthorizationService {

  private final PageActionRepository pageActionRepo;
  private final PermissionRepository permissionRepo;

  public boolean canPerform(Authentication auth, String pageKey, String actionKey) {
    if (isAdmin(auth)) return true;

    // page_key + action_key에 매핑된 Permission 또는 Role을 보유하는지 검사
    var actions = pageActionRepo.findByPageKeyAndActionKey(pageKey, actionKey);
    if (actions.isEmpty()) return false;

    for (var pa : actions) {
      // Permission 검사
      var perms = pa.getPermissions().stream()
        .map(this::permAuthorityKey)
        .toList();
      for (var a : auth.getAuthorities()) {
        if (perms.contains(a.getAuthority())) return true;
      }
      // Role 검사
      var roles = pa.getRoles().stream().map(Role::getRoleKey).toList();
      for (var a : auth.getAuthorities()) {
        if (roles.contains(a.getAuthority())) return true;
      }
    }
    return false;
  }

  private boolean isAdmin(Authentication auth) {
    return auth.getAuthorities().stream()
      .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
  }

  private String permAuthorityKey(Permission p) {
    return "PERM_" + p.getResourceKey() + ":" + p.getActionKey();
  }
}



컨트롤러 예시:
[JAVA]
@RestController
@RequestMapping("/inventory")
public class InventoryController {

  @GetMapping("/items")
  @PreAuthorize("@authz.canPerform(authentication, 'PAGE_INVENTORY_LIST', 'QUERY')")
  public List<ItemDto> list(...) { ... }

  @PostMapping("/items")
  @PreAuthorize("@authz.canPerform(authentication, 'PAGE_INVENTORY_LIST', 'SAVE')")
  public ItemDto save(...) { ... }

  @GetMapping("/items/export")
  @PreAuthorize("@authz.canPerform(authentication, 'PAGE_INVENTORY_LIST', 'EXPORT_XLS')")
  public ResponseEntity<Resource> exportXls(...) { ... }

  @PostMapping("/items/import")
  @PreAuthorize("@authz.canPerform(authentication, 'PAGE_INVENTORY_LIST', 'IMPORT_XLS')")
  public ImportResult importXls(...) { ... }
}



메뉴 API 예시(프런트가 네비게이션 구성에 사용):
[JAVA]
@RestController
@RequestMapping("/menus")
public class MenuController {

  @GetMapping
  public List<MenuDto> myMenus(Authentication auth) {
    // 현재 사용자 권한으로 접근 가능한 메뉴만 반환
    // 메뉴에 요구되는 Role/Permission과 교집합이 있는지 검사해서 필터링
  }
}



허용 액션 조회 API 예시(화면 진입 시 호출):
[JAVA]
@RestController
@RequestMapping("/pages")
public class PageController {

  @GetMapping("/{pageKey}/actions")
  public Set<String> allowedActions(Authentication auth, @PathVariable String pageKey) {
    // 해당 pageKey의 모든 action_key 중 @authz.canPerform(...)가 true인 것만 반환
    // 예: {"QUERY","SAVE","EXPORT_XLS"}
  }
}



프런트엔드 연동 포인트
• Label: 메뉴 렌더링
	- /menus 호출로 “접근 가능한 메뉴”만 표시. 라우팅 진입 전 가드에서도 재검증하면 안전합니다.
• Label: 버튼 노출
	- 화면 마운트 시 /pages/{pageKey}/actions 호출로 허용 액션 세트를 받아 버튼 가시성 제어.
• Label: 이중 방어
	- 프런트에서 버튼을 숨기더라도 백엔드가 최종 인가를 강제합니다(@PreAuthorize로 서버-사이드 검증).

운영 팁과 마이그레이션
• Label: 마이그레이션
	- Flyway/Liquibase로 DDL 관리, 시드 데이터는 별도 마이그레이션 파일로 분리합니다.
• Label: 캐싱
	- 권한/메뉴/페이지 액션은 변경이 빈번하지 않으므로 Caffeine/Redis 캐시로 응답 속도 개선.
• Label: 감사 로그
	- 권한 실패/성공 이벤트를 audit_log에 기록해 보안 점검과 이슈 분석에 활용.
• Label: 관리자 도구
	- Role/Permission/RoleGroup 편집용 관리 화면을 제공하면 운영 효율이 크게 올라갑니다.

원하는 코드 스택(Spring Boot 버전, JWT 대신 세션 사용 여부, 프런트 프레임워크 등)을 알려주시면, 
위 설계를 기반으로 구체적인 패키지 구조, DTO, 리포지토리, 서비스 계층 코드까지 이어서 맞춤 샘플을 작성해 드릴게요.