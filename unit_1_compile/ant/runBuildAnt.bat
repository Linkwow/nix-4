@color 2
@echo *************************************
@echo ************* ANT building **********
@echo *************************************
@echo.
@set ANT_HOME=%~dp0apache-ant
@path %PATH%;%ANT_HOME%\bin
@set CLASSPATH=
@echo Checking version
@call ant -version
@echo.
@echo ************* Cleaning **************
@call ant clean 
@echo.
@echo ************* Compiling *************
@call ant compile
@echo.
@echo ************* Making jar ************
@call ant jar
@echo.
@echo ************* Running ***************
@call ant run