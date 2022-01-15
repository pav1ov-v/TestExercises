drop table if exists tAccessToken;

create table tAccessToken
(
    TokenID    int primary key auto_increment,
    AuditID    int not null,
    UserID     int not null,
    ExpireDate datetime not null,
    foreign key (AuditID) references tAudit (AuditID) on delete cascade,
    foreign key (UserID) references tUser (UserID) on delete cascade
);

insert into tAccessToken (AuditID, UserID, ExpireDate)
values ('6', '3', '2010-01-01 23:59:59.999'),
       ('9', '1', '2023-01-01 12:52:00.000'),
       ('10', '1', '2021-08-01 12:52:00.000'),
       ('15', '5', '2022-02-01 12:52:00.000'),
       ('18', '6', '2022-04-01 12:52:00.000');

select *
from tAccessToken;