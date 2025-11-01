-- 1) 사용자/계정
drop table if exists tbl_app_user;
create table tbl_app_user (
  id            bigint auto_increment primary key,
  username      varchar(100) unique not null,
  email         varchar(255) unique,
  password_hash varchar(255) not null,
  is_active     tinyint(1) not null default 1,
  is_admin      tinyint(1) not null default 0,  -- 최상위 관리자 플래그
  created_at    timestamp not null default current_timestamp,
  updated_at    timestamp not null default current_timestamp on update current_timestamp
);

-- 2) 권한(permission)
drop table if exists tbl_permission;
create table tbl_permission (
  id           bigint auto_increment primary key,
  resource_key varchar(100) not null,
  action_key   varchar(100) not null,
  description  varchar(255),
  unique (resource_key, action_key)
);

-- 3) 역할(role)
drop table if exists tbl_role;
create table tbl_role (
  id          bigint auto_increment primary key,
  role_key    varchar(100) unique not null,
  name        varchar(100) not null,
  description varchar(255)
);

-- 4) 역할-권한 매핑
drop table if exists tbl_role_permission;
create table tbl_role_permission (
  role_id       bigint not null,
  permission_id bigint not null,
  primary key (role_id, permission_id),
  foreign key (role_id) references tbl_role(id) on delete cascade,
  foreign key (permission_id) references tbl_permission(id) on delete cascade
);

-- 5) 권한그룹(rolegroup)
drop table if exists tbl_role_group;
create table tbl_role_group (
  id          bigint auto_increment primary key,
  group_key   varchar(100) unique not null,
  name        varchar(100) not null,
  description varchar(255)
);

-- 6) 권한그룹-역할 매핑
drop table if exists tbl_role_group_role;
create table tbl_role_group_role (
  role_group_id bigint not null,
  role_id       bigint not null,
  primary key (role_group_id, role_id),
  foreign key (role_group_id) references tbl_role_group(id) on delete cascade,
  foreign key (role_id) references tbl_role(id) on delete cascade
);

-- 7) 사용자-권한그룹 매핑
drop table if exists tbl_user_role_group;
create table tbl_user_role_group (
  user_id       bigint not null,
  role_group_id bigint not null,
  primary key (user_id, role_group_id),
  foreign key (user_id) references tbl_app_user(id) on delete cascade,
  foreign key (role_group_id) references tbl_role_group(id) on delete cascade
);

-- 8) 사용자-직접 역할 매핑
drop table if exists tbl_user_role;
create table tbl_user_role (
  user_id bigint not null,
  role_id bigint not null,
  primary key (user_id, role_id),
  foreign key (user_id) references tbl_app_user(id) on delete cascade,
  foreign key (role_id) references tbl_role(id) on delete cascade
);

-- 9) 메뉴
drop table if exists tbl_menu;
create table tbl_menu (
  id             bigint auto_increment primary key,
  menu_key       varchar(100) unique not null,
  name           varchar(100) not null,
  path           varchar(255),
  parent_id      bigint,
  display_order  int not null default 0,
  is_enabled     tinyint(1) not null default 1,
  foreign key (parent_id) references tbl_menu(id) on delete set null
);

-- 10) 메뉴 접근에 필요한 역할/권한 정의
drop table if exists tbl_menu_required_role;
create table tbl_menu_required_role (
  menu_id bigint not null,
  role_id bigint not null,
  primary key (menu_id, role_id),
  foreign key (menu_id) references tbl_menu(id) on delete cascade,
  foreign key (role_id) references tbl_role(id) on delete cascade
);

drop table if exists tbl_menu_required_permission;
create table tbl_menu_required_permission (
  menu_id bigint not null,
  permission_id bigint not null,
  primary key (menu_id, permission_id),
  foreign key (menu_id) references tbl_menu(id) on delete cascade,
  foreign key (permission_id) references tbl_permission(id) on delete cascade
);

-- 11) 화면(page)와 액션 정의
drop table if exists tbl_page;
create table tbl_page (
  id        bigint auto_increment primary key,
  page_key  varchar(120) unique not null,
  name      varchar(120) not null,
  menu_id   bigint,
  foreign key (menu_id) references tbl_menu(id) on delete set null
);

drop table if exists tbl_page_action;
create table tbl_page_action (
  id             bigint auto_increment primary key,
  page_id        bigint not null,
  action_key     varchar(120) not null,
  description    varchar(255),
  unique (page_id, action_key),
  foreign key (page_id) references tbl_page(id) on delete cascade
);

-- 12) 페이지 액션과 권한/역할 매핑
drop table if exists tbl_page_action_permission;
create table tbl_page_action_permission (
  page_action_id bigint not null,
  permission_id  bigint not null,
  primary key (page_action_id, permission_id),
  foreign key (page_action_id) references tbl_page_action(id) on delete cascade,
  foreign key (permission_id) references tbl_permission(id) on delete cascade
);

drop table if exists tbl_page_action_role;
create table tbl_page_action_role (
  page_action_id bigint not null,
  role_id        bigint not null,
  primary key (page_action_id, role_id),
  foreign key (page_action_id) references tbl_page_action(id) on delete cascade,
  foreign key (role_id) references tbl_role(id) on delete cascade
);

-- 13) 감사/로그
drop table if exists tbl_audit_log;
create table tbl_audit_log (
  id          bigint auto_increment primary key,
  user_id     bigint,
  event       varchar(200) not null,
  details     json,
  occurred_at timestamp not null default current_timestamp,
  foreign key (user_id) references tbl_app_user(id)
);
