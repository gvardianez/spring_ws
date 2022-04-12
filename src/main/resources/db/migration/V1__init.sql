create table groups (id bigserial primary key, title varchar(255));
insert into groups (title)
values
('ABC-123');

create table students (id bigserial primary key, name varchar(255), age integer, group_id bigint references groups(id));
insert into students (name, age, group_id)
values
('Bob', 30, 1),
('Max', 32, 1);

create table categories
(
    id         bigserial primary key,
    title      varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into categories (title)
values ('Food'),
       ('Electronic');

create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       numeric(8, 2),
    category_id bigint references categories (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into products (title, price, category_id)
values ('Bread', 32.00, 1),
       ('Milk', 120.00, 1),
       ('Butter', 320.00, 1),
       ('Cheese', 500.00, 1);
