@ECHO off
@CHCP 65001 >nul 2>&1
ECHO A programkód tesztelése...

SET RESULT_DIR="test\results"

IF EXIST %RESULT_DIR% (
	RD /S /Q %RESULT_DIR%
)

MD %RESULT_DIR%

FOR %%f IN ("test\input\*.in") DO (
	"%JAVA_HOME%\bin\java" -classpath out\production\szoftlab Game < %%f > "%RESULT_DIR%\%%~nf.test"
	ECHO TEST: %%~nf >> testresult.txt
	FC "%RESULT_DIR%\%%~nf.test" "test\output\%%~nf.out" >> testresult.txt
)
