alter table TESTERY_JSON_TESTSTEP_INPUT add constraint FK_TESTERY_JSON_TESTSTEP_INPUT_ON_ID foreign key (ID) references TESTERY_TESTSTEP_INPUT(ID) on delete CASCADE;
