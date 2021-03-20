@chcp 1251
@set MAVEN_DIR=%~dp0apache-maven
@set ROOT=%~dp0
@path %PATH%;%MAVEN_DIR%\bin
@call mvn -v
@echo *************************************
@echo ******Starting string revers*********
@echo *************************************
@cd %ROOT%demonstration
@call mvn clean install
@java -jar target\demonstration-1.0-SNAPSHOT.jar