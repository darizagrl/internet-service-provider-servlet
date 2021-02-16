-- DROP TABLE IF EXISTS users cascade;
-- DROP TABLE IF EXISTS users_roles;
-- DROP TABLE IF EXISTS role;
-- DROP SEQUENCE IF EXISTS users_id_seq;
-- DROP SEQUENCE IF EXISTS roles_id_seq;
-- DROP SEQUENCE IF EXISTS tariff_id_seq CASCADE ;
drop table  users cascade;
CREATE TABLE IF NOT EXISTS users
(
    id        SERIAL PRIMARY KEY,
    firstname VARCHAR(255)        NOT NULL,
    lastname  VARCHAR(255)        NOT NULL,
    email     VARCHAR(255) UNIQUE NOT NULL,
    password  VARCHAR(255)        NOT NULL,
    isBlocked BOOLEAN DEFAULT FALSE,
    balance   NUMERIC DEFAULT 0.0
);
INSERT INTO users(firstname, lastname, email, password)
values ('Admin', 'Admin', 'admin@mail.com', 'admin');
CREATE TABLE IF NOT EXISTS role
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS users_roles
(
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE TABLE IF NOT EXISTS account
(
    account_id SERIAL PRIMARY KEY,
    account    BIGINT  NOT NULL,
    balance    NUMERIC NOT NULL
);
CREATE TABLE IF NOT EXISTS service
(
    id   SERIAL PRIMARY KEY,
    name varchar(45) NOT NULL
);
-- drop table  tariff;
CREATE TABLE IF NOT EXISTS tariff
(
    id          SERIAL PRIMARY KEY,
    name        varchar(45)  NOT NULL,
    description varchar(255) NOT NULL,
    price       NUMERIC      NOT NULL,
    service_id  INTEGER      NOT NULL
);
CREATE TABLE IF NOT EXISTS users_tariffs
(
    user_id   INTEGER NOT NULL,
    tariff_id INTEGER NOT NULL,
    FOREIGN KEY (tariff_id) REFERENCES tariff (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);