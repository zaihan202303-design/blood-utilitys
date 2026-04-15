package com.bloodutilitys.ui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import com.bloodutilitys.config.ModConfig;
import com.bloodutilitys.ui.widget.ColorPickerWidget;
import com.bloodutilitys.ui.widget.HudEditorWidget;

public class SettingsScreen extends Screen {
	private Screen parent;
	private int currentTab = 0;
	private HudEditorWidget hudEditor;
	private ColorPickerWidget colorPicker;
	
	public SettingsScreen(Screen parent) {
		super(Text.literal("BLOOD UTILITYS Settings"));
		this.parent = parent;
	}
	
	@Override
	protected void init() {
		this.clearChildren();
		
		int centerX = this.width / 2;
		int centerY = this.height / 2;
		
		// Tab buttons
		this.addDrawableChild(ButtonWidget.builder(Text.literal("Modules"), button -> currentTab = 0)
			.dimensions(centerX - 150, 20, 70, 20).build());
		this.addDrawableChild(ButtonWidget.builder(Text.literal("HUD Editor"), button -> currentTab = 1)
			.dimensions(centerX - 75, 20, 70, 20).build());
		this.addDrawableChild(ButtonWidget.builder(Text.literal("Hitbox"), button -> currentTab = 2)
			.dimensions(centerX, 20, 70, 20).build());
		this.addDrawableChild(ButtonWidget.builder(Text.literal("Back"), button -> this.close())
			.dimensions(centerX + 75, 20, 70, 20).build());
		
		if (currentTab == 0) {
			initModulesTab(centerX, centerY);
		} else if (currentTab == 1) {
			initHudEditorTab(centerX, centerY);
		} else if (currentTab == 2) {
			initHitboxTab(centerX, centerY);
		}
	}
	
	private void initModulesTab(int centerX, int centerY) {
		this.addDrawableChild(ButtonWidget.builder(
			Text.literal("HUD: " + (ModConfig.hudEnabled ? "ON" : "OFF")),
			button -> {
				ModConfig.hudEnabled = !ModConfig.hudEnabled;
				ModConfig.save();
				button.setMessage(Text.literal("HUD: " + (ModConfig.hudEnabled ? "ON" : "OFF")));
			})
			.dimensions(centerX - 75, centerY - 40, 150, 20).build());
		
		this.addDrawableChild(ButtonWidget.builder(
			Text.literal("Hitbox: " + (ModConfig.hitboxEnabled ? "ON" : "OFF")),
			button -> {
				ModConfig.hitboxEnabled = !ModConfig.hitboxEnabled;
				ModConfig.save();
				button.setMessage(Text.literal("Hitbox: " + (ModConfig.hitboxEnabled ? "ON" : "OFF")));
			})
			.dimensions(centerX - 75, centerY, 150, 20).build());
	}
	
	private void initHudEditorTab(int centerX, int centerY) {
		hudEditor = new HudEditorWidget(centerX - 100, centerY - 80, 200, 160);
		this.addDrawable(hudEditor);
	}
	
	private void initHitboxTab(int centerX, int centerY) {
		this.addDrawableChild(ButtonWidget.builder(
			Text.literal("Outline: " + (ModConfig.hitboxOutline ? "ON" : "OFF")),
			button -> {
				ModConfig.hitboxOutline = !ModConfig.hitboxOutline;
				ModConfig.save();
				button.setMessage(Text.literal("Outline: " + (ModConfig.hitboxOutline ? "ON" : "OFF")));
			})
			.dimensions(centerX - 75, centerY - 60, 150, 20).build());
		
		this.addDrawableChild(ButtonWidget.builder(
			Text.literal("Fill: " + (ModConfig.hitboxFill ? "ON" : "OFF")),
			button -> {
				ModConfig.hitboxFill = !ModConfig.hitboxFill;
				ModConfig.save();
				button.setMessage(Text.literal("Fill: " + (ModConfig.hitboxFill ? "ON" : "OFF")));
			})
			.dimensions(centerX - 75, centerY - 30, 150, 20).build());
		
		this.addDrawableChild(new SliderWidget(centerX - 75, centerY, 150, 20, Text.literal("Thickness"), ModConfig.hitboxThickness / 5.0) {
			@Override
			protected void updateMessage() {
				this.setMessage(Text.literal("Thickness: " + String.format("%.1f", this.value * 5.0)));
			}
			
			@Override
			protected void applyValue() {
				ModConfig.hitboxThickness = (float)(this.value * 5.0);
				ModConfig.save();
			}
		});
		
		this.addDrawableChild(new SliderWidget(centerX - 75, centerY + 30, 150, 20, Text.literal("Alpha"), ModConfig.hitboxAlpha) {
			@Override
			protected void updateMessage() {
				this.setMessage(Text.literal("Alpha: " + String.format("%.2f", this.value)));
			}
			
			@Override
			protected void applyValue() {
				ModConfig.hitboxAlpha = (float)this.value;
				ModConfig.save();
			}
		});
	}
	
	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		this.renderBackground(context, mouseX, mouseY, delta);
		
		int centerX = this.width / 2;
		context.drawCenteredTextWithShadow(this.textRenderer, this.title, centerX, 10, 0xFF0000);
		
		super.render(context, mouseX, mouseY, delta);
	}
	
	@Override
	public void close() {
		this.client.setScreen(parent);
	}
	
	@Override
	public boolean shouldCloseOnEsc() {
		return true;
	}
}
