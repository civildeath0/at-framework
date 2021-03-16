# language: ru

@Meetings
# noinspection NonAsciiCharacters
Функция: Автотесты в категории "Встречи"

  Предыстория: Авторизоваться в роли помощника руководителя
    Дано запросы будут посылаться от имени пользователя с логином "ATStudyFirstMainAssistant"
    Тогда пользователь создал встречу, заполнив данными из таблицы:
      | Название         | Тест SMARTOFF-T23                      |
      | Место проведения | Адрес:"Москва", Комментарий:"<8 слов>" |
      | Дата начала      | сегодня 21:00                          |
      | Дата окончания   | сегодня 21:15                          |
      | Часовой пояс     | GMT+3                                  |
      | Тип встречи      | Личная                                 |
      | Описание         | <20 слов>                              |
      | Агенда           | <5 слов>                               |
      | Участники        | Врунгель                               |
    Если статус-код последнего ответа равен 201
    То он сохраняет поле "id" из тела последнего ответа как "ID встречи"
    Также он совершает запрос PUT на адрес "/api/web/events/status/{ID встречи}" с параметрами из таблицы:
      | HEAD | accept       | application/json            |
      | BODY | Content-Type | application/json-patch+json |
      | BODY | status       | Directed                    |
    И статус-код последнего ответа равен 200

    Дано запросы будут посылаться от имени пользователя с логином "ThirdFakeChiefAssistant"
    Также он совершает запрос PUT на адрес "/api/web/events/participantstatus/{ID встречи}" с параметрами из таблицы:
      | HEAD | accept            | application/json            |
      | BODY | Content-Type      | application/json-patch+json |
      | BODY | participantStatus | Confirmed                   |
    И статус-код последнего ответа равен 200
    Затем запросы будут посылаться от имени пользователя с логином "ThirdFakeChiefAssistant"

    Дано пользователь переходит на страницу "Авторизация"
    Тогда пользователь вводит логин "ATStudyFirstMainAssistant" в поле "Логин"
    И вводит пароль в поле "Пароль"
    Когда он нажимает на кнопку "Войти"
    То находится на странице "База контактов"
    Когда пользователь нажимает на элемент "[Рабочий график]"
    Тогда он находится на странице "Встречи"

  @tmsLink=T23
  @severity=blocker
  @DeleteMeetingAfter
  Сценарий: SMARTOFF-T23 Редактирование подтвержденной участниками встречи
    Затем листает таблицу, пока подгружаются новые записи
    И нажимает на строку в таблице, для которой выполняются условия:
      | Встреча           | Тест SMARTOFF-T23     |
      | Состояние встречи | Направлено участникам |
    Тогда он находится на странице "Детали встречи"
    Затем очищает текст в поле "Название"
    Также очищает текст в поле "Описание" с клавиатуры
    Тогда он нажимает на элемент "Календарь даты начала"
    И выбирает завтрашнюю дату в календаре
    Когда пользователь нажимает на элемент "Время начала"
    То он выбирает "9:15" из выпадающего списка
    Затем он нажимает на элемент "Календарь даты окончания"
    И он выбирает завтрашнюю дату в календаре
    Когда пользователь нажимает на элемент "Время окончания"
    То он выбирает "9:30" из выпадающего списка
    И вводит текст "Отредактированная SMARTOFF-T23" в поле "Название"
    И вводит от 3 до 5 параграфов случайного текста в поле "Описание"
    То пользователь нажимает на кнопку "Сохранить"
    И закрывает уведомление с текстом "Встреча обновлена!"
    Затем он нажимает на кнопку "Выход"
    Тогда пользователь оказался на странице "Авторизация"
    И он вводит логин "ThirdFakeChief" в поле "Логин"
    Также он вводит пароль в поле "Пароль"
    И нажимает на кнопку "Войти"
    Тогда он оказался на странице "База контактов"
    Затем пользователь нажимает на элемент "[Рабочий график]"
    Тогда он находится на странице "Встречи"
    Тогда нажимает на кнопку "Месяц"
    Затем нажимает на кнопку "Обновить" пока в таблице отсутствует строка, удовлетворяющая условиям:
      | Встреча | Отредактированная SMARTOFF-T23 |
    Затем он нажимает на строку в таблице, для которой выполняются условия:
      | Встреча | Отредактированная SMARTOFF-T23 |
    Тогда он находится на странице "Детали встречи"
    И проверяет значения, записанные в полях:
      | Название         | Отредактированная SMARTOFF-T23 |
      | Дата начала      | завтра                         |
      | Время начала     | 09:15                          |
      | Дата окончания   | завтра                         |
      | Время окончания  | 09:30                          |
      | Место проведения | Москва                         |
      | Тип встречи      | Личная                         |
      | Часовой пояс     | GMT+3                          |
    Затем он нажимает на кнопку "Выход"
    Тогда пользователь оказался на странице "Авторизация"
    И он вводит логин "ThirdFakeChiefAssistant" в поле "Логин"
    Также он вводит пароль в поле "Пароль"
    И нажимает на кнопку "Войти"
    Тогда он оказался на странице "База контактов"
    Затем пользователь нажимает на элемент "[Рабочий график]"
    Тогда он находится на странице "Встречи"
    Тогда нажимает на кнопку "Месяц"
    И листает таблицу, пока подгружаются новые записи
    Затем он нажимает на строку в таблице, для которой выполняются условия:
      | Встреча | Отредактированная SMARTOFF-T23 |
    Тогда он находится на странице "Детали встречи"
    И проверяет значения, записанные в полях:
      | Название         | Отредактированная SMARTOFF-T23 |
      | Дата начала      | завтра                         |
      | Время начала     | 09:15                          |
      | Дата окончания   | завтра                         |
      | Время окончания  | 09:30                          |
      | Место проведения | Москва                         |
      | Тип встречи      | Личная                         |
      | Часовой пояс     | GMT+3                          |
    Затем он нажимает на кнопку "Выход"
    Тогда пользователь оказался на странице "Авторизация"
    И он вводит логин "ATStudyFirstMainAssistant" в поле "Логин"
    Также он вводит пароль в поле "Пароль"
    И нажимает на кнопку "Войти"
    Тогда он оказался на странице "База контактов"
    Затем пользователь нажимает на элемент "[Рабочий график]"
    Тогда он находится на странице "Встречи"
    Тогда нажимает на кнопку "Месяц"
    И листает таблицу, пока подгружаются новые записи
    Затем он нажимает на строку в таблице, для которой выполняются условия:
      | Встреча | Отредактированная SMARTOFF-T23 |
    Тогда он находится на странице "Детали встречи"
    И проверяет значения, записанные в полях:
      | Название         | Отредактированная SMARTOFF-T23 |
      | Дата начала      | завтра                         |
      | Время начала     | 09:15                          |
      | Дата окончания   | завтра                         |
      | Время окончания  | 09:30                          |
      | Место проведения | Москва                         |
      | Тип встречи      | Личная                         |
      | Часовой пояс     | GMT+3                          |
