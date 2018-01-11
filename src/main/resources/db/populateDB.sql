DELETE FROM user_roles;
DELETE FROM votes;
DELETE FROM dishes;
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

INSERT INTO dishes (date, restaurant, name, price) VALUES
  ('2017-01-10', 'rest1', 'dish1', 1000),
  ('2017-01-10', 'rest1', 'dish2', 1500),
  ('2017-01-10', 'rest1', 'dish3', 1700),
  ('2017-01-10', 'rest1', 'dish4', 500),
  ('2017-01-10', 'rest2', 'dish1', 1000),
  ('2017-01-10', 'rest2', 'dish2', 1300),
  ('2017-01-11', 'rest1', 'dish1', 2000),
  ('2017-01-11', 'rest1', 'dish2', 2500),
  ('2017-01-11', 'rest1', 'dish3', 2700),
  ('2017-01-11', 'rest1', 'dish4', 700),
  ('2017-01-11', 'rest2', 'dish1', 2000),
  ('2017-01-11', 'rest2', 'dish2', 2300),
  ('2017-01-11', 'rest2', 'dish3', 2300);

INSERT INTO votes (date, user_id, restaurant) VALUES
  ('2017-01-10', 100000, 'rest1'),
  ('2017-01-10', 100001, 'rest2');