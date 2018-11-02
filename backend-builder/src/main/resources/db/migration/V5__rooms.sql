create table rooms (
  room_id int not null AUTO_INCREMENT,
  room_name varchar(255),
  building_id int references buildings(id),
  floor_id int references rooms(floor_id),
  area float,
  cube float,
  heating float,
  light float,
  primary key (room_id)
) engine = MyISAM;
