-- begin TESTERY_TESTCASE
alter table TESTERY_TESTCASE add constraint FK_TESTERY_TESTCASE_ON_TESTSUITE foreign key (TESTSUITE_ID) references TESTERY_TESTSUITE(ID)^
create index IDX_TESTERY_TESTCASE_ON_TESTSUITE on TESTERY_TESTCASE (TESTSUITE_ID)^
-- end TESTERY_TESTCASE
-- begin TESTERY_TESTSTEP
alter table TESTERY_TESTSTEP add constraint FK_TESTERY_TESTSTEP_ON_TESTCASE foreign key (TESTCASE_ID) references TESTERY_TESTCASE(ID)^
alter table TESTERY_TESTSTEP add constraint FK_TESTERY_TESTSTEP_ON_INPUT foreign key (INPUT_ID) references TESTERY_TESTSTEP_INPUT(ID)^
alter table TESTERY_TESTSTEP add constraint FK_TESTERY_TESTSTEP_ON_ACTION foreign key (ACTION_ID) references TESTERY_TESTACTION(ID)^
alter table TESTERY_TESTSTEP add constraint FK_TESTERY_TESTSTEP_ON_RESULT foreign key (RESULT_ID) references TESTERY_TESTSTEP_RESULT(ID)^
create index IDX_TESTERY_TESTSTEP_ON_TESTCASE on TESTERY_TESTSTEP (TESTCASE_ID)^
create index IDX_TESTERY_TESTSTEP_ON_INPUT on TESTERY_TESTSTEP (INPUT_ID)^
create index IDX_TESTERY_TESTSTEP_ON_ACTION on TESTERY_TESTSTEP (ACTION_ID)^
create index IDX_TESTERY_TESTSTEP_ON_RESULT on TESTERY_TESTSTEP (RESULT_ID)^
-- end TESTERY_TESTSTEP
-- begin TESTERY_ACTION_SCRIPT
alter table TESTERY_ACTION_SCRIPT add constraint FK_TESTERY_ACTION_SCRIPT_ON_ACTION foreign key (ACTION_ID) references TESTERY_TESTACTION(ID)^
alter table TESTERY_ACTION_SCRIPT add constraint FK_TESTERY_ACTION_SCRIPT_ON_SCRIPT foreign key (SCRIPT_ID) references TESTERY_TESTSCRIPT(ID)^
create index IDX_TESTERY_ACTION_SCRIPT_ON_ACTION on TESTERY_ACTION_SCRIPT (ACTION_ID)^
create index IDX_TESTERY_ACTION_SCRIPT_ON_SCRIPT on TESTERY_ACTION_SCRIPT (SCRIPT_ID)^
-- end TESTERY_ACTION_SCRIPT
-- begin TESTERY_JSON_TESTSTEP_INPUT
alter table TESTERY_JSON_TESTSTEP_INPUT add constraint FK_TESTERY_JSON_TESTSTEP_INPUT_ON_ID foreign key (ID) references TESTERY_TESTSTEP_INPUT(ID) on delete CASCADE^
-- end TESTERY_JSON_TESTSTEP_INPUT
