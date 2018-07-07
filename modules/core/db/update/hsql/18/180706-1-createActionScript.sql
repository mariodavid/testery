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
    SCRIPT_ID varchar(36) not null,
    --
    primary key (ID)
);
