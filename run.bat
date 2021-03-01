@cd unit_1_compiler
@echo ********************
@echo performance with CLI
@echo ********************
@echo Cleaning
@rd bin /S /Q
@echo Compiling
@javac -sourcepath src -d bin -classpath libs/commons-lang3-3.11.jar src/task/car/engine/Engine.java src/task/car/Car.java src/task/Main.java
@echo Starting
@java -classpath bin/;./libs/commons-lang3-3.11.jar src/task/Main
@pause
@cd ..
@cd unit_1_ant
@echo ********************
@echo performance with ANT
@echo ********************
@call ant clean compile jar run
@pause
@cd..
@cd unit_1_maven
@rd application /S /Q
@call mvn archetype:generate -DgroupId=src.task -DartifactId=application -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
@copy /Y D:\Test\unit_1_maven\filesForRewrite\pom.xml  D:\Test\unit_1_maven\application\
@cd application
@call mvn clean install
@copy /Y D:\Test\unit_1_maven\filesForRewrite\App.java D:\Test\unit_1_maven\application\src\main\java\src\task\App.java 
@xcopy /Y /O /E D:\Test\unit_1_maven\files\src\main\java\src\task D:\Test\unit_1_maven\application\src\main\java\src\task
@del /S D:\Test\unit_1_maven\application\src\main\java\src\task\Main.java
@REM copy /Y D:\Test\unit_1_maven\filesForRewrite\pom.xml  D:\Test\unit_1_maven\application\
@call mvn clean install
@java -jar target/application-1.0-SNAPSHOT.jar
@pause
@cd D:\Test

