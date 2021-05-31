CREATE TABLE NFSYS_ATTACHMENT
(
  ID           NUMBER                           NOT NULL,
  FILE_NAME    VARCHAR2(200 BYTE),
  FILE_PATH    VARCHAR2(500 BYTE),
  THUMB_PATH    VARCHAR2(500 BYTE),
  FILE_SUFFIX  VARCHAR2(50 BYTE),
  FILE_SIZE    NUMBER                           NOT NULL,
  CREATE_ID    NUMBER                           DEFAULT - 1                   NOT NULL,
  CREATE_DATE  DATE                             DEFAULT sysdate               NOT NULL
)
#split#
COMMENT ON TABLE NFSYS_ATTACHMENT IS '附件'
#split#
COMMENT ON COLUMN NFSYS_ATTACHMENT.ID IS '主键'
#split#
COMMENT ON COLUMN NFSYS_ATTACHMENT.FILE_NAME IS '文件名称'
#split#
COMMENT ON COLUMN NFSYS_ATTACHMENT.FILE_PATH IS '文件路径'
#split#
COMMENT ON COLUMN NFSYS_ATTACHMENT.THUMB_PATH IS '缩略图路径，只针对图片类型'
#split#
COMMENT ON COLUMN NFSYS_ATTACHMENT.FILE_SUFFIX IS '文件后缀名'
#split#
COMMENT ON COLUMN NFSYS_ATTACHMENT.FILE_SIZE IS '文件大小'
#split#
COMMENT ON COLUMN NFSYS_ATTACHMENT.CREATE_ID IS '创建用户ID'
#split#
COMMENT ON COLUMN NFSYS_ATTACHMENT.CREATE_DATE IS '创建时间'
#split#
alter table NFSYS_ATTACHMENT add constraint NFSYS_ATTACHMENT_PK primary key(id)
#split#
Create Sequence NFSYS_ATTACHMENT_S Start With 10001
#split#
CREATE TABLE NFSYS_ATTACHMENT_HOST
(
  ID           NUMBER                           NOT NULL,
  ORG_ID       NUMBER                           DEFAULT 0                     NOT NULL,
  HOST_TYPE    NUMBER                           NOT NULL,
  HOST_ID      NUMBER                           NOT NULL,
  ATTACH_ID    NUMBER                           NOT NULL,
  CREATE_ID    NUMBER                           DEFAULT - 1                   NOT NULL,
  CREATE_DATE  DATE                             DEFAULT sysdate               NOT NULL
)
#split#
COMMENT ON TABLE NFSYS_ATTACHMENT_HOST IS '附件宿主'
#split#
COMMENT ON COLUMN NFSYS_ATTACHMENT_HOST.ID IS '主键'
#split#
COMMENT ON COLUMN NFSYS_ATTACHMENT_HOST.ORG_ID IS '组织ID'
#split#
COMMENT ON COLUMN NFSYS_ATTACHMENT_HOST.HOST_TYPE IS '宿主类型  在 Core/Models/Enums 中定义'
#split#
COMMENT ON COLUMN NFSYS_ATTACHMENT_HOST.HOST_ID IS '宿主单据id'
#split#
COMMENT ON COLUMN NFSYS_ATTACHMENT_HOST.ATTACH_ID IS '附件id'
#split#
COMMENT ON COLUMN NFSYS_ATTACHMENT_HOST.CREATE_ID IS '创建用户ID'
#split#
COMMENT ON COLUMN NFSYS_ATTACHMENT_HOST.CREATE_DATE IS '创建时间'
#split#
alter table NFSYS_ATTACHMENT_HOST add constraint NFSYS_ATTACHMENT_HOST_PK primary key(id)
#split#
Create Sequence NFSYS_ATTACHMENT_HOST_S Start With 10001
#split#
CREATE TABLE nfreg_doctor (
id NUMBER NOT NULL,
org_id NUMBER NOT NULL,
doctor_code VARCHAR2(255) NOT NULL,
doctor_name VARCHAR2(255) NOT NULL,
title VARCHAR2(4000) NULL,
description VARCHAR2(4000) NULL,
good_at VARCHAR2(4000) NULL,
create_date DATE DEFAULT sysdate NOT NULL,
PRIMARY KEY (id)
)
#split#
COMMENT ON TABLE nfreg_doctor IS '挂号医生'
#split#
COMMENT ON COLUMN nfreg_doctor.id IS '主键'
#split#
COMMENT ON COLUMN nfreg_doctor.org_id IS '机构'
#split#
COMMENT ON COLUMN nfreg_doctor.doctor_code IS '挂号医生编码'
#split#
COMMENT ON COLUMN nfreg_doctor.doctor_name IS '挂号医生名称'
#split#
COMMENT ON COLUMN nfreg_doctor.title IS '医生职称'
#split#
COMMENT ON COLUMN nfreg_doctor.description IS '简介'
#split#
COMMENT ON COLUMN nfreg_doctor.good_at IS '特长'
#split#
Create Sequence nfreg_doctor_S Start With 10001
#split#
CREATE TABLE nfreg_dept (
id NUMBER NOT NULL,
org_id NUMBER NOT NULL,
dept_code VARCHAR2(255) NOT NULL,
dept_name VARCHAR2(255) NOT NULL,
dept_type VARCHAR2(100) NULL,
category_code VARCHAR2(100) NULL,
seq NUMBER DEFAULT 0 NOT NULL,
create_date DATE DEFAULT sysdate NOT NULL,
PRIMARY KEY (id)
)
#split#
COMMENT ON TABLE nfreg_dept IS '挂号科室'
#split#
COMMENT ON COLUMN nfreg_dept.org_id IS '机构'
#split#
COMMENT ON COLUMN nfreg_dept.dept_code IS '挂号科室编号'
#split#
COMMENT ON COLUMN nfreg_dept.dept_name IS '挂号科室名称'
#split#
COMMENT ON COLUMN nfreg_dept.dept_type IS '挂号科室类型 1普通 2急诊 3专家 4专科'
#split#
COMMENT ON COLUMN nfreg_dept.category_code IS '分组id'
#split#
Create Sequence nfreg_dept_S Start With 10001
#split#
CREATE TABLE nfreg_schedule (
id NUMBER NOT NULL,
org_id NUMBER NOT NULL,
dept_code VARCHAR2(100) NOT NULL,
doctor_code VARCHAR2(100) NOT NULL,
schedule_date DATE NOT NULL,
schedule_time VARCHAR2(100) NOT NULL,
reg_fee NUMBER NOT NULL,
clinic_fee NUMBER NOT NULL,
begin_time DATE NOT NULL,
end_time DATE NOT NULL,
rated_num NUMBER NOT NULL,
last_num NUMBER NOT NULL,
stop_flag VARCHAR2(10) DEFAULT 'N' NOT NULL,
reg_flag VARCHAR2(10) DEFAULT '1' NOT NULL,
create_date DATE DEFAULT sysdate NOT NULL,
last_update_date DATE,
PRIMARY KEY (id)
)
#split#
COMMENT ON TABLE nfreg_schedule IS '排版信息'
#split#
COMMENT ON COLUMN nfreg_schedule.org_id IS '机构'
#split#
COMMENT ON COLUMN nfreg_schedule.dept_code IS '挂号科室编码'
#split#
COMMENT ON COLUMN nfreg_schedule.doctor_code IS '挂号医生编码'
#split#
COMMENT ON COLUMN nfreg_schedule.schedule_date IS '排版日期'
#split#
COMMENT ON COLUMN nfreg_schedule.schedule_time IS '班次 A上午、P下午、F全天、N夜间'
#split#
COMMENT ON COLUMN nfreg_schedule.reg_fee IS '挂号费'
#split#
COMMENT ON COLUMN nfreg_schedule.clinic_fee IS '诊疗费'
#split#
COMMENT ON COLUMN nfreg_schedule.begin_time IS '排班开始日期'
#split#
COMMENT ON COLUMN nfreg_schedule.end_time IS '排班结束日期'
#split#
COMMENT ON COLUMN nfreg_schedule.rated_num IS '核定号源数量'
#split#
COMMENT ON COLUMN nfreg_schedule.last_num IS '号源剩余数量'
#split#
COMMENT ON COLUMN nfreg_schedule.stop_flag IS '停诊标识 Y=不可挂号/N=可挂号'
#split#
COMMENT ON COLUMN nfreg_schedule.reg_flag IS '挂号方式 1顺序2分时段 '
#split#
Create Sequence nfreg_schedule_S Start With 10001
#split#
CREATE TABLE nfreg_schedule_no (
id NUMBER NOT NULL,
org_id NUMBER NOT NULL,
no NUMBER NOT NULL,
clinc_start_time DATE,
clinc_end_time DATE,
status NUMBER DEFAULT 2 NOT NULL,
schedule_id NUMBER NOT NULL,
create_date DATE DEFAULT sysdate NULL,
last_update_date DATE,
PRIMARY KEY (id)
)
#split#
COMMENT ON TABLE nfreg_schedule_no IS '排版号源'
#split#
COMMENT ON COLUMN nfreg_schedule_no.org_id IS '机构id'
#split#
COMMENT ON COLUMN nfreg_schedule_no.no IS '序号'
#split#
COMMENT ON COLUMN nfreg_schedule_no.clinc_start_time IS '就诊开始时间'
#split#
COMMENT ON COLUMN nfreg_schedule_no.clinc_end_time IS '就诊结束时间 可为空'
#split#
COMMENT ON COLUMN nfreg_schedule_no.status IS '1：已预约  2：未预约'
#split#
COMMENT ON COLUMN nfreg_schedule_no.schedule_id IS '排版id'
#split#
Create Sequence nfreg_schedule_no_S Start With 10001
#split#
CREATE TABLE nfreg_appointment (
id NUMBER NOT NULL,
org_id NUMBER NOT NULL,
dept_code VARCHAR2(100) NOT NULL,
doctor_code VARCHAR2(100) NOT NULL,
schedule_date DATE NOT NULL,
schedule_time VARCHAR2(100) NOT NULL,
schedule_type VARCHAR2(100),
name VARCHAR2(100) NOT NULL,
idcard VARCHAR2(100) NOT NULL,
phone VARCHAR2(100) NOT NULL,
status NUMBER DEFAULT 1 NOT NULL,
sex VARCHAR2(10) NOT NULL,
birthday DATE NOT NULL,
schedule_vercode VARCHAR2(255) NOT NULL,
schedule_num NUMBER NOT NULL,
sug_visit_time DATE NULL,
get_time DATE NULL,
expect_fee NUMBER NULL,
res_num VARCHAR2(255) NOT NULL,
res_source VARCHAR2(255),
res_type VARCHAR2(255) NOT NULL,
reg_fee NUMBER DEFAULT 0 NOT NULL,
app_key VARCHAR2(255),
cancel_type NUMBER,
cancel_reason VARCHAR2(255),
create_date DATE DEFAULT sysdate NOT NULL,
last_update_date DATE,
PRIMARY KEY (id)
)
#split#
COMMENT ON TABLE nfreg_appointment IS '排版号源'
#split#
COMMENT ON COLUMN nfreg_appointment.org_id IS '机构id'
#split#
COMMENT ON COLUMN nfreg_appointment.dept_code IS '挂号科室code'
#split#
COMMENT ON COLUMN nfreg_appointment.doctor_code IS '医生code'
#split#
COMMENT ON COLUMN nfreg_appointment.schedule_date IS '挂号日期'
#split#
COMMENT ON COLUMN nfreg_appointment.schedule_time IS '门诊时间 A上午、P下午、F全天、N夜间'
#split#
COMMENT ON COLUMN nfreg_appointment.schedule_type IS '门诊类型 专家 转科'
#split#
COMMENT ON COLUMN nfreg_appointment.name IS '病人名称'
#split#
COMMENT ON COLUMN nfreg_appointment.idcard IS '身份证'
#split#
COMMENT ON COLUMN nfreg_appointment.phone IS '手机号码'
#split#
COMMENT ON COLUMN nfreg_appointment.status IS '1 已挂号，2 预约取消，3预约过期'
#split#
COMMENT ON COLUMN nfreg_appointment.sex IS '性别 M=男，F=女'
#split#
COMMENT ON COLUMN nfreg_appointment.birthday IS '出生日期 YYYY-MM-DD'
#split#
COMMENT ON COLUMN nfreg_appointment.schedule_vercode IS '挂号验证码'
#split#
COMMENT ON COLUMN nfreg_appointment.schedule_num IS '预约号子'
#split#
COMMENT ON COLUMN nfreg_appointment.sug_visit_time IS '建议就诊时间'
#split#
COMMENT ON COLUMN nfreg_appointment.get_time IS '建议取号时间'
#split#
COMMENT ON COLUMN nfreg_appointment.expect_fee IS '预计挂号诊查费'
#split#
COMMENT ON COLUMN nfreg_appointment.res_num IS '预约唯一码'
#split#
COMMENT ON COLUMN nfreg_appointment.res_source IS '挂号来源，根据金唐、联众区分'
#split#
COMMENT ON COLUMN nfreg_appointment.res_type IS '挂号平台。金唐、联众'
#split#
COMMENT ON COLUMN nfreg_appointment.reg_fee IS '挂号费'
#split#
COMMENT ON COLUMN nfreg_appointment.app_key IS '服务来源appkey'
#split#
COMMENT ON COLUMN nfreg_appointment.cancel_type IS '取消类型'
#split#
COMMENT ON COLUMN nfreg_appointment.cancel_reason IS '取消原因'
#split#
Create Sequence nfreg_appointment_S Start With 10001
#split#
CREATE TABLE nfreg_doctor_emp (
id NUMBER NOT NULL,
doctor_id NUMBER NOT NULL,
emp_id NUMBER NOT NULL,
create_date DATE DEFAULT sysdate NOT NULL,
PRIMARY KEY (id)
)
#split#
COMMENT ON TABLE nfreg_doctor_emp IS '挂号医生'
#split#
COMMENT ON COLUMN nfreg_doctor_emp.id IS '主键'
#split#
COMMENT ON COLUMN nfreg_doctor_emp.doctor_id IS '挂号医生id'
#split#
COMMENT ON COLUMN nfreg_doctor_emp.emp_id IS '本系统员工id'
#split#
COMMENT ON COLUMN nfreg_doctor_emp.create_date IS '创建日期'
#split#
Create Sequence nfreg_doctor_emp_S Start With 10001
#split#
CREATE TABLE "NFREG_SERVICE" (
  "ID" NUMBER NOT NULL,
  "NAME" VARCHAR2(255) NOT NULL,
  "DEVELOPER" VARCHAR2(255),
  "APP_KEY" VARCHAR2(255),
  "APP_SECRET" VARCHAR2(255),
  "STATUS" NUMBER DEFAULT 1 NOT NULL,
  "CREATE_DATE" DATE DEFAULT SYSDATE NOT NULL,
  PRIMARY KEY ("ID")
)
#split#
COMMENT ON TABLE "NFREG_SERVICE" IS '第三方服务'
#split#
COMMENT ON COLUMN "NFREG_SERVICE"."ID" IS '主键'
#split#
COMMENT ON COLUMN "NFREG_SERVICE"."NAME" IS '服务名称'
#split#
COMMENT ON COLUMN "NFREG_SERVICE"."DEVELOPER" IS '开发商'
#split#
COMMENT ON COLUMN "NFREG_SERVICE"."APP_KEY" IS 'appkey'
#split#
COMMENT ON COLUMN "NFREG_SERVICE"."APP_SECRET" IS 'appsecret'
#split#
COMMENT ON COLUMN "NFREG_SERVICE"."STATUS" IS '0停用 1启用'
#split#
COMMENT ON COLUMN "NFREG_SERVICE"."CREATE_DATE" IS '创建时间'
#split#
Create Sequence NFREG_SERVICE_S Start With 10001
#split#
CREATE TABLE NFSYS_DICTIONARY
(
  ID           NUMBER                           NOT NULL,
  ORG_ID       NUMBER                           NOT NULL,
  DIC_TYPE     VARCHAR2(50 BYTE)                NOT NULL,
  DIC_CODE     VARCHAR2(50 BYTE),
  DIC_NAME     VARCHAR2(50 BYTE)                NOT NULL,
  SEQ          NUMBER                           DEFAULT 0                     NOT NULL,
  INPUTCODE1   VARCHAR2(200 BYTE),
  INPUTCODE2   VARCHAR2(200 BYTE),
  CREATE_ID    NUMBER                           DEFAULT -1                    NOT NULL,
  CREATE_DATE  DATE                             DEFAULT sysdate               NOT NULL,
  FORBID       NUMBER                           DEFAULT 1                     NOT NULL
);
ALTER TABLE NFSYS_DICTIONARY ADD CONSTRAINT NFSYS_DICTIONARY_PK PRIMARY KEY (ID)
#split#
CREATE SEQUENCE NFSYS_DICTIONARY_S START WITH 10001
#split#
COMMENT ON TABLE NFSYS_DICTIONARY IS '通用字典表'
#split#
COMMENT ON COLUMN NFSYS_DICTIONARY.ID IS '主键'
#split#
COMMENT ON COLUMN NFSYS_DICTIONARY.ORG_ID IS '机构ID'
#split#
COMMENT ON COLUMN NFSYS_DICTIONARY.DIC_TYPE IS '分类类型'
#split#
COMMENT ON COLUMN NFSYS_DICTIONARY.DIC_CODE IS '分类代码'
#split#
COMMENT ON COLUMN NFSYS_DICTIONARY.DIC_NAME IS '分类名称'
#split#
COMMENT ON COLUMN NFSYS_DICTIONARY.SEQ IS '排序号'
#split#
COMMENT ON COLUMN NFSYS_DICTIONARY.INPUTCODE1 IS '首拼码'
#split#
COMMENT ON COLUMN NFSYS_DICTIONARY.INPUTCODE2 IS '五笔码'
#split#
COMMENT ON COLUMN NFSYS_DICTIONARY.CREATE_ID IS '创建用户ID'
#split#
COMMENT ON COLUMN NFSYS_DICTIONARY.CREATE_DATE IS '创建时间'
#split#
COMMENT ON COLUMN NFSYS_DICTIONARY.FORBID IS '禁用状态 1启用 99禁用'
#split#




