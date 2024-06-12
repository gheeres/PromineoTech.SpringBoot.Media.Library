/*
CREATE DATABASE media;
-- -- CREATE SCHEMA media;
CREATE OR REPLACE USER 'media'@'%' IDENTIFIED BY 'media';
GRANT ALL PRIVILEGES ON media.* TO 'media'@'%';

DROP TABLE IF EXISTS episode_actor;
DROP TABLE IF EXISTS episode;
DROP TABLE IF EXISTS actor;
DROP TABLE IF EXISTS `show`;

create table actor (
  actor_birthdate datetime(6), 
  actor_id bigint not null auto_increment, 
  actor_description varchar(255), 
  actor_firstname varchar(255), 
  actor_lastname varchar(255), 
  primary key (actor_id)
);
create table episode (
  episode_episode bigint, 
  episode_id bigint not null auto_increment, 
  episode_season bigint, 
  show_id bigint not null, 
  episode_description varchar(255), 
  episode_name varchar(255), 
  primary key (episode_id)
);
create table episode_actor (
  actor_id bigint not null, 
  episode_id bigint not null, 
  primary key (actor_id, episode_id)
);
create table `show` (
  show_id bigint not null auto_increment, 
  show_description varchar(255), 
  show_name varchar(255), 
  primary key (show_id)
);

-- */
