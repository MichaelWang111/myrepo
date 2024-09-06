/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : passjava_qms

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 06/07/2024 00:02:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qms_exam
-- ----------------------------
DROP TABLE IF EXISTS `qms_exam`;
CREATE TABLE `qms_exam` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '题目主键 id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '试卷标题',
  `limit_time` int NOT NULL COMMENT '试卷限时',
  `level` tinyint(1) NOT NULL COMMENT '试卷难度等级(1-简单，2-中等，3-最难）',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否开启',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  `create_user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='试卷表';

-- ----------------------------
-- Records of qms_exam
-- ----------------------------
BEGIN;
INSERT INTO `qms_exam` (`id`, `title`, `limit_time`, `level`, `enable`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`) VALUES (2, '测试', 100, 0, 1, '2023-06-06 09:12:27', '2023-06-11 17:08:56', 0, '', '');
INSERT INTO `qms_exam` (`id`, `title`, `limit_time`, `level`, `enable`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`) VALUES (3, '312', 312, 1, 1, '2023-06-11 09:16:14', '2023-06-11 09:16:39', 0, '', '');
INSERT INTO `qms_exam` (`id`, `title`, `limit_time`, `level`, `enable`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`) VALUES (4, '213', 23, 1, 0, '2023-06-11 09:17:20', '2023-06-11 09:17:40', 1, '', '');
COMMIT;

-- ----------------------------
-- Table structure for qms_exam_question
-- ----------------------------
DROP TABLE IF EXISTS `qms_exam_question`;
CREATE TABLE `qms_exam_question` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '题目主键 id',
  `question` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '问题',
  `choose_a` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '答案选项 A',
  `choose_b` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '答案选项 B',
  `choose_c` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '答案选项 C',
  `choose_d` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '答案选项 D',
  `right_choose` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '正确选项(单选或多选，A/B/C/D)',
  `type` tinyint(1) NOT NULL COMMENT '题目类型，如 JVM',
  `multiple` tinyint(1) NOT NULL COMMENT '是否多选',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  `create_user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否开启',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='试卷题目表';

-- ----------------------------
-- Records of qms_exam_question
-- ----------------------------
BEGIN;
INSERT INTO `qms_exam_question` (`id`, `question`, `choose_a`, `choose_b`, `choose_c`, `choose_d`, `right_choose`, `type`, `multiple`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`, `enable`) VALUES (1, '5+2=？', '52', '0', '10', '7', 'A,C,D', 1, 1, '2023-06-03 18:00:00', '2023-06-05 09:36:55', 0, '1', '2', 1);
INSERT INTO `qms_exam_question` (`id`, `question`, `choose_a`, `choose_b`, `choose_c`, `choose_d`, `right_choose`, `type`, `multiple`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`, `enable`) VALUES (2, '1', '2', '34', '5', '6', 'A,B', 2, 0, '2023-06-04 23:39:31', '2023-06-06 08:56:19', 0, '1', '2', 1);
INSERT INTO `qms_exam_question` (`id`, `question`, `choose_a`, `choose_b`, `choose_c`, `choose_d`, `right_choose`, `type`, `multiple`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`, `enable`) VALUES (3, '2222', '1', '2', '4', '45', 'A', 1, 0, '2023-06-08 21:05:18', '2023-06-09 06:20:19', 0, NULL, NULL, 1);
INSERT INTO `qms_exam_question` (`id`, `question`, `choose_a`, `choose_b`, `choose_c`, `choose_d`, `right_choose`, `type`, `multiple`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`, `enable`) VALUES (4, '333', '34545', '33', '334', '434', 'A,B,C,D', 2, 0, '2023-06-09 06:20:13', '2023-06-09 06:20:13', 0, NULL, NULL, 1);
INSERT INTO `qms_exam_question` (`id`, `question`, `choose_a`, `choose_b`, `choose_c`, `choose_d`, `right_choose`, `type`, `multiple`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`, `enable`) VALUES (5, 'rtdt', 'fgdg', 'etre', 'etredt', 'etdtr', 'A', 1, 0, '2023-06-09 06:20:33', '2023-06-09 06:20:33', 0, NULL, NULL, 1);
INSERT INTO `qms_exam_question` (`id`, `question`, `choose_a`, `choose_b`, `choose_c`, `choose_d`, `right_choose`, `type`, `multiple`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`, `enable`) VALUES (6, 'fgh', 'dfgd', 'dfg', 'qfhgf', 'fgfg', 'A', 1, 0, '2023-06-09 06:22:45', '2023-06-09 06:22:45', 0, NULL, NULL, 0);
INSERT INTO `qms_exam_question` (`id`, `question`, `choose_a`, `choose_b`, `choose_c`, `choose_d`, `right_choose`, `type`, `multiple`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`, `enable`) VALUES (7, 'dfg', 'drt', 'dgd', 'gfdf', 'fhh', 'A', 1, 0, '2023-06-09 06:23:22', '2023-06-09 06:23:22', 0, NULL, NULL, 1);
INSERT INTO `qms_exam_question` (`id`, `question`, `choose_a`, `choose_b`, `choose_c`, `choose_d`, `right_choose`, `type`, `multiple`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`, `enable`) VALUES (8, 'ghqdfg', 'er', 'er', 'esrs', 'sdf', 'B', 3, 0, '2023-06-09 06:23:34', '2023-06-09 06:23:34', 0, NULL, NULL, 0);
INSERT INTO `qms_exam_question` (`id`, `question`, `choose_a`, `choose_b`, `choose_c`, `choose_d`, `right_choose`, `type`, `multiple`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`, `enable`) VALUES (9, 'hj', 'fg', 'hjk', 'kl', 'l;', 'A', 1, 0, '2023-06-09 06:23:47', '2023-06-09 06:23:47', 0, NULL, NULL, 0);
INSERT INTO `qms_exam_question` (`id`, `question`, `choose_a`, `choose_b`, `choose_c`, `choose_d`, `right_choose`, `type`, `multiple`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`, `enable`) VALUES (10, 'ghjq', 'fh', 'tyr', 'rtgr', 'dtr', 'A', 2, 0, '2023-06-09 06:24:01', '2023-06-09 06:24:01', 0, NULL, NULL, 0);
INSERT INTO `qms_exam_question` (`id`, `question`, `choose_a`, `choose_b`, `choose_c`, `choose_d`, `right_choose`, `type`, `multiple`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`, `enable`) VALUES (11, 'ghjq', 'fgf', 'dfd', 'dfgq', 'drgd', 'B', 3, 0, '2023-06-09 06:24:14', '2023-06-09 06:24:14', 0, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for qms_exam_question_relation
-- ----------------------------
DROP TABLE IF EXISTS `qms_exam_question_relation`;
CREATE TABLE `qms_exam_question_relation` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键 id',
  `question_id` int NOT NULL COMMENT '问题 id',
  `exam_id` int NOT NULL COMMENT '试卷 id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  `create_user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `question_id_ exam_id_ del_flag` (`question_id`,`exam_id`,`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='试卷题目关联表';

-- ----------------------------
-- Records of qms_exam_question_relation
-- ----------------------------
BEGIN;
INSERT INTO `qms_exam_question_relation` (`id`, `question_id`, `exam_id`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`) VALUES (71, 3, 2, '2023-06-11 09:08:10', '2023-06-11 09:34:16', 1, NULL, NULL);
INSERT INTO `qms_exam_question_relation` (`id`, `question_id`, `exam_id`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`) VALUES (72, 1, 2, '2023-06-11 09:08:10', '2023-06-11 09:34:16', 1, NULL, NULL);
INSERT INTO `qms_exam_question_relation` (`id`, `question_id`, `exam_id`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`) VALUES (73, 1, 3, '2023-06-11 09:16:14', '2023-06-11 09:16:14', 0, NULL, NULL);
INSERT INTO `qms_exam_question_relation` (`id`, `question_id`, `exam_id`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`) VALUES (76, 3, 4, '2023-06-11 09:17:40', '2023-06-11 09:17:40', 0, NULL, NULL);
INSERT INTO `qms_exam_question_relation` (`id`, `question_id`, `exam_id`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`) VALUES (77, 1, 2, '2023-06-11 09:34:16', '2023-06-11 09:34:29', 1, NULL, NULL);
INSERT INTO `qms_exam_question_relation` (`id`, `question_id`, `exam_id`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`) VALUES (78, 3, 2, '2023-06-11 09:34:16', '2023-06-11 09:34:29', 1, NULL, NULL);
INSERT INTO `qms_exam_question_relation` (`id`, `question_id`, `exam_id`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`) VALUES (79, 1, 2, '2023-06-11 09:34:29', '2023-06-11 09:34:36', 1, NULL, NULL);
INSERT INTO `qms_exam_question_relation` (`id`, `question_id`, `exam_id`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`) VALUES (80, 3, 2, '2023-06-11 09:34:29', '2023-06-11 09:34:36', 1, NULL, NULL);
INSERT INTO `qms_exam_question_relation` (`id`, `question_id`, `exam_id`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`) VALUES (81, 1, 2, '2023-06-11 09:34:36', '2023-06-11 17:08:56', 1, NULL, NULL);
INSERT INTO `qms_exam_question_relation` (`id`, `question_id`, `exam_id`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`) VALUES (82, 3, 2, '2023-06-11 09:34:36', '2023-06-11 17:08:56', 1, NULL, NULL);
INSERT INTO `qms_exam_question_relation` (`id`, `question_id`, `exam_id`, `create_time`, `update_time`, `del_flag`, `create_user`, `update_user`) VALUES (83, 3, 2, '2023-06-11 17:08:56', '2023-06-11 17:08:56', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for qms_question
-- ----------------------------
DROP TABLE IF EXISTS `qms_question`;
CREATE TABLE `qms_question` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '题目标题',
  `answer` varchar(15000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '题目解答',
  `LEVEL` tinyint DEFAULT NULL COMMENT '题目难度等级',
  `display_order` int DEFAULT NULL COMMENT '排序',
  `sub_title` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '副标题',
  `TYPE` tinyint DEFAULT NULL COMMENT '题目类型',
  `ENABLE` tinyint DEFAULT NULL COMMENT '是否显示',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记（0-正常，1-删除）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='八股文题目和解答';

-- ----------------------------
-- Records of qms_question
-- ----------------------------
BEGIN;
INSERT INTO `qms_question` (`id`, `title`, `answer`, `LEVEL`, `display_order`, `sub_title`, `TYPE`, `ENABLE`, `del_flag`, `create_time`, `update_time`) VALUES (1, 'JVM垃圾回收机制', '垃圾自动回收', 1, 1, 'GC', 2, 1, 0, '2022-08-16 22:40:53', '2022-08-16 22:40:53');
INSERT INTO `qms_question` (`id`, `title`, `answer`, `LEVEL`, `display_order`, `sub_title`, `TYPE`, `ENABLE`, `del_flag`, `create_time`, `update_time`) VALUES (2, '知道synchronized原理吗？', 'synchronized是java提供的原子性内置锁，这种内置的并且使用者看不到的锁也被称为**监视器锁**，使用synchronized之后，会在编译之后在同步的代码块前后加上monitorenter和monitorexit字节码指令，他依赖操作系统底层互斥锁实现。他的作用主要就是实现原子性操作和解决共享变量的内存可见性问题。\n\n执行monitorenter指令时会尝试获取对象锁，如果对象没有被锁定或者已经获得了锁，锁的计数器+1。此时其他竞争锁的线程则会进入等待队列中。\n\n执行monitorexit指令时则会把计数器-1，当计数器值为0时，则锁释放，处于等待队列中的线程再继续竞争锁。\n\nsynchronized是排它锁，当一个线程获得锁之后，其他线程必须等待该线程释放锁后才能获得锁，而且由于Java中的线程和操作系统原生线程是一一对应的，线程被阻塞或者唤醒时时会从用户态切换到内核态，这种转换非常消耗性能。\n\n从内存语义来说，加锁的过程会清除工作内存中的共享变量，再从主内存读取，而释放锁的过程则是将工作内存中的共享变量写回主内存。\n\n\n\n*实际上大部分时候我认为说到monitorenter就行了，但是为了更清楚的描述，还是再具体一点*。\n\n如果再深入到源码来说，synchronized实际上有两个队列waitSet和entryList。\n\n1. 当多个线程进入同步代码块时，首先进入entryList\n2. 有一个线程获取到monitor锁后，就赋值给当前线程，并且计数器+1\n3. 如果线程调用wait方法，将释放锁，当前线程置为null，计数器-1，同时进入waitSet等待被唤醒，调用notify或者notifyAll之后又会进入entryList竞争锁\n4. 如果线程执行完毕，同样释放锁，计数器-1，当前线程置为null\n\n![](https://tva1.sinaimg.cn/large/0081Kckwgy1gkmh2gf3v6j31jy0l0go2.jpg)', 2, 1, 'Synchronized', 1, 1, 0, '2022-08-16 22:40:53', '2022-08-16 22:40:53');
INSERT INTO `qms_question` (`id`, `title`, `answer`, `LEVEL`, `display_order`, `sub_title`, `TYPE`, `ENABLE`, `del_flag`, `create_time`, `update_time`) VALUES (3, '说说进程和线程的区别？', '进程是程序的一次执行，是系统进行资源分配和调度的独立单位，他的作用是是程序能够并发执行提高资源利用率和吞吐率。\n\n由于进程是资源分配和调度的基本单位，因为进程的创建、销毁、切换产生大量的时间和空间的开销，进程的数量不能太多，而线程是比进程更小的能独立运行的基本单位，他是进程的一个实体，可以减少程序并发执行时的时间和空间开销，使得操作系统具有更好的并发性。\n\n线程基本不拥有系统资源，只有一些运行时必不可少的资源，比如程序计数器、寄存器和栈，进程则占有堆、栈。\n\n进程是程序的一次执行，是系统进行资源分配和调度的独立单位，他的作用是是程序能够并发执行提高资源利用率和吞吐率。\n\n由于进程是资源分配和调度的基本单位，因为进程的创建、销毁、切换产生大量的时间和空间的开销，进程的数量不能太多，而线程是比进程更小的能独立运行的基本单位，他是进程的一个实体，可以减少程序并发执行时的时间和空间开销，使得操作系统具有更好的并发性。\n\n线程基本不拥有系统资源，只有一些运行时必不可少的资源，比如程序计数器、寄存器和栈，进程则占有堆、栈。\n\n进程是程序的一次执行，是系统进行资源分配和调度的独立单位，他的作用是是程序能够并发执行提高资源利用率和吞吐率。\n\n由于进程是资源分配和调度的基本单位，因为进程的创建、销毁、切换产生大量的时间和空间的开销，进程的数量不能太多，而线程是比进程更小的能独立运行的基本单位，他是进程的一个实体，可以减少程序并发执行时的时间和空间开销，使得操作系统具有更好的并发性。\n\n线程基本不拥有系统资源，只有一些运行时必不可少的资源，比如程序计数器、寄存器和栈，进程则占有堆、栈。\n\n进程是程序的一次执行，是系统进行资源分配和调度的独立单位，他的作用是是程序能够并发执行提高资源利用率和吞吐率。\n\n由于进程是资源分配和调度的基本单位，因为进程的创建、销毁、切换产生大量的时间和空间的开销，进程的数量不能太多，而线程是比进程更小的能独立运行的基本单位，他是进程的一个实体，可以减少程序并发执行时的时间和空间开销，使得操作系统具有更好的并发性。\n\n线程基本不拥有系统资源，只有一些运行时必不可少的资源，比如程序计数器、寄存器和栈，进程则占有堆、栈。\n\n', 2, 2, '进程', 1, 1, 0, '2022-08-16 22:40:53', '2022-08-16 22:40:53');
INSERT INTO `qms_question` (`id`, `title`, `answer`, `LEVEL`, `display_order`, `sub_title`, `TYPE`, `ENABLE`, `del_flag`, `create_time`, `update_time`) VALUES (4, '那锁的优化机制了解吗？', '从JDK1.6版本之后，synchronized本身也在不断优化锁的机制，有些情况下他并不会是一个很重量级的锁了。优化机制包括自适应锁、自旋锁、锁消除、锁粗化、轻量级锁和偏向锁。\n\n锁的状态从低到高依次为**无锁->偏向锁->轻量级锁->重量级锁**，升级的过程就是从低到高，降级在一定条件也是有可能发生的。\n\n**自旋锁**：由于大部分时候，锁被占用的时间很短，共享变量的锁定时间也很短，所有没有必要挂起线程，用户态和内核态的来回上下文切换严重影响性能。自旋的概念就是让线程执行一个忙循环，可以理解为就是啥也不干，防止从用户态转入内核态，自旋锁可以通过设置-XX:+UseSpining来开启，自旋的默认次数是10次，可以使用-XX:PreBlockSpin设置。\n\n**自适应锁**：自适应锁就是自适应的自旋锁，自旋的时间不是固定时间，而是由前一次在同一个锁上的自旋时间和锁的持有者状态来决定。\n\n**锁消除**：锁消除指的是JVM检测到一些同步的代码块，完全不存在数据竞争的场景，也就是不需要加锁，就会进行锁消除。\n\n**锁粗化**：锁粗化指的是有很多操作都是对同一个对象进行加锁，就会把锁的同步范围扩展到整个操作序列之外。\n\n**偏向锁**：当线程访问同步块获取锁时，会在对象头和栈帧中的锁记录里存储偏向锁的线程ID，之后这个线程再次进入同步块时都不需要CAS来加锁和解锁了，偏向锁会永远偏向第一个获得锁的线程，如果后续没有其他线程获得过这个锁，持有锁的线程就永远不需要进行同步，反之，当有其他线程竞争偏向锁时，持有偏向锁的线程就会释放偏向锁。可以用过设置-XX:+UseBiasedLocking开启偏向锁。\n\n**轻量级锁**：JVM的对象的对象头中包含有一些锁的标志位，代码进入同步块的时候，JVM将会使用CAS方式来尝试获取锁，如果更新成功则会把对象头中的状态位标记为轻量级锁，如果更新失败，当前线程就尝试自旋来获得锁。\n\n整个锁升级的过程非常复杂，我尽力去除一些无用的环节，简单来描述整个升级的机制。\n\n简单点说，偏向锁就是通过对象头的偏向线程ID来对比，甚至都不需要CAS了，而轻量级锁主要就是通过CAS修改对象头锁记录和自旋来实现，重量级锁则是除了拥有锁的线程其他全部阻塞。\n\n![](https://tva1.sinaimg.cn/large/0081Kckwgy1gkl9z3kg06j31as0u0doj.jpg)', 2, 3, '锁', 1, 1, 0, '2022-11-01 22:59:29', '2022-11-01 22:59:29');
COMMIT;

-- ----------------------------
-- Table structure for qms_type
-- ----------------------------
DROP TABLE IF EXISTS `qms_type`;
CREATE TABLE `qms_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `TYPE` char(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类型名称',
  `comments` char(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `logo_url` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类型logo路径',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记（0-正常，1-删除）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='题目-题目类型表';

-- ----------------------------
-- Records of qms_type
-- ----------------------------
BEGIN;
INSERT INTO `qms_type` (`id`, `TYPE`, `comments`, `logo_url`, `del_flag`, `create_time`, `update_time`) VALUES (1, 'javaBasic', 'Java基础', 'https://passjava.oss-cn-beijing.aliyuncs.com/2022-08-16/b150bdb3-9e57-4354-85e0-21c0d48d3581_logo.png', 0, '2022-08-16 22:28:16', '2022-08-16 22:28:16');
INSERT INTO `qms_type` (`id`, `TYPE`, `comments`, `logo_url`, `del_flag`, `create_time`, `update_time`) VALUES (2, 'jvm', 'Java虚拟机', 'https://passjava.oss-cn-beijing.aliyuncs.com/2022-08-16/b150bdb3-9e57-4354-85e0-21c0d48d3581_logo.png', 0, '2022-08-16 22:28:16', '2022-08-16 22:28:16');
INSERT INTO `qms_type` (`id`, `TYPE`, `comments`, `logo_url`, `del_flag`, `create_time`, `update_time`) VALUES (3, 'spring', 'Spring核心原理', 'https://passjava.oss-cn-beijing.aliyuncs.com/2022-08-16/b150bdb3-9e57-4354-85e0-21c0d48d3581_logo.png', 0, '2022-08-16 22:28:16', '2022-08-16 22:28:16');
INSERT INTO `qms_type` (`id`, `TYPE`, `comments`, `logo_url`, `del_flag`, `create_time`, `update_time`) VALUES (4, 'bigData', '大数据', 'https://passjava.oss-cn-beijing.aliyuncs.com/2022-08-16/b150bdb3-9e57-4354-85e0-21c0d48d3581_logo.png', 0, '2022-08-16 22:28:16', '2022-08-16 22:28:16');
INSERT INTO `qms_type` (`id`, `TYPE`, `comments`, `logo_url`, `del_flag`, `create_time`, `update_time`) VALUES (5, 'thread', '多线程', 'https://passjava.oss-cn-beijing.aliyuncs.com/2022-08-16/b150bdb3-9e57-4354-85e0-21c0d48d3581_logo.png', 0, '2022-08-16 22:28:16', '2022-08-16 22:28:16');
INSERT INTO `qms_type` (`id`, `TYPE`, `comments`, `logo_url`, `del_flag`, `create_time`, `update_time`) VALUES (7, 'dubbo', 'dubbo面试题', 'https://passjava.oss-cn-beijing.aliyuncs.com/2022-08-16/90e0c69b-77b2-4041-83d4-0c8b928ec2ed_logo.png', 0, '2022-08-16 23:56:02', '2022-08-16 23:56:02');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
