@echo ********************
@echo performance with CLI
@echo ********************
@echo Compiling
@rd bin /S /Q
@javac -sourcepath src -d bin -classpath libs/commons-lang3-3.11.jar src/task/car/engine/Engine.java src/task/car/Car.java src/task/Main.java
@echo Starting
@java -classpath bin/;./libs/commons-lang3-3.11.jar src/task/Main
@echo Cleaning
@echo ********************
@echo end performance with CLI
@echo ********************
@cd ..