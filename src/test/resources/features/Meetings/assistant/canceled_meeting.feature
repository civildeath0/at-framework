# language: ru

@All
@Meetings
@Deleting
# noinspection NonAsciiCharacters, SpellCheckingInspection
Функционал: Удаление встречи

  Предыстория: Создать встречу и авторизоваться
    Допустим помощник "Брусилов Алексей Алексеевич" создал сущность "Встреча"
      | [Название поля]        | [Значение]                     |
      | Заголовок              | AT Удаление отмененной встречи |
      | Место проведения       | Спрингфилд                     |
      | Дата и время начала    | сегодня 8:00                   |
      | Дата и время окончания | сегодня 8:15                   |
      | Часовой пояс           | GMT+3                          |
      | Тип                    | Внутренняя                     |
    И обновил на статус "Отменено"
    И я авторизовался как помощник "Брусилов Алексей Алексеевич"
    Затем нажал на раздел "Рабочий график"
    Тогда я оказался на странице "Встречи"

  @tmsLink=T1096
  @severity=normal
  @Regression
  Сценарий: SMARTOFF-T1096 Удаление встречи в статусе "Отменено" из списка встреч
    Когда я нажимаю на крестик у строки из таблицы "Встречи"
      | [Встреча]                      | [Состояние встречи] |
      | AT Удаление отмененной встречи | Отменено            |
    Тогда на странице появляется предупреждение
    И в заглавии этого предупреждения написано "Удаление события"
    А в содержании этого предупреждения написано:
      | Вы уверены, что хотите удалить событие "AT Удаление отмененной встречи"? |
    Когда я нажимаю кнопку "Удалить" у этого предупреждения
    И закрываю уведомление с текстом "Встреча удалена!"
    И в таблице "Встречи" отсутствует строка:
      | [Встреча]           | [Состояние встречи] |
      | AT Удаление встречи | Отменено            |

  @tmsLink=T1097
  @severity=normal
  @Regression
  Сценарий: SMARTOFF-T1097 Удаление встречи в статусе "Отменено" из карточки
    Дано я нажимаю на строку в таблице "Встречи"
      | [Встреча]                      | [Состояние встречи] |
      | AT Удаление отмененной встречи | Отменено            |
    То оказался на странице "Детали встречи"
    И нажимаю кнопку "Удалить"
    Тогда на странице появляется предупреждение
    И в заглавии этого предупреждения написано "Удаление встречи"
    И в содержании этого предупреждения написано:
      | Вы уверены, что хотите удалить встречу "AT Удаление отмененной встречи"? |
    Тогда я нажимаю кнопку "Удалить" у этого предупреждения
    И закрываю уведомление с текстом "Встреча удалена!"
    Тогда я оказался на странице "Встречи"
    И в таблице "Встречи" отсутствует строка:
      | [Встреча]                      | [Состояние встречи] |
      | AT Удаление отмененной встречи | Отменено            |
