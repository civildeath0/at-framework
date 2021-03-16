# language: ru

#@Meetings
# noinspection NonAsciiCharacters
Функция: Автотесты в категории "Встречи"

  Предыстория: Создать встречу через API от имени главного помощника руководителя
    Пусть запросы будут посылаться от имени пользователя с логином "ATStudyFirstMainAssistant"
    Тогда он создал встречу, заполнив данными из таблицы:
      | Название         | Тестовая встреча SMARTOFF-T678 |
      | Место проведения | Адрес:"Москва"                 |
      | Дата начала      | завтра 23:30                   |
      | Дата окончания   | завтра 23:45                   |
      | Часовой пояс     | GMT+3                          |
      | Тип встречи      | Личная                         |
      | Участники        | Врунгель                       |
    Если статус-код последнего ответа равен 201
    То он сохраняет поле "id" из тела последнего ответа как "ID встречи"
    Затем совершает запрос PUT на адрес "/api/web/events/status/{ID встречи}" с параметрами из таблицы:
      | HEAD | accept       | application/json            |
      | HEAD | Content-Type | application/json-patch+json |
      | BODY | status       | Canceled                    |
    И статус-код последнего ответа равен 200
    Дано пользователь переходит на страницу "Авторизация"
    Тогда пользователь вводит логин "ATStudyFirstMainAssistant" в поле "Логин"
    И вводит пароль в поле "Пароль"
    Когда он нажимает на кнопку "Войти"
    То находится на странице "База контактов"
    Когда пользователь нажимает на элемент "[Рабочий график]"
    Тогда он находится на странице "Встречи"
    И нажимает на кнопку "Месяц"
    Тогда нажимает на строку в таблице, для которой выполняются условия:
      | Встреча           | Тестовая встреча SMARTOFF-T678 |
      | Состояние встречи | Отменено                       |
    Если он оказался на странице "Детали встречи"
    Затем он вводит произвольный текст из 5 слов в поле "Комментарий к названию"
    К тому же пользователь вводит произвольный текст от 8 до 12 слов в поле "Комментарий к месту проведения"
    Затем он вводит текст "Описание встречи" в поле "Описание"
    И нажимает на кнопку "Опубликовать"
    Затем закрывает уведомление с текстом "Статус встречи обновлен!"
    Тогда у элемента "Статус" отобразился текст "Направлено участникам"
    Затем проверяет ранее введенные значения в следующих полях:
      | Описание                       |
      | Комментарий к названию         |
      | Комментарий к месту проведения |

  @tmsLink=T678
    @severity=critical
    @issue=3128
    @DeleteMeetingAfter
  Структура сценария: SMARTOFF-T678 Редактирование отмененной встречи
    Дано пользователь переходит на страницу "Авторизация"
    И он вводит логин "ThirdFakeChief" в поле "Логин"
    Также он вводит пароль в поле "Пароль"
    И нажимает на кнопку "Войти"
    Тогда он оказался на странице "База контактов"
    Затем пользователь нажимает на элемент "[Рабочий график]"
    Тогда он находится на странице "Встречи"
    И нажимает на кнопку "Месяц"
    Затем подгружает строки таблицы, пока не найдется строка, удовлетворяющая условиям:
      | Встреча           | Тестовая встреча SMARTOFF-T678 |
      | Состояние встречи | Направлено участникам          |
    Тогда он нажимает на строку в таблице, для которой выполняются условия:
      | Встреча           | Тестовая встреча SMARTOFF-T678 |
      | Состояние встречи | Направлено участникам          |
    Затем он оказался на странице "Детали встречи"
    Тогда у элемента "Статус" отобразился текст "Направлено участникам"
    Затем проверяет ранее введенные значения в следующих полях:
      | Комментарий к названию         |
      | Комментарий к месту проведения |
    А у элемента "Поле описания встречи" появился текст "Описание встречи"
    И нажимает на кнопку "Выход"
    Тогда пользователь оказался на странице "Авторизация"
    И он вводит логин "ThirdFakeChiefAssistant" в поле "Логин"
    Также он вводит пароль в поле "Пароль"
    И нажимает на кнопку "Войти"
    Тогда он оказался на странице "База контактов"
    Затем пользователь нажимает на элемент "[Рабочий график]"
    Тогда он находится на странице "Встречи"
    И нажимает на кнопку "Месяц"
    Затем нажимает на кнопку "Обновить" пока в таблице отсутствует строка, удовлетворяющая условиям:
      | Встреча | Тестовая встреча SMARTOFF-T678 |
    Тогда нажимает на строку в таблице, для которой выполняются условия:
      | Встреча           | Тестовая встреча SMARTOFF-T678 |
      | Состояние встречи | Направлено участникам          |
    Затем он оказался на странице "Детали встречи"
    Тогда у элемента "Статус" отобразился текст "Направлено участникам"
    Затем проверяет ранее введенные значения в следующих полях:
      | Комментарий к названию         |
      | Комментарий к месту проведения |
    А у элемента "Поле описания встречи" появился текст "Описание встречи"
    И нажимает на кнопку "Выход"
    Тогда пользователь оказался на странице "Авторизация"
    И он вводит логин "ATStudyFirstChief" в поле "Логин"
    Также он вводит пароль в поле "Пароль"
    И нажимает на кнопку "Войти"
    Тогда он оказался на странице "База контактов"
    Затем пользователь нажимает на элемент "[Рабочий график]"
    Тогда он находится на странице "Встречи"
    И нажимает на кнопку "Месяц"
    Затем нажимает на кнопку "Обновить" пока в таблице отсутствует строка, удовлетворяющая условиям:
      | Встреча           | Тестовая встреча SMARTOFF-T678 |
      | Состояние встречи | Направлено участникам          |
    Тогда нажимает на строку в таблице, для которой выполняются условия:
      | Встреча           | Тестовая встреча SMARTOFF-T678 |
      | Состояние встречи | Направлено участникам          |
    Затем он оказался на странице "Детали встречи"
    Тогда у элемента "Статус" отобразился текст "Направлено участникам"
    Затем проверяет ранее введенные значения в следующих полях:
      | Комментарий к названию         |
      | Комментарий к месту проведения |
    А у элемента "Поле описания встречи" появился текст "Описание встречи"

    Примеры:
