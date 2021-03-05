@color 5
@echo *************************************
@echo ************* CLI building **********
@echo *************************************
@echo. 
@set CURRENT_DIR=%~dp0bin
@if exist %CURRENT_DIR% (
@echo ************* Cleaning **************
@rd bin /S /Q)
@echo. 
@echo ************* Compiling *************
@javac -sourcepath src -d bin -classpath libs/commons-lang3-3.11.jar src/task/car/engine/Engine.java src/task/car/Car.java src/task/Main.java
@echo.  
@echo ************* Running ***************
@java -classpath bin/;./libs/commons-lang3-3.11.jar src/task/Main