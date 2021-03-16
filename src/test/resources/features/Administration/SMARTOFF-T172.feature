## language: ru
#
#@Administration
## noinspection NonAsciiCharacters
#Функция: Автотесты в категории "Администрирование"
#
#  Предыстория: Авторизоваться в роли администратора
#    Дано запросы будут посылаться от имени пользователя с логином "skynet"
#    Тогда он совершает запрос GET на адрес "/api/web/users" с параметрами из таблицы:
#      | HEAD  | accept    | text/plain |
#      | QUERY | LimitFrom | 1          |
#      | QUERY | LimitSize | 1000       |
#    Если статус-код последнего ответа равен 200
#    То сохраняет поле "items" из тела последнего ответа как "Пользователи"
#    Затем преобразует значение поля "status" в массиве "Пользователи" по следующему принципу:
#      | Active  | Активно       |
#      | Blocked | Заблокировано |
#
#    Дано пользователь переходит на страницу "Авторизация"
#    Тогда пользователь вводит логин "skynet" в поле "Логин"
#    И он вводит пароль в поле "Пароль"
#    Затем нажимает на кнопку "Войти"
#    Тогда он оказался на странице "База контактов"
#    Затем пользователь нажимает на элемент "[Администрирование]"
#    Тогда он оказался на странице "Пользователи"
#
#  @tmsLink=T172
#  @severity=critical
#  @issue=4279
#  Сценарий: SMARTOFF-T172 В списке пользователей неправильно отображаются сущности в колонках "должность" и "роль"
#    Дано пользователь листает таблицу, пока подгружаются новые записи
#    И сверяет значения в ячейках 20 строк таблицы с массивом "Пользователи" по следующему принципу:
#      | Должность | position |
#      | Роль      | roleName |
#      | Логин     | login    |
#      | Состояние | status   |