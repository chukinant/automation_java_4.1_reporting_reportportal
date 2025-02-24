# Как выполнять задание по интеграции с Report Portal:
Report Portal (далее RP) это система для автоматизации и управления отчетами о тестировании ПО.
1. Запустить Docker Desktop
2. Открыть [страницу репозитория RP на GitHub](https://github.com/reportportal/reportportal)
3. Найти и скачать _docker-compose.yml_ файл с GitHub
4. Открыть проект c тестами в intelliJ
5. Вставить скопированный файл в корневую папку проекта
6. Открыть окно терминала (Alt+F12)
7. Запустить команду `docker-compose -p reportportal up -d --force-recreate`
8. Дождаться пока контейнер скачает нужные образы и запустится (может занять некоторое время). Убедиться что образ запустился и работает можно в Docker Desktop
10. В браузере открыть страницу <http://localhost:8080>, должна открыться страница авторизации в RP
11. Авторизоваться со следующими credentials:
      login: default
      password: 1q2w3e
12. На появившейся странице RP, перейти в раздел PROFILE настройек профиля:

![image](https://github.com/user-attachments/assets/a3783275-b2cf-4745-b3f6-8608b92b7bdf)

13. Перейти на вкладку Configuration examples и скопировать требуемые параметры для файла конфигурации проекта на Java:

![image](https://github.com/user-attachments/assets/898dc4e5-10af-4b33-97c3-a3f377491112)

13. В IntelliJ IDEA в подпапке проекта `\src\test\resources\` создать файл reportportal.properties и вставить в него скопированные параметры.
15. Вернуться на страницу RP, перейти на вкладку API_KEYS и нажать кнопку _Generate API Key_:

![image](https://github.com/user-attachments/assets/97558a19-54dd-44aa-af73-99ad92c999f6)

14. Далее ввести желаемое название для ключа и скопировать сгенерированный ключ
15. В файле `reportportal.properties` для свойства `rp.api.key` заменить значение `<API_KEY>` на сгенерированное
16. В файл build.gradle добавить зависимость `testImplementation 'com.epam.reportportal:junit5-agent:5.0.0'`, синхронизировать изменения
17. Запустить тестируемое приложение командой `java -jar ./artifacts/app-replan-delivery.jar`
18. Запустить тесты командой `./gradlew clean test`
19. Перейти на страницу RP, в разделе _Launches_ должны отображаться логи тестов.

![Screenshot 2025-02-24 203719](https://github.com/user-attachments/assets/bf5cde2c-30e2-46a6-a1ed-76853387260c)






