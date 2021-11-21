create table if not exists post(
    id serial primary key,
    title varchar(256),
    description text,
    link varchar(1000) unique,
    created timestamp
);