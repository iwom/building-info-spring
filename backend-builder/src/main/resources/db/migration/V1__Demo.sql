create table student_entity (
  id bigint(20) not null AUTO_INCREMENT,
  name varchar(100),
  passport_number varchar(100),
  primary key (id)
) engine = MyISAM;