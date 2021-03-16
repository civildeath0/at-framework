#language: ru

@All
@Contacts
# noinspection NonAsciiCharacters, SpellCheckingInspection
Функция: Создание русскоязычного контакта

  Предыстория: Авторизоваться в роли помощника руководителя
    Допустим я авторизовался как помощник "Брусилов Алексей Алексеевич"
    Затем нажал на раздел "База контактов"
    И оказался на странице "Контакты"

  @tmsLink=T86
  Сценарий: SMARTOFF-T86 Создание контакта
    Дано я нажимаю кнопку "Добавить контакт"
    Затем оказался на странице "Детали контакта"
    И загружаю валидную картинку "image_002.jpg"
    Тогда нажимаю кнопку "Применить"
    И проверяю, что у полей есть соответствующие предупреждения:
      | [Название поля] | [Текст предупреждения] |
      | Фамилия         | Обязательное поле      |
      | Имя             | Обязательное поле      |
      | Пол             | Обязательное поле      |
    Тогда я ввожу текст в поля:
      | [Название поля] | [Текст] |
      | Фамилия         | Тест    |
      | Имя             | Ат      |
    Затем выбираю значение "Женский" в поле "Пол"
    И кнопка "Создать контакт" активна
    Тогда я нажимаю кнопку "Создать контакт"
    И появляется уведомление с текстом "Контакт создан!"
    Затем сущность успешно создалась