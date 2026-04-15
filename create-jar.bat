@echo off
setlocal enabledelayedexpansion

echo Creating BLOOD UTILITYS JAR...
echo.

REM Create temporary directories
if exist temp_jar rmdir /s /q temp_jar
mkdir temp_jar\com\bloodutilitys\config
mkdir temp_jar\com\bloodutilitys\feature\hud
mkdir temp_jar\com\bloodutilitys\feature\hitbox
mkdir temp_jar\com\bloodutilitys\keybind
mkdir temp_jar\com\bloodutilitys\ui\widget
mkdir temp_jar\META-INF
mkdir temp_jar\assets\blood-utilitys\lang

echo Copying source files...

REM Copy Java files
copy src\main\java\com\bloodutilitys\BloodUtilitysClient.java temp_jar\com\bloodutilitys\ >nul
copy src\main\java\com\bloodutilitys\config\ModConfig.java temp_jar\com\bloodutilitys\config\ >nul
copy src\main\java\com\bloodutilitys\feature\hud\HudManager.java temp_jar\com\bloodutilitys\feature\hud\ >nul
copy src\main\java\com\bloodutilitys\feature\hitbox\HitboxRenderer.java temp_jar\com\bloodutilitys\feature\hitbox\ >nul
copy src\main\java\com\bloodutilitys\keybind\KeyBindManager.java temp_jar\com\bloodutilitys\keybind\ >nul
copy src\main\java\com\bloodutilitys\ui\SettingsScreen.java temp_jar\com\bloodutilitys\ui\ >nul
copy src\main\java\com\bloodutilitys\ui\widget\HudEditorWidget.java temp_jar\com\bloodutilitys\ui\widget\ >nul
copy src\main\java\com\bloodutilitys\ui\widget\ColorPickerWidget.java temp_jar\com\bloodutilitys\ui\widget\ >nul

REM Copy resource files
copy src\main\resources\fabric.mod.json temp_jar\META-INF\ >nul
copy src\main\resources\blood-utilitys.mixins.json temp_jar\ >nul
copy src\main\resources\assets\blood-utilitys\lang\en_us.json temp_jar\assets\blood-utilitys\lang\ >nul

echo Creating JAR file...

REM Create JAR using jar command
jar cvf build\libs\blood-utilitys-1.0.0.jar -C temp_jar .

if !errorlevel! equ 0 (
    echo.
    echo SUCCESS! JAR created at: build\libs\blood-utilitys-1.0.0.jar
    echo.
    echo Installation:
    echo 1. Copy the JAR to: .minecraft\mods\
    echo 2. Launch Minecraft with Fabric Loader 1.21.1
    echo 3. Press Right Shift to open the mod GUI
) else (
    echo Failed to create JAR
    exit /b 1
)

REM Cleanup
rmdir /s /q temp_jar

endlocal
