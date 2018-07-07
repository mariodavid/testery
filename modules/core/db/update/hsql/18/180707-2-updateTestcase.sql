-- update TESTERY_TESTCASE set TESTSUITE_ID = <default_value> where TESTSUITE_ID is null ;
alter table TESTERY_TESTCASE alter column TESTSUITE_ID set not null ;
