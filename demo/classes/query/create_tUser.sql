drop table if exists tUser;

create table tUser
(
    UserID int primary key auto_increment,
    Name   varchar(50) not null,
    Pass   varchar(50) not null,
    Mail   varchar(255) null
);

insert into tUser (Name, Pass, Mail)
values ('Petr', '123', 'Petr@dot.com'),
       ('Basyl', 'ABC', 'XXX@YYY.com'),
       ('Vasiliy', '321', 'Vas@my.ru'),
       ('Ivan', '010', 'Vans@mail.ru'),
       ('Max', 'min', 'mix@gmail.com'),
       ('Root', '@#$', 'admin@factory.com'),
       ('User', '111', 'user@yandex.ru');

select *
from tUser;