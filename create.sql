create table agent (agent_id bigint not null, agent_address varchar(255) not null, agent_date_of_birth datetime not null, agent_description varchar(255) not null, agent_email varchar(255) not null, agent_first_name varchar(255) not null, agent_id_number varchar(255) not null, agent_last_name varchar(255) not null, agent_password varchar(255) not null, agent_phone_number varchar(255) not null, agent_username varchar(255) not null, profile_picture_id bigint, primary key (agent_id)) type=InnoDB
create table company_details (company_details_id bigint not null, company_address varchar(255) not null, company_email varchar(255) not null, company_logo_url varchar(255) not null, company_name varchar(255) not null, company_phone_number varchar(255) not null, primary key (company_details_id)) type=InnoDB
create table hibernate_sequence (next_val bigint) type=InnoDB
insert into hibernate_sequence values ( 1 )
create table profile_picture (profile_picture_id bigint not null, profile_picture_url varchar(255) not null, primary key (profile_picture_id)) type=InnoDB
create table property_attribute (property_attribute_id bigint not null, property_attribute_description varchar(255) not null, property_attribute_name varchar(255) not null, primary key (property_attribute_id)) type=InnoDB
create table property_media (property_media_id bigint not null, property_media_type varchar(255) not null, property_media_url varchar(255) not null, primary key (property_media_id)) type=InnoDB
alter table agent add constraint UK_ovd2qsiqref4xunrl8ubbvdig unique (agent_id_number)
alter table agent add constraint UK_rh8q18r8mhuc7d5j8umqx2cjs unique (agent_username)
alter table property_attribute add constraint UK_3u0o4cmdl91o90yowy21k94po unique (property_attribute_name)
alter table agent add constraint FKkn5a6j0vn0oy4xngifdw02khy foreign key (profile_picture_id) references profile_picture (profile_picture_id)
create table agent (agent_id bigint not null, agent_address varchar(255) not null, agent_date_of_birth datetime not null, agent_description varchar(255) not null, agent_email varchar(255) not null, agent_first_name varchar(255) not null, agent_id_number varchar(255) not null, agent_last_name varchar(255) not null, agent_password varchar(255) not null, agent_phone_number varchar(255) not null, agent_username varchar(255) not null, profile_picture_id bigint, primary key (agent_id)) type=InnoDB
create table company_details (company_details_id bigint not null, company_address varchar(255) not null, company_email varchar(255) not null, company_logo_url varchar(255) not null, company_name varchar(255) not null, company_phone_number varchar(255) not null, primary key (company_details_id)) type=InnoDB
create table hibernate_sequence (next_val bigint) type=InnoDB
insert into hibernate_sequence values ( 1 )
create table profile_picture (profile_picture_id bigint not null, profile_picture_url varchar(255) not null, primary key (profile_picture_id)) type=InnoDB
create table property_attribute (property_attribute_id bigint not null, property_attribute_description varchar(255) not null, property_attribute_name varchar(255) not null, primary key (property_attribute_id)) type=InnoDB
create table property_media (property_media_id bigint not null, property_media_type varchar(255) not null, property_media_url varchar(255) not null, primary key (property_media_id)) type=InnoDB
alter table agent add constraint UK_ovd2qsiqref4xunrl8ubbvdig unique (agent_id_number)
alter table agent add constraint UK_rh8q18r8mhuc7d5j8umqx2cjs unique (agent_username)
alter table property_attribute add constraint UK_3u0o4cmdl91o90yowy21k94po unique (property_attribute_name)
alter table agent add constraint FKkn5a6j0vn0oy4xngifdw02khy foreign key (profile_picture_id) references profile_picture (profile_picture_id)
create table agent (agent_id bigint not null, agent_address varchar(255) not null, agent_date_of_birth datetime not null, agent_description varchar(255) not null, agent_email varchar(255) not null, agent_first_name varchar(255) not null, agent_id_number varchar(255) not null, agent_last_name varchar(255) not null, agent_password varchar(255) not null, agent_phone_number varchar(255) not null, agent_username varchar(255) not null, profile_picture_id bigint, primary key (agent_id)) type=InnoDB
create table company_details (company_details_id bigint not null, company_address varchar(255) not null, company_email varchar(255) not null, company_logo_url varchar(255) not null, company_name varchar(255) not null, company_phone_number varchar(255) not null, primary key (company_details_id)) type=InnoDB
create table hibernate_sequence (next_val bigint) type=InnoDB
insert into hibernate_sequence values ( 1 )
create table profile_picture (profile_picture_id bigint not null, profile_picture_url varchar(255) not null, primary key (profile_picture_id)) type=InnoDB
create table property_attribute (property_attribute_id bigint not null, property_attribute_description varchar(255) not null, property_attribute_name varchar(255) not null, primary key (property_attribute_id)) type=InnoDB
create table property_media (property_media_id bigint not null, property_media_type varchar(255) not null, property_media_url varchar(255) not null, primary key (property_media_id)) type=InnoDB
alter table agent add constraint UK_ovd2qsiqref4xunrl8ubbvdig unique (agent_id_number)
alter table agent add constraint UK_rh8q18r8mhuc7d5j8umqx2cjs unique (agent_username)
alter table property_attribute add constraint UK_3u0o4cmdl91o90yowy21k94po unique (property_attribute_name)
alter table agent add constraint FKkn5a6j0vn0oy4xngifdw02khy foreign key (profile_picture_id) references profile_picture (profile_picture_id)
create table agent (agent_id bigint not null, agent_address varchar(255) not null, agent_date_of_birth datetime not null, agent_description varchar(255) not null, agent_email varchar(255) not null, agent_first_name varchar(255) not null, agent_id_number varchar(255) not null, agent_last_name varchar(255) not null, agent_password varchar(255) not null, agent_phone_number varchar(255) not null, agent_username varchar(255) not null, profile_picture_id bigint, primary key (agent_id)) type=InnoDB
create table company_details (company_details_id bigint not null, company_address varchar(255) not null, company_email varchar(255) not null, company_logo_url varchar(255) not null, company_name varchar(255) not null, company_phone_number varchar(255) not null, primary key (company_details_id)) type=InnoDB
create table hibernate_sequence (next_val bigint) type=InnoDB
insert into hibernate_sequence values ( 1 )
create table profile_picture (profile_picture_id bigint not null, profile_picture_url varchar(255) not null, primary key (profile_picture_id)) type=InnoDB
create table property_attribute (property_attribute_id bigint not null, property_attribute_description varchar(255) not null, property_attribute_name varchar(255) not null, primary key (property_attribute_id)) type=InnoDB
create table property_media (property_media_id bigint not null, property_media_type varchar(255) not null, property_media_url varchar(255) not null, primary key (property_media_id)) type=InnoDB
alter table agent add constraint UK_ovd2qsiqref4xunrl8ubbvdig unique (agent_id_number)
alter table agent add constraint UK_rh8q18r8mhuc7d5j8umqx2cjs unique (agent_username)
alter table property_attribute add constraint UK_3u0o4cmdl91o90yowy21k94po unique (property_attribute_name)
alter table agent add constraint FKkn5a6j0vn0oy4xngifdw02khy foreign key (profile_picture_id) references profile_picture (profile_picture_id)
create table agent (agent_id bigint not null, agent_address varchar(255) not null, agent_date_of_birth datetime not null, agent_description varchar(255) not null, agent_email varchar(255) not null, agent_first_name varchar(255) not null, agent_id_number varchar(255) not null, agent_last_name varchar(255) not null, agent_password varchar(255) not null, agent_phone_number varchar(255) not null, agent_username varchar(255) not null, profile_picture_id bigint, primary key (agent_id)) type=InnoDB
create table company_details (company_details_id bigint not null, company_address varchar(255) not null, company_email varchar(255) not null, company_logo_url varchar(255) not null, company_name varchar(255) not null, company_phone_number varchar(255) not null, primary key (company_details_id)) type=InnoDB
create table hibernate_sequence (next_val bigint) type=InnoDB
insert into hibernate_sequence values ( 1 )
create table profile_picture (profile_picture_id bigint not null, profile_picture_url varchar(255) not null, primary key (profile_picture_id)) type=InnoDB
create table property_attribute (property_attribute_id bigint not null, property_attribute_description varchar(255) not null, property_attribute_name varchar(255) not null, primary key (property_attribute_id)) type=InnoDB
create table property_media (property_media_id bigint not null, property_media_type varchar(255) not null, property_media_url varchar(255) not null, primary key (property_media_id)) type=InnoDB
alter table agent add constraint UK_ovd2qsiqref4xunrl8ubbvdig unique (agent_id_number)
alter table agent add constraint UK_rh8q18r8mhuc7d5j8umqx2cjs unique (agent_username)
alter table property_attribute add constraint UK_3u0o4cmdl91o90yowy21k94po unique (property_attribute_name)
alter table agent add constraint FKkn5a6j0vn0oy4xngifdw02khy foreign key (profile_picture_id) references profile_picture (profile_picture_id)
create table agent (agent_id bigint not null, agent_address varchar(255) not null, agent_date_of_birth datetime not null, agent_description varchar(255) not null, agent_email varchar(255) not null, agent_first_name varchar(255) not null, agent_id_number varchar(255) not null, agent_last_name varchar(255) not null, agent_password varchar(255) not null, agent_phone_number varchar(255) not null, agent_username varchar(255) not null, profile_picture_id bigint, primary key (agent_id)) type=InnoDB
create table company_details (company_details_id bigint not null, company_address varchar(255) not null, company_email varchar(255) not null, company_logo_url varchar(255) not null, company_name varchar(255) not null, company_phone_number varchar(255) not null, primary key (company_details_id)) type=InnoDB
create table hibernate_sequence (next_val bigint) type=InnoDB
insert into hibernate_sequence values ( 1 )
create table profile_picture (profile_picture_id bigint not null, profile_picture_url varchar(255) not null, primary key (profile_picture_id)) type=InnoDB
create table property_attribute (property_attribute_id bigint not null, property_attribute_description varchar(255) not null, property_attribute_name varchar(255) not null, primary key (property_attribute_id)) type=InnoDB
create table property_media (property_media_id bigint not null, property_media_type varchar(255) not null, property_media_url varchar(255) not null, primary key (property_media_id)) type=InnoDB
alter table agent add constraint UK_ovd2qsiqref4xunrl8ubbvdig unique (agent_id_number)
alter table agent add constraint UK_rh8q18r8mhuc7d5j8umqx2cjs unique (agent_username)
alter table property_attribute add constraint UK_3u0o4cmdl91o90yowy21k94po unique (property_attribute_name)
alter table agent add constraint FKkn5a6j0vn0oy4xngifdw02khy foreign key (profile_picture_id) references profile_picture (profile_picture_id)
create table agent (agent_id bigint not null, agent_address varchar(255) not null, agent_date_of_birth datetime not null, agent_description varchar(255) not null, agent_email varchar(255) not null, agent_first_name varchar(255) not null, agent_id_number varchar(255) not null, agent_last_name varchar(255) not null, agent_password varchar(255) not null, agent_phone_number varchar(255) not null, agent_username varchar(255) not null, profile_picture_id bigint, primary key (agent_id)) type=InnoDB
create table company_details (company_details_id bigint not null, company_address varchar(255) not null, company_email varchar(255) not null, company_logo_url varchar(255) not null, company_name varchar(255) not null, company_phone_number varchar(255) not null, primary key (company_details_id)) type=InnoDB
create table hibernate_sequence (next_val bigint) type=InnoDB
insert into hibernate_sequence values ( 1 )
create table profile_picture (profile_picture_id bigint not null, profile_picture_url varchar(255) not null, primary key (profile_picture_id)) type=InnoDB
create table property_attribute (property_attribute_id bigint not null, property_attribute_description varchar(255) not null, property_attribute_name varchar(255) not null, primary key (property_attribute_id)) type=InnoDB
create table property_media (property_media_id bigint not null, property_media_type varchar(255) not null, property_media_url varchar(255) not null, primary key (property_media_id)) type=InnoDB
alter table agent add constraint UK_ovd2qsiqref4xunrl8ubbvdig unique (agent_id_number)
alter table agent add constraint UK_rh8q18r8mhuc7d5j8umqx2cjs unique (agent_username)
alter table property_attribute add constraint UK_3u0o4cmdl91o90yowy21k94po unique (property_attribute_name)
alter table agent add constraint FKkn5a6j0vn0oy4xngifdw02khy foreign key (profile_picture_id) references profile_picture (profile_picture_id)
create table agent (agent_id bigint not null, agent_address varchar(255) not null, agent_date_of_birth datetime not null, agent_description varchar(255) not null, agent_email varchar(255) not null, agent_first_name varchar(255) not null, agent_id_number varchar(255) not null, agent_last_name varchar(255) not null, agent_password varchar(255) not null, agent_phone_number varchar(255) not null, agent_username varchar(255) not null, profile_picture_id bigint, primary key (agent_id)) type=InnoDB
create table company_details (company_details_id bigint not null, company_address varchar(255) not null, company_email varchar(255) not null, company_logo_url varchar(255) not null, company_name varchar(255) not null, company_phone_number varchar(255) not null, primary key (company_details_id)) type=InnoDB
create table hibernate_sequence (next_val bigint) type=InnoDB
insert into hibernate_sequence values ( 1 )
create table profile_picture (profile_picture_id bigint not null, profile_picture_url varchar(255) not null, primary key (profile_picture_id)) type=InnoDB
create table property_attribute (property_attribute_id bigint not null, property_attribute_description varchar(255) not null, property_attribute_name varchar(255) not null, primary key (property_attribute_id)) type=InnoDB
create table property_media (property_media_id bigint not null, property_media_type varchar(255) not null, property_media_url varchar(255) not null, primary key (property_media_id)) type=InnoDB
alter table agent add constraint UK_ovd2qsiqref4xunrl8ubbvdig unique (agent_id_number)
alter table agent add constraint UK_rh8q18r8mhuc7d5j8umqx2cjs unique (agent_username)
alter table property_attribute add constraint UK_3u0o4cmdl91o90yowy21k94po unique (property_attribute_name)
alter table agent add constraint FKkn5a6j0vn0oy4xngifdw02khy foreign key (profile_picture_id) references profile_picture (profile_picture_id)
