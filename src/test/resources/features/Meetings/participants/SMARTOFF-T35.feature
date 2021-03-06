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

  @tmsLink=T35
  @severity=normal
  Сценарий: SMARTOFF-T35 Добавление участника встречи не из списка контактов
    Дано пользователь нажимает на кнопку "Добавить встречу"
    Тогда он находится на странице "Детали встречи"
    Затем он нажимает на кнопку "Создать нового"
    И вводит текст "Добронравов" в поле "Фамилия"
    Затем вводит текст "Виктор" в поле "Имя"
    К тому же вводит текст "Павлович" в поле "Отчество"
    Затем он вводит текст "Счастье" в поле "Компания"
    И он вводит текст "Эндорфин" в поле "Должность"
    Затем он нажимает на кнопку "Добавить участника"
    Тогда в таблице "Участники" появилась строка, для которой выполняются условия:
      | Фамилия Имя Отчество    | Добронравов Виктор Павлович |
      | Организация и должность | Счастье Эндорфин            |
    А у элемента "Счетчик участников" отобразился текст "1"
    А кнопка "Добавить из списка" активна
    И кнопка "Создать нового" активна
