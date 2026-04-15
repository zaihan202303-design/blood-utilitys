# BLOOD UTILITYS - Build Guide

## Quick Summary

The mod is complete and ready to build. You just need Gradle installed.

## Method 1: Using IntelliJ IDEA (Easiest)

1. Download and install IntelliJ IDEA Community Edition (free)
2. Open `D:\blood-utilitys` as a project
3. Wait for Gradle sync to complete
4. Go to: View → Tool Windows → Gradle
5. Expand Tasks → build → double-click `build`
6. JAR will be created at: `build/libs/blood-utilitys-1.0.0.jar`

## Method 2: Using VS Code

1. Install VS Code
2. Install "Gradle for Java" extension (vscjava.gradle)
3. Open `D:\blood-utilitys` folder
4. Click Gradle icon in left sidebar
5. Expand BLOOD-UTILITYS → Tasks → build
6. Click the play icon next to `build`
7. JAR will be created at: `build/libs/blood-utilitys-1.0.0.jar`

## Method 3: Command Line with Gradle

1. Download Gradle 8.5 from: https://gradle.org/releases/
2. Extract to a folder (e.g., `C:\gradle-8.5`)
3. Add to PATH:
   - Right-click "This PC" → Properties
   - Advanced system settings → Environment Variables
   - Add `C:\gradle-8.5\bin` to PATH
4. Open Command Prompt in `D:\blood-utilitys`
5. Run: `gradle build`
6. JAR will be at: `build\libs\blood-utilitys-1.0.0.jar`

## Method 4: Using Docker (If installed)

```bash
cd D:\blood-utilitys
docker run --rm -v %cd%:/workspace -w /workspace gradle:8.5 gradle build
```

## After Building

1. Locate the JAR: `build\libs\blood-utilitys-1.0.0.jar`
2. Copy to: `.minecraft\mods\`
3. Launch Minecraft with Fabric Loader 1.21.1
4. Press Right Shift to open the mod GUI

## Troubleshooting

**"gradle: command not found"**
- Gradle is not in your PATH
- Use Method 1 or 2 instead

**Build fails with Java version error**
- Ensure Java 21+ is installed
- Check: `java -version`

**Out of memory during build**
- Increase heap: `set GRADLE_OPTS=-Xmx2g`
- Then run gradle build

## Project Structure

```
D:\blood-utilitys\
├── src/main/java/com/bloodutilitys/     (Source code)
├── src/main/resources/                   (Resources & config)
├── build.gradle                          (Build configuration)
├── gradle.properties                     (Gradle settings)
└── settings.gradle                       (Project settings)
```

## What Gets Built

- **Input**: Java source files + Fabric API
- **Output**: `blood-utilitys-1.0.0.jar` (client-side mod)
- **Size**: ~50-100 KB
- **Target**: Minecraft 1.21.1 with Fabric Loader

## Features in the Mod

✅ Custom PvP HUD (armor, totems, FPS, ping, potions)
✅ Advanced Hitbox Rendering
✅ Settings GUI with drag-and-drop HUD editor
✅ Customizable keybinds
✅ Red and black theme
✅ Modular architecture
✅ Optimized performance

---

**Recommended**: Use IntelliJ IDEA or VS Code for easiest setup.
