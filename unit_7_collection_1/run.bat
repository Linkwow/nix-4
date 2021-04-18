@chcp 1251
@set ROOT=%~dp0
@call mvn -v
@call mvn clean install
@java -jar app\target\app-1.0-SNAPSHOT.jar