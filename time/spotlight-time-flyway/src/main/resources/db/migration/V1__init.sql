create table if not exists working_time (
    entity_id varchar(255) not null primary key,
    started_at timestamptz not null,
    completed_at timestamptz not null,
    planned_minutes serial not null
);

create table if not exists break_time (
    entity_id varchar(255) not null primary key,
    started_at timestamptz not null,
    completed_at timestamptz not null,
    planned_minutes serial not null
);

create table if not exists user_assessment (
    entity_id varchar(255) not null primary key,
    mood varchar(255) not null,
    feedback varchar(255) default null
);

create table if not exists interruption (
    entity_id varchar(255) not null,
    recorded_at timestamptz not null,
    reason varchar(255) not null,
    type varchar(255) not null,
    break_time_id varchar(255) default null,
    working_time_id varchar(255) default null,

    primary key (entity_id),

    constraint fk_working_time
    foreign key (working_time_id)
    references working_time(entity_id),

    constraint fk_break_time
    foreign key (break_time_id)
    references break_time(entity_id)
);

create table if not exists focus_unit (
    entity_id varchar(255) not null primary key,
    user_id varchar(255) not null,
    working_time_id varchar(255) unique not null,
    break_time_id varchar(255) unique not null,
    user_assessment_id varchar(255) unique default null,
    linked_project_point_id varchar(255),
    foreign key (working_time_id) references working_time(entity_id),
    foreign key (break_time_id) references break_time(entity_id),
    foreign key (user_assessment_id) references user_assessment(entity_id)
);
