DELETE FROM user_roles;
DELETE FROM votes;
DELETE FROM dishes;
DELETE FROM RESTAURANTS;
DELETE FROM users;
ALTER SEQUENCE global_seq
RESTART WITH 100000;

INSERT INTO users (name, password) VALUES
  ('User', 'password'),
  ('User1', 'password1'),
  ('Admin', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100002),
  ('ROLE_USER', 100001);

INSERT INTO restaurants (name, current_menu_date) VALUES
  ('rest1', '2017-01-11' ),
  ('rest2', '2017-01-11');

INSERT INTO dishes (date, rest_id, name, price) VALUES
  ('2017-01-10', 100003, 'dish1', 1000),
  ('2017-01-10', 100003, 'dish2', 1500),
  ('2017-01-10', 100003, 'dish3', 1700),
  ('2017-01-10', 100003, 'dish4', 500),
  ('2017-01-10', 100004, 'dish1', 1000),
  ('2017-01-10', 100004, 'dish2', 1300),
  ('2017-01-11', 100003, 'dish1', 2000),
  ('2017-01-11', 100003, 'dish2', 2500),
  ('2017-01-11', 100003, 'dish3', 2700),
  ('2017-01-11', 100003, 'dish4', 700),
  ('2017-01-11', 100004, 'dish1', 2000),
  ('2017-01-11', 100004, 'dish2', 2300),
  ('2017-01-11', 100004, 'dish3', 2300);

INSERT INTO votes (DATE, REST_ID, USER_ID) VALUES
  ('2017-01-10', 100003, 100000),
  ('2017-01-10', 100003, 100001),
  ('2017-01-11', 100004, 100000);
