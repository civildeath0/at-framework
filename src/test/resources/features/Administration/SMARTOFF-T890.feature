## language: ru
#
#@Administration
## noinspection NonAsciiCharacters
#Функция: Автотесты в категории "Администрирование"
#
#  Предыстория: Авторизоваться в роли администратора
#    Дано пользователь переходит на страницу "Авторизация"
#    Тогда пользователь вводит логин "skynet" в поле "Логин"
#    И он вводит пароль в поле "Пароль"
#    Затем нажимает на кнопку "Войти"
#    Тогда он оказался на странице "База контактов"
#    Затем нажимает на элемент "[Администрирование]"
#    Тогда он оказался на странице "Пользователи"
#
#  @tmsLink=T890
#  @severity=minor
#  Сценарий: SMARTOFF-T890 Вместо имени руководителя выводится undefined
#    Дано пользователь подгружает строки таблицы, пока отсутствует строка, удовлетворяющая условиям:
#      | Логин     | ThirdFakeChiefAssistant |
#      | Состояние | Активно                 |
#    Затем он нажимает на строку в таблице, для которой выполняются условия:
#      | Логин     | ThirdFakeChiefAssistant |
#      | Состояние | Активно                 |
#    Тогда пользователь оказался на странице "Детали пользователя"
#    И в поле "Руководитель" отобразился текст "Врунгель Христофор Бонифатьевич"
#    Затем он нажимает на элемент "Руководитель"