# language: ru

@All
@Assignments
# noinspection NonAsciiCharacters
Функция: Автотесты в категории "Задачи"

  Предыстория: Авторизоваться в роли главного помощника руководителя
    Допустим я авторизовался как помощник "Брусилов Алексей Алексеевич"
    Затем нажал на раздел "Задачи"
    Тогда оказался на странице "Задачи"

  @tmsLink=T1074
  @severity=normal
  Сценарий: SMARTOFF-T1074 Применение фильтра без изменения критериев фильтрации
    Тогда был совершен Query запрос на адрес "api/web/assignments", со статусом 200
      | [Query параметр] | [Значение] |
      | Search           |            |
      | SortBy           | isExpired  |
      | SortDesc         | true       |
      | LimitFrom        | 1          |
      | LimitSize        | 20         |
    Затем я нажимаю кнопку "Фильтр"
    И очищаю консоль браузера
    Затем нажимаю кнопку "Применить"
    Тогда был совершен Query запрос на адрес "api/web/assignments", со статусом 200
      | [Query параметр] | [Значение] |
      | Search           |            |
      | SortBy           | isExpired  |
      | SortDesc         | true       |
      | LimitFrom        | 1          |
      | LimitSize        | 20         |
