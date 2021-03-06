## language: ru
#
#@Administration
## noinspection NonAsciiCharacters
#Функция: Администрирования SmartOffice
#
#  Предыстория: Авторизоваться в роли администратора
#    Дано запросы будут посылаться от имени пользователя с логином "skynet"
#    Тогда он совершает запрос GET на адрес "/api/web/users/devices" с параметрами из таблицы:
#      | HEAD  | accept    | text/plain |
#      | QUERY | LimitFrom | 1          |
#      | QUERY | LimitSize | 1000       |
#    Если статус-код последнего ответа равен 200
#    То сохраняет поле "items" из тела последнего ответа как "Устройства"
#    Затем преобразует значение поля "isBlocked" в массиве "Устройства" по следующему принципу:
#      | true  | Разблокировать |
#      | false | Заблокировать  |
#
#    Дано пользователь переходит на страницу "Авторизация"
#    Тогда пользователь вводит логин "skynet" в поле "Логин"
#    И он вводит пароль в поле "Пароль"
#    Затем нажимает на кнопку "Войти"
#    Тогда он оказался на странице "База контактов"
#    Затем пользователь нажимает на элемент "[Администрирование]"
#    Тогда он находится на странице "Пользователи"
#    Затем нажимает на элемент "[Статусы устройств]"
#    И оказался на странице "Статусы устройств"
#
#  @tmsLink=T835
#  @severity=normal
#  @issue=3797
#  Сценарий: SMARTOFF-T835 Корректность отображения данных в таблице "Устройства пользователей"
#    Дано шапка таблицы имеет следующий вид:
#      | Логин | Device ID | Время | Модель | Версия iOS | Версия приложения | Действие |
#    Тогда пользователь листает таблицу, пока подгружаются новые записи
#    И сверяет значения в ячейках 20 строк таблицы с массивом "Устройства" по следующему принципу:
#      | Логин             | login       |
#      | Device ID         | deviceId    |
#      | Модель            | deviceModel |
#      | Версия iOS        | iosVersion  |
#      | Версия приложения | appVersion  |
