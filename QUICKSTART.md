# BLOOD UTILITYS - Quick Start

## 🚀 Build & Install (Windows)

1. Open Command Prompt in `D:\blood-utilitys`
2. Run: `build.bat`
3. Wait for build to complete
4. JAR will be in: `build\libs\blood-utilitys-1.0.0.jar`
5. Copy JAR to: `.minecraft\mods\`
6. Launch Minecraft with Fabric Loader

## ⌨️ Default Controls

- **Right Shift** - Open Settings GUI
- **H** - Toggle HUD
- **B** - Toggle Hitbox

## 🎮 In-Game Usage

### First Launch
1. Press Right Shift to open Settings
2. Go to "Modules" tab to enable/disable features
3. Go to "HUD Editor" to position HUD elements
4. Go to "Hitbox" to customize hitbox appearance

### HUD Elements
- **Armor**: Shows durability % for each piece (color-coded)
- **Totems**: Counts totems in inventory + offhand
- **FPS**: Current frame rate
- **Ping**: Network latency in ms
- **Potions**: Active effects with duration

### Hitbox Rendering
- Renders entity hitboxes when enabled
- Customizable color, thickness, and transparency
- Toggle outline and/or fill rendering

## 📁 Configuration

Settings saved in: `config/blood-utilitys.json`

Edit directly or use in-game GUI to customize:
- HUD element positions
- Hitbox color (RGB)
- Hitbox thickness
- Hitbox transparency (alpha)

## 🔧 Troubleshooting

**Mod not loading?**
- Ensure Fabric Loader is installed
- Check Java version (need 21+)
- Verify JAR is in mods folder

**HUD not showing?**
- Press H to toggle HUD on
- Check config file for valid positions
- Ensure HUD elements aren't off-screen

**Performance issues?**
- Disable hitbox fill rendering
- Reduce entity render distance
- Check for conflicting mods

## 📚 More Info

See `SETUP.md` for detailed development guide
See `README.md` for full feature documentation

---

**Version**: 1.0.0  
**Minecraft**: 1.21.1  
**Loader**: Fabric  
**License**: MIT
