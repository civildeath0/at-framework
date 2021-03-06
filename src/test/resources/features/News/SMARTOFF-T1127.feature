#language: ru

@All
@Contacts
# noinspection NonAsciiCharacters, SpellCheckingInspection
Функция: Сохранение параметров фильрации

  Предыстория: Создать задачу и авторизоваться
    Допустим помощник "Брусилов Алексей Алексеевич" создал сущность "Новость"
      | [Название поля]         | [Значение]                   |
      | Заголовок               | АТ1127 10 дней до НГ         |
      | Подзаголовок            | Всего 10 дней до Нового Года |
      | Рубрика                 | Загрузка                     |
      | Показать всем           | Да                           |
      | Новость дня             | Да                           |
      | Компания                | Space                        |
      | Текст новости           | Ровно 240 часов              |
      | Дата и время публикации | Сегодня 18:00                |
    И я авторизовался как помощник "Брусилов Алексей Алексеевич"
    Затем нажал на раздел "Новости"
    И оказался на странице "Новости и обзоры"

  @tmsLink=T1127
  Сценарий: SMARTOFF-1127 Сохранение параметров фильтрации после возрата к списку
    Дано был совершен Query запрос на адрес "api/web/news/", со статусом 200
      | [Query параметр] | [Значение]  |
      | Search           |             |
      | SortBy           | publishedAt |
      | SortDesc         | true        |
      | Year             | текущий     |
      | Month            | текущий     |
      | LimitFrom        | 1           |
      | LimitSize        | 20          |
    Затем я нажимаю кнопку "Фильтр"
    И выбираю значение "Черновик" в поле "Статус"
    Затем нажимаю кнопку "Применить"
    Тогда был совершен Query запрос на адрес "api/web/news/", со статусом 200
      | [Query параметр] | [Значение]  |
      | Search           |             |
      | SortBy           | publishedAt |
      | SortDesc         | true        |
      | Year             | текущий     |
      | Month            | текущий     |
      | Status           | Draft       |
      | LimitFrom        | 1           |
      | LimitSize        | 20          |
    И я нажимаю на строку в таблице "Новости"
      | [Заголовок]          | [Рубрика]                |
      | АТ1127 10 дней до НГ | Загрузка новосей из docx |
    Тогда оказался на странице "Детали PressHighlight"
    Затем я нажимаю кнопку "Назад к списку"
    И я оказался на странице "Новости и обзоры"
    Также был совершен Query запрос на адрес "api/web/news/", со статусом 200
      | [Query параметр] | [Значение]  |
      | Search           |             |
      | SortBy           | publishedAt |
      | SortDesc         | true        |
      | Year             | текущий     |
      | Month            | текущий     |
      | Status           | Draft       |
      | LimitFrom        | 1           |
      | LimitSize        | 20          |