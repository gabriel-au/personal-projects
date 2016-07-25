ECHO OFF

@rem capturando endereço do .bat
SET BASE_DIR=%~f0
ECHO "BASE_DIR=%BASE_DIR%"
@rem montando endereço do diretório base da aplicação
:STRIP
SET REMOVED=%BASE_DIR:~-1%
SET JAVA_HOME="C:\Program Files (x86)\Java\jdk1.6.0_30\bin\"
SET PATH=%JAVA_HOME%;%PATH%

SET BASE_DIR=%BASE_DIR:~0,-1%
IF NOT "%REMOVED%"=="\" GOTO STRIP

ECHO "BASE_DIR=%BASE_DIR%"
ECHO. 
ECHO. 

SET CLASSPATH=
SET CLASSPATH="%BASE_DIR%\framework-windows.jar";
SET CLASSPATH=%CLASSPATH%"%BASE_DIR%\framework-base.jar";
SET CLASSPATH=%CLASSPATH%"%BASE_DIR%\conversor-win.jar";
SET CLASSPATH=%CLASSPATH%"%BASE_DIR%\conversor-core.jar";
SET CLASSPATH=%CLASSPATH%"%BASE_DIR%\conversor-module.jar";
SET CLASSPATH=%CLASSPATH%"%BASE_DIR%\sqlitejdbc-v056.jar";
SET CLASSPATH=%CLASSPATH%"%BASE_DIR%\log4j-1.2.16.jar";
SET CLASSPATH=%CLASSPATH%"%BASE_DIR%\commons-lang3-3.1.jar";
SET CLASSPATH=%CLASSPATH%"%BASE_DIR%\swt.jar";
SET CLASSPATH=%CLASSPATH%"%BASE_DIR%\DJNativeSwing.jar";
SET CLASSPATH=%CLASSPATH%"%BASE_DIR%\DJNativeSwing-SWT.jar";
SET CLASSPATH=%CLASSPATH%"%BASE_DIR%\xpp3-1.1.4c.jar"

ECHO JAVA_HOME=%JAVA_HOME%
ECHO. 
ECHO CLASSPATH=%CLASSPATH%
ECHO.

@rem START /B/WAIT/HIGH javaw -classpath %CLASSPATH% br.com.martins.vendas.conversor.ConversorWindows
START /B/HIGH javaw -classpath %CLASSPATH% br.com.martins.vendas.conversor.ConversorWindows