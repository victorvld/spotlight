create table if not exists account (
    entity_id varchar(255) not null primary key,
    type varchar(255) not null,
    username varchar(255) default null,
    web_domain varchar(255) default null,
    token varchar(255) not null
);

create table if not exists board (
    board_id varchar(255) not null primary key,
    board_name varchar(255) not null,
    location_avatar_uri varchar(255) default null,
    location_project_name varchar(255) default null,
    location_project_key varchar(255) not null
);
