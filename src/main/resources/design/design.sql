/*微信公众号信息*/
drop table if exists ACCOUNT;
CREATE TABLE `ACCOUNT` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,  /*id*/
  `openid` varchar(32)  NOT NULL unique,   /*微信唯一码-----用户的标识，对当前微信公众号唯一*/
  `nickname` varchar(32)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL, /*昵称*/
  `portrait` varchar(300) DEFAULT NULL,   /*头像url*/
  `sex` varchar(4) DEFAULT 0, /*性别*/
  `city` varchar(20) DEFAULT NULL, /*城市*/
  `province` varchar(20) DEFAULT NULL, /*省份*/
  `country` varchar(20) DEFAULT NULL,  /*国家*/
  `create_time` datetime DEFAULT NULL,   /*创建时间*/
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci;

/*爬取id记录表*/
drop table if exists RECORD;
CREATE TABLE `RECORD` (
  `id` varchar(100) NOT NULL,  /*id*/
  `type` varchar(2) NOT NULL, /*类型  01公开招标  02询价公告 */
  `create_time` datetime DEFAULT NULL   /*创建时间*/
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci;