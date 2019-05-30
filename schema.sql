 drop table IF EXISTS branch_devices;

 drop table  IF EXISTS  model_devices;

 drop table IF EXISTS  device;

 drop table IF EXISTS  branch;

 drop table IF EXISTS  location;

 drop table IF EXISTS  model;

 create table location
 (
   id           serial not null
     constraint location_pkey
       primary key,
   build_number varchar(255),
   city         varchar(255),
   country      varchar(255),
   street       varchar(255)
 );

 create table model
 (
   id     serial not null
     constraint model_pkey
       primary key,
   serial varchar(255),
   title  varchar(255)
 );

   create table branch
 (
   id          serial not null
     constraint branch_pkey
       primary key,
   title       varchar(255),
   location_id integer
     constraint fkgj7ygs16yusst2tqo9hwbricr
       references location
 );


 create table device
 (
   id        integer not null
     constraint device_pkey
       primary key,
   hostname  varchar(255),
   ip        varchar(255),
   branch_id integer
     constraint fkipmy3spv1yujs0d5pdh25h3xu
       references branch,
   model_id  integer
     constraint fkc4mplfuo8xrtaysjnia8lyl3h
       references model
 );
