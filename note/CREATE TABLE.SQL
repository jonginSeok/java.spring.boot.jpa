-- 1) 사용자/계정
DROP TABLE IF EXISTS app_user;
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
DROP TABLE IF EXISTS permission;
CREATE TABLE permission (
  id           BIGSERIAL PRIMARY KEY,
  resource_key VARCHAR(100) NOT NULL,      -- e.g., "inventory", "user", "order"
  action_key   VARCHAR(100) NOT NULL,      -- e.g., "VIEW", "QUERY", "SAVE", "IMPORT_XLS", "EXPORT_XLS"
  description  VARCHAR(255),
  UNIQUE (resource_key, action_key)
);

-- 3) 역할(Role)
DROP TABLE IF EXISTS role;
CREATE TABLE role (
  id          BIGSERIAL PRIMARY KEY,
  role_key    VARCHAR(100) UNIQUE NOT NULL,  -- e.g., "ROLE_INVENTORY_MANAGER"
  name        VARCHAR(100) NOT NULL,
  description VARCHAR(255)
);

-- 4) 역할-권한 매핑
DROP TABLE IF EXISTS role_permission;
CREATE TABLE role_permission (
  role_id       BIGINT NOT NULL REFERENCES role(id) ON DELETE CASCADE,
  permission_id BIGINT NOT NULL REFERENCES permission(id) ON DELETE CASCADE,
  PRIMARY KEY (role_id, permission_id)
);

-- 5) 권한그룹(RoleGroup)
DROP TABLE IF EXISTS role_group;
CREATE TABLE role_group (
  id          BIGSERIAL PRIMARY KEY,
  group_key   VARCHAR(100) UNIQUE NOT NULL,  -- e.g., "RG_SEOUL_BRANCH"
  name        VARCHAR(100) NOT NULL,
  description VARCHAR(255)
);

-- 6) 권한그룹-역할 매핑
DROP TABLE IF EXISTS role_group_role;
CREATE TABLE role_group_role (
  role_group_id BIGINT NOT NULL REFERENCES role_group(id) ON DELETE CASCADE,
  role_id       BIGINT NOT NULL REFERENCES role(id) ON DELETE CASCADE,
  PRIMARY KEY (role_group_id, role_id)
);

-- 7) 사용자-권한그룹 매핑
DROP TABLE IF EXISTS user_role_group;
CREATE TABLE user_role_group (
  user_id       BIGINT NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
  role_group_id BIGINT NOT NULL REFERENCES role_group(id) ON DELETE CASCADE,
  PRIMARY KEY (user_id, role_group_id)
);

-- 8) 사용자-직접 역할 매핑 (선택)
DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role (
  user_id BIGINT NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
  role_id BIGINT NOT NULL REFERENCES role(id) ON DELETE CASCADE,
  PRIMARY KEY (user_id, role_id)
);

-- 9) 메뉴
DROP TABLE IF EXISTS menu;
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
DROP TABLE IF EXISTS menu_required_role;
CREATE TABLE menu_required_role (
  menu_id BIGINT NOT NULL REFERENCES menu(id) ON DELETE CASCADE,
  role_id BIGINT NOT NULL REFERENCES role(id) ON DELETE CASCADE,
  PRIMARY KEY (menu_id, role_id)
);

DROP TABLE IF EXISTS menu_required_permission;
CREATE TABLE menu_required_permission (
  menu_id      BIGINT NOT NULL REFERENCES menu(id) ON DELETE CASCADE,
  permission_id BIGINT NOT NULL REFERENCES permission(id) ON DELETE CASCADE,
  PRIMARY KEY (menu_id, permission_id)
);

-- 11) 화면(Page)와 액션 정의
DROP TABLE IF EXISTS page;
CREATE TABLE page (
  id        BIGSERIAL PRIMARY KEY,
  page_key  VARCHAR(120) UNIQUE NOT NULL,   -- e.g., "PAGE_INVENTORY_LIST"
  name      VARCHAR(120) NOT NULL,
  menu_id   BIGINT REFERENCES menu(id) ON DELETE SET NULL
);

DROP TABLE IF EXISTS page_action;
CREATE TABLE page_action (
  id             BIGSERIAL PRIMARY KEY,
  page_id        BIGINT NOT NULL REFERENCES page(id) ON DELETE CASCADE,
  action_key     VARCHAR(120) NOT NULL,     -- e.g., "SAVE", "QUERY", "EXPORT_XLS", "IMPORT_XLS"
  description    VARCHAR(255),
  UNIQUE (page_id, action_key)
);

-- 12) 페이지 액션과 권한/역할 매핑 (버튼 노출 제어의 핵심)
DROP TABLE IF EXISTS page_action_permission;
CREATE TABLE page_action_permission (
  page_action_id BIGINT NOT NULL REFERENCES page_action(id) ON DELETE CASCADE,
  permission_id  BIGINT NOT NULL REFERENCES permission(id) ON DELETE CASCADE,
  PRIMARY KEY (page_action_id, permission_id)
);

DROP TABLE IF EXISTS page_action_role;
CREATE TABLE page_action_role (
  page_action_id BIGINT NOT NULL REFERENCES page_action(id) ON DELETE CASCADE,
  role_id        BIGINT NOT NULL REFERENCES role(id) ON DELETE CASCADE,
  PRIMARY KEY (page_action_id, role_id)
);

-- 13) 감사/로그 (선택)
DROP TABLE IF EXISTS audit_log;
CREATE TABLE audit_log (
  id          BIGSERIAL PRIMARY KEY,
  user_id     BIGINT REFERENCES app_user(id),
  event       VARCHAR(200) NOT NULL,
  details     JSONB,
  occurred_at TIMESTAMP NOT NULL DEFAULT NOW()
);


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

 */



/*

 * PostgreSQL에서 테이블의 컬럼명을 변경하거나 특정 컬럼을 제거
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
