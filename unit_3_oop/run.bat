@chcp 866
@set MAVEN_DIR=%~dp0apache-maven
@set ROOT=%~dp0
@path %PATH%;%MAVEN_DIR%\bin
@call mvn -v
@echo *************************************
@echo ***Ariphmetics operatons*************
@echo *************************************
@cd %ROOT%app
@call mvn clean install
@java -jar target\app-1.0-SNAPSHOT.jar