-- Создать SQL скрипт инициализирующий создание новой базы данных.
CREATE DATABASE items;
-- Создать SQL скрипт создающий таблицы для хранения структуры системы заявок.
CREATE TABLE roles (
	id SERIAL PRIMARY KEY,
	role CHARACTER VARYING(255) NOT NULL
);
CREATE TABLE rules (
	id SERIAL PRIMARY KEY,
	rule CHARACTER VARYING(255) NOT NULL
);
CREATE TABLE roles_rules (
	id SERIAL PRIMARY KEY,
	role_id INTEGER REFERENCES roles(id) NOT NULL,
	rule_id INTEGER REFERENCES rules(id) NOT NULL
);
CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	name CHARACTER VARYING(255) NOT NULL,
	role_id INTEGER REFERENCES roles(id)
);
CREATE TABLE category (
	id SERIAL PRIMARY KEY,
	category CHARACTER VARYING(255) NOT NULL
);
CREATE TABLE state (
	id SERIAL PRIMARY KEY,
	state CHARACTER VARYING(255) NOT NULL
);
CREATE TABLE items (
	id INTEGER PRIMARY KEY REFERENCES users(id),
	name CHARACTER VARYING(255) NOT NULL,
	category_id INTEGER REFERENCES category(id) NOT NULL,
	state_id INTEGER REFERENCES state(id) NOT NULL
);
CREATE TABLE comments (
	id SERIAL PRIMARY KEY,
	comment TEXT NOT NULL,
	item_id INTEGER REFERENCES items(id) NOT NULL
);
CREATE TABLE attaches (
	id SERIAL PRIMARY KEY,
	attache TEXT NOT NULL,
	item_id INTEGER REFERENCES items(id) NOT NULL
);
-- Создать SQL скрипт заполняющий начальные данные для системы заявок.
INSERT INTO roles (role) VALUES ('admin'), ('user');
INSERT INTO rules (rule) VALUES ('rule1'), ('rule2');
INSERT INTO roles_rules (role_id, rule_id) VALUES (1, 1), (1, 2), (2, 2);
INSERT INTO users (name, role_id) VALUES ('Admin', 1), ('User', 2);
INSERT INTO category (category) VALUES ('Cat1'), ('Cat2');
INSERT INTO state (state) VALUES ('State1'), ('State2');
INSERT INTO items (id, name, category_id, state_id) VALUES (1, 'Item1', 1, 1), (2, 'Item2', 2, 2);
INSERT INTO comments (comment, item_id) VALUES ('Text1', 1), ('Text2', 1), ('Text1', 2);
INSERT INTO attaches (attache, item_id) VALUES ('File1', 1), ('File1', 2), ('File2', 2);