create schema if not exists katerniuksm;
create table if not exists katerniuksm.clients (id bigserial not null, email varchar(255) not null, login varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id));
create table if not exists katerniuksm.products (amount integer not null, price numeric(38,2) not null, id bigserial not null, name varchar(255) not null, primary key (id));
create table if not exists katerniuksm.products_carts (count integer not null, client_id bigint not null, id bigserial not null, product_id bigint not null, primary key (id));
create table if not exists katerniuksm.promocodes (percent integer not null, promocode varchar(255) not null, primary key (promocode));
alter table if exists katerniuksm.products_carts add constraint FKnjanbd4m9fxv8t5jcm3qoi1qt foreign key (client_id) references katerniuksm.clients;
alter table if exists katerniuksm.products_carts add constraint FKoypyla9kn9mxnenu0yaxau8d6 foreign key (product_id) references katerniuksm.products;
