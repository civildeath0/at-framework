# language: ru

@All
@Meetings
# noinspection NonAsciiCharacters, SpellCheckingInspection
Функция: Редактирование нового участника

  Предыстория: Создать встречу и авторизоваться
    Допустим помощник "Брусилов Алексей Алексеевич" создал сущность "Встреча"
      | [Название поля]        | [Значение]                       |
      | Заголовок              | AT 1114 редактирование участника |
      | Место проведения       | Спрингфилд                       |
      | Описание               | <p>Test</p>                      |
      | Короткое описание      | Test                             |
      | Дата и время начала    | сегодня 8:00                     |
      | Дата и время окончания | сегодня 8:15                     |
      | Часовой пояс           | GMT+3                            |
      | Тип                    | Внутренняя                       |
    И я авторизовался как помощник "Брусилов Алексей Алексеевич"
    Затем нажал на раздел "Рабочий график"
    Тогда я оказался на странице "Встречи"

  @tmsLink=T1114
  Сценарий: SMARTOFF-T1114 Редактирование нового участника во встрече
    Дано нажимаю на строку в таблице "Встречи"
      | [Встреча]                        | [Состояние встречи] |
      | AT 1114 редактирование участника | Черновик            |
    Затем оказался на странице "Детали встречи"
    И нажимаю кнопку "Создать нового"
    И жду 1 секунд
    Затем проверяю, что у полей есть соответствующие предупреждения:
      | [Название поля] | [Текст предупреждения] |
      | Фамилия         | Обязательное поле      |
      | Имя             | Обязательное поле      |
    Тогда ввожу текст в поля:
      | [Название поля] | [Текст]  |
      | Фамилия         | Тестовый |
      | Имя             | Два      |
    И нажимаю кнопку "Добавить контакт"
    Затем в таблице "Участники" присутствует строка
      | [Фамилия Имя Отчество] |
      | Тестовый Два           |
    Тогда нажимаю на редактирование у строки из таблицы "Участники"
      | [Фамилия Имя Отчество] |
      | Тестовый Два           |
    И жду 1 секунд
    Тогда заменяю текст в полях
      | [Название поля] | [Текст]  |
      | Фамилия         | Изменено |
      | Имя             | Да       |
    И нажимаю кнопку "Добавить контакт"
