create table floors (
  floor_id int not null AUTO_INCREMENT,
  floor_name varchar(255),
  building_id int references buildings(id),
  primary key (floor_id)
) engine = MyISAM;