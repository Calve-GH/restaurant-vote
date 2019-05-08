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
       ('ITAKA');

INSERT INTO dish (name)
VALUES ('Soup'),
       ('French fries'),
       ('hamburger'),
       ('tea'),
       ('coffee');

INSERT INTO menu (date, restaurant_id, vote_count)
VALUES (now(), 100000, 10);

INSERT INTO menu_item (menu_id, dish_id, price)
VALUES (100007, 100002, 5),
       (100007, 100003, 10),
       (100007, 100004, 15),
       (100007, 100005, 2),
       (100007, 100006, 3);

INSERT INTO users (name, email, password, restaurant_id)
VALUES ('Ivanov', 'ivanov@gmail.com', '1234567', 100000),
       ('Bibiziana', 'makaka@gmail.com', '9876543', null),
       ('Padlov', 'papadlov@mail.ru', '12351514', null);

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_ADMIN', 100013),
       ('ROLE_USER', 100014),
       ('ROLE_USER', 100015);

INSERT INTO vote_log(user_id, restaurant_id, date)
VALUES (100013, 100000, now()),
       (100015, 100001, now());

INSERT INTO history(date, restaurant_id, data, count)
VALUES (now() - INTERVAL '1 day', 100000, 'SOME DISHES LIST WITH PRICES 1', 112),
       (now() - INTERVAL '2 day', 100000, 'SOME DISHES LIST WITH PRICES 2', 79),
       (now() - INTERVAL '1 day', 100001, 'SOME DISHES LIST WITH PRICES 3', 179);


