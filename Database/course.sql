-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.0.21-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 coursedb 的数据库结构
CREATE DATABASE IF NOT EXISTS `coursedb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `coursedb`;


-- 导出  表 coursedb.t_attendance 结构
CREATE TABLE IF NOT EXISTS `t_attendance` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_id` char(32) NOT NULL,
  `t_attendance_type_id` char(32) NOT NULL,
  `t_teacher_id` char(32) NOT NULL,
  `begin_datetime` datetime DEFAULT NULL,
  `end_datetime` datetime DEFAULT NULL,
  `note` text,
  PRIMARY KEY (`id`),
  KEY `fk_t_attendance_t_course_teaching_class1_idx` (`t_course_teaching_class_id`),
  KEY `fk_t_attendance_t_attendance_type1_idx` (`t_attendance_type_id`),
  KEY `fk_t_attendance_t_teacher1_idx` (`t_teacher_id`),
  CONSTRAINT `fk_t_attendance_t_attendance_type1_idx` FOREIGN KEY (`t_attendance_type_id`) REFERENCES `t_attendance_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_attendance_t_course_teaching_class1` FOREIGN KEY (`t_course_teaching_class_id`) REFERENCES `t_course_teaching_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_attendance_t_teacher1` FOREIGN KEY (`t_teacher_id`) REFERENCES `t_teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_attendance_mode 结构
CREATE TABLE IF NOT EXISTS `t_attendance_mode` (
  `id` char(32) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_attendance_state 结构
CREATE TABLE IF NOT EXISTS `t_attendance_state` (
  `id` char(32) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_attendance_student 结构
CREATE TABLE IF NOT EXISTS `t_attendance_student` (
  `id` char(32) NOT NULL,
  `t_attendance_id` char(32) NOT NULL,
  `t_student_id` char(32) NOT NULL,
  `t_attendance_state_id` char(32) NOT NULL,
  `t_attendance_mode_id` char(32) NOT NULL,
  `checking_in_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_attendance_student_t_attendance1_idx` (`t_attendance_id`),
  KEY `fk_t_attendance_student_t_student1_idx` (`t_student_id`),
  KEY `fk_t_attendance_student_t_attendance_mode1_idx` (`t_attendance_mode_id`),
  KEY `fk_t_attendance_student_t_attendance_state1` (`t_attendance_state_id`),
  CONSTRAINT `fk_t_attendance_student_t_attendance1` FOREIGN KEY (`t_attendance_id`) REFERENCES `t_attendance` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_attendance_student_t_attendance_mode1` FOREIGN KEY (`t_attendance_mode_id`) REFERENCES `t_attendance_mode` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_attendance_student_t_attendance_state1` FOREIGN KEY (`t_attendance_state_id`) REFERENCES `t_attendance_state` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_attendance_student_t_student1` FOREIGN KEY (`t_student_id`) REFERENCES `t_student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_attendance_type 结构
CREATE TABLE IF NOT EXISTS `t_attendance_type` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_id` char(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_attendance_type_1_idx` (`t_course_teaching_class_id`),
  CONSTRAINT `fk_t_attendance_type_1_idx` FOREIGN KEY (`t_course_teaching_class_id`) REFERENCES `t_course_teaching_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course 结构
CREATE TABLE IF NOT EXISTS `t_course` (
  `id` char(32) NOT NULL,
  `name` varchar(2048) NOT NULL,
  `num` varchar(1024) NOT NULL,
  `english_name` varchar(2048) DEFAULT NULL,
  `experiment_hours` int(11) NOT NULL,
  `class_hours` int(11) NOT NULL,
  `t_course_style_id` char(32) NOT NULL,
  `t_course_type_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_t_course_style1_idx` (`t_course_style_id`),
  KEY `fk_t_course_t_course_type1_idx` (`t_course_type_id`),
  CONSTRAINT `fk_t_course_t_course_style1` FOREIGN KEY (`t_course_style_id`) REFERENCES `t_course_style` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_course_t_course_type1` FOREIGN KEY (`t_course_type_id`) REFERENCES `t_course_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_database 结构
CREATE TABLE IF NOT EXISTS `t_course_database` (
  `id` char(32) NOT NULL,
  `t_database_id` char(32) NOT NULL,
  `t_user_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_database_t_database1_idx` (`t_database_id`),
  KEY `fk_t_course_database_t_user1_idx` (`t_user_id`),
  CONSTRAINT `fk_t_course_database_t_database1` FOREIGN KEY (`t_database_id`) REFERENCES `t_database` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_course_database_t_user1` FOREIGN KEY (`t_user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_department 结构
CREATE TABLE IF NOT EXISTS `t_course_department` (
  `id` char(32) NOT NULL,
  `t_course_id` char(32) NOT NULL,
  `t_department_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_department_t_course1_idx` (`t_course_id`),
  KEY `fk_t_course_department_t_department1_idx` (`t_department_id`),
  CONSTRAINT `fk_t_course_department_t_course1` FOREIGN KEY (`t_course_id`) REFERENCES `t_course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_course_department_t_department1` FOREIGN KEY (`t_department_id`) REFERENCES `t_department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_style 结构
CREATE TABLE IF NOT EXISTS `t_course_style` (
  `id` char(32) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class` (
  `id` char(32) NOT NULL,
  `t_course_id` char(32) NOT NULL,
  `name` varchar(2048) NOT NULL,
  `t_course_teaching_term_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_teaching_class_t_course1_idx` (`t_course_id`),
  KEY `FK_t_course_teaching_class_t_course_teaching_term` (`t_course_teaching_term_id`),
  CONSTRAINT `FK_t_course_teaching_class_t_course_teaching_term` FOREIGN KEY (`t_course_teaching_term_id`) REFERENCES `t_course_teaching_term` (`id`),
  CONSTRAINT `fk_t_course_teaching_class_t_course1` FOREIGN KEY (`t_course_id`) REFERENCES `t_course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class_forum_reply 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class_forum_reply` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_forum_topic_id` char(32) NOT NULL,
  `t_user_id` char(32) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_teaching_class_forum_reply1_idx` (`t_course_teaching_class_forum_topic_id`),
  KEY `fk_t_course_teaching_class_forum_reply2_idx` (`t_user_id`),
  CONSTRAINT `fk_t_course_teaching_class_forum_reply1_idx` FOREIGN KEY (`t_course_teaching_class_forum_topic_id`) REFERENCES `t_course_teaching_class_forum_topic` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_course_teaching_class_forum_reply2_idx` FOREIGN KEY (`t_user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class_forum_reply_file 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class_forum_reply_file` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_forum_reply_id` char(32) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `filepath` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_teaching_class_forum_reply_file1_idx` (`t_course_teaching_class_forum_reply_id`),
  CONSTRAINT `fk_t_course_teaching_class_forum_reply_file1_idx` FOREIGN KEY (`t_course_teaching_class_forum_reply_id`) REFERENCES `t_course_teaching_class_forum_reply` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class_forum_topic 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class_forum_topic` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_id` char(32) NOT NULL,
  `t_user_id` char(32) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_date` datetime NOT NULL,
  `view_count` int(11) NOT NULL,
  `flag` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_teaching_class_forum_topic1_idx` (`t_course_teaching_class_id`),
  KEY `fk_t_course_teaching_class_forum_topic2_idx` (`t_user_id`),
  CONSTRAINT `fk_t_course_teaching_class_forum_topic1_idx` FOREIGN KEY (`t_course_teaching_class_id`) REFERENCES `t_course_teaching_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_course_teaching_class_forum_topic2_idx` FOREIGN KEY (`t_user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class_forum_topic_file 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class_forum_topic_file` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_forum_topic_id` char(32) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `filepath` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_teaching_class_forum_topic_file1_idx` (`t_course_teaching_class_forum_topic_id`),
  CONSTRAINT `fk_t_course_teaching_class_forum_topic_file1_idx` FOREIGN KEY (`t_course_teaching_class_forum_topic_id`) REFERENCES `t_course_teaching_class_forum_topic` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class_homework_baseinfo 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class_homework_baseinfo` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_id` char(32) NOT NULL,
  `t_teacher_id` char(32) NOT NULL,
  `t_course_teaching_class_homework_type_id` char(32) NOT NULL,
  `flag` char(32) NOT NULL COMMENT '作业标记，例如是否是小组作业等',
  `file_requirement` mediumtext NOT NULL COMMENT '作业要求',
  `title` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `pubdate` datetime NOT NULL,
  `enddate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_teaching_class_homework_baseinfo_1_idx` (`t_course_teaching_class_id`),
  KEY `fk_t_course_teaching_class_homework_baseinfo_2_idx` (`t_teacher_id`),
  KEY `fk_t_course_teaching_class_homework_baseinfo_3_idx` (`t_course_teaching_class_homework_type_id`),
  CONSTRAINT `fk_t_course_teaching_class_homework_baseinfo_1_idx` FOREIGN KEY (`t_course_teaching_class_id`) REFERENCES `t_course_teaching_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_course_teaching_class_homework_baseinfo_2_idx` FOREIGN KEY (`t_teacher_id`) REFERENCES `t_teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_course_teaching_class_homework_baseinfo_3_idx` FOREIGN KEY (`t_course_teaching_class_homework_type_id`) REFERENCES `t_course_teaching_class_homework_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class_homework_delayed 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class_homework_delayed` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_homework_baseinfo_id` char(32) NOT NULL,
  `t_teacher_id` char(32) NOT NULL,
  `pubdate` datetime NOT NULL,
  `enddate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_teaching_class_homework_delayed_1_idx` (`t_course_teaching_class_homework_baseinfo_id`),
  KEY `fk_t_course_teaching_class_homework_delayed_2_idx` (`t_teacher_id`),
  CONSTRAINT `fk_t_course_teaching_class_homework_delayed_1_idx` FOREIGN KEY (`t_course_teaching_class_homework_baseinfo_id`) REFERENCES `t_course_teaching_class_homework_baseinfo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_course_teaching_class_homework_delayed_2_idx` FOREIGN KEY (`t_teacher_id`) REFERENCES `t_teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class_homework_file 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class_homework_file` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_homework_baseinfo_id` char(32) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `filepath` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_teaching_class_homework_file_1_idx` (`t_course_teaching_class_homework_baseinfo_id`),
  CONSTRAINT `fk_t_course_teaching_class_homework_file_1_idx` FOREIGN KEY (`t_course_teaching_class_homework_baseinfo_id`) REFERENCES `t_course_teaching_class_homework_baseinfo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class_homework_reply 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class_homework_reply` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_homework_submit_baseinfo_id` char(32) NOT NULL,
  `t_teacher_id` char(32) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `submitdate` datetime NOT NULL,
  `modifieddate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_teaching_class_homework_reply_1_idx` (`t_course_teaching_class_homework_submit_baseinfo_id`),
  KEY `fk_t_course_teaching_class_homework_reply_2_idx` (`t_teacher_id`),
  CONSTRAINT `fk_t_course_teaching_class_homework_reply_1_idx` FOREIGN KEY (`t_course_teaching_class_homework_submit_baseinfo_id`) REFERENCES `t_course_teaching_class_homework_submit_baseinfo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_course_teaching_class_homework_reply_2_idx` FOREIGN KEY (`t_teacher_id`) REFERENCES `t_teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class_homework_reply_file 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class_homework_reply_file` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_homework_reply_id` char(32) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `filepath` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_teaching_class_homework_reply_file_1_idx` (`t_course_teaching_class_homework_reply_id`),
  CONSTRAINT `fk_t_course_teaching_class_homework_reply_file_1_idx` FOREIGN KEY (`t_course_teaching_class_homework_reply_id`) REFERENCES `t_course_teaching_class_homework_reply` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class_homework_score 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class_homework_score` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_homework_baseinfo_id` char(32) NOT NULL,
  `t_score_system_type_id` char(32) NOT NULL,
  `score` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_teaching_class_homework_score_1_idx` (`t_course_teaching_class_homework_baseinfo_id`),
  KEY `fk_t_course_teaching_class_homework_score_2_idx` (`t_score_system_type_id`),
  CONSTRAINT `fk_t_course_teaching_class_homework_score_1_idx` FOREIGN KEY (`t_course_teaching_class_homework_baseinfo_id`) REFERENCES `t_course_teaching_class_homework_baseinfo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_course_teaching_class_homework_score_2_idx` FOREIGN KEY (`t_score_system_type_id`) REFERENCES `t_score_system_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class_homework_submit_baseinfo 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class_homework_submit_baseinfo` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_homework_baseinfo_id` char(32) NOT NULL,
  `t_student_id` char(32) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `submitdate` datetime NOT NULL,
  `modifieddate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_teaching_class_homework_submit_baseinfo_1_idx` (`t_course_teaching_class_homework_baseinfo_id`),
  KEY `fk_t_course_teaching_class_homework_submit_baseinfo_2_idx` (`t_student_id`),
  CONSTRAINT `fk_t_course_teaching_class_homework_submit_baseinfo_1_idx` FOREIGN KEY (`t_course_teaching_class_homework_baseinfo_id`) REFERENCES `t_course_teaching_class_homework_baseinfo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_course_teaching_class_homework_submit_baseinfo_2_idx` FOREIGN KEY (`t_student_id`) REFERENCES `t_student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class_homework_submit_file 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class_homework_submit_file` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_homework_submit_baseinfo_id` char(32) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `filepath` varchar(255) NOT NULL,
  `file_node_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_teaching_class_homework_submit_file_1_idx` (`t_course_teaching_class_homework_submit_baseinfo_id`),
  CONSTRAINT `fk_t_course_teaching_class_homework_submit_file_1_idx` FOREIGN KEY (`t_course_teaching_class_homework_submit_baseinfo_id`) REFERENCES `t_course_teaching_class_homework_submit_baseinfo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class_homework_type 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class_homework_type` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_id` char(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_homework_type_1_idx` (`t_course_teaching_class_id`),
  CONSTRAINT `fk_t_homework_type_1_idx` FOREIGN KEY (`t_course_teaching_class_id`) REFERENCES `t_course_teaching_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class_reference 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class_reference` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_id` char(32) NOT NULL,
  `t_teacher_id` char(32) NOT NULL,
  `t_course_teaching_class_reference_type_id` char(32) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `pubdate` datetime NOT NULL,
  `modifieddate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_teaching_class_reference_1_idx` (`t_course_teaching_class_id`),
  KEY `fk_t_course_teaching_class_reference_2_idx` (`t_teacher_id`),
  KEY `fk_t_course_teaching_class_reference_3_idx` (`t_course_teaching_class_reference_type_id`),
  CONSTRAINT `fk_t_course_teaching_class_reference_1_idx` FOREIGN KEY (`t_course_teaching_class_id`) REFERENCES `t_course_teaching_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_course_teaching_class_reference_2_idx` FOREIGN KEY (`t_teacher_id`) REFERENCES `t_teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_course_teaching_class_reference_3_idx` FOREIGN KEY (`t_course_teaching_class_reference_type_id`) REFERENCES `t_course_teaching_class_reference_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class_reference_file 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class_reference_file` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_reference_id` char(32) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `filepath` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_teaching_class_reference_file_1_idx` (`t_course_teaching_class_reference_id`),
  CONSTRAINT `fk_t_course_teaching_class_reference_file_1_idx` FOREIGN KEY (`t_course_teaching_class_reference_id`) REFERENCES `t_course_teaching_class_reference` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class_reference_type 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class_reference_type` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_id` char(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_teaching_class_reference_type_1_idx` (`t_course_teaching_class_id`),
  CONSTRAINT `fk_t_course_teaching_class_reference_type_1_idx` FOREIGN KEY (`t_course_teaching_class_id`) REFERENCES `t_course_teaching_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class_student 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class_student` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_id` char(32) NOT NULL,
  `t_student_id` char(32) NOT NULL,
  `show_index` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`t_course_teaching_class_id`),
  KEY `fk_t_teaching_class_student1_idx` (`t_course_teaching_class_id`),
  KEY `fk_t_teaching_class_student1_2dx` (`t_student_id`),
  CONSTRAINT `fk_t_teaching_class_student1_2dx` FOREIGN KEY (`t_student_id`) REFERENCES `t_student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程-教学班级-学生';

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class_student_group 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class_student_group` (
  `id` char(32) NOT NULL,
  `t_group_id` char(32) NOT NULL,
  `t_course_teaching_class_id` char(32) NOT NULL,
  `show_index` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_course_teaching_class_student_group_t_group` (`t_group_id`),
  KEY `FK_t_course_teaching_class_student_group_student` (`t_course_teaching_class_id`),
  CONSTRAINT `FK_t_course_teaching_class_student_group_student` FOREIGN KEY (`t_course_teaching_class_id`) REFERENCES `t_course_teaching_class` (`id`),
  CONSTRAINT `FK_t_course_teaching_class_student_group_t_group` FOREIGN KEY (`t_group_id`) REFERENCES `t_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分组情况';

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_class_teacher 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_class_teacher` (
  `id` char(32) NOT NULL,
  `t_course_teaching_class_id` char(32) NOT NULL,
  `t_teacher_id` char(32) NOT NULL,
  `t_teaching_type_id` char(32) NOT NULL,
  PRIMARY KEY (`id`,`t_teacher_id`),
  KEY `fk_t_teaching_class_teacher1_idx` (`t_course_teaching_class_id`),
  KEY `fk_t_teaching_class_teacher2_idx` (`t_teaching_type_id`),
  KEY `fk_t_teaching_class_teacher3_idx` (`t_teacher_id`),
  CONSTRAINT `fk_t_teaching_class_teacher1_idx` FOREIGN KEY (`t_course_teaching_class_id`) REFERENCES `t_course_teaching_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_teaching_class_teacher2_idx` FOREIGN KEY (`t_teaching_type_id`) REFERENCES `t_teaching_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_teaching_class_teacher3_idx` FOREIGN KEY (`t_teacher_id`) REFERENCES `t_teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教学班级-教师';

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_teaching_term 结构
CREATE TABLE IF NOT EXISTS `t_course_teaching_term` (
  `id` char(32) NOT NULL,
  `teaching_year_begin` int(11) NOT NULL,
  `teaching_year_end` int(11) NOT NULL,
  `teaching_term` tinyint(4) NOT NULL,
  `weeks` tinyint(4) NOT NULL,
  `week_begin` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教学学期';

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_course_type 结构
CREATE TABLE IF NOT EXISTS `t_course_type` (
  `id` char(32) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_database 结构
CREATE TABLE IF NOT EXISTS `t_database` (
  `id` char(32) NOT NULL,
  `t_item_difficulty_id` char(32) NOT NULL,
  `t_item_type_id` char(32) NOT NULL,
  `t_item_kinds_id` char(32) NOT NULL,
  `content` longtext,
  `answer` longtext,
  `tips` mediumtext,
  `solutions` longtext,
  PRIMARY KEY (`id`),
  KEY `fk_t_database_t_item_difficulty1_idx` (`t_item_difficulty_id`),
  KEY `fk_t_database_t_item_type1_idx` (`t_item_type_id`),
  KEY `fk_t_database_t_item_kinds1_idx` (`t_item_kinds_id`),
  CONSTRAINT `fk_t_database_t_item_difficulty1` FOREIGN KEY (`t_item_difficulty_id`) REFERENCES `t_item_difficulty` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_database_t_item_kinds1` FOREIGN KEY (`t_item_kinds_id`) REFERENCES `t_item_kinds` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_database_t_item_type1` FOREIGN KEY (`t_item_type_id`) REFERENCES `t_item_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_database_knowledgepoint 结构
CREATE TABLE IF NOT EXISTS `t_database_knowledgepoint` (
  `id` char(32) NOT NULL,
  `t_knowledgepoint_id` char(32) NOT NULL,
  `t_database_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_database_knowledgepoint_t_knowledgepoint1_idx` (`t_knowledgepoint_id`),
  KEY `fk_t_database_knowledgepoint_t_database1_idx` (`t_database_id`),
  CONSTRAINT `fk_t_database_knowledgepoint_t_database1` FOREIGN KEY (`t_database_id`) REFERENCES `t_database` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_database_knowledgepoint_t_knowledgepoint1` FOREIGN KEY (`t_knowledgepoint_id`) REFERENCES `t_knowledgepoint` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_database_logging 结构
CREATE TABLE IF NOT EXISTS `t_database_logging` (
  `id` char(32) NOT NULL,
  `t_database_id` char(32) NOT NULL,
  `t_user_id` char(32) NOT NULL,
  `t_database_logging_type_id` char(32) NOT NULL,
  `logging_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_database_log_t_database1_idx` (`t_database_id`),
  KEY `fk_t_database_log_t_user1_idx` (`t_user_id`),
  KEY `fk_t_database_log_t_database_logging_type1_idx` (`t_database_logging_type_id`),
  CONSTRAINT `fk_t_database_log_t_database1` FOREIGN KEY (`t_database_id`) REFERENCES `t_database` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_database_log_t_database_logging_type1` FOREIGN KEY (`t_database_logging_type_id`) REFERENCES `t_database_logging_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_database_log_t_user1` FOREIGN KEY (`t_user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_database_logging_type 结构
CREATE TABLE IF NOT EXISTS `t_database_logging_type` (
  `id` char(32) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_department 结构
CREATE TABLE IF NOT EXISTS `t_department` (
  `id` char(32) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `note` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_department_teacher 结构
CREATE TABLE IF NOT EXISTS `t_department_teacher` (
  `id` char(32) NOT NULL,
  `t_department_id` char(32) NOT NULL,
  `t_teacher_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_department_teacher_t_department1_idx` (`t_department_id`),
  KEY `fk_t_department_teacher_t_teacher1_idx` (`t_teacher_id`),
  CONSTRAINT `fk_t_department_teacher_t_department1` FOREIGN KEY (`t_department_id`) REFERENCES `t_department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_department_teacher_t_teacher1` FOREIGN KEY (`t_teacher_id`) REFERENCES `t_teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_examination 结构
CREATE TABLE IF NOT EXISTS `t_examination` (
  `id` char(32) NOT NULL,
  `t_teacher_id` char(32) NOT NULL,
  `t_examination_type_id` char(32) NOT NULL,
  `total_score` varchar(45) DEFAULT NULL,
  `begin_datetime` datetime DEFAULT NULL,
  `end_datetime` datetime DEFAULT NULL,
  `t_course_id` char(32) NOT NULL,
  `t_examination_constitution_type_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_examination_t_teacher1_idx` (`t_teacher_id`),
  KEY `fk_t_examination_t_examination_type1_idx` (`t_examination_type_id`),
  KEY `fk_t_examination_t_course1_idx` (`t_course_id`),
  KEY `fk_t_examination_t_examination_constitution_type1_idx` (`t_examination_constitution_type_id`),
  CONSTRAINT `fk_t_examination_t_course1` FOREIGN KEY (`t_course_id`) REFERENCES `t_course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_examination_t_examination_constitution_type1` FOREIGN KEY (`t_examination_constitution_type_id`) REFERENCES `t_examination_constitution_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_examination_t_examination_type1` FOREIGN KEY (`t_examination_type_id`) REFERENCES `t_examination_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_examination_t_teacher1` FOREIGN KEY (`t_teacher_id`) REFERENCES `t_teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_examination_constitution 结构
CREATE TABLE IF NOT EXISTS `t_examination_constitution` (
  `id` char(32) NOT NULL,
  `t_examination_id` char(32) NOT NULL,
  `t_item_type_id` char(32) NOT NULL,
  `item_count` int(11) DEFAULT NULL,
  `percent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_examination_constitution_t_examination1_idx` (`t_examination_id`),
  KEY `fk_t_examination_constitution_t_item_type1_idx` (`t_item_type_id`),
  CONSTRAINT `fk_t_examination_constitution_t_examination1` FOREIGN KEY (`t_examination_id`) REFERENCES `t_examination` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_examination_constitution_t_item_type1` FOREIGN KEY (`t_item_type_id`) REFERENCES `t_item_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_examination_constitution_type 结构
CREATE TABLE IF NOT EXISTS `t_examination_constitution_type` (
  `id` char(32) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_examination_database 结构
CREATE TABLE IF NOT EXISTS `t_examination_database` (
  `id` char(32) NOT NULL,
  `t_examination_id` char(32) NOT NULL,
  `t_database_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_examination_database_t_examination1_idx` (`t_examination_id`),
  KEY `fk_t_examination_database_t_database1_idx` (`t_database_id`),
  CONSTRAINT `fk_t_examination_database_t_database1` FOREIGN KEY (`t_database_id`) REFERENCES `t_database` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_examination_database_t_examination1` FOREIGN KEY (`t_examination_id`) REFERENCES `t_examination` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_examination_type 结构
CREATE TABLE IF NOT EXISTS `t_examination_type` (
  `id` char(32) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_group 结构
CREATE TABLE IF NOT EXISTS `t_group` (
  `id` char(32) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_group_role 结构
CREATE TABLE IF NOT EXISTS `t_group_role` (
  `id` char(32) NOT NULL,
  `t_group_id` char(32) NOT NULL,
  `t_role_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_group_role_t_group1_idx` (`t_group_id`),
  KEY `fk_t_group_role_t_role1_idx` (`t_role_id`),
  CONSTRAINT `fk_t_group_role_t_group1` FOREIGN KEY (`t_group_id`) REFERENCES `t_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_group_role_t_role1` FOREIGN KEY (`t_role_id`) REFERENCES `t_role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_item_difficulty 结构
CREATE TABLE IF NOT EXISTS `t_item_difficulty` (
  `id` char(32) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_item_kinds 结构
CREATE TABLE IF NOT EXISTS `t_item_kinds` (
  `id` char(32) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_item_type 结构
CREATE TABLE IF NOT EXISTS `t_item_type` (
  `id` char(32) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_knowledgepoint 结构
CREATE TABLE IF NOT EXISTS `t_knowledgepoint` (
  `id` char(32) NOT NULL,
  `content` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `t_knowledgepoint_type_id` char(32) NOT NULL,
  `t_item_difficulty_id` char(32) NOT NULL,
  `father` varchar(32) DEFAULT NULL,
  `next` varchar(32) DEFAULT NULL,
  `t_knowledgepointcol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_knowledgepoint_t_knowledgepoint_type1_idx` (`t_knowledgepoint_type_id`),
  KEY `fk_t_knowledgepoint_t_item_difficulty1_idx` (`t_item_difficulty_id`),
  CONSTRAINT `fk_t_knowledgepoint_t_item_difficulty1` FOREIGN KEY (`t_item_difficulty_id`) REFERENCES `t_item_difficulty` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_knowledgepoint_t_knowledgepoint_type1` FOREIGN KEY (`t_knowledgepoint_type_id`) REFERENCES `t_knowledgepoint_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_knowledgepoint_logging 结构
CREATE TABLE IF NOT EXISTS `t_knowledgepoint_logging` (
  `id` char(32) NOT NULL,
  `t_database_knowledgepoint_id` char(32) NOT NULL,
  `t_user_id` char(32) NOT NULL,
  `t_logging_type_id` char(32) NOT NULL,
  `t_knowledgepoint_id` char(32) NOT NULL,
  `logging_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_knowledgepoint_logging_t_database_knowledgepoint1_idx` (`t_database_knowledgepoint_id`),
  KEY `fk_t_knowledgepoint_logging_t_user1_idx` (`t_user_id`),
  KEY `fk_t_knowledgepoint_logging_t_logging_type1_idx` (`t_logging_type_id`),
  KEY `fk_t_knowledgepoint_logging_t_knowledgepoint1_idx` (`t_knowledgepoint_id`),
  CONSTRAINT `fk_t_knowledgepoint_logging_t_database_knowledgepoint1` FOREIGN KEY (`t_database_knowledgepoint_id`) REFERENCES `t_database_knowledgepoint` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_knowledgepoint_logging_t_knowledgepoint1` FOREIGN KEY (`t_knowledgepoint_id`) REFERENCES `t_knowledgepoint` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_knowledgepoint_logging_t_logging_type1` FOREIGN KEY (`t_logging_type_id`) REFERENCES `t_logging_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_knowledgepoint_logging_t_user1` FOREIGN KEY (`t_user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_knowledgepoint_type 结构
CREATE TABLE IF NOT EXISTS `t_knowledgepoint_type` (
  `id` char(32) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_login 结构
CREATE TABLE IF NOT EXISTS `t_login` (
  `id` char(32) NOT NULL,
  `t_user_id` char(32) NOT NULL,
  `login_datetime` datetime NOT NULL,
  `login_ip` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_login_t_user_idx` (`t_user_id`),
  CONSTRAINT `fk_t_login_t_user` FOREIGN KEY (`t_user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_mail_box_received 结构
CREATE TABLE IF NOT EXISTS `t_mail_box_received` (
  `id` char(32) NOT NULL,
  `t_user_id_from` varchar(32) NOT NULL,
  `t_user_id_to` varchar(32) NOT NULL,
  `state` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `senddate` datetime NOT NULL,
  `readdate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_mail_received_box_1_idx` (`t_user_id_from`),
  KEY `fk_t_mail_received_box_2_idx` (`t_user_id_to`),
  CONSTRAINT `fk_t_mail_received_box_1_idx` FOREIGN KEY (`t_user_id_from`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_mail_received_box_2_idx` FOREIGN KEY (`t_user_id_to`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_mail_box_received_file 结构
CREATE TABLE IF NOT EXISTS `t_mail_box_received_file` (
  `id` char(32) NOT NULL,
  `t_mail_box_received_id` char(32) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `filepath` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_mail_box_received_file_1_idx` (`t_mail_box_received_id`),
  CONSTRAINT `fk_t_mail_box_received_file_1_idx` FOREIGN KEY (`t_mail_box_received_id`) REFERENCES `t_mail_box_received` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_mail_box_send 结构
CREATE TABLE IF NOT EXISTS `t_mail_box_send` (
  `id` char(32) NOT NULL,
  `t_user_id_from` varchar(32) NOT NULL,
  `t_user_id_to` varchar(32) NOT NULL,
  `state` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `senddate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_mail_box_send_1_idx` (`t_user_id_from`),
  KEY `fk_t_mail_box_send_2_idx` (`t_user_id_to`),
  CONSTRAINT `fk_t_mail_box_send_1_idx` FOREIGN KEY (`t_user_id_from`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_mail_box_send_2_idx` FOREIGN KEY (`t_user_id_to`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_mail_box_send_file 结构
CREATE TABLE IF NOT EXISTS `t_mail_box_send_file` (
  `id` char(32) NOT NULL,
  `t_mail_box_send_id` char(32) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `filepath` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_mail_box_send_file_1_idx` (`t_mail_box_send_id`),
  CONSTRAINT `fk_t_mail_box_send_file_1_idx` FOREIGN KEY (`t_mail_box_send_id`) REFERENCES `t_mail_box_send` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_mail_reset 结构
CREATE TABLE IF NOT EXISTS `t_mail_reset` (
  `id` char(32) NOT NULL,
  `t_user_id` char(32) NOT NULL,
  `mail_server` varchar(256) NOT NULL,
  `mail_user` varchar(256) NOT NULL,
  `mail_password` varchar(256) NOT NULL,
  `mail_config_valid_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_mail_reset_t_user` (`t_user_id`),
  CONSTRAINT `FK_t_mail_reset_t_user` FOREIGN KEY (`t_user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='重置密码';

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_natural_class 结构
CREATE TABLE IF NOT EXISTS `t_natural_class` (
  `id` char(32) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `note` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_natural_class_department 结构
CREATE TABLE IF NOT EXISTS `t_natural_class_department` (
  `id` char(32) NOT NULL,
  `t_department_id` char(32) NOT NULL,
  `t_natural_class_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_natural_class_school_t_department1_idx` (`t_department_id`),
  KEY `fk_t_natural_class_school_t_natural_class1_idx` (`t_natural_class_id`),
  CONSTRAINT `fk_t_natural_class_school_t_department1` FOREIGN KEY (`t_department_id`) REFERENCES `t_department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_natural_class_school_t_natural_class1` FOREIGN KEY (`t_natural_class_id`) REFERENCES `t_natural_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_natural_class_student 结构
CREATE TABLE IF NOT EXISTS `t_natural_class_student` (
  `id` char(32) NOT NULL,
  `t_natural_class_id` char(32) NOT NULL,
  `t_student_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_natural_class_student_t_t_natural_class1_idx` (`t_natural_class_id`),
  KEY `fk_t_natural_class_student_t_t_student1_idx` (`t_student_id`),
  CONSTRAINT `fk_t_natural_class_student_t_natural_class1` FOREIGN KEY (`t_natural_class_id`) REFERENCES `t_natural_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_natural_class_student_t_t_student1` FOREIGN KEY (`t_student_id`) REFERENCES `t_student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_permission 结构
CREATE TABLE IF NOT EXISTS `t_permission` (
  `id` char(32) NOT NULL,
  `t_permission_operator_id` char(32) NOT NULL,
  `t_resource_id` char(32) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_permission_t_permission_operator1_idx` (`t_permission_operator_id`),
  KEY `fk_t_permission_t_resource1_idx` (`t_resource_id`),
  CONSTRAINT `fk_t_permission_t_permission_operator1` FOREIGN KEY (`t_permission_operator_id`) REFERENCES `t_permission_operator` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_permission_t_resource1` FOREIGN KEY (`t_resource_id`) REFERENCES `t_resource` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_permission_operator 结构
CREATE TABLE IF NOT EXISTS `t_permission_operator` (
  `id` char(32) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_precourse 结构
CREATE TABLE IF NOT EXISTS `t_precourse` (
  `id` char(32) NOT NULL,
  `t_course_id_this` varchar(32) NOT NULL,
  `t_course_id_pre` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_course_id_t_course1_idx` (`t_course_id_this`),
  KEY `fk_t_course_id_t_course2_idx` (`t_course_id_pre`),
  CONSTRAINT `fk_t_precourse_t_course1` FOREIGN KEY (`t_course_id_this`) REFERENCES `t_course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_precourse_t_course2` FOREIGN KEY (`t_course_id_pre`) REFERENCES `t_course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_resource 结构
CREATE TABLE IF NOT EXISTS `t_resource` (
  `id` char(32) NOT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `uri_element` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_role 结构
CREATE TABLE IF NOT EXISTS `t_role` (
  `id` char(32) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_role_permission 结构
CREATE TABLE IF NOT EXISTS `t_role_permission` (
  `id` char(32) NOT NULL,
  `t_role_id` char(32) NOT NULL,
  `t_permission_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_role_permission_t_role1_idx` (`t_role_id`),
  KEY `fk_t_role_permission_t_permission1_idx` (`t_permission_id`),
  CONSTRAINT `fk_t_role_permission_t_permission1` FOREIGN KEY (`t_permission_id`) REFERENCES `t_permission` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_role_permission_t_role1` FOREIGN KEY (`t_role_id`) REFERENCES `t_role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_school 结构
CREATE TABLE IF NOT EXISTS `t_school` (
  `id` char(32) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `note` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_school_department 结构
CREATE TABLE IF NOT EXISTS `t_school_department` (
  `id` char(32) NOT NULL,
  `t_school_id` char(32) NOT NULL,
  `t_department_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_school_department_t_school1_idx` (`t_school_id`),
  KEY `fk_t_school_department_t_department1_idx` (`t_department_id`),
  CONSTRAINT `fk_t_school_department_t_department1` FOREIGN KEY (`t_department_id`) REFERENCES `t_department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_school_department_t_school1` FOREIGN KEY (`t_school_id`) REFERENCES `t_school` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_score 结构
CREATE TABLE IF NOT EXISTS `t_score` (
  `id` char(32) NOT NULL,
  `t_student_id` char(32) NOT NULL,
  `t_course_id` char(32) NOT NULL,
  `t_examination_id` char(32) NOT NULL,
  `score` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_score_t_student1_idx` (`t_student_id`),
  KEY `fk_t_score_t_course1_idx` (`t_course_id`),
  KEY `fk_t_score_t_examination1_idx` (`t_examination_id`),
  CONSTRAINT `fk_t_score_t_course1` FOREIGN KEY (`t_course_id`) REFERENCES `t_course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_score_t_examination1` FOREIGN KEY (`t_examination_id`) REFERENCES `t_examination` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_score_t_student1` FOREIGN KEY (`t_student_id`) REFERENCES `t_student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_score_constitution 结构
CREATE TABLE IF NOT EXISTS `t_score_constitution` (
  `id` char(32) NOT NULL,
  `t_score_id` char(32) NOT NULL,
  `percent` int(11) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_score_constitution_t_score1_idx` (`t_score_id`),
  CONSTRAINT `fk_t_score_constitution_t_score1` FOREIGN KEY (`t_score_id`) REFERENCES `t_score` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_score_system_type 结构
CREATE TABLE IF NOT EXISTS `t_score_system_type` (
  `id` char(32) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_student 结构
CREATE TABLE IF NOT EXISTS `t_student` (
  `id` char(32) NOT NULL,
  `t_user_id` char(32) NOT NULL,
  `student_num` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_teacher 结构
CREATE TABLE IF NOT EXISTS `t_teacher` (
  `id` char(32) NOT NULL,
  `t_user_id` char(32) NOT NULL,
  `teacher_num` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_teacher_t_user1_idx` (`t_user_id`),
  CONSTRAINT `fk_t_teacher_t_user1` FOREIGN KEY (`t_user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_teaching_type 结构
CREATE TABLE IF NOT EXISTS `t_teaching_type` (
  `id` char(32) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` char(32) NOT NULL,
  `user_name` varchar(128) NOT NULL,
  `user_password` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_user_basic_info 结构
CREATE TABLE IF NOT EXISTS `t_user_basic_info` (
  `id` char(50) NOT NULL,
  `t_user_id` char(32) NOT NULL,
  `user_contact_info_name` varchar(32) DEFAULT NULL,
  `user_contact_info_birthday` datetime DEFAULT NULL,
  `user_contact_info_sex` int(11) DEFAULT NULL,
  `user_contact_info_address` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_user_basic_info_t_user1_idx` (`t_user_id`),
  CONSTRAINT `fk_t_user_basic_info_t_user1` FOREIGN KEY (`t_user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_user_config 结构
CREATE TABLE IF NOT EXISTS `t_user_config` (
  `id` char(32) NOT NULL,
  `t_user_id` char(32) NOT NULL,
  `page_size` smallint(6) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_user_config_t_user` (`t_user_id`),
  CONSTRAINT `FK_t_user_config_t_user` FOREIGN KEY (`t_user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_user_contact_info 结构
CREATE TABLE IF NOT EXISTS `t_user_contact_info` (
  `id` char(32) NOT NULL,
  `t_user_id` char(32) NOT NULL,
  `t_user_contact_type_id` char(32) NOT NULL,
  `user_contact_value` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_user_contact_info_t_user1_idx` (`t_user_id`),
  KEY `fk_t_user_contact_info_t_user_contact_type1_idx` (`t_user_contact_type_id`),
  CONSTRAINT `FK_t_user_contact_info_t_user_contact_type` FOREIGN KEY (`t_user_contact_type_id`) REFERENCES `t_user_contact_type` (`id`),
  CONSTRAINT `fk_t_user_contact_info_t_user1` FOREIGN KEY (`t_user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_user_contact_type 结构
CREATE TABLE IF NOT EXISTS `t_user_contact_type` (
  `id` char(32) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `note` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 coursedb.t_user_group 结构
CREATE TABLE IF NOT EXISTS `t_user_group` (
  `id` char(32) NOT NULL,
  `t_group_id` char(32) NOT NULL,
  `t_user_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_user_group_t_group1_idx` (`t_group_id`),
  KEY `fk_t_user_group_t_user1_idx` (`t_user_id`),
  CONSTRAINT `fk_t_user_group_t_group1` FOREIGN KEY (`t_group_id`) REFERENCES `t_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_user_group_t_user1` FOREIGN KEY (`t_user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
