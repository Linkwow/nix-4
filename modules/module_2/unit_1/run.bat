@chcp 1251
@echo ***************************************
@echo ******Соответствие форматов датам******
@echo ***************************************
@call mvn clean install
@java -jar target\unit_1-1.0-SNAPSHOT.jar