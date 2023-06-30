
create schema if not exists katerniuksm;

create table if not exists product
(
    id     integer generated always as identity
        primary key,
    name   varchar(255) not null,
    price  numeric      not null,
    amount integer          not null
);

create table if not exists cart
(
    id        integer generated always as identity
        primary key,
    promocode varchar(255)
);

create table if not exists client
(
    id       integer generated always as identity
        primary key,
    name     varchar(255) not null,
    username varchar(255) not null,
    password varchar(255) not null,
    cart_id  integer      not null
        constraint client_cart_id_fk
            references cart,
    email    varchar(255) not null
);

create table if not exists product_client
(
    id         integer generated always as identity
        primary key,
    id_product integer not null
        constraint product_client_products_id_fk
            references product,
    id_cart    integer not null
        constraint product_client_cart_id_fk
            references cart,
    count      integer not null
);

 create table if not exists promocodes
(
    promocode varchar(255)
        primary key,
    percent   integer not null

);

