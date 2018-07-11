-- begin TESTERY_TESTCASE
create table TESTERY_TESTCASE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TESTSUITE_ID uuid not null,
    TESTCASE_ID varchar(255) not null,
    NAME varchar(255),
    DESCRIPTION text,
    SUCCESSFUL_ boolean not null,
    COMMENT_ text,
    --
    primary key (ID)
)^
-- end TESTERY_TESTCASE
-- begin TESTERY_TESTSUITE
create table TESTERY_TESTSUITE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    CODE varchar(20) not null,
    DESCRIPTION text,
    --
    primary key (ID)
)^
-- end TESTERY_TESTSUITE
-- begin TESTERY_TESTSTEP
create table TESTERY_TESTSTEP (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TESTCASE_ID uuid not null,
    INPUT_ID uuid not null,
    POSITION_ integer not null,
    ACTION_ID uuid not null,
    EXECUTED boolean not null,
    EXECUTED_AT timestamp,
    SUCCESSFUL_ boolean not null,
    RESULT_ID uuid,
    COMMENT_ text,
    --
    primary key (ID)
)^
-- end TESTERY_TESTSTEP
-- begin TESTERY_TESTACTION
create table TESTERY_TESTACTION (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    CODE varchar(255) not null,
    DESRCIPTION text,
    --
    primary key (ID)
)^
-- end TESTERY_TESTACTION
-- begin TESTERY_TESTSTEP_RESULT
create table TESTERY_TESTSTEP_RESULT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    DTYPE varchar(100),
    --
    SUMMARY varchar(255) not null,
    ERROR text,
    STACKTRACE text,
    --
    -- from testery$TableValueTeststepResult
    EXPECTED_TABLE text,
    ACTUAL_TABLE text,
    --
    -- from testery$SingleValueTeststepResult
    EXPECTED_VALUE text,
    ACTUAL_VALUE text,
    --
    primary key (ID)
)^
-- end TESTERY_TESTSTEP_RESULT
-- begin TESTERY_ACTION_SCRIPT
create table TESTERY_ACTION_SCRIPT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ACTION_ID uuid not null,
    RESULT_TYPE varchar(255) not null,
    SCRIPT_ID uuid not null,
    --
    primary key (ID)
)^
-- end TESTERY_ACTION_SCRIPT
-- begin TESTERY_TESTSCRIPT
create table TESTERY_TESTSCRIPT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    DESCRIPTION text,
    SCRIPT text not null,
    --
    primary key (ID)
)^
-- end TESTERY_TESTSCRIPT
-- begin TESTERY_TESTSTEP_INPUT
create table TESTERY_TESTSTEP_INPUT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    DTYPE varchar(31),
    --
    NAME varchar(255) not null,
    TESTACTION_ID uuid,
    --
    primary key (ID)
)^
-- end TESTERY_TESTSTEP_INPUT
-- begin TESTERY_JSON_TESTSTEP_INPUT
create table TESTERY_JSON_TESTSTEP_INPUT (
    ID uuid,
    --
    INPUT_ text,
    --
    primary key (ID)
)^
-- end TESTERY_JSON_TESTSTEP_INPUT
