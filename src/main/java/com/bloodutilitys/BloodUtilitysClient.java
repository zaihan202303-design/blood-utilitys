package com.bloodutilitys;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import com.bloodutilitys.config.ModConfig;
import com.bloodutilitys.feature.hud.HudManager;
import com.bloodutilitys.feature.hitbox.HitboxRenderer;
import com.bloodutilitys.keybind.KeyBindManager;
import com.bloodutilitys.ui.SettingsScreen;

public class BloodUtilitysClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModConfig.load();
		KeyBindManager.register();
		
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			KeyBindManager.handleInput();
		});
		
		HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {
			HudManager.render(drawContext);
		});
		
		WorldRenderEvents.AFTER_ENTITIES.register(context -> {
			if (ModConfig.hitboxEnabled) {
				HitboxRenderer.render(context);
			}
		});
	}
}
