@chcp 1251
@echo *************************************
@echo *******проверка скобок в строке******
@echo *************************************
@call mvn clean install
@java -jar target\unit_1-1.0-SNAPSHOT.jar