## language: ru
#
#@Airports
## noinspection NonAsciiCharacters
#Функция: Автотесты в категории "Аэропорты"
#
#  Предыстория: Авторизоваться в роли помощника руководителя
#    Дано запросы будут посылаться от имени пользователя с логином "ATStudyFirstMainAssistant"
#    Тогда пользователь совершает запрос POST на адрес "/api/web/events/flights/airports" с параметрами из таблицы:
#      | HEAD | accept       | text/plain                  |
#      | HEAD | Content-Type | application/json-patch+json |
#      | NODE | //////////// | airport                     |
#    Если статус-код последнего ответа равен 201
#    То он сохраняет поле "id" из тела последнего ответа как "ID аэропорта"
#
#    Дано пользователь переходит на страницу "Авторизация"
#    Тогда пользователь вводит логин "ATStudyFirstMainAssistant" в поле "Логин"
#    К тому же запросы будут посылаться от имени пользователя с логином "ATStudyFirstMainAssistant"
#    И вводит пароль в поле "Пароль"
#    Когда он нажимает на кнопку "Войти"
#    То находится на странице "База контактов"
#    Затем он нажимает на кнопку "[Справочники]"
#    К тому же нажимает на кнопку "[Аэропорты]"
#    Тогда он оказался на странице "Аэропорты"
#
#  @tmsLink=T529
#  @severity=normal
#  @DeleteAirportAfter
#  Сценарий: SMARTOFF-T529 Редактирование существующего аэропорта
#    Дано пользователь вводит текст "Анд" в поле "Поиск"
#    Затем он нажимает на строку в таблице, для которой выполняются условия:
#      | Наименование | Андропово |
#    И оказался на странице "Детали аэропорта"
#    Тогда пользователь очищает текст в поле "Название:"
#    Затем вводит текст "Плюшкин" в поле "Название:"
#    И нажимает на кнопку "Сохранить"
#    Затем закрывает уведомление с текстом "Аэропорт обновлен!"
#    А в поле "Название:" записано значение "Плюшкин"
