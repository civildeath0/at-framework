#@tmsLink=T920
#@severity=critical
#@issue=4432
#@RevertChanges
#Сценарий: SMARTOFF-T920 - Изменение информации о пользователе
#Дано пользователь переходит на страницу "Авторизация"
#Тогда пользователь вводит логин "skynet" в поле "Логин"
#И он вводит пароль в поле "Пароль"
#Затем нажимает на кнопку "Войти"
#Тогда он оказался на странице "База контактов"
#Дано пользователь нажимает на элемент "[Администрирование]"
#Тогда он находится на странице "Пользователи"
#И подгружает строки таблицы, пока не найдется строка, удовлетворяющая условиям:
#| Логин     | ATStudyFirstMainAssistant |
#| Состояние | Активно                   |
#Затем он нажимает на строку в таблице, для которой выполняются условия:
#| Логин     | ATStudyFirstMainAssistant |
#| Состояние | Активно                   |
#Тогда он оказался на странице "Детали пользователя"
#Пусть пользователь очищает текст в поле "Отчество"
#И вводит текст "Артемьевич" в поле "Отчество"
#Пусть он очищает текст в поле "Должность"
#И вводит текст "Кондитер" в поле "Должность"
#Тогда кнопка "Сохранить" активна
#И он нажимает на кнопку "Сохранить"
#Затем закрывает уведомление с текстом "Пользователь обновлен!"
#И кнопка "Сохранить" не активна