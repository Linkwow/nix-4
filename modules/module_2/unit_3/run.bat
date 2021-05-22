@chcp 1251
@echo *************************************
@echo *********Путевая задача**************
@echo *************************************
@call mvn clean install
@java -jar graphapp\target\graphapp-1.0-SNAPSHOT.jar