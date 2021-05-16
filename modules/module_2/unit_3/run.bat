@chcp 1251
@echo *************************************
@echo *********Путевая задача**************
@echo *************************************
@call mvn clean install
@java -jar target\unit_3-1.0-SNAPSHOT.jar