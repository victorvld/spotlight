create table if not exists account (
    entity_id varchar(255) not null primary key,
    type varchar(255) not null,
    username varchar(255) default null,
    url varchar(255) default null,
    token varchar(255) not null
);
