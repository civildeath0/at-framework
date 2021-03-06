# language: ru

@All
@Companies
# noinspection NonAsciiCharacters, SpellCheckingInspection
Функция: Автотесты в категории "Компании"

  Предыстория: Авторизоваться в роли помощника СМИ
    Затем я авторизовался как помощник "Шеин Михаил Борисович"
    Затем нажал на раздел "Справочники"
    И оказался на странице "Справочник компаний"

  @tmsLink=T1030
  @severity=blocker
  Сценарий: SMARTOFF-T1030 - Создание компании
    Дано я нажимаю кнопку "Добавить компанию"
    И нахожусь на странице "Детали компании"
    Затем я ввожу текст в поля:
      | [Название поля] | [Текст]                             |
      | Название        | АТ Компания с анимированной кнопкой |
      | Ссылка          | https://wikipedia.org               |
    И загружаю в поле "Лого компании" валидную картинку "image_002.jpg"
    Затем нажимаю кнопку "Применить"
    К тому же на странице появилась кнопка "Удалить логотип"
    Затем нажимаю кнопку "Сохранить"
    Тогда сущность успешно создалась
    И на странице присутствует уведомление с текстом "Компания создана!"
    К тому же кнопка "Сохранить" неактивна
    А кнопка "Удалить" активна
