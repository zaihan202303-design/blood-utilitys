package com.bloodutilitys.ui.widget;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import com.bloodutilitys.config.ModConfig;

public class HudEditorWidget implements Drawable, Element, Selectable {
	private int x, y, width, height;
	private int draggedElement = -1;
	private int dragOffsetX, dragOffsetY;
	
	private static final int[] ELEMENT_X = {ModConfig.armorX, ModConfig.totemX, ModConfig.fpsX, ModConfig.pingX, ModConfig.potionX};
	private static final int[] ELEMENT_Y = {ModConfig.armorY, ModConfig.totemY, ModConfig.fpsY, ModConfig.pingY, ModConfig.potionY};
	private static final String[] ELEMENT_NAMES = {"Armor", "Totems", "FPS", "Ping", "Potions"};
	private static final int[] ELEMENT_WIDTHS = {100, 60, 50, 60, 100};
	
	public HudEditorWidget(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		context.fill(x, y, x + width, y + height, 0x1A000000);
		context.drawBorder(x, y, width, height, 0xFFFF0000);
		
		context.drawCenteredTextWithShadow(context.getClient().textRenderer, "Drag elements to reposition", x + width / 2, y + 5, 0xFFFFFF);
		
		int offsetY = y + 20;
		for (int i = 0; i < ELEMENT_NAMES.length; i++) {
			int elemX = x + 10;
			int elemY = offsetY;
			int elemWidth = ELEMENT_WIDTHS[i];
			
			int bgColor = draggedElement == i ? 0xFF333333 : 0xFF1A1A1A;
			context.fill(elemX, elemY, elemX + elemWidth, elemY + 15, bgColor);
			context.drawBorder(elemX, elemY, elemWidth, 15, 0xFFFF0000);
			
			context.drawTextWithShadow(context.getClient().textRenderer, ELEMENT_NAMES[i], elemX + 2, elemY + 3, 0xFFFFFF);
			
			offsetY += 20;
		}
	}
	
	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
		return false;
	}
	
	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
		if (draggedElement >= 0) {
			int newX = (int)(mouseX - dragOffsetX);
			int newY = (int)(mouseY - dragOffsetY);
			
			updateElementPosition(draggedElement, newX, newY);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		int offsetY = y + 20;
		for (int i = 0; i < ELEMENT_NAMES.length; i++) {
			int elemX = x + 10;
			int elemY = offsetY;
			int elemWidth = ELEMENT_WIDTHS[i];
			
			if (mouseX >= elemX && mouseX <= elemX + elemWidth && mouseY >= elemY && mouseY <= elemY + 15) {
				draggedElement = i;
				dragOffsetX = (int)(mouseX - elemX);
				dragOffsetY = (int)(mouseY - elemY);
				return true;
			}
			
			offsetY += 20;
		}
		return false;
	}
	
	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		draggedElement = -1;
		return false;
	}
	
	private void updateElementPosition(int element, int newX, int newY) {
		switch (element) {
			case 0 -> ModConfig.armorX = newX;
			case 1 -> ModConfig.totemX = newX;
			case 2 -> ModConfig.fpsX = newX;
			case 3 -> ModConfig.pingX = newX;
			case 4 -> ModConfig.potionX = newX;
		}
		
		switch (element) {
			case 0 -> ModConfig.armorY = newY;
			case 1 -> ModConfig.totemY = newY;
			case 2 -> ModConfig.fpsY = newY;
			case 3 -> ModConfig.pingY = newY;
			case 4 -> ModConfig.potionY = newY;
		}
		
		ModConfig.save();
	}
	
	@Override
	public net.minecraft.client.gui.navigation.SelectionType getType() {
		return net.minecraft.client.gui.navigation.SelectionType.NONE;
	}
}
