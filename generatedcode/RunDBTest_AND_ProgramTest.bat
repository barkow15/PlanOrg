javac -cp "." EODatabaseInterface.java
javac -cp "." Run.java

java -cp ".;%~dp0sqlite-jdbc-3.23.1.jar" Run


pause
