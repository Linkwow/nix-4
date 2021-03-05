@color 2
@echo *************************************
@echo ************* Maven building ********
@echo *************************************
@echo.
@set MAVEN_DIR=%~dp0apache-maven
@path %PATH%;%MAVEN_DIR%\bin
@call mvn -v
@echo.
@call mvn clean install
@java -jar target\main-1.0-SNAPSHOT.jar