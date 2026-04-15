# BLOOD UTILITYS - Setup & Development Guide

## Project Structure

```
blood-utilitys/
├── src/main/
│   ├── java/com/bloodutilitys/
│   │   ├── BloodUtilitysClient.java          # Main entry point
│   │   ├── config/
│   │   │   └── ModConfig.java                # Configuration management
│   │   ├── feature/
│   │   │   ├── hud/
│   │   │   │   └── HudManager.java           # HUD rendering
│   │   │   └── hitbox/
│   │   │       └── HitboxRenderer.java       # Hitbox rendering
│   │   ├── keybind/
│   │   │   └── KeyBindManager.java           # Keybind handling
│   │   └── ui/
│   │       ├── SettingsScreen.java           # Main settings GUI
│   │       └── widget/
│   │           ├── HudEditorWidget.java      # HUD editor
│   │           └── ColorPickerWidget.java    # Color picker
│   └── resources/
│       ├── fabric.mod.json                   # Mod metadata
│       ├── blood-utilitys.mixins.json        # Mixin configuration
│       └── assets/blood-utilitys/
│           └── lang/en_us.json               # Language file
├── build.gradle                              # Gradle build config
├── gradle.properties                         # Gradle properties
├── settings.gradle                           # Gradle settings
├── gradlew & gradlew.bat                     # Gradle wrapper
└── build.bat                                 # Windows build script
```

## Prerequisites

- Java 21 or higher
- Gradle (or use the included wrapper)

## Building the Mod

### Windows
```bash
cd D:\blood-utilitys
build.bat
```

### Linux/Mac
```bash
cd blood-utilitys
./gradlew build
```

The compiled JAR will be in `build/libs/blood-utilitys-1.0.0.jar`

## Installation

1. Install Fabric Loader for Minecraft 1.21.1
2. Copy the JAR to `.minecraft/mods/`
3. Launch Minecraft

## Features Overview

### HUD Display
- **Armor Durability**: Shows percentage for each armor piece (color-coded)
- **Totem Count**: Displays totems in inventory + offhand
- **FPS**: Real-time frame rate
- **Ping**: Network latency
- **Potions**: Active effects with remaining duration

### Hitbox Rendering
- Renders entity hitboxes with customizable appearance
- Only renders nearby entities (64 block range)
- Configurable outline and fill rendering

### Settings GUI
- Access with Right Shift (default)
- Modules tab: Toggle HUD and Hitbox
- HUD Editor: Drag elements to reposition
- Hitbox Settings: Customize appearance

## Keybinds

| Key | Action |
|-----|--------|
| Right Shift | Open Settings GUI |
| H | Toggle HUD |
| B | Toggle Hitbox |

All keybinds can be customized in Minecraft Controls menu.

## Configuration

Settings are stored in `config/blood-utilitys.json`:

```json
{
  "hudEnabled": true,
  "hitboxEnabled": true,
  "armorX": 10,
  "armorY": 30,
  "totemX": 10,
  "totemY": 50,
  "fpsX": 10,
  "fpsY": 70,
  "pingX": 10,
  "pingY": 90,
  "potionX": 10,
  "potionY": 110,
  "hitboxColor": 16711680,
  "hitboxThickness": 1.0,
  "hitboxAlpha": 0.5,
  "hitboxOutline": true,
  "hitboxFill": false
}
```

## Development

### Adding New Features

1. Create a new package under `src/main/java/com/bloodutilitys/feature/`
2. Implement your feature class
3. Register it in `BloodUtilitysClient.java`
4. Add configuration options to `ModConfig.java`

### Code Style

- Use meaningful variable names
- Keep methods focused and concise
- Add comments for complex logic
- Follow Minecraft/Fabric conventions

## Performance Notes

- HUD rendering is optimized with minimal draw calls
- Hitbox rendering only processes entities within 64 blocks
- All rendering uses efficient vertex consumers
- No tick-based processing for non-essential features

## Troubleshooting

### Mod not loading
- Ensure Fabric Loader is installed
- Check that Java 21+ is being used
- Verify the JAR is in the mods folder

### HUD not visible
- Press H to toggle HUD on
- Check that HUD elements are within screen bounds
- Verify settings in config file

### Performance issues
- Reduce hitbox rendering distance in code
- Disable hitbox fill rendering
- Check for conflicting mods

## License

MIT License - See LICENSE file for details
