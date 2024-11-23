# Проект по автоматизации тестирования API <a target="_blank" href="https://restful-booker.herokuapp.com/">restful-booker</a>

## Содержание:

* <a href="#tools">Технологии и инструменты</a>
* <a href="#cases">Реализованные проверки</a>
* <a href="#console">Запуск тестов</a>
* <a href="#allure">Отчеты в Allure</a>
* <a href="#testops">Интеграция с Allure TestOps</a>
* <a href="#telegram">Уведомления в Telegram с использованием бота</a>

<a id="tools"></a>

## Технологии и инструменты

<p align="center">
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/intellij-original.svg" width="50" height="50"  alt="IDEA"/></a>
<a href="https://www.java.com/"><img src="images/logo/java-original.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://github.com/"><img src="images/logo/github-original.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://gradle.org/"><img src="images/logo/gradle-original.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://junit.org/junit5/"><img src="images/logo/junit-original.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://rest-assured.io/"><img src="images/logo/RestAssured.png" width="50" height="50" alt="RestAssured"/></a>
<a href="https://www.jenkins.io/"><img src="images/logo/jenkins-original.svg" width="50" height="50"  alt="Jenkins"/></a>
<a href="https://github.com/allure-framework/"><img src="images/logo/AllureReports.png" width="50" height="50" alt="Allure Report"/></a>
<a href="https://qameta.io/"><img src="images/logo/AllureTestOps.svg" width="50" height="50" alt="Allure TestOps"/></a> 
<a href="https://telegram.org/"><img src="images/logo/Telegram.png" width="50" height="50" alt="Telegram"/></a>
</p>

<a id="cases"></a>

## Реализованы тесты для методов

* Создание нового бронирования - CREATE
* Получение информации бронирования - GET
* Обновление информации бронирования - UPDATE
* Удаление бронирования - DELETE

<a id="console"></a>

## Запуск тестов

Для запуска локально и в Jenkins используется команда:

```
gradle clean test
```

### Запуск в Jenkins

Для запуска проекта через Jenkins была создана <a target="_blank" href="https://jenkins.autotests.cloud/job/C29-bochkareva_a-booker-api-tests/">**задача**</a>. Для запуска используете кнопку Build Now/Собрать сейчас.
После выполнения сборки, результаты тестов станут доступны в Allure Report и Allure TestOps.

![Jenkins_build](/images/screens/Jenkins_build.jpg)

<a id="allure"></a>

## Отчеты в <a target="_blank" href="https://jenkins.autotests.cloud/job/C29-bochkareva_a-booker-api-tests/allure/">**Allure**</a>

На главной странице Allure отчета возможно узнать основную информацию о сбоке и тендецию выполнения тестов за все запуски.

![allure](/images/screens/allure.jpg)

На странице Suites отображется список тестов с полной информацией (шаги, артефакты, продолжительность выполнения).

![allure](/images/screens/allure_suites.jpg)

<a id="testops"></a>

## Интеграция с <a target="_blank" href="https://jenkins.autotests.cloud/job/C29-bochkareva_a-booker-api-tests/allure/">**Allure TestOps**</a>

![allure](/images/screens/allure_testOps.jpg)

Написанный код теста импортируются в тест-кейсы проекта. 

![allure](/images/screens/allure_suites.jpg)

<a id="telegram"></a>

## Уведомления в Telegram с использованием бота

После завершения сборки Telegram бот автоматически обрабатывает и отправляет сообщение с отчетом о прогоне тестов.

![allure](/images/screens/telegram.jpg)