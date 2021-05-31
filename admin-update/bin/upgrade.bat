@echo off
rem LANG=zh_CN
set CURPATH=%~dp0
set JAVA_HOME=%CURPATH%\..\..\jre
set JAVA_PATH=%JAVA_HOME%\bin

set CLASSPATH=
set CLASSPATH=%CLASSPATH%;%CURPATH%WEB-INF\lib\commons-io-2.6.jar
set CLASSPATH=%CLASSPATH%;%CURPATH%WEB-INF\lib\ojdbc6-11.2.0.3.jar
set CLASSPATH=%CLASSPATH%;%CURPATH%WEB-INF\lib\log4j-1.2.17.jar
set CLASSPATH=%CLASSPATH%;%CURPATH%WEB-INF\classes

%JAVA_PATH%\java.exe -cp %CLASSPATH% -Ddefault.client.encoding=gbk -Dfile.encoding=gbk -Duser.language=Zh -Duser.region=CN com.nbnfsoft.update.DBUpgrade

pause
