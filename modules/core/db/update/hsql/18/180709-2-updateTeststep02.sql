-- update TESTERY_TESTSTEP set INPUT_ID = <default_value> where INPUT_ID is null ;
alter table TESTERY_TESTSTEP alter column INPUT_ID set not null ;
