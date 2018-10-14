-- Нужно написать SQL скрипты:
-- 1. Создать структур данных в базе.
--    Таблицы.
--     Кузов. Двигатель, Коробка передач.
CREATE TABLE transmission (
	id SERIAL PRIMARY KEY,
	name CHARACTER VARYING (255)
);
CREATE TABLE car_body (
	id SERIAL PRIMARY KEY,
	name CHARACTER VARYING (255)
);
CREATE TABLE engine (
	id SERIAL PRIMARY KEY,
	name CHARACTER VARYING (255)
);
-- 2. Создать структуру Машина. Машина не может существовать без данных из п.1.
CREATE TABLE car (
 	id SERIAL PRIMARY KEY,
 	transmission_id INTEGER REFERENCES transmission(id),
 	car_body_id INTEGER REFERENCES car_body(id),
 	engine_id INTEGER REFERENCES engine(id),
  name CHARACTER VARYING (255)
);
-- 3. Заполнить таблицы через insert.
INSERT INTO transmission (name) VALUES ('AT'), ('MT'), ('AM'), ('CVT');
INSERT INTO car_body (name) VALUES ('Hatchback'), ('Sedan'), ('Crossover'), ('Coupe'), ('Convertible');
INSERT INTO engine (name) VALUES ('Petrol'), ('Disel'), ('Gas');
INSERT INTO car (transmission_id, car_body_id, engine_id, name)
  VALUES (1,1,1,'Mers'), (2,2,2,'Lada'), (3,3,1,'Accent'), (1,4,2,'Toyota');

-- Создать SQL запросы:
-- 1. Вывести список всех машин и все привязанные к ним детали.
SELECT c.id, c.name, t.name AS transmission, cb.name AS car_body, e.name AS engine
	FROM car AS c
		INNER JOIN transmission AS t ON c.transmission_id = t.id
		INNER JOIN car_body AS cb ON c.car_body_id = cb.id
		INNER JOIN engine AS e ON c.engine_id = e.id;
-- 2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
SELECT t.name
	FROM transmission AS t
		LEFT OUTER JOIN car AS c ON c.transmission_id = t.id
	WHERE c.name IS NULL;
SELECT cb.name
	FROM car_body AS cb
		LEFT OUTER JOIN car AS c ON c.car_body_id = cb.id
	WHERE c.name IS NULL;
SELECT e.name
	FROM car AS c
		RIGHT OUTER JOIN engine AS e ON c.engine_id = e.id
	WHERE c.name IS NULL;