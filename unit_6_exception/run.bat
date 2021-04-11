@chcp 1251
@set ROOT=%~dp0
@call mvn -v
@call mvn clean install
@echo ***************************************
@echo ******Starting create demobase*********
@echo ***************************************
@java -jar target\calendar-1.0-SNAPSHOT.jar