CREATE DATABASE IF NOT EXISTS `delivery`;

CREATE TABLE IF NOT EXISTS `delivery`.`user` (
`id` BIGINT(32) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL COMMENT '사용자 이름',
`email` VARCHAR(100) NOT NULL COMMENT '사용자 이메일',
`password` VARCHAR(100) NOT NULL COMMENT '비밀번호',
`status` VARCHAR(50) NOT NULL COMMENT '상태',
`address` varchar(150) NOT NULL COMMENT '집 주소',
`registered_at` DATETIME NULL COMMENT '등록 일자',
`unregistered_at` DATETIME NULL COMMENT '탈퇴 일자',
`last_login_at` DATETIME NULL COMMENT '마지막 로그인 시간',
PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '사용자';


CREATE TABLE IF NOT EXISTS `delivery`.`store` (
`id` BIGINT(32) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(100) NOT NULL COMMENT '가게 이름',
`address` VARCHAR(150) NOT NULL COMMENT '가게 주소',
`status` VARCHAR(50) NOT NULL COMMENT '상태',
`category` VARCHAR(50) NOT NULL COMMENT '가게 분류',
`star` DOUBLE NULL DEFAULT 0 COMMENT '별점',
`thumbnail_url` VARCHAR(200) NOT NULL COMMENT '사진 이미지',
`minimum_amount` DECIMAL(11,4) NOT NULL COMMENT '최소 주문 금액',
`minimum_delivery_amount` DECIMAL(11,4) NOT NULL COMMENT '최소 배달 금액',
`phone_number` VARCHAR(20) NULL COMMENT '가게 번호',
`registered_at` DATETIME NULL COMMENT '등록 일자',
`unregistered_at` DATETIME NULL COMMENT '탈퇴 일자',
PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '가맹점';