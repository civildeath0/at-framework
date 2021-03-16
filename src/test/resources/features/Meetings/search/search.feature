#language: ru

@Drafts
#noinspection NonAsciiCharacters
Функция: Автотесты в категории "Встречи"

  Предыстория: Создать встречу и авторизоваться
    Допустим помощник "Брусилов Алексей Алексеевич" создал сущность "Встреча"
      | [Название поля]        | [Значение]          |
      | Заголовок              | AT Поиск встречи    |
      | Место проведения       | Москва              |
      | Дата и время начала    | сегодня 8:00        |
      | Дата и время окончания | сегодня 8:15        |
      | Часовой пояс           | GMT+3               |
      | Тип                    | Внутренняя          |
    И я авторизовался как помощник "Брусилов Алексей Алексеевич"
    Затем нажал на раздел "Рабочий график"
    Тогда я оказался на странице "Встречи"

  @tmsLink=T522
  @severity=normal
  Сценарий: SMARTOFF-T27 Быстрый поиск
    Допустим в таблице "Встречи" присутствует строка
      | [Встреча]           | [Место проведения] |
      | АТ Создание встречи | Москва             |
    Тогда я ввожу текст "вст" в поле "Поиск"
    И в таблице "Встречи" присутствует строка
      | [Встреча]           | [Место проведения] |
      | АТ Создание встречи | Москва             |
    Затем заменяю текст в полях