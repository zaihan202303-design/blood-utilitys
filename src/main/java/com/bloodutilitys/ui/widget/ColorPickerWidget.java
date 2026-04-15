package com.bloodutilitys.ui.widget;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import com.bloodutilitys.config.ModConfig;

public class ColorPickerWidget implements Drawable, Element, Selectable {
	private int x, y, width, height;
	private boolean dragging = false;
	
	public ColorPickerWidget(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		context.fill(x, y, x + width, y + height, ModConfig.hitboxColor | 0xFF000000);
		context.drawBorder(x, y, width, height, 0xFFFFFFFF);
	}
	
	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
			dragging = true;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		dragging = false;
		return false;
	}
	
	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
		if (dragging) {
			int relX = (int)((mouseX - x) / width * 255);
			int relY = (int)((mouseY - y) / height * 255);
			
			relX = Math.max(0, Math.min(255, relX));
			relY = Math.max(0, Math.min(255, relY));
			
			int r = relX;
			int g = relY;
			int b = 0;
			
			ModConfig.hitboxColor = (r << 16) | (g << 8) | b;
			ModConfig.save();
			return true;
		}
		return false;
	}
	
	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
		return false;
	}
	
	@Override
	public net.minecraft.client.gui.navigation.SelectionType getType() {
		return net.minecraft.client.gui.navigation.SelectionType.NONE;
	}
}
