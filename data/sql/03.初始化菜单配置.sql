USE passjava_admin;

insert  into `sys_menu`(`menu_id`,`parent_id`,`name`,`url`,`perms`,`type`,`icon`,`order_num`)
values 
(31,0,'题目中心','','',0,'editor',0),
(32,31,'背题管理','question/question','',1,'config', 0),
(33,31,'类型配置','question/type','',1,'config', 0),

(34,0,'内容中心','','',0,'editor',0),
(35,34,'横幅配置','content/banner','',1,'config', 0),
(36,34,'资讯配置','content/news','',1,'config', 0);



-- 试卷管理 SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES ('31', '试卷管理', 'question/exam', NULL, '1', 'config', '6');

-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '查看', null, 'question:exam:list,question:exam:info', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '新增', null, 'question:exam:save', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '修改', null, 'question:exam:update', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '删除', null, 'question:exam:delete', '2', null, '6';

-- 试卷题目管理 SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES ('31', '试卷题目管理', 'question/examquestion', NULL, '1', 'config', '6');

-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '查看', null, 'question:examquestion:list,question:examquestion:info', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '新增', null, 'question:examquestion:save', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '修改', null, 'question:examquestion:update', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '删除', null, 'question:examquestion:delete', '2', null, '6';