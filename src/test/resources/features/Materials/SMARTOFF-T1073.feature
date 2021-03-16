# language: ru

@All
@Materials
# noinspection NonAsciiCharacters, SpellCheckingInspection
Функция: Взаимодействие с разделом "Материалы"
  в роли главного помощника без созданной сущности

  Предыстория: Авторизоваться в роли помощника руководителя
    Допустим я авторизовался как помощник "Брусилов Алексей Алексеевич"
    И нажал на раздел "Материалы"
    Тогда я оказался на странице "Материалы"

  @tmsLink=T1073
  @severity=normal
  Сценарий: SMARTOFF-T1073 Применение фильтра без изменения критериев фильтрации
    Тогда был совершен Query запрос на адрес "/api/web/documents", со статусом 200
      | [Query параметр] | [Значение] |
      | Search           |            |
      | SortBy           | UpdatedAt  |
      | SortDesc         | true       |
      | LimitFrom        | 1          |
      | LimitSize        | 20         |
    Когда я нажимаю кнопку "Фильтр"
    И очищаю консоль браузера
    И я нажимаю кнопку "Применить"
    То был совершен Query запрос на адрес "/api/web/documents", со статусом 200
      | [Query параметр] | [Значение] |
      | Search           |            |
      | SortBy           | UpdatedAt  |
      | SortDesc         | true       |
      | LimitFrom        | 1          |
      | LimitSize        | 20         |
