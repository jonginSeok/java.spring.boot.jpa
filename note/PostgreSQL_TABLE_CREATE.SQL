-- 1) 사용자/계정
drop table if exists tbl_app_user;
create table tbl_app_user (
  id            bigserial primary key,
  username      varchar(100) unique not null,
  email         varchar(255) unique,
  password_hash varchar(255) not null,
  is_active     boolean not null default true,
  is_admin      boolean not null default false,  -- 최상위 관리자 플래그
  created_at    timestamp not null default now(),
  updated_at    timestamp not null default now()
);

-- 2) 권한(permission)
drop table if exists tbl_permission;
create table tbl_permission (
  id           bigserial primary key,
  resource_key varchar(100) not null,      -- e.g., "inventory", "user", "order"
  action_key   varchar(100) not null,      -- e.g., "view", "query", "save", "import_xls", "export_xls"
  description  varchar(255),
  unique (resource_key, action_key)
);

-- 3) 역할(role)
drop table if exists tbl_role;
create table tbl_role (
  id          bigserial primary key,
  role_key    varchar(100) unique not null,  -- e.g., "role_inventory_manager"
  name        varchar(100) not null,
  description varchar(255)
);

-- 4) 역할-권한 매핑
drop table if exists tbl_role_permission;
create table tbl_role_permission (
  role_id       bigint not null references tbl_role(id) on delete cascade,
  permission_id bigint not null references tbl_permission(id) on delete cascade,
  primary key (role_id, permission_id)
);

-- 5) 권한그룹(rolegroup)
drop table if exists tbl_role_group;
create table tbl_role_group (
  id          bigserial primary key,
  group_key   varchar(100) unique not null,  -- e.g., "rg_seoul_branch"
  name        varchar(100) not null,
  description varchar(255)
);

-- 6) 권한그룹-역할 매핑
drop table if exists tbl_role_group_role;
create table tbl_role_group_role (
  role_group_id bigint not null references tbl_role_group(id) on delete cascade,
  role_id       bigint not null references tbl_role(id) on delete cascade,
  primary key (role_group_id, role_id)
);

-- 7) 사용자-권한그룹 매핑
drop table if exists tbl_user_role_group;
create table tbl_user_role_group (
  user_id       bigint not null references tbl_app_user(id) on delete cascade,
  role_group_id bigint not null references tbl_role_group(id) on delete cascade,
  primary key (user_id, role_group_id)
);

-- 8) 사용자-직접 역할 매핑 (선택)
drop table if exists tbl_user_role;
create table tbl_user_role (
  user_id bigint not null references tbl_app_user(id) on delete cascade,
  role_id bigint not null references tbl_role(id) on delete cascade,
  primary key (user_id, role_id)
);

-- 9) 메뉴
drop table if exists tbl_menu;
create table tbl_menu (
  id             bigserial primary key,
  menu_key       varchar(100) unique not null,   -- e.g., "menu_inventory"
  name           varchar(100) not null,
  path           varchar(255),                    -- 프런트 라우트
  parent_id      bigint references tbl_menu(id) on delete set null,
  display_order  int not null default 0,
  is_enabled     boolean not null default true
);

-- 10) 메뉴 접근에 필요한 역할/권한 정의
drop table if exists tbl_menu_required_role;
create table tbl_menu_required_role (
  menu_id bigint not null references tbl_menu(id) on delete cascade,
  role_id bigint not null references tbl_role(id) on delete cascade,
  primary key (menu_id, role_id)
);

drop table if exists tbl_menu_required_permission;
create table tbl_menu_required_permission (
  menu_id      bigint not null references tbl_menu(id) on delete cascade,
  permission_id bigint not null references tbl_permission(id) on delete cascade,
  primary key (menu_id, permission_id)
);

-- 11) 화면(page)와 액션 정의
drop table if exists tbl_page;
create table tbl_page (
  id        bigserial primary key,
  page_key  varchar(120) unique not null,   -- e.g., "page_inventory_list"
  name      varchar(120) not null,
  menu_id   bigint references tbl_menu(id) on delete set null
);

drop table if exists tbl_page_action;
create table tbl_page_action (
  id             bigserial primary key,
  page_id        bigint not null references tbl_page(id) on delete cascade,
  action_key     varchar(120) not null,     -- e.g., "save", "query", "export_xls", "import_xls"
  description    varchar(255),
  unique (page_id, action_key)
);

-- 12) 페이지 액션과 권한/역할 매핑 (버튼 노출 제어의 핵심)
drop table if exists tbl_page_action_permission;
create table tbl_page_action_permission (
  page_action_id bigint not null references tbl_page_action(id) on delete cascade,
  permission_id  bigint not null references tbl_permission(id) on delete cascade,
  primary key (page_action_id, permission_id)
);

drop table if exists tbl_page_action_role;
create table tbl_page_action_role (
  page_action_id bigint not null references tbl_page_action(id) on delete cascade,
  role_id        bigint not null references tbl_role(id) on delete cascade,
  primary key (page_action_id, role_id)
);

-- 13) 감사/로그 (선택)
drop table if exists tbl_audit_log;
create table tbl_audit_log (
  id          bigserial primary key,
  user_id     bigint references tbl_app_user(id),
  event       varchar(200) not null,
  details     jsonb,
  occurred_at timestamp not null default now()
);

/*
drop table if exists tbl_role_permission;
drop table if exists tbl_role_group_role;
drop table if exists tbl_user_role_group;
drop table if exists tbl_role_group;
drop table if exists tbl_menu_required_role;
drop table if exists tbl_menu_required_permission;
drop table if exists tbl_page_action_permission;
drop table if exists tbl_page_action_role;
drop table if exists tbl_page_action;
drop table if exists tbl_audit_log;
drop table if exists tbl_user_role;
drop table if exists tbl_page;
drop table if exists tbl_permission;
drop table if exists tbl_menu;
drop table if exists tbl_role;
drop table if exists tbl_app_user;
*/


