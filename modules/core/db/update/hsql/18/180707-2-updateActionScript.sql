alter table TESTERY_ACTION_SCRIPT add column RESULT_TYPE varchar(255) ^
update TESTERY_ACTION_SCRIPT set RESULT_TYPE = '' where RESULT_TYPE is null ;
alter table TESTERY_ACTION_SCRIPT alter column RESULT_TYPE set not null ;
