@echo off
setlocal enabledelayedexpansion

echo Building BLOOD UTILITYS Mod...
echo.

REM Check if gradle wrapper JAR exists
if not exist "gradle\wrapper\gradle-wrapper.jar" (
    echo Gradle wrapper not found. Setting up...
    echo.
    
    REM Download gradle wrapper JAR
    echo Downloading gradle-wrapper.jar...
    powershell -Command "[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; (New-Object System.Net.WebClient).DownloadFile('https://repo.gradle.org/gradle/gradle-8.5-bin/gradle-8.5-bin.zip', '%cd%\gradle-8.5-bin.zip')"
    
    if !errorlevel! neq 0 (
        echo Failed to download Gradle.
        echo Please ensure you have internet connection or install Gradle manually.
        exit /b 1
    )
    
    echo Extracting gradle...
    powershell -Command "Expand-Archive -Path '!cd!\gradle-8.5-bin.zip' -DestinationPath '!cd!'"
    
    echo Moving gradle files...
    if exist "gradle-8.5\lib\gradle-wrapper.jar" (
        move "gradle-8.5\lib\gradle-wrapper.jar" "gradle\wrapper\" >nul 2>&1
    )
    
    echo Cleaning up...
    rmdir /s /q "gradle-8.5" >nul 2>&1
    del "gradle-8.5-bin.zip" >nul 2>&1
    
    echo Gradle setup complete.
    echo.
)

echo Running build...
call gradlew build

if !errorlevel! equ 0 (
    echo.
    echo Build successful!
    echo JAR file: build\libs\blood-utilitys-1.0.0.jar
    echo.
    echo Installation:
    echo 1. Copy the JAR to your .minecraft\mods folder
    echo 2. Launch Minecraft with Fabric Loader
) else (
    echo Build failed!
    exit /b 1
)

endlocal
