@set MAVEN_DIR=%~dp0apache-maven
@set ROOT=%~dp0
@path %PATH%;%MAVEN_DIR%\bin
@call mvn -v
@echo *************************************
@echo *Start count sum of numbers in row***
@echo *************************************
@cd %ROOT%task_1
@call run
@pause
@cls
@echo ***************************************
@echo *Start count and sort letters in row***
@echo ***************************************
@cd %ROOT%task_2
@call run
@pause
@cls
@echo ***************************************
@echo *****Let's know when lesson end********
@echo ***************************************
@cd %ROOT%task_3
@call run
@pause
@cls
