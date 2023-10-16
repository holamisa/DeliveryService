CREATE DATABASE IF NOT EXISTS `delivery`;

CREATE TABLE IF NOT EXISTS `delivery`.`user` (
`id` BIGINT(32) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
`email` VARCHAR(100) NOT NULL,
`password` VARCHAR(100) NOT NULL,
`status` VARCHAR(45) NOT NULL,
`address` varchar(150) NOT NULL,
`registered_at` DATETIME NULL,
`unregistered_at` DATETIME NULL,
`last_login_at` DATETIME NULL,
PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '사용자';