alter table TESTERY_ACTION_SCRIPT add constraint FK_TESTERY_ACTION_SCRIPT_ON_ACTION foreign key (ACTION_ID) references TESTERY_TESTACTION(ID);
alter table TESTERY_ACTION_SCRIPT add constraint FK_TESTERY_ACTION_SCRIPT_ON_SCRIPT foreign key (SCRIPT_ID) references TESTERY_TESTSCRIPT(ID);
create index IDX_TESTERY_ACTION_SCRIPT_ON_ACTION on TESTERY_ACTION_SCRIPT (ACTION_ID);
create index IDX_TESTERY_ACTION_SCRIPT_ON_SCRIPT on TESTERY_ACTION_SCRIPT (SCRIPT_ID);
