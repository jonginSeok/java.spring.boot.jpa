-- 예시 권한
INSERT INTO tbl_permission(resource_key, action_key, description) VALUES
('inventory', 'VIEW', '재고 화면 보기'),
('inventory', 'QUERY', '재고 조회'),
('inventory', 'SAVE', '재고 저장'),
('inventory', 'EXPORT_XLS', '재고 엑셀 다운로드'),
('inventory', 'IMPORT_XLS', '재고 엑셀 업로드');

-- 예시 역할
INSERT INTO tbl_role(role_key, name, description) VALUES
('ROLE_INVENTORY_VIEWER', 'Inventory Viewer', '재고 보기/조회'),
('ROLE_INVENTORY_MANAGER', 'Inventory Manager', '재고 저장/엑셀');

-- 역할-권한 매핑
-- Viewer: VIEW, QUERY
INSERT INTO tbl_role_permission(role_id, permission_id)
SELECT r.id, p.id FROM tbl_role r, tbl_permission p
WHERE r.role_key = 'ROLE_INVENTORY_VIEWER' AND p.resource_key='inventory' AND p.action_key IN ('VIEW','QUERY');

-- Manager: SAVE, EXPORT_XLS, IMPORT_XLS (+ VIEW, QUERY 포함해도 됨)
INSERT INTO tbl_role_permission(role_id, permission_id)
SELECT r.id, p.id FROM tbl_role r, tbl_permission p
WHERE r.role_key = 'ROLE_INVENTORY_MANAGER' AND p.resource_key='inventory' AND p.action_key IN ('VIEW','QUERY','SAVE','EXPORT_XLS','IMPORT_XLS');

-- 메뉴/페이지/액션
INSERT INTO tbl_menu(menu_key, name, path, display_order) VALUES ('MENU_INVENTORY', '재고관리', '/inventory', 10);

INSERT INTO tbl_page(page_key, name, menu_id)
SELECT 'PAGE_INVENTORY_LIST', '재고 목록', m.id FROM tbl_menu m WHERE m.menu_key='MENU_INVENTORY';

INSERT INTO tbl_page_action(page_id, action_key, description)
SELECT p.id, k.action_key, k.description
FROM tbl_page p
JOIN (VALUES ('QUERY','조회'), ('SAVE','저장'), ('EXPORT_XLS','엑셀 다운로드'), ('IMPORT_XLS','엑셀 업로드')) AS k(action_key, description)
ON TRUE
WHERE p.page_key='PAGE_INVENTORY_LIST';

-- 페이지 액션 ↔ 권한 매핑
INSERT INTO tbl_page_action_permission(page_action_id, permission_id)
SELECT pa.id, pm.id
FROM tbl_page_action pa
JOIN tbl_permission pm ON pm.resource_key='inventory' AND pm.action_key = pa.action_key
WHERE pa.page_id = (SELECT id FROM tbl_page WHERE page_key='PAGE_INVENTORY_LIST');

-- 메뉴 접근 제한 (Viewer 이상 접근 허용)
INSERT INTO tbl_menu_required_role(menu_id, role_id)
SELECT (SELECT id FROM tbl_menu WHERE menu_key='MENU_INVENTORY'), r.id
FROM tbl_role r
WHERE r.role_key IN ('ROLE_INVENTORY_VIEWER', 'ROLE_INVENTORY_MANAGER');
