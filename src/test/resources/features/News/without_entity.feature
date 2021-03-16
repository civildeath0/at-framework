# language: ru

@All
@News
# noinspection NonAsciiCharacters, SpellCheckingInspection
Функционал: Взаимодействие с разделом "Новости"
  в роли помощника СМИ без созданной сущности

  Предыстория: Авторизоваться в роли главного помощника
    Допустим я авторизовался как помощник "Брусилов Алексей Алексеевич"
    Затем нажал на раздел "Новости"
    И оказался на странице "Новости и обзоры"

  @tmsLink=T1057
  @severity=normal
  Сценарий: SMARTOFF-T1057 Применение фильтра без изменения критериев фильтрации для Presshighlights
    Тогда был совершен Query запрос на адрес "/api/web/news/", со статусом 200
      | [Query параметр] | [Значение]  |
      | Search           |             |
      | SortBy           | publishedAt |
      | SortDesc         | true        |
      | Year             | Текущий     |
      | Month            | Текущий     |
      | LimitFrom        | 1           |
      | LimitSize        | 20          |
    Затем выбираю отображение "Presshighlights"
    Когда я нажимаю кнопку "Фильтр"
    И очищаю консоль браузера
    Тогда на странице появилось всплывающее окно
    Затем я нажимаю кнопку "Применить"
    Тогда был совершен Query запрос на адрес "/api/web/news/", со статусом 200
      | [Query параметр] | [Значение]  |
      | Search           |             |
      | SortBy           | publishedAt |
      | SortDesc         | true        |
      | Year             | Текущий     |
      | Month            | Текущий     |
      | LimitFrom        | 1           |
      | LimitSize        | 20          |

  @tmsLink=T1059
    @severity=normal
  Структура сценария: SMARTOFF-T1059 Применение фильтра без изменения критериев фильтрации для Digests
    Тогда был совершен Query запрос на адрес "/api/web/news/", со статусом 200
      | [Query параметр] | [Значение]  |
      | Search           |             |
      | SortBy           | publishedAt |
      | SortDesc         | true        |
      | Year             | Текущий     |
      | Month            | Текущий     |
      | LimitFrom        | 1           |
      | LimitSize        | 20          |
    Затем выбираю отображение <Category>
    Тогда был совершен Query запрос на адрес "/api/web/digests/", со статусом 200
      | [Query параметр] | [Значение]     |
      | Search           |                |
      | SortBy           | publishedAt    |
      | SortDesc         | true           |
      | Year             | Текущий        |
      | Month            | Текущий        |
      | CategoryName     | <CategoryName> |
      | LimitFrom        | 1              |
      | LimitSize        | 20             |
    Затем я нажимаю кнопку "Фильтр"
    И очищаю консоль браузера
    Затем я нажимаю кнопку "Применить"
    Тогда был совершен Query запрос на адрес "/api/web/digests/", со статусом 200
      | [Query параметр] | [Значение]     |
      | Search           |                |
      | SortBy           | publishedAt    |
      | SortDesc         | true           |
      | Year             | Текущий        |
      | Month            | Текущий        |
      | CategoryName     | <CategoryName> |
      | LimitFrom        | 1              |
      | LimitSize        | 20             |

    Примеры: Разделы digests'ов
      | Category              | CategoryName |
      | "Картина дня"         | DayPicture   |
      | "Утренний мониторинг" | Morning      |
      | "Новости IT"          | Foreign      |
