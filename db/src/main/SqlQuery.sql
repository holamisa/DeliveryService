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


CREATE TABLE IF NOT EXISTS `delivery`.`store_menu` (
 `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
 `store_id` BIGINT(32) NOT NULL COMMENT '가게 ID',
 `name` VARCHAR(100) NOT NULL COMMENT '메뉴 이름',
 `amount` DECIMAL(11,4) NOT NULL COMMENT '메뉴 가격',
 `status` VARCHAR(50) NOT NULL COMMENT '메뉴 상태',
 `thumbnail_url` VARCHAR(200) NOT NULL COMMENT '사진 이미지',
 `star` DOUBLE NULL DEFAULT 0 COMMENT '좋아요 카운트',
 `sequence` INT NULL DEFAULT 0 COMMENT '정렬 순서',
 `registered_at` DATETIME NULL COMMENT '등록 일자',
 `unregistered_at` DATETIME NULL COMMENT '삭제 일자',
 PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '가맹점 메뉴';
ALTER TABLE `delivery`.`store_menu`
    ADD INDEX `idx_store_id` (`store_id` ASC) VISIBLE;
;


CREATE TABLE IF NOT EXISTS `delivery`.`user_order` (
 `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
 `user_id` BIGINT(32) NOT NULL COMMENT '사용자 ID',
 `store_id` BIGINT(32) NOT NULL COMMENT '가멩점 ID',
 `status` VARCHAR(50) NOT NULL COMMENT '주문 상태',
 `amount` DECIMAL(11,4) NOT NULL COMMENT '주문 가격',
 `ordered_at` DATETIME NULL COMMENT '주문 일자',
 `accepted_at` DATETIME NULL COMMENT '가맹점 주문 확인 일자',
 `cooking_started_at` DATETIME NULL COMMENT '조리 시작 일자',
 `delivery_started_at` DATETIME NULL COMMENT '배달 시작 일자',
 `received_at` DATETIME NULL COMMENT '배달 완료 일자',
 PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '사용자 주문 내역';
ALTER TABLE `delivery`.`user_order`
ADD INDEX `idx_user_id` (`user_id` ASC) VISIBLE;
;


CREATE TABLE IF NOT EXISTS `delivery`.`user_order_menu` (
`id` BIGINT(32) NOT NULL AUTO_INCREMENT,
`user_order_id` BIGINT(32) NOT NULL COMMENT '주문내역 ID',
`store_menu_id` BIGINT(32) NOT NULL COMMENT '가맹점 메뉴 ID',
`status` VARCHAR(50) NOT NULL COMMENT '상태',
PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '주문내역 메뉴';
ALTER TABLE `delivery`.`user_order_menu`
ADD INDEX `idx_user_order_id` (`user_order_id` ASC) VISIBLE,
ADD INDEX `idx_store_menu_id` (`store_menu_id` ASC) VISIBLE;
;