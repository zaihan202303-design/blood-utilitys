package com.bloodutilitys.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ModConfig {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final File CONFIG_FILE = new File("config/blood-utilitys.json");
	
	public static boolean hudEnabled = true;
	public static boolean hitboxEnabled = true;
	
	public static int armorX = 10;
	public static int armorY = 30;
	public static int totemX = 10;
	public static int totemY = 50;
	public static int fpsX = 10;
	public static int fpsY = 70;
	public static int pingX = 10;
	public static int pingY = 90;
	public static int potionX = 10;
	public static int potionY = 110;
	
	public static int hitboxColor = 0xFF0000;
	public static float hitboxThickness = 1.0f;
	public static float hitboxAlpha = 0.5f;
	public static boolean hitboxOutline = true;
	public static boolean hitboxFill = false;
	
	public static void load() {
		if (!CONFIG_FILE.exists()) {
			save();
			return;
		}
		
		try (FileReader reader = new FileReader(CONFIG_FILE)) {
			ConfigData data = GSON.fromJson(reader, ConfigData.class);
			if (data != null) {
				hudEnabled = data.hudEnabled;
				hitboxEnabled = data.hitboxEnabled;
				armorX = data.armorX;
				armorY = data.armorY;
				totemX = data.totemX;
				totemY = data.totemY;
				fpsX = data.fpsX;
				fpsY = data.fpsY;
				pingX = data.pingX;
				pingY = data.pingY;
				potionX = data.potionX;
				potionY = data.potionY;
				hitboxColor = data.hitboxColor;
				hitboxThickness = data.hitboxThickness;
				hitboxAlpha = data.hitboxAlpha;
				hitboxOutline = data.hitboxOutline;
				hitboxFill = data.hitboxFill;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void save() {
		CONFIG_FILE.getParentFile().mkdirs();
		
		ConfigData data = new ConfigData();
		data.hudEnabled = hudEnabled;
		data.hitboxEnabled = hitboxEnabled;
		data.armorX = armorX;
		data.armorY = armorY;
		data.totemX = totemX;
		data.totemY = totemY;
		data.fpsX = fpsX;
		data.fpsY = fpsY;
		data.pingX = pingX;
		data.pingY = pingY;
		data.potionX = potionX;
		data.potionY = potionY;
		data.hitboxColor = hitboxColor;
		data.hitboxThickness = hitboxThickness;
		data.hitboxAlpha = hitboxAlpha;
		data.hitboxOutline = hitboxOutline;
		data.hitboxFill = hitboxFill;
		
		try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
			GSON.toJson(data, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static class ConfigData {
		public boolean hudEnabled = true;
		public boolean hitboxEnabled = true;
		public int armorX = 10;
		public int armorY = 30;
		public int totemX = 10;
		public int totemY = 50;
		public int fpsX = 10;
		public int fpsY = 70;
		public int pingX = 10;
		public int pingY = 90;
		public int potionX = 10;
		public int potionY = 110;
		public int hitboxColor = 0xFF0000;
		public float hitboxThickness = 1.0f;
		public float hitboxAlpha = 0.5f;
		public boolean hitboxOutline = true;
		public boolean hitboxFill = false;
	}
}
