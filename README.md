# Как выполнять задание по интеграции с Report Portal:
Report Portal (далее RP) это система для автоматизации и управления отчетами о тестировании ПО.
1. Запустить Docker Desktop
2. Открыть [страницу репозитория RP на GitHub](https://github.com/reportportal/reportportal)
3. Найти и скачать docker-compose.yml файл с GitHub
4. Открыть проект c тестами в intelliJ
5. Вставить скопированный файл в корневую папку проекта
6. Открыть окно терминала (Alt+F12)
7. Запустить команду docker-compose -p reportportal up -d --force-recreate
8. Дождаться пока контейнер скачает нужные образы и запустится (может занять значительное время)
9. В браузере открыть страницу http//localhost:8080, убедиться что образ запустился и работает
10. Авторизоваться со следующими credentials (login\pass): default\1q2w3e
11. Нажать на кнопку создания дэшборда ![image](https://github.com/user-attachments/assets/466a3d5e-23e0-47e5-8294-f8a00acaf911)
12. Нажать на кнопку создания нового виджета ![image](https://github.com/user-attachments/assets/f842dae5-286b-4e86-96c1-766579c0e9d7)
13. ![image](https://github.com/user-attachments/assets/199fa8f6-af7c-476e-9d4b-7ee1d9bd76b1)
14. Добавить зависимость в build.gradle:     
testImplementation 'com.epam.reportportal:junit5-agent:5.0.0'




