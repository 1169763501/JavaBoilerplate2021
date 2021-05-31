INSERT INTO NFSYS_PARAMETERS(ID,CODE,VALUE,DESCRIPTION,app_code) VALUES (NFSYS_PARAMETERS_S.nextval, 'version_number','0','预约挂号-数据库版本号','appointment')
#split#
INSERT INTO NFSYS_PARAMETERS(ID,CODE,VALUE,DESCRIPTION,app_code) VALUES (NFSYS_PARAMETERS_S.nextval, 'upload_file_prefix','/appointmentapi','附件上传地址前缀','appointment')
#split#
insert into NFSYS_APP(id,APP_CODE,APP_NAME,URL,SEQ,MEMBER_ID) VALUES(NFSYS_APP_S.nextval,'appointment','预约挂号','appointmentpc',0,(select id from NFSYS_CATEGORY where CATEGORY_TYPE='app_group' and CATEGORY_CODE='default' and org_id=0))
#split#
INSERT INTO "NFSYS_MENU" ("ID","APP_CODE", "CODE", "NAME", "ICON", "TYPE", "SEQ", "PARENT_ID", "CREATE_ID", "CREATE_DATE") VALUES (NFSYS_MENU_S.nextval, 'appointment','reg_doctor', '挂号医生', NULL, 'menu', '11', 0, '-1', sysdate)
#split#
INSERT INTO "NFSYS_MENU" ("ID","APP_CODE", "CODE", "NAME", "ICON", "TYPE", "SEQ", "PARENT_ID", "CREATE_ID", "CREATE_DATE") VALUES (NFSYS_MENU_S.nextval, 'appointment','reg_dept', '挂号科室', NULL, 'menu', '21', 0, '-1', sysdate)
#split#
INSERT INTO "NFSYS_MENU" ("ID","APP_CODE", "CODE", "NAME", "ICON", "TYPE", "SEQ", "PARENT_ID", "CREATE_ID", "CREATE_DATE") VALUES (NFSYS_MENU_S.nextval, 'appointment','reg_schedule', '排班号源', NULL, 'menu', '3', 0, '-1', sysdate)
#split#
INSERT INTO "NFSYS_MENU" ("ID","APP_CODE", "CODE", "NAME", "ICON", "TYPE", "SEQ", "PARENT_ID", "CREATE_ID", "CREATE_DATE") VALUES (NFSYS_MENU_S.nextval, 'appointment','reg_appointment', '预约记录', NULL, 'menu', '41', 0, '-1', sysdate)
#split#
INSERT INTO "NFSYS_MENU" ("ID","APP_CODE", "CODE", "NAME", "ICON", "TYPE", "SEQ", "PARENT_ID", "CREATE_ID", "CREATE_DATE") VALUES (NFSYS_MENU_S.nextval, 'appointment','reg_service', '三方服务', NULL, 'menu', '51', 0, '-1', sysdate)
#split#
INSERT INTO "NFSYS_POWER" ("ID", "APP_CODE","PAGE_CODE", "POWER_CODE", "CREATE_ID", "CREATE_DATE", "PAGE_NAME", "POWER_NAME", "GROUP_NAME") VALUES (NFSYS_POWER_S.nextval,'appointment', 'reg_doctor', 'select', '-1', sysdate, '挂号医生', '查看', '挂号管理')
#split#
INSERT INTO "NFSYS_POWER" ("ID", "APP_CODE","PAGE_CODE", "POWER_CODE", "CREATE_ID", "CREATE_DATE", "PAGE_NAME", "POWER_NAME", "GROUP_NAME") VALUES (NFSYS_POWER_S.nextval,'appointment', 'reg_dept', 'select', '-1', sysdate, '挂号科室', '查看', '挂号管理')
#split#
INSERT INTO "NFSYS_POWER" ("ID", "APP_CODE","PAGE_CODE", "POWER_CODE", "CREATE_ID", "CREATE_DATE", "PAGE_NAME", "POWER_NAME", "GROUP_NAME") VALUES (NFSYS_POWER_S.nextval,'appointment', 'reg_schedule', 'select', '-1', sysdate, '排班管理', '查看', '挂号管理')
#split#
INSERT INTO "NFSYS_POWER" ("ID", "APP_CODE","PAGE_CODE", "POWER_CODE", "CREATE_ID", "CREATE_DATE", "PAGE_NAME", "POWER_NAME", "GROUP_NAME") VALUES (NFSYS_POWER_S.nextval,'appointment', 'reg_appointment', 'select', '-1', sysdate, '预约记录', '查看', '挂号管理')
#split#
INSERT INTO "NFSYS_POWER" ("ID", "APP_CODE","PAGE_CODE", "POWER_CODE", "CREATE_ID", "CREATE_DATE", "PAGE_NAME", "POWER_NAME", "GROUP_NAME") VALUES (NFSYS_POWER_S.nextval,'appointment', 'reg_service', 'select', '-1', sysdate, '三方服务', '查看', '挂号管理')
#split#
DECLARE
    cursor c1 is select id from NFSYS_ORGANIZATION;
BEGIN
    FOR R1 IN C1 LOOP
INSERT INTO NFSYS_PARAMETERS (ID, org_id,CODE, VALUE, DESCRIPTION,app_code) VALUES (NFSYS_PARAMETERS_S.nextval, R1.id,'kingt_api_url', '', '金塘软件接口地址','appointment');
INSERT INTO NFSYS_PARAMETERS (ID, org_id,CODE, VALUE, DESCRIPTION,app_code) VALUES (NFSYS_PARAMETERS_S.nextval, R1.id,'kingt_api_user_id', '', '金塘软件接口用户名','appointment');
INSERT INTO NFSYS_PARAMETERS (ID, org_id,CODE, VALUE, DESCRIPTION,app_code) VALUES (NFSYS_PARAMETERS_S.nextval, R1.id,'kingt_api_user_pwd', '', '金塘软件接口密码','appointment');
INSERT INTO NFSYS_PARAMETERS (ID, org_id,CODE, VALUE, DESCRIPTION,app_code) VALUES (NFSYS_PARAMETERS_S.nextval, R1.id,'kingt_api_yyly', '', '金唐软件，预约来源','appointment');

INSERT INTO NFSYS_PARAMETERS (ID, org_id,CODE, VALUE, DESCRIPTION,app_code) VALUES (NFSYS_PARAMETERS_S.nextval, R1.id,'medi_api_url', '', '联众软件接口地址','appointment');
INSERT INTO NFSYS_PARAMETERS (ID, org_id,CODE, VALUE, DESCRIPTION,app_code) VALUES (NFSYS_PARAMETERS_S.nextval, R1.id,'medi_api_caozuoydm', '', '联众软件,操作员代码','appointment');
INSERT INTO NFSYS_PARAMETERS (ID, org_id,CODE, VALUE, DESCRIPTION,app_code) VALUES (NFSYS_PARAMETERS_S.nextval, R1.id,'medi_api_caozuoyxm', '', '联众软件,操作员姓名','appointment');
INSERT INTO NFSYS_PARAMETERS (ID, org_id,CODE, VALUE, DESCRIPTION,app_code) VALUES (NFSYS_PARAMETERS_S.nextval, R1.id,'medi_api_fenyuandm', '', '联众软件,分院代码','appointment');
INSERT INTO NFSYS_PARAMETERS (ID, org_id,CODE, VALUE, DESCRIPTION,app_code) VALUES (NFSYS_PARAMETERS_S.nextval, R1.id,'medi_api_zhongduanjbh', '', '联众软件,终端机编码','appointment');
INSERT INTO NFSYS_PARAMETERS (ID, org_id,CODE, VALUE, DESCRIPTION,app_code) VALUES (NFSYS_PARAMETERS_S.nextval, R1.id,'medi_api_res_source', '', '联众软件,挂号/预约来源','appointment');

INSERT INTO NFSYS_PARAMETERS (ID, org_id,CODE, VALUE, DESCRIPTION,app_code) VALUES (NFSYS_PARAMETERS_S.nextval, R1.id,'his_sync_company', '', 'his同步厂商,KINGT40:金唐4.0,MEDIINFO:联众，二选一','appointment');
INSERT INTO NFSYS_PARAMETERS (ID, org_id,CODE, VALUE, DESCRIPTION,app_code) VALUES (NFSYS_PARAMETERS_S.nextval, R1.id,'his_sync_last_day', '14', 'his同步最近X天的排班，默认14天','appointment');
INSERT INTO NFSYS_PARAMETERS (ID, org_id,CODE, VALUE, DESCRIPTION,app_code) VALUES (NFSYS_PARAMETERS_S.nextval, R1.id,'medi_api_dept_guahaoyybz', 'Y', '联众软件同步挂号科室，是否过滤guahaoyybz为2的科室。Y：是，N：否','appointment');
INSERT INTO NFSYS_PARAMETERS (ID, org_id,CODE, VALUE, DESCRIPTION,app_code) VALUES (NFSYS_PARAMETERS_S.nextval, R1.id,'his_wsdl_header_soap_action', '', 'his厂商wsdl请求头，必填项','appointment');
    END LOOP;
END;
#split#


