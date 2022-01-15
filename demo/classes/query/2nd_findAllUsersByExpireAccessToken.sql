/*Найти всех пользователей, у которых просрочился токен доступа.*/

select UserID, Name, Mail
from tUser
where UserID in (
    select distinct UserID
    from tAccessToken
    where ExpireDate < now()
    );