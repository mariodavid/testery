-- begin TESTERY_TESTCASE
create table TESTERY_TESTCASE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TESTSUITE_ID varchar(36) not null,
    TESTCASE_ID varchar(255) not null,
    NAME varchar(255),
    DESCRIPTION longvarchar,
    SUCCESSFUL_ boolean not null,
    COMMENT_ longvarchar,
    --
    primary key (ID)
)^
-- end TESTERY_TESTCASE
-- begin TESTERY_TESTSUITE
create table TESTERY_TESTSUITE (
    ID varchar(36) not null,
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
    DESCRIPTION longvarchar,
    --
    primary key (ID)
)^
-- end TESTERY_TESTSUITE
-- begin TESTERY_TESTSTEP
create table TESTERY_TESTSTEP (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TESTCASE_ID varchar(36) not null,
    INPUT_ID varchar(36) not null,
    POSITION_ integer not null,
    ACTION_ID varchar(36) not null,
    EXECUTED boolean not null,
    EXECUTED_AT timestamp,
    SUCCESSFUL_ boolean not null,
    RESULT_ID varchar(36),
    COMMENT_ longvarchar,
    --
    primary key (ID)
)^
-- end TESTERY_TESTSTEP
-- begin TESTERY_TESTACTION
create table TESTERY_TESTACTION (
    ID varchar(36) not null,
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
    DESRCIPTION longvarchar,
    --
    primary key (ID)
)^
-- end TESTERY_TESTACTION
-- begin TESTERY_TESTSTEP_RESULT
create table TESTERY_TESTSTEP_RESULT (
    ID varchar(36) not null,
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
    ERROR longvarchar,
    STACKTRACE longvarchar,
    --
    -- from testery$TableValueTeststepResult
    EXPECTED_TABLE longvarchar,
    ACTUAL_TABLE longvarchar,
    --
    -- from testery$SingleValueTeststepResult
    EXPECTED_VALUE longvarchar,
    ACTUAL_VALUE longvarchar,
    --
    primary key (ID)
)^
-- end TESTERY_TESTSTEP_RESULT
-- begin TESTERY_ACTION_SCRIPT
create table TESTERY_ACTION_SCRIPT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ACTION_ID varchar(36) not null,
    RESULT_TYPE varchar(255) not null,
    SCRIPT_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end TESTERY_ACTION_SCRIPT
-- begin TESTERY_TESTSCRIPT
create table TESTERY_TESTSCRIPT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    DESCRIPTION longvarchar,
    SCRIPT longvarchar not null,
    --
    primary key (ID)
)^
-- end TESTERY_TESTSCRIPT
-- begin TESTERY_TESTSTEP_INPUT
create table TESTERY_TESTSTEP_INPUT (
    ID varchar(36) not null,
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
    TESTACTION_ID varchar(36),
    --
    primary key (ID)
)^
-- end TESTERY_TESTSTEP_INPUT
-- begin TESTERY_JSON_TESTSTEP_INPUT
create table TESTERY_JSON_TESTSTEP_INPUT (
    ID varchar(36) not null,
    --
    INPUT_ longvarchar,
    --
    primary key (ID)
)^
-- end TESTERY_JSON_TESTSTEP_INPUT
