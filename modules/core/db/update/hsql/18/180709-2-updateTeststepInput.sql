alter table TESTERY_TESTSTEP_INPUT add column NAME varchar(255) ^
update TESTERY_TESTSTEP_INPUT set NAME = '' where NAME is null ;
alter table TESTERY_TESTSTEP_INPUT alter column NAME set not null ;
