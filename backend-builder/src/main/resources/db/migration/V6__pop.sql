insert into buildings (name)
values ('CW'), ('BM'), (null), ('WE'), (null);

insert into floors (floor_name, building_id)
values ('ONE', 4), ('TWO', 4), ('THREE', 4),
  ('CW1', 1), ('CW2', 1), (null, 1),
  ('CELLAR', 2),
  (null, 3), (null, 3), (null, 3), (null, 3);

insert into rooms (room_name, building_id, floor_id, area, cube, heating, light)
values ('100', 1, 4, 1, 2, 3, 4), ('200', 1, 4, 2, 3, 4, 5),
  ('201', 1, 5, 3, 4, 5, 6), (null, 1, 5, 4, 5, 6, 7),
  (null, 1, 6, 5, 6, 7, 8), (null, 1, 6, 6, 7, 8, 9),
  ('10C', 2, 7, 1, 2, 3, 4), ('10B', 2, 7, 2, 3, 4, 5),
  ('WC', 3, 9, 3, 4, 5, 6), ('CLOSET', 3, 9, 4, 5, 6, 7),
  (null, 3, 9, 5, 6, 7, 8), ('LIBRARY', 3, 10, 6, 7, 8, 9);