# 🩸 BLOOD UTILITYS - Get Your JAR (No Installation Needed!)

Your mod is complete and ready. Here are **3 ways** to get a working JAR without installing anything:

---

## ✅ **OPTION 1: GitHub Actions (Easiest - Recommended)**

**No installation needed. Completely free.**

### Steps:

1. **Create GitHub Account** (free)
   - Go to: https://github.com/signup

2. **Create New Repository**
   - Go to: https://github.com/new
   - Name: `blood-utilitys`
   - Click "Create repository"

3. **Upload Your Project**
   - Download Git for Windows: https://git-scm.com/download/win
   - Open Command Prompt in `D:\blood-utilitys`
   - Run these commands:
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git branch -M main
   git remote add origin https://github.com/YOUR_USERNAME/blood-utilitys.git
   git push -u origin main
   ```
   (Replace `YOUR_USERNAME` with your GitHub username)

4. **Wait for Build**
   - Go to your repo on GitHub
   - Click "Actions" tab
   - Wait for build to complete (2-5 minutes)
   - Click the workflow run
   - Download artifact: `blood-utilitys-mod`

5. **Install JAR**
   - Extract the ZIP
   - Copy `blood-utilitys-1.0.0.jar` to `.minecraft\mods\`
   - Launch Minecraft with Fabric Loader 1.21.11
   - Press Right Shift to open mod GUI

---

## ✅ **OPTION 2: Gitpod (Web-Based IDE)**

**No installation needed. Completely free.**

### Steps:

1. **Create GitHub Account** (if you don't have one)
   - Go to: https://github.com/signup

2. **Upload Project to GitHub**
   - Follow steps 2-3 from Option 1

3. **Open in Gitpod**
   - Go to: `https://gitpod.io/#https://github.com/YOUR_USERNAME/blood-utilitys`
   - Wait for workspace to load

4. **Build the Mod**
   - In the terminal, run: `./gradlew build`
   - Wait for build to complete

5. **Download JAR**
   - In the file explorer on the left, navigate to: `build/libs/`
   - Right-click `blood-utilitys-1.0.0.jar`
   - Select "Download"

6. **Install JAR**
   - Copy to `.minecraft\mods\`
   - Launch Minecraft with Fabric Loader 1.21.11
   - Press Right Shift to open mod GUI

---

## ✅ **OPTION 3: Docker (If Installed)**

**If you already have Docker installed.**

### Steps:

1. **Run Build Script**
   - Double-click: `D:\blood-utilitys\build-docker.bat`
   - Wait for build to complete

2. **Install JAR**
   - JAR will be at: `build\libs\blood-utilitys-1.0.0.jar`
   - Copy to `.minecraft\mods\`
   - Launch Minecraft with Fabric Loader 1.21.11
   - Press Right Shift to open mod GUI

---

## 🎯 **Which Option Should I Choose?**

| Option | Difficulty | Installation | Time |
|--------|-----------|--------------|------|
| **GitHub Actions** | Easy | Git (5 min) | 5-10 min |
| **Gitpod** | Very Easy | None | 5-10 min |
| **Docker** | Medium | Docker (20 min) | 5 min |

**Recommendation**: Start with **GitHub Actions** or **Gitpod** (no installation needed)

---

## 📥 **Installation (All Options)**

Once you have the JAR:

1. **Locate your Minecraft folder**
   - Windows: `%APPDATA%\.minecraft\`
   - Or: `C:\Users\[YourUsername]\AppData\Roaming\.minecraft\`

2. **Create/Open mods folder**
   - Go to: `.minecraft\mods\`
   - Create folder if it doesn't exist

3. **Copy JAR**
   - Copy `blood-utilitys-1.0.0.jar` to `.minecraft\mods\`

4. **Launch Minecraft**
   - Make sure Fabric Loader 1.21.11 is installed
   - Launch Minecraft
   - Mod should load automatically

5. **Use the Mod**
   - Press **Right Shift** to open Settings GUI
   - Press **H** to toggle HUD
   - Press **B** to toggle Hitbox

---

## ❓ **FAQ**

**Q: Do I need to install Java?**
A: No! GitHub Actions and Gitpod handle it automatically.

**Q: Is it free?**
A: Yes! All options are completely free.

**Q: How long does it take?**
A: 5-10 minutes total (mostly waiting for build).

**Q: Can I use it on multiplayer servers?**
A: Yes! It's client-side only and safe for all servers.

**Q: What if the build fails?**
A: Let me know and I'll help debug it.

---

## 🚀 **Ready to Get Started?**

Choose an option above and follow the steps. You'll have a working JAR in 10 minutes!

**Need help?** Just ask! 🎉
