-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.23 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 xiaowa 的数据库结构
CREATE DATABASE IF NOT EXISTS `xiaowa` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `xiaowa`;

-- 导出  表 xiaowa.usertable 结构
CREATE TABLE IF NOT EXISTS `usertable` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `real_name` varchar(50) DEFAULT NULL COMMENT '中文名',
  `path` varchar(255) DEFAULT NULL COMMENT '跳转路径',
  `level` varchar(50) DEFAULT NULL COMMENT '等级',
  `status` varchar(50) DEFAULT '正常' COMMENT '默认正常',
  `disable` int NOT NULL DEFAULT '0' COMMENT '删除标记 0为正常 1为删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  xiaowa.usertable 的数据：~4 rows (大约)
DELETE FROM `usertable`;
/*!40000 ALTER TABLE `usertable` DISABLE KEYS */;
INSERT INTO `usertable` (`id`, `user_name`, `real_name`, `path`, `level`, `status`, `disable`, `create_time`) VALUES
	(1, 'zhangsan', '张三', '123456', '系统管理员', '正常', 0, '2021-02-26 18:47:45'),
	(2, 'lisi', '李四', '223456', NULL, '正常', 0, '2021-02-26 20:40:50'),
	(3, 'wangwu', '王五', '323456', NULL, '正常', 0, '2021-02-26 20:41:15'),
	(4, 'zhaoliu', '赵六', '423456', '系统管理员', '正常', 0, '2021-02-26 22:47:09');
/*!40000 ALTER TABLE `usertable` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
