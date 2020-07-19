## Конфигурация
`config.properties` - настройки по умолчанию
`local.properties` - локальные настройки. Параметры, указанные в этом файле перезаписывают параметры по умолчанию

## Запуск
`mvn clean test -Dlogin=$USER_LOGIN -Dpassword=$USER_PASSWORD -DthreadCount=$THREAD_COUNT -Dgroup=$GROUP`

Необязательные параметры:
* $USER_LOGIN - логин пользователя
* $USER_PASSWORD - пароль пользователя
* $THREAD_COUNT - Количество потоков, используемое для тестов (по умолчанию: 1)
* $GROUP - Группы тестов через запятую (по умолчанию: все группы)

**Пример**

`mvn test -Dlogin=soasto24@yandex.ru -Dpassword=Pas394Wors840 -Dgroup=authorization`

## Отчет
Генерация Allure отчета:

`allure serve ./target/allure-results`
