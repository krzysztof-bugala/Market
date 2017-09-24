drop table if exists standard_product;

drop sequence if exists standard_product_sequence;

create sequence standard_product_sequence start with 1;

create table standard_product (product_id number default standard_product_sequence.nextval not null,
            barcode number unique not null, name varchar2, description clob, price number not null,
            special_price number not null, price_Threshold number not null);
ALTER TABLE standard_product ADD PRIMARY KEY (product_id);
insert into standard_product(barcode, name, description, price, special_price, price_Threshold) values (24732478274, 'A', 'This is basicProduct A', 40.00, 38.13, 70);
insert into standard_product(barcode, name, description, price, special_price, price_Threshold) values (32487984982, 'B', 'This is basicProduct B', 10.00, 8.60, 15);
insert into standard_product(barcode, name, description, price, special_price, price_Threshold) values (7732478274, 'C', 'This is basicProduct C', 30.00, 25.00, 60);
insert into standard_product(barcode, name, description, price, special_price, price_Threshold) values (84732478274, 'D', 'This is basicProduct D', 25.00, 24.00, 40);
insert into standard_product(barcode, name, description, price, special_price, price_Threshold) values (847324782745, 'AB', 'This is compositeProduct AB', 48.00, 46.00, 60);
insert into standard_product(barcode, name, description, price, special_price, price_Threshold) values (847324782746, 'CD', 'This is compositeProduct CD', 52.00, 50.00, 40);

drop table if exists composite_product;

create table composite_product(product_id number not null, first_product varchar2 not null, second_product varchar2  not null);
ALTER TABLE composite_product ADD PRIMARY KEY (product_id);
alter table composite_product add foreign key (product_id)  references standard_product (product_id);
alter table composite_product add foreign key (first_product) references standard_product (product_id);
alter table composite_product add foreign key (second_product) references standard_product (product_id);
insert into composite_product values (5, 1, 2);
insert into composite_product values (6, 3, 4);






