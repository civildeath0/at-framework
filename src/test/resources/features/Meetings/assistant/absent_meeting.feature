# language: ru

@All
@Events
@Meetings
# noinspection NonAsciiCharacters, SpellCheckingInspection
Функция: Автотесты в категории "Встречи"

  Предыстория: Авторизоваться в роли помощника руководителя
    Допустим я авторизовался как помощник "Брусилов Алексей Алексеевич"
    И оказался на странице "Контакты"
    Затем нажал на раздел "Рабочий график"
    И оказался на странице "Встречи"

  @tmsLink=T33
  @severity=blocker
  Сценарий: SMARTOFF-T33 - Создание встречи
    Когда я нажимаю кнопку "Добавить встречу"
    То оказался на странице "Детали встречи"
    Затем ввожу текст в поля:
      | [Название поля]  | [Текст]             |
      | Название         | АТ Создание встречи |
      | Место проведения | Москва              |
    И выбираю значение в следующих полях:
      | [Название поля] | [Значение] |
      | Дата начала     | сегодня    |
      | Дата окончания  | сегодня    |
      | Время начала    | 6:00       |
      | Время окончания | 6:15       |
      | Тип             | Внешняя    |
      | Часовой пояс    | GMT+3      |
    Затем нажимаю кнопку "Сохранить"
    Тогда сущность успешно создалась
    К тому же на странице присутствует уведомление с текстом "Встреча создана!"
    И кнопки активны
      | Опубликовать |
      | Удалить      |
    А кнопка "Сохранить" неактивна
#    И закрываю уведомление с текстом "Встреча создана!"
#    Когда нажимаю кнопку "Назад к списку"
#    Тогда я оказался на странице "Встречи"
#    И в таблице "Встречи" присутствует строка
#      | [Встреча]           | [Состояние встречи] |
#      | АТ Создание встречи | Черновик            |

  @tmsLink=T522
  @severity=normal
  Сценарий: SMARTOFF-T522 Появление предупреждения о несохраненных изменениях при создании встречи
    Дано я нажимаю кнопку "Добавить встречу"
    Тогда оказался на странице "Детали встречи"
    Затем я нажимаю кнопку "Назад к списку"
    Тогда оказался на странице "Встречи"
    И нажимаю кнопку "Добавить встречу"
    Тогда оказался на странице "Детали встречи"
    Затем ввожу случайный текст в поле "Название"
    И нажимаю кнопку "Назад к списку"
    Тогда на странице появляется предупреждение
    И в заглавии этого предупреждения написано "Форма не сохранена"
    А в содержании этого предупреждения написано "Покинуть страницу без сохранения изменений?"
    Тогда я нажимаю кнопку "Нет" у этого предупреждения

  @tmsLink=T1055
  @severity=normal
  Сценарий: SMARTOFF-T1055 Применение фильтра без изменения критериев фильтрации
    Тогда был совершен Query запрос на адрес "/api/web/events", со статусом 200
      | [Query параметр] | [Значение] |
      | Search           |            |
      | SortBy           | dateStart  |
      | SortDesc         | true       |
      | DateFrom         | Сегодня    |
      | DateTo           | Сегодня    |
      | LimitFrom        | 1          |
      | LimitSize        | 20         |
    Затем я нажимаю кнопку "Фильтр"
    И очищаю консоль браузера
    К тому же нажимаю кнопку "Применить"
    Тогда был совершен Query запрос на адрес "/api/web/events", со статусом 200
      | [Query параметр] | [Значение] |
      | Search           |            |
      | SortBy           | dateStart  |
      | SortDesc         | true       |
      | DateFrom         | Сегодня    |
      | DateTo           | Сегодня    |
      | LimitFrom        | 1          |
      | LimitSize        | 20         |

  @tmsLink=T1078
  @severity=blocker
  Сценарий: SMARTOFF-T1078 Создание встречи с пермишеном eventPrivate
    Допустим я нажимаю кнопку "Добавить встречу"
    И нахожусь на странице "Детали встречи"
    И проверяю, что в поле "Тип" доступны для выбора только значения:
      | Внешняя    |
      | Внутренняя |
      | Личная     |