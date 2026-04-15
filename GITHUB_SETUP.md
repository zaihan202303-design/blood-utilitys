# 🩸 BLOOD UTILITYS - GitHub Setup

## Quick Start

Follow these steps to get your compiled JAR:

### Step 1: Create a GitHub Account
Go to https://github.com/signup (free)

### Step 2: Create a New Repository
1. Go to https://github.com/new
2. Name it: `blood-utilitys`
3. Click "Create repository"

### Step 3: Upload the Project
1. Open Command Prompt
2. Run these commands:
```bash
cd D:\blood-utilitys
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/blood-utilitys.git
git push -u origin main
```

(Replace `YOUR_USERNAME` with your GitHub username)

### Step 4: Wait for Build
1. Go to your repository on GitHub
2. Click the "Actions" tab
3. Wait for the build to complete (usually 2-5 minutes)
4. Once done, click the workflow run
5. Scroll down to "Artifacts"
6. Download `blood-utilitys-mod`

### Step 5: Install the JAR
1. Extract the downloaded ZIP
2. Copy `blood-utilitys-1.0.0.jar` to `.minecraft\mods\`
3. Launch Minecraft with Fabric Loader 1.21.11
4. Press Right Shift to open the mod GUI

---

## Alternative: Use Gitpod (No Git Knowledge Needed)

1. Go to: https://gitpod.io/#https://github.com/YOUR_USERNAME/blood-utilitys
2. Wait for the workspace to load
3. In the terminal, run: `./gradlew build`
4. Download the JAR from `build/libs/`

---

## Need Help?

If you get stuck, just let me know which step!
