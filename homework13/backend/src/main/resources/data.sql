INSERT INTO katerniuksm.roles(name) VALUES('ROLE_USER');
INSERT INTO katerniuksm.roles(name) VALUES('ROLE_ADMIN');

INSERT INTO katerniuksm.promocodes(promocode, percent)
VALUES('ZERO', 5),
VALUES('ONE', 10),
VALUES('TWO', 15),
VALUES('THREE', 20)
;

insert into katerniuksm.user_roles(user_id, role_id) values (1, 2);

INSERT INTO katerniuksm.products (name, price, amount)
VALUES ('Золотой дождь', 1000, 10),
       ('Кофе с чипсами', 150, 20),
       ('Антигравитационные носки', 5000, 5),
       ('Шоколадный фонтан', 2000, 3),
       ('Космический забивной мяч', 300, 15);