alter table TESTERY_TESTSTEP add constraint FK_TESTERY_TESTSTEP_ON_INPUT foreign key (INPUT_ID) references TESTERY_TESTSTEP_INPUT(ID);
create index IDX_TESTERY_TESTSTEP_ON_INPUT on TESTERY_TESTSTEP (INPUT_ID);
