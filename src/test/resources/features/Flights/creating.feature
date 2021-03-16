# language: ru

@All
@Flights
# noinspection NonAsciiCharacters
Функция: Черновые автотесты

  Предыстория: Авторизоваться в роли помощника руководителя
    Допустим я авторизовался как помощник "Брусилов Алексей Алексеевич"
    Затем нажал на раздел "Рабочий график"
    И оказался на странице "Встречи"

  @tmsLink=T38
  Сценарий: SMARTOFF-T38 Создание перелета
    Когда я нажимаю кнопку "Добавить перелет"
    Тогда я оказался на странице "Детали перелета"
    Затем ввожу текст в поля:
      | [Название поля] | [Текст]              |
      | Название        | АТ Создание перелета |
      | Рейс            | Тестовый рейс        |
    И выбираю значение в следующих полях:
      | [Название поля]       | [Значение] |
      | Дата вылета           | сегодня    |
      | Дата прибытия         | сегодня    |
      | Время вылета          | 6:00       |
      | Время прибытия        | 8:15       |
      | Откуда                | ТЕСТ 1     |
      | Куда                  | ТЕСТ 2     |
      | Часовой пояс вылета   | GMT+3      |
      | Часовой пояс прибытия | GMT+3      |
    Затем очищаю консоль браузера
    И нажимаю кнопку "Сохранить"
    Затем закрываю уведомление с текстом "Перелет создан!"
    Тогда кнопки активны
      | Опубликовать |
      | Удалить      |
    А кнопка "Сохранить" неактивна
    К тому же сущность успешно создалась


#  @DeleteFlight1
#  Сценарий: Удаление перелета из списка
#    Дано пользователь переходит на страницу "Авторизация"
#    Тогда пользователь вводит логин "ATStudyFirstMainAssistant" в поле "Логин"
#    И он вводит пароль в поле "Пароль"
#    Затем нажимает на кнопку "Войти"
#    Тогда он оказался на странице "База контактов"
#    * пользователь находится на странице "База контактов"
#    * пользователь нажимает на кнопку "[Рабочий график]"
#    * пользователь находится на странице "Встречи"
#    * пользователь нажимает на кнопку "Добавить перелет"
#    * пользователь находится на странице "Перелетов"
#    И пользователь нажимает на элемент "ОткудА"
#    * пользователь вводит текст "ннн" в поле "Откуда"
#    * пользователь выбирает "ннннн, ннннн, ннннн, ннннн" из выпадающего списка
#    * пользователь вводит текст "FG37" в поле "Рейс"
#    * пользователь вводит завтрашнюю дату в поле "Дата вылета"
#    * пользователь нажимает на элемент "Время вылета"
#    * пользователь выбирает "17:00" из выпадающего списка
#    * пользователь нажимает на элемент "Поле выбора часового пояса прилета"
#    * пользователь выбирает "GMT+3" из выпадающего списка
#    * пользователь нажимает на элемент "Поле выбора часового пояса вылета"
#    * пользователь выбирает "GMT+3" из выпадающего списка
#    И пользователь нажимает на элемент "КудА"
#    * пользователь вводит текст "ааа" в поле "Куда"
#    * пользователь выбирает "ааааа, ааааа, ааааа, ааааа" из выпадающего списка
#    * пользователь вводит завтрашнюю дату в поле "Дата прилета"
#    * пользователь нажимает на элемент "Время прилета"
#    * пользователь выбирает "19:30" из выпадающего списка
#    * пользователь вводит текст "Тестовый перелет" в поле "Название"
#    * пользователь нажимает на кнопку "Добавить из списка"
#    * пользователь вводит текст "Грозный" в поле "Поле ввода участника"
#    * пользователь выбирает "Грозный Иван Васильевич" из выпадающего списка
#    * пользователь нажимает на кнопку "Сохранить"
#    И закрывает уведомление с текстом "Перелет создан!"
#    * пользователь нажимает на кнопку "Назад к списку"
#    * пользователь нажимает на кнопку "Стрелка вправо"
#    * пользователь вводит текст "Тестовый перелет" в поле "Поиск"
#    * он нажимает на 9 ячейку строки с текстом "Тестовый перелет" по столбцу "Встреча"
#    К тому же нажимает кнопку "Удалить" у предупреждения с текстом "Удаление события"
#    И закрывает уведомление с текстом "Перелет удален!"
#    И пользователь нажимает на кнопку "Выход"

