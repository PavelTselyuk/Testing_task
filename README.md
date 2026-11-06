**Тестовый проект для Java-приложения

***Локальный запуск: 

****Для локального запуска нужно запустить непосредственно сам проект а также wiremock-standalone с сайта wiremock  
(на момент написания актуальная wiremock-standalone-3.13.2.jar)  
****Для запуска вводим команды(bash-терминал должен быть в папке, в которой лежат файлы jar):
`java -jar wiremock-standalone-3.13.2.jar --port 8888`
`java -jar -Dsecret=*your_x_api_key* -Dmock=http://localhost:8888/ internal-0.0.1-SNAPSHOT.jar`
****Для корректной работы нам нужно добавить 2 мока(приложение возвращает ответ только в зависимости от кода, заглушки будут возвращать 200 код):
при запуске wiremock-standalone появляются 2 папки(mappings и __files) внутри mappings создаем 2 json файла(имена любые):
```
{
  "request": {
    "method": "POST",
    "url": "/doAction"
  },
  "response": {
    "status": 200
  }
}
```
---
```
{
  "request": {
    "method": "POST",
    "url": "/auth"
  },
  "response": {
    "status": 200
  }
}
```
---
****Далее внутри этого(тестового) проекта нужно создать файл .env и в нём указать x-api-key(пример в файле .env.example) после этого можно запустить тесты командой:
mvn clean test
