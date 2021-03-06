# language: ru

@MaterialCategories
# noinspection NonAsciiCharacters
Функция: Автотесты в категории "Категории материалов"

  Предыстория: Авторизоваться в роли помощника руководителя
    Дано пользователь переходит на страницу "Авторизация"
    Тогда пользователь вводит логин "ATStudyFirstMainAssistant" в поле "Логин"
    К тому же запросы будут посылаться от имени пользователя с логином "ATStudyFirstMainAssistant"
    И вводит пароль в поле "Пароль"
    Когда он нажимает на кнопку "Войти"
    То находится на странице "База контактов"

  @tmsLink=T203
  @DeleteCategoryAfter
  Сценарий: SMARTOFF-T203 - Редактирование существующей категории материалов
    Пусть пользователь совершает запрос POST на адрес "/api/web/documents/categories" с параметрами из таблицы:
      | HEAD | accept       | text/plain                  |
      | HEAD | Content-Type | application/json-patch+json |
      | BODY | name         | Пушистик                    |
    Если статус-код последнего ответа равен 201
    То сохраняет поле "id" из тела последнего ответа как "ID категории"
    Пусть пользователь нажимает на кнопку "[Справочники]"
    Затем он нажимает на кнопку "[Категории материалов]"
    Тогда он оказался на странице "Категории материалов"
    Затем пользователь вводит текст "Пуш" в поле "Поиск"
    И нажимает на строку в таблице, для которой выполняются условия:
      | Наименование | Пушистик |
    Тогда он оказался на странице "Детали категории"
    Допустим пользователь очищает текст в поле "Название:"
    И вводит текст "Колючкин" в поле "Название:"
    Тогда пользователь нажимает на кнопку "Сохранить"
    Затем закрывает уведомление с текстом "Категория обновлена!"