/*
-- Add comments to the table and its columns
COMMENT ON TABLE public.training_trainingsession IS '정보';

COMMENT ON COLUMN public.training_trainingsession.id IS '학습id';

COMMENT ON COLUMN public.training_trainingsession.model_name IS '모델명';

COMMENT ON COLUMN public.training_trainingsession.version IS '버전';

COMMENT ON COLUMN public.training_trainingsession.status IS '상태';

COMMENT ON COLUMN public.training_trainingsession.dataset_name IS '데이터셋';

COMMENT ON COLUMN public.training_trainingsession.gpu_info IS 'GPU 정보';

COMMENT ON COLUMN public.training_trainingsession.memory_info IS '메모리 정보';

COMMENT ON COLUMN public.training_trainingsession.total_epochs IS '총 에포크';

COMMENT ON COLUMN public.training_trainingsession.current_epoch IS '현재 에포크';

COMMENT ON COLUMN public.training_trainingsession.batch_size IS '배치 크기';

COMMENT ON COLUMN public.training_trainingsession.learning_rate IS '학습률';

COMMENT ON COLUMN public.training_trainingsession.image_size IS '이미지 크기';

COMMENT ON COLUMN public.training_trainingsession.optimizer IS '옵티마이저';

COMMENT ON COLUMN public.training_trainingsession.early_stopping IS '조기 종료 사용';

COMMENT ON COLUMN public.training_trainingsession.patience IS '조기 종료 patience';

COMMENT ON COLUMN public.training_trainingsession.augmentation IS '데이터 증강 사용';

COMMENT ON COLUMN public.training_trainingsession.rotation_angle IS '이미지 회전(각)';

COMMENT ON COLUMN public.training_trainingsession.train_percent IS 'train %';

COMMENT ON COLUMN public.training_trainingsession.valid_percent IS 'valid %';

COMMENT ON COLUMN public.training_trainingsession.test_percent IS 'test %';

COMMENT ON COLUMN public.training_trainingsession.description IS '설명';

COMMENT ON COLUMN public.training_trainingsession.notify_method IS '알림구분';

COMMENT ON COLUMN public.training_trainingsession.notify_email IS '알림이메일';

COMMENT ON COLUMN public.training_trainingsession.dataset_path IS '데이터셋 경로';

COMMENT ON COLUMN public.training_trainingsession.config IS '설정정보 ';

COMMENT ON COLUMN public.training_trainingsession.start_time IS '시작 시간';

COMMENT ON COLUMN public.training_trainingsession.end_time IS '종료 시간';

COMMENT ON COLUMN public.training_trainingsession.created_at IS '생성시간';

COMMENT ON COLUMN public.training_trainingsession.created_id IS '생성자 id';

COMMENT ON COLUMN public.training_trainingsession.updated_at IS '수정시간';

COMMENT ON COLUMN public.training_trainingsession.updated_id IS '수정자 id';



-- Add comments to the table and its columns
COMMENT ON TABLE public.training_trainingmetric IS '측정';

COMMENT ON COLUMN public.training_trainingmetric.id IS '분류id';

COMMENT ON COLUMN public.training_trainingmetric.session_id IS '학습id';

COMMENT ON COLUMN public.training_trainingmetric.epoch IS '에포크';

COMMENT ON COLUMN public.training_trainingmetric.loss_total IS '[CNN] 총 손실';

COMMENT ON COLUMN public.training_trainingmetric.train_acc IS '[CNN] 훈련 정확도';

COMMENT ON COLUMN public.training_trainingmetric.val_acc IS '[CNN] 유효 정확도';

COMMENT ON COLUMN public.training_trainingmetric.train_loss IS '훈련 손실';

COMMENT ON COLUMN public.training_trainingmetric.val_loss IS '검증 손실';

COMMENT ON COLUMN public.training_trainingmetric.map50 IS 'mAP@0.5';

COMMENT ON COLUMN public.training_trainingmetric.map95 IS 'mAP@0.5:0.95';

COMMENT ON COLUMN public.training_trainingmetric.precision IS '정밀도';

COMMENT ON COLUMN public.training_trainingmetric.recall IS '재현율';

COMMENT ON COLUMN public.training_trainingmetric.timestamp IS '현재 에포크';

COMMENT ON COLUMN public.training_trainingmetric.created_at IS '생성시간';

COMMENT ON COLUMN public.training_trainingmetric.created_id IS '생성자 id';

COMMENT ON COLUMN public.training_trainingmetric.updated_at IS '수정시간';

COMMENT ON COLUMN public.training_trainingmetric.updated_id IS '수정자 id';



-- Add comments to the table and its columns
COMMENT ON TABLE public.training_classmetric IS '분류';

COMMENT ON COLUMN public.training_classmetric.id IS '분류id';

COMMENT ON COLUMN public.training_classmetric.session_id IS '학습id';

COMMENT ON COLUMN public.training_classmetric.index IS '인덱스';

COMMENT ON COLUMN public.training_classmetric.class_name IS '분류명';

COMMENT ON COLUMN public.training_classmetric.precision IS '정밀도';

COMMENT ON COLUMN public.training_classmetric.recall IS '재현율';

COMMENT ON COLUMN public.training_classmetric.f1_score IS 'F1';

COMMENT ON COLUMN public.training_classmetric.instances IS '검증손실';

COMMENT ON COLUMN public.training_classmetric.created_at IS '생성시간';

COMMENT ON COLUMN public.training_classmetric.created_id IS '생성자 id';

COMMENT ON COLUMN public.training_classmetric.updated_at IS '수정시간';

COMMENT ON COLUMN public.training_classmetric.updated_id IS '수정자 id';





-- PostgreSQL에서 테이블의 컬럼명을 변경하거나 특정 컬럼을 제거

--ALTER TABLE 테이블_이름 RENAME COLUMN 변경_전_컬럼_이름 TO 변경_후_컬럼_이름;
ALTER TABLE training_trainingsession RENAME COLUMN current_epoch TO epoch;

--ALTER TABLE 테이블_이름 DROP COLUMN 컬럼_이름;
ALTER TABLE training_trainingsession DROP COLUMN total_epochs;

ALTER TABLE training_trainingsession
ADD COLUMN created_id  VARCHAR(500) NOT NULL;

ALTER TABLE training_sessions
ADD COLUMN loss FLOAT,
ADD COLUMN epoch INTEGER;

-- TRUNCATE TABLE training_trainingmetric RESTART IDENTITY;
-- TRUNCATE TABLE training_classmetric RESTART IDENTITY;
-- TRUNCATE TABLE training_trainingsession RESTART IDENTITY;

-- ALTER SEQUENCE training_trainingmetric_id_seq RESTART WITH 1;
-- ALTER SEQUENCE training_classmetric_id_seq RESTART WITH 1;
-- ALTER SEQUENCE training_trainingsession_id_seq RESTART WITH 1;

 */
