create database if not exists characters;

use characters;

drop table if exists story;
drop table if exists person;

create table story(
    id int(10) not null auto_increment primary key,
    name varchar(50) not null
);

create table person(
    id int(10) not null auto_increment primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    story_id int(10) not null,
    foreign key(story_id) references story(id)
);
