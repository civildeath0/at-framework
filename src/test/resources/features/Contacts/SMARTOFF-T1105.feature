#language: ru

@All
@Contacts
# noinspection NonAsciiCharacters, SpellCheckingInspection
Функция: Фильтрация контактов по месяцу

  Предыстория: Авторизоваться в роли помощника руководителя
    Допустим я авторизовался как помощник "Брусилов Алексей Алексеевич"
    Затем нажал на раздел "База контактов"
    И оказался на странице "Контакты"

  @tmsLink=T1105
  Сценарий: SMARTOFF-1105 Фильтрация контактов
    Дано был совершен Query запрос на адрес "/api/web/contacts", со статусом 200
      | [Query параметр] | [Значение] |
      | Search           |            |
      | LimitFrom        | 1          |
      | LimitSize        | 20         |
    Когда я нажимаю кнопку "Фильтр"
    Затем выбираю значение "Январь" в поле "Дата рождения (месяц) от"
    И выбираю значение "Январь" в поле "Дата рождения (месяц) до"
    И очищаю консоль браузера
    Затем я нажимаю кнопку "Применить"
    Тогда был совершен Query запрос на адрес "/api/web/contacts", со статусом 200
      | [Query параметр] | [Значение] |
      | Search           |            |
      | SortBy           | birthDate  |
      | BirthMonthFrom   | 1          |
      | BirthMonthTo     | 1          |
      | LimitFrom        | 1          |
      | LimitSize        | 20         |