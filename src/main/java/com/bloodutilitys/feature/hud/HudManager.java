package com.bloodutilitys.feature.hud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import com.bloodutilitys.config.ModConfig;
import java.util.Collection;

public class HudManager {
	private static final MinecraftClient CLIENT = MinecraftClient.getInstance();
	
	public static void render(DrawContext context) {
		if (!ModConfig.hudEnabled || CLIENT.player == null) return;
		
		int textColor = 0xFF0000;
		int bgColor = 0x1A000000;
		
		renderArmor(context, textColor, bgColor);
		renderTotems(context, textColor, bgColor);
		renderFPS(context, textColor, bgColor);
		renderPing(context, textColor, bgColor);
		renderPotions(context, textColor, bgColor);
	}
	
	private static void renderArmor(DrawContext context, int textColor, int bgColor) {
		int x = ModConfig.armorX;
		int y = ModConfig.armorY;
		
		context.fill(x - 2, y - 2, x + 102, y + 22, bgColor);
		context.drawBorder(x - 2, y - 2, 104, 24, 0xFFFF0000);
		
		context.drawTextWithShadow(CLIENT.textRenderer, "Armor", x + 2, y + 2, textColor);
		
		int armorX = x + 2;
		for (int i = 0; i < 4; i++) {
			ItemStack armor = CLIENT.player.getInventory().getArmorStack(3 - i);
			if (!armor.isEmpty()) {
				int durability = armor.getMaxDamage() - armor.getDamage();
				int maxDurability = armor.getMaxDamage();
				int percent = (durability * 100) / maxDurability;
				
				int color = percent > 50 ? 0xFF00FF00 : percent > 25 ? 0xFFFFFF00 : 0xFFFF0000;
				context.drawTextWithShadow(CLIENT.textRenderer, percent + "%", armorX, y + 12, color);
				armorX += 24;
			}
		}
	}
	
	private static void renderTotems(DrawContext context, int textColor, int bgColor) {
		int x = ModConfig.totemX;
		int y = ModConfig.totemY;
		
		int totems = 0;
		for (int i = 0; i < CLIENT.player.getInventory().size(); i++) {
			if (CLIENT.player.getInventory().getStack(i).getItem() == Items.TOTEM_OF_UNDYING) {
				totems += CLIENT.player.getInventory().getStack(i).getCount();
			}
		}
		if (CLIENT.player.getOffHandStack().getItem() == Items.TOTEM_OF_UNDYING) {
			totems += CLIENT.player.getOffHandStack().getCount();
		}
		
		context.fill(x - 2, y - 2, x + 62, y + 22, bgColor);
		context.drawBorder(x - 2, y - 2, 64, 24, 0xFFFF0000);
		context.drawTextWithShadow(CLIENT.textRenderer, "Totems: " + totems, x + 2, y + 8, textColor);
	}
	
	private static void renderFPS(DrawContext context, int textColor, int bgColor) {
		int x = ModConfig.fpsX;
		int y = ModConfig.fpsY;
		int fps = (int) (1.0 / (CLIENT.getRenderTickCounter().getTickDelta() / 20.0));
		
		context.fill(x - 2, y - 2, x + 52, y + 22, bgColor);
		context.drawBorder(x - 2, y - 2, 54, 24, 0xFFFF0000);
		context.drawTextWithShadow(CLIENT.textRenderer, "FPS: " + fps, x + 2, y + 8, textColor);
	}
	
	private static void renderPing(DrawContext context, int textColor, int bgColor) {
		int x = ModConfig.pingX;
		int y = ModConfig.pingY;
		int ping = 0;
		
		if (CLIENT.player != null && CLIENT.getNetworkHandler() != null) {
			ping = CLIENT.getNetworkHandler().getPlayerListEntry(CLIENT.player.getUuid()).getLatency();
		}
		
		context.fill(x - 2, y - 2, x + 62, y + 22, bgColor);
		context.drawBorder(x - 2, y - 2, 64, 24, 0xFFFF0000);
		context.drawTextWithShadow(CLIENT.textRenderer, "Ping: " + ping, x + 2, y + 8, textColor);
	}
	
	private static void renderPotions(DrawContext context, int textColor, int bgColor) {
		int x = ModConfig.potionX;
		int y = ModConfig.potionY;
		
		Collection<StatusEffect> effects = CLIENT.player.getStatusEffects();
		int offsetY = 0;
		
		for (StatusEffect effect : effects) {
			int duration = CLIENT.player.getStatusEffectInstance(effect).getDuration();
			String name = effect.getName().getString();
			String text = name + " " + (duration / 20) + "s";
			
			context.fill(x - 2, y + offsetY - 2, x + 102, y + offsetY + 12, bgColor);
			context.drawBorder(x - 2, y + offsetY - 2, 104, 14, 0xFFFF0000);
			context.drawTextWithShadow(CLIENT.textRenderer, text, x + 2, y + offsetY + 2, textColor);
			
			offsetY += 14;
		}
	}
}
