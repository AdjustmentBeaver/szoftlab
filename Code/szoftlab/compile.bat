@CHCP 65001 >nul 2>&1
ECHO A programkód fordítása...

IF EXIST out (
	RD /S /Q out
)

MD out
MD out\production
MD out\production\szoftlab

"%JAVA_HOME%\bin\javac" -encoding utf8 -d out\production\szoftlab -sourcepath src src\Game.java