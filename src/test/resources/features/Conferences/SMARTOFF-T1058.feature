# language: ru

@All
@Conferences
# noinspection NonAsciiCharacters, SpellCheckingInspection
Функция: Взаимодействие с разделом "Мероприятия"
  в роли главного помощника без созданной сущности

  Предыстория: Авторизоваться в роли помощника руководителя
    Допустим я авторизовался как помощник "Брусилов Алексей Алексеевич"
    И нажал на раздел "Рабочий график"
    Затем выбрал подраздел "Мероприятия"
    И оказался на странице "Мероприятия"

  @tmsLink=T1058
  @severity=normal
  Сценарий: SMARTOFF-T1058 Применение фильтра без изменения критериев фильтрации
    Тогда был совершен Query запрос на адрес "/api/web/events/conferences", со статусом 200
      | [Query параметр] | [Значение]    |
      | Search           |               |
      | SortBy           | dateStart     |
      | SortDesc         | true          |
      | DateFrom         | Начало месяца |
      | DateTo           | Конец месяца  |
      | LimitFrom        | 1             |
      | LimitSize        | 20            |
    Когда я нажимаю кнопку "Фильтр"
    И очищаю консоль браузера
    Затем я нажимаю кнопку "Применить"
    Тогда был совершен Query запрос на адрес "/api/web/events/conferences", со статусом 200
      | [Query параметр] | [Значение]    |
      | Search           |               |
      | SortBy           | dateStart     |
      | SortDesc         | true          |
      | DateFrom         | Начало месяца |
      | DateTo           | Конец месяца  |
      | LimitFrom        | 1             |
      | LimitSize        | 20            |
