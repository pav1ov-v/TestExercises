/*Найти всех пользователей, которые зарегистрировались, но ни разу не заходили в систему.*/

select UserID, Name, Mail
from tUser
where UserID not in (
    select distinct UserID
    from tAudit
    where ActionType <> 1
      and ActionType = 3
    );