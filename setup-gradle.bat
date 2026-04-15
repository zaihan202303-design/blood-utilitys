@echo off
setlocal enabledelayedexpansion

echo BLOOD UTILITYS - Gradle Setup
echo.
echo This script will download and setup Gradle for building the mod.
echo.

set GRADLE_VERSION=8.5
set GRADLE_URL=https://services.gradle.org/distributions/gradle-8.5-bin.zip
set GRADLE_ZIP=gradle-8.5-bin.zip

echo Downloading Gradle %GRADLE_VERSION%...
powershell -Command "[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; (New-Object System.Net.WebClient).DownloadFile('%GRADLE_URL%', '%GRADLE_ZIP%')"

if !errorlevel! neq 0 (
    echo Failed to download Gradle. Check your internet connection.
    pause
    exit /b 1
)

echo Extracting Gradle...
powershell -Command "Expand-Archive -Path '%GRADLE_ZIP%' -DestinationPath '.'"

if !errorlevel! neq 0 (
    echo Failed to extract Gradle.
    pause
    exit /b 1
)

echo Setting up gradle wrapper...
if not exist "gradle\wrapper" mkdir gradle\wrapper

if exist "gradle-8.5\lib\gradle-wrapper.jar" (
    copy "gradle-8.5\lib\gradle-wrapper.jar" "gradle\wrapper\gradle-wrapper.jar"
    echo Gradle wrapper JAR copied successfully.
)

echo Cleaning up...
rmdir /s /q "gradle-8.5"
del "%GRADLE_ZIP%"

echo.
echo Setup complete! You can now run: build.bat
echo.
pause
