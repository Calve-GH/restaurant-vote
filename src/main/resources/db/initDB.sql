DROP TABLE IF EXISTS menu_item;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS vote_log;
DROP TABLE IF EXISTS history;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS restaurant;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE dish
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL
);

CREATE TABLE restaurant
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL
);

CREATE TABLE menu
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    date          DATE                          NOT NULL,
    restaurant_id INTEGER                       NOT NULL,
    vote_count    INTEGER             DEFAULT 0 NOT NULL,
    CONSTRAINT restaurant_idx UNIQUE (restaurant_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE menu_item
(
    id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    menu_id INTEGER                       NOT NULL,
    dish_id INTEGER                       NOT NULL,
    price   DECIMAL             DEFAULT 0 NOT NULL,
    FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE
);

CREATE TABLE users
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name          VARCHAR                           NOT NULL,
    email         VARCHAR                           NOT NULL,
    password      VARCHAR                           NOT NULL,
    registered    TIMESTAMP           DEFAULT now() NOT NULL,
    enabled       BOOL                DEFAULT TRUE  NOT NULL,
    restaurant_id INTEGER             DEFAULT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE vote_log
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id       INTEGER NOT NULL,
    restaurant_id INTEGER NOT NULL,
    date          DATE    NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE history
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    date          DATE    NOT NULL,
    restaurant_id INTEGER NOT NULL,
    data          VARCHAR NOT NULL,
    vote_count         INTEGER NOT NULL    DEFAULT 0,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION process_voting() RETURNS TRIGGER AS
$menu$
BEGIN
    IF (TG_OP = 'DELETE') THEN
        UPDATE menu SET vote_count = menu.vote_count - 1 WHERE restaurant_id = OLD.restaurant_id;
        RETURN OLD;
    ELSIF (TG_OP = 'INSERT') THEN
        INSERT INTO menu(date, restaurant_id, vote_count)
        VALUES (now(), NEW.restaurant_id, 1)
        ON CONFLICT(restaurant_id) DO UPDATE
            SET vote_count = menu.vote_count + 1;
        RETURN NEW;
    END IF;
    RETURN NULL; -- result is ignored since this is an AFTER trigger
END;
$menu$ LANGUAGE plpgsql;

CREATE TRIGGER vote_increment
    AFTER INSERT OR DELETE
    ON vote_log
    FOR EACH ROW
EXECUTE PROCEDURE process_voting();