#  @EditFlights
#  Сценарий: Редактировать список участников
#    Дано пользователь переходит на страницу "Авторизация"
#    Тогда пользователь вводит логин "ATStudyFirstMainAssistant" в поле "Логин"
#    И он вводит пароль в поле "Пароль"
#    Затем нажимает на кнопку "Войти"
#    Тогда он оказался на странице "База контактов"
#    * пользователь находится на странице "База контактов"
#    * пользователь нажимает на кнопку "[Рабочий график]"
#    * пользователь находится на странице "Встречи"
#    * пользователь нажимает на кнопку "Добавить перелет"
#    * пользователь находится на странице "Перелетов"
#    И пользователь нажимает на элемент "ОткудА"
#    * пользователь вводит текст "ннн" в поле "Откуда"
#    * пользователь выбирает "ннннн, ннннн, ннннн, ннннн" из выпадающего списка
#    * пользователь вводит текст "FG37" в поле "Рейс"
#    * пользователь вводит завтрашнюю дату в поле "Дата вылета"
#    * пользователь нажимает на элемент "Время вылета"
#    * пользователь выбирает "17:00" из выпадающего списка
#    * пользователь нажимает на элемент "Поле выбора часового пояса прилета"
#    * пользователь выбирает "GMT+3" из выпадающего списка
#    * пользователь нажимает на элемент "Поле выбора часового пояса вылета"
#    * пользователь выбирает "GMT+3" из выпадающего списка
#    И пользователь нажимает на элемент "КудА"
#    * пользователь вводит текст "ааа" в поле "Куда"
#    * пользователь выбирает "ааааа, ааааа, ааааа, ааааа" из выпадающего списка
#    * пользователь вводит завтрашнюю дату в поле "Дата прилета"
#    * пользователь нажимает на элемент "Время прилета"
#    * пользователь выбирает "19:30" из выпадающего списка
#    * пользователь вводит текст "Тестовый перелет" в поле "Название"
#    * пользователь нажимает на кнопку "Добавить из списка"
#    * пользователь вводит текст "Грозный" в поле "Поле ввода участника"
#    * пользователь выбирает "Грозный Иван Васильевич" из выпадающего списка
#    * пользователь нажимает на кнопку "Добавить из списка"
#    * пользователь вводит текст "Иванович" в поле "Поле ввода участника"
#    * пользователь выбирает "Иванович Иван Иванов" из выпадающего списка
#    * пользователь нажимает на кнопку "Сохранить"
#    И закрывает уведомление с текстом "Перелет создан!"
#    * пользователь он нажимает на 4 ячейку строки с текстом "Грозный Иван Васильевич" по столбцу "Фамилия Имя Отчество"
#    К тому же нажимает кнопку "Удалить" у предупреждения с текстом "Удаление участника"


#  @tmsLink=T832
#  @DeleteUser
#  Сценарий: При отмене добавления участника перелета, добавляется пустое поле
#    Дано пользователь переходит на страницу "Авторизация"
#    Тогда пользователь вводит логин "ATStudyFirstMainAssistant" в поле "Логин"
#    И он вводит пароль в поле "Пароль"
#    Затем нажимает на кнопку "Войти"
#    Тогда он оказался на странице "База контактов"
#    Дано пользователь находится на странице "База контактов"
#    Когда пользователь нажимает на элемент "[Рабочий график]"
#    Тогда он находится на странице "Встречи"
#    Когда он нажимает на кнопку "Добавить перелет"
#    Тогда он находится на странице "Перелетов"
#    Пусть пользователь нажимает на кнопку "Добавить из списка"
#    Затем он нажимает на элемент "Крестик" внутри элемента "Поле ввода участника"
#    Тогда в таблице "Участники" отсутствуют строки