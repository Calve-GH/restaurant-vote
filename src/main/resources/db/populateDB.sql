DELETE FROM menu_item;
DELETE FROM menu;
DELETE FROM dish;
DELETE FROM restaurant;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM vote_log;
DELETE FROM history;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO restaurant (name)
VALUES ('Sweet bobaleh'),
       ('ITAKA'),
       ('Hunter Village');

INSERT INTO dish (name)
VALUES ('Soup'),
       ('French fries'),
       ('Hamburger'),
       ('Tea'),
       ('Coffee');

INSERT INTO menu (date, restaurant_id, vote_count)
VALUES (now(), 100000, 10);

INSERT INTO menu_item (menu_id, dish_id, price)
VALUES (100008, 100003, 5),
       (100008, 100004, 10),
       (100008, 100005, 15),
       (100008, 100006, 2),
       (100008, 100007, 3);

INSERT INTO users (name, email, password, restaurant_id)
VALUES ('Ivanov', 'ivanov@gmail.com', '{noop}1234567', 100000),
       ('Petrov', 'petrov@gmail.com', '{noop}9876543', null),
       ('Sidorov', 'sidirov@mail.ru', '{noop}12351514', null),
       ('Davidov', 'davidov009@gmail.com', '{noop}1234567', 100002);

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_ADMIN', 100014),
       ('ROLE_USER', 100015),
       ('ROLE_USER', 100016),
       ('ROLE_USER', 100017);

INSERT INTO vote_log(user_id, restaurant_id, date)
VALUES (100014, 100000, now());

INSERT INTO history(date, restaurant_id, data, vote_count)
VALUES (now() - INTERVAL '1' day , 100000, 'Soup:10.5 French fries:10.0 Coffee:5.3', 112),
       (now() - INTERVAL '2' day, 100000, 'Hamburger:13.12 Tea:3.0', 79),
       (now() - INTERVAL '1' day, 100001, 'Soup:7.0 Hamburger:11.0 Tea:3.4 Coffee:4.3', 179);
