DELETE FROM menu_item;
DELETE FROM menu;
DELETE FROM dish;
DELETE FROM users;
DELETE FROM restaurant;
DELETE FROM user_roles;

DELETE FROM vote_log;
DELETE FROM history;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO restaurant (name, menu_exist)
VALUES ('Sweet bobaleh', false),
       ('ITAKA', false),
       ('Hunter Village', false);
-- 100002
INSERT INTO dish (name)
VALUES ('Soup'),
       ('French fries'),
       ('Hamburger'),
       ('Tea'),
       ('Coffee');
-- 100007
INSERT INTO menu (date, restaurant_id, vote_count)
VALUES (now(), 100000, 10),
       (now(), 100001, 10),
       (now()+ INTERVAL '1' day, 100002, 0),
       (now() + INTERVAL '1' day, 100000, 0),
       (now() + INTERVAL '2' day, 100000, 0);

-- 100008 100012 +4
INSERT INTO menu_item (menu_id, dish_id, price)
VALUES (100008, 100003, 5.1),
       (100008, 100004, 10.2),
       (100008, 100005, 15.3),
       (100008, 100006, 2),
       (100008, 100007, 3),
       (100009, 100003, 5.4),
       (100009, 100004, 10.7),
       (100010, 100004, 10.2),
       (100010, 100005, 15.7),
       (100011, 100005, 15.3),
       (100011, 100006, 2),
       (100011, 100007, 3),
       (100012, 100005, 15.1),
       (100012, 100006, 2.1),
       (100012, 100007, 3.2);

-- 100013 100027

INSERT INTO users (name, email, password)
VALUES ('Ivanov', 'ivanov@gmail.com', '{noop}1234567'),
       ('Petrov', 'petrov@gmail.com', '{noop}9876543'),
       ('Sidorov', 'sidirov@mail.ru', '{noop}12351514'),
       ('Davidov', 'davidov009@gmail.com', '{noop}1234567');
-- 100031
INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_ADMIN', 100028),
       ('ROLE_USER', 100029),
       ('ROLE_USER', 100030),
       ('ROLE_USER', 100031);

INSERT INTO vote_log(user_id, restaurant_id, date)
VALUES (100028, 100000, now()),
       (100029, 100001, now()),
       (100030, 100001, now());
-- 100018 100034
INSERT INTO history(date, restaurant_id, data, vote_count)
VALUES (now() - INTERVAL '1' day, 100000, 'Soup:10.5 French fries:10.0 Coffee:5.3', 112),
       (now() - INTERVAL '2' day, 100000, 'Hamburger:13.12 Tea:3.0', 79),
       (now() - INTERVAL '1' day, 100001, 'Soup:7.0 Hamburger:11.0 Tea:3.4 Coffee:4.3', 179);
-- 100021 --100037