# language: ru

@Meetings
# noinspection NonAsciiCharacters
Функция: Автотесты в категории "Встречи"

  Предыстория: Авторизоваться в роли помощника руководителя
    Дано пользователь переходит на страницу "Авторизация"
    Тогда пользователь вводит логин "ATStudyFirstMainAssistant" в поле "Логин"
    И вводит пароль в поле "Пароль"
    Когда он нажимает на кнопку "Войти"
    То находится на странице "База контактов"
    Затем пользователь нажимает на элемент "[Рабочий график]"
    Тогда он находится на странице "Встречи"

  @tmsLink=T831
  @severity=normal
  Сценарий: SMARTOFF-T831 Отмена добавления участника встречи
    Когда он нажимает на кнопку "Добавить встречу"
    Тогда он находится на странице "Детали встречи"
    Пусть пользователь нажимает на кнопку "Добавить из списка"
    Затем он нажимает на элемент "Крестик" внутри элемента "Поле ввода участника"
    Тогда в таблице "Участники" отсутствуют строки
    И со страницы пропал элемент "Поле ввода участника"
    А у элемента "Счетчик участников" отобразился текст "0"
    А кнопка "Добавить из списка" активна
    И кнопка "Создать нового" активна
