package com.bloodutilitys.keybind;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import com.bloodutilitys.ui.SettingsScreen;
import com.bloodutilitys.config.ModConfig;

public class KeyBindManager {
	public static KeyBinding openGuiKey;
	public static KeyBinding toggleHudKey;
	public static KeyBinding toggleHitboxKey;
	
	public static void register() {
		openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.blood-utilitys.open_gui",
			GLFW.GLFW_KEY_RIGHT_SHIFT,
			"category.blood-utilitys"
		));
		
		toggleHudKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.blood-utilitys.toggle_hud",
			GLFW.GLFW_KEY_H,
			"category.blood-utilitys"
		));
		
		toggleHitboxKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.blood-utilitys.toggle_hitbox",
			GLFW.GLFW_KEY_B,
			"category.blood-utilitys"
		));
	}
	
	public static void handleInput() {
		MinecraftClient client = MinecraftClient.getInstance();
		
		while (openGuiKey.wasPressed()) {
			client.setScreen(new SettingsScreen(null));
		}
		
		while (toggleHudKey.wasPressed()) {
			ModConfig.hudEnabled = !ModConfig.hudEnabled;
			ModConfig.save();
		}
		
		while (toggleHitboxKey.wasPressed()) {
			ModConfig.hitboxEnabled = !ModConfig.hitboxEnabled;
			ModConfig.save();
		}
	}
}
