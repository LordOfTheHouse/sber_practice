create schema if not exists katerniuksm;

create table product
(
    id     integer generated always as identity
        primary key,
    name   varchar(255) not null,
    price  numeric      not null,
    amount integer          not null
);

create table cart
(
    id        integer generated always as identity
        primary key,
    promocode varchar(255)
);

create table client
(
    id       integer generated always as identity
        primary key,
    name     varchar(255) not null,
    username varchar(255) not null,
    password varchar(255) not null,
    cart_id  integer      not null
        constraint client_cart_id_fk
            references katerniuksm.cart,
    email    varchar(255) not null
);

create table product_client
(
    id         integer generated always as identity
        primary key,
    id_product integer not null
        constraint product_client_products_id_fk
            references katerniuksm.product,
    id_cart    integer not null
        constraint product_client_cart_id_fk
            references katerniuksm.cart,
    count      integer not null
);

create table promocodes
(
    promocode varchar(255)
        primary key,
    percent   integer not null

);

