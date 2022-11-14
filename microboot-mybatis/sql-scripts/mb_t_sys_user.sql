/*
 Navicat Premium Data Transfer

 Source Server         : postgre
 Source Server Type    : PostgreSQL
 Source Server Version : 140005
 Source Host           : localhost:5432
 Source Catalog        : awesomejava
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 140005
 File Encoding         : 65001

 Date: 30/09/2022 15:38:54
*/


-- ----------------------------
-- Table structure for mb_t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."mb_t_sys_user";
CREATE TABLE "public"."mb_t_sys_user" (
  "user_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "user_name" varchar(50) COLLATE "pg_catalog"."default",
  "password" varchar(128) COLLATE "pg_catalog"."default",
  "id" int4 NOT NULL DEFAULT nextval('upms_log_id_seq'::regclass)
)
;

-- ----------------------------
-- Primary Key structure for table mb_t_sys_user
-- ----------------------------
ALTER TABLE "public"."mb_t_sys_user" ADD CONSTRAINT "mb_t_sys_user_pkey" PRIMARY KEY ("id");


-- ----------------------------
-- Table structure for mb_t_sys_employee
-- ----------------------------
DROP TABLE IF EXISTS "public"."mb_t_sys_employee";
CREATE TABLE "public"."mb_t_sys_employee" (
                                              "eid" int8 NOT NULL,
                                              "employee_name" varchar(255) COLLATE "pg_catalog"."default",
                                              "age" int4,
                                              "sex" bool,
                                              "email" varchar(255) COLLATE "pg_catalog"."default",
                                              "did" int4
)
;

-- ----------------------------
-- Primary Key structure for table mb_t_sys_employee
-- ----------------------------
ALTER TABLE "public"."mb_t_sys_employee" ADD CONSTRAINT "mb_t_sys_employee_pkey" PRIMARY KEY ("eid");


-- ----------------------------
-- Table structure for mb_t_sys_department
-- ----------------------------
DROP TABLE IF EXISTS "public"."mb_t_sys_department";
CREATE TABLE "public"."mb_t_sys_department" (
                                                "did" int4 NOT NULL,
                                                "department_name" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Primary Key structure for table mb_t_sys_department
-- ----------------------------
ALTER TABLE "public"."mb_t_sys_department" ADD CONSTRAINT "mb_t_sys_department_pkey" PRIMARY KEY ("did");

