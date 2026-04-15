@echo off
REM BLOOD UTILITYS - Docker Build Script
REM This script builds the mod using Docker (no Java 21 installation needed)

echo BLOOD UTILITYS - Docker Build
echo.
echo Checking if Docker is installed...

docker --version >nul 2>&1
if %errorlevel% neq 0 (
    echo Docker is not installed or not in PATH.
    echo.
    echo To install Docker, go to: https://www.docker.com/products/docker-desktop
    echo.
    echo After installing Docker, run this script again.
    pause
    exit /b 1
)

echo Docker found! Building mod...
echo.

REM Build using Docker with Java 21 and Gradle
docker run --rm -v %cd%:/workspace -w /workspace gradle:8.5-jdk21 gradle build

if %errorlevel% equ 0 (
    echo.
    echo BUILD SUCCESSFUL!
    echo.
    echo JAR file created at: build\libs\blood-utilitys-1.0.0.jar
    echo.
    echo Installation:
    echo 1. Copy the JAR to: .minecraft\mods\
    echo 2. Launch Minecraft with Fabric Loader 1.21.11
    echo 3. Press Right Shift to open the mod GUI
) else (
    echo Build failed!
    exit /b 1
)

pause
