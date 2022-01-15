/*Найти первых четырех пользователей, которые чаще всего неверно вводят пароль.*/

select UserID, Name, Mail
from tUser
where UserID in (
    select UserID
    from tAudit
    where ActionType = 5
    group by UserID
    order by count(ActionType) desc
    limit 4
    );