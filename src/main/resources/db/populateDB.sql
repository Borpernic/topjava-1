DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO user_meals (user_id, dateTime,description,calories) VALUES
  (100000, '2017-04-26 10:00:00', 'Затрак User', 500),
  (100000, '2017-04-26 14:00:00', 'Обед User', 1000),
  (100000, '2017-04-26 18:00:00', 'Ужин User', 500),
  (100001, '2017-04-26 10:00:00', 'Затрак админ', 500),
  (100001, '2017-04-26 14:00:00', 'Обед админ', 1000),
  (100001, '2017-04-26 18:00:00', 'Ужин админ', 500);

