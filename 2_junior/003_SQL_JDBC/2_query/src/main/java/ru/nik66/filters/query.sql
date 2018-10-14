-- 1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT p.id, p.name, t.name AS type, p.expired_date, p.price
	FROM product AS p INNER JOIN type AS t ON p.type_id = t.id
	WHERE t.name = 'СЫР';
-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT *
	FROM product AS p
	WHERE p.name LIKE '%мороженное%';
-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT *
	FROM product AS p
	WHERE p.expired_date <= CURRENT_DATE + INTERVAL '1 month';
-- 4. Написать запрос, который выводит самый дорогой продукт.
SELECT *
	FROM product AS p
	WHERE p.price = (SELECT MAX(p.price) FROM product AS p);
-- 5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT count(p.id)
	FROM product AS p INNER JOIN type AS t ON p.type_id = t.id
	WHERE t.name = 'СЫР';
-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT p.id, p.name, t.name AS type, p.expired_date, p.price
	FROM product AS p INNER JOIN type AS t ON p.type_id = t.id
	WHERE t.name = 'СЫР' OR t.name = 'МОЛОКО';
-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT t.name
	FROM product AS p INNER JOIN type AS t ON p.type_id = t.id
	GROUP BY t.name	HAVING count(p.type_id) < 10;
-- 8. Вывести все продукты и их тип.
SELECT p.id, p.name, t.name AS type, p.expired_date, p.price
	FROM product AS p INNER JOIN type AS t ON p.type_id = t.id;