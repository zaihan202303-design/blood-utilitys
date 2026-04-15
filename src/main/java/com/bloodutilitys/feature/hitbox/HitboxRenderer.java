package com.bloodutilitys.feature.hitbox;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;
import com.bloodutilitys.config.ModConfig;
import org.joml.Matrix4f;

public class HitboxRenderer {
	private static final MinecraftClient CLIENT = MinecraftClient.getInstance();
	
	public static void render(WorldRenderContext context) {
		if (!ModConfig.hitboxEnabled || CLIENT.player == null) return;
		
		for (Entity entity : CLIENT.world.getEntities()) {
			if (entity == CLIENT.player || !(entity instanceof LivingEntity)) continue;
			
			double distance = CLIENT.player.distanceTo(entity);
			if (distance > 64) continue;
			
			renderEntityHitbox(context, entity);
		}
	}
	
	private static void renderEntityHitbox(WorldRenderContext context, Entity entity) {
		Box box = entity.getBoundingBox();
		double x = box.minX - CLIENT.getEntityRenderDispatcher().camera.getPos().x;
		double y = box.minY - CLIENT.getEntityRenderDispatcher().camera.getPos().y;
		double z = box.minZ - CLIENT.getEntityRenderDispatcher().camera.getPos().z;
		double width = box.getXLength();
		double height = box.getYLength();
		double depth = box.getZLength();
		
		int color = ModConfig.hitboxColor;
		float r = ((color >> 16) & 0xFF) / 255.0f;
		float g = ((color >> 8) & 0xFF) / 255.0f;
		float b = (color & 0xFF) / 255.0f;
		float a = ModConfig.hitboxAlpha;
		
		if (ModConfig.hitboxOutline) {
			drawBoxOutline(context, x, y, z, width, height, depth, r, g, b, a);
		}
		
		if (ModConfig.hitboxFill) {
			drawBoxFill(context, x, y, z, width, height, depth, r, g, b, a * 0.3f);
		}
	}
	
	private static void drawBoxOutline(WorldRenderContext context, double x, double y, double z, 
										double w, double h, double d, float r, float g, float b, float a) {
		VertexConsumer consumer = context.consumers().getBuffer(net.minecraft.client.render.RenderLayer.getLines());
		Matrix4f matrix = context.matrixStack().peek().getPositionMatrix();
		
		float thickness = ModConfig.hitboxThickness;
		
		// Draw 12 edges
		drawLine(consumer, matrix, (float)x, (float)y, (float)z, (float)(x+w), (float)y, (float)z, r, g, b, a, thickness);
		drawLine(consumer, matrix, (float)x, (float)y, (float)z, (float)x, (float)(y+h), (float)z, r, g, b, a, thickness);
		drawLine(consumer, matrix, (float)x, (float)y, (float)z, (float)x, (float)y, (float)(z+d), r, g, b, a, thickness);
		
		drawLine(consumer, matrix, (float)(x+w), (float)y, (float)z, (float)(x+w), (float)(y+h), (float)z, r, g, b, a, thickness);
		drawLine(consumer, matrix, (float)(x+w), (float)y, (float)z, (float)(x+w), (float)y, (float)(z+d), r, g, b, a, thickness);
		
		drawLine(consumer, matrix, (float)x, (float)(y+h), (float)z, (float)(x+w), (float)(y+h), (float)z, r, g, b, a, thickness);
		drawLine(consumer, matrix, (float)x, (float)(y+h), (float)z, (float)x, (float)(y+h), (float)(z+d), r, g, b, a, thickness);
		
		drawLine(consumer, matrix, (float)x, (float)y, (float)(z+d), (float)(x+w), (float)y, (float)(z+d), r, g, b, a, thickness);
		drawLine(consumer, matrix, (float)x, (float)y, (float)(z+d), (float)x, (float)(y+h), (float)(z+d), r, g, b, a, thickness);
		
		drawLine(consumer, matrix, (float)(x+w), (float)(y+h), (float)z, (float)(x+w), (float)(y+h), (float)(z+d), r, g, b, a, thickness);
		drawLine(consumer, matrix, (float)(x+w), (float)y, (float)(z+d), (float)(x+w), (float)(y+h), (float)(z+d), r, g, b, a, thickness);
		drawLine(consumer, matrix, (float)x, (float)(y+h), (float)(z+d), (float)(x+w), (float)(y+h), (float)(z+d), r, g, b, a, thickness);
	}
	
	private static void drawLine(VertexConsumer consumer, Matrix4f matrix, float x1, float y1, float z1, 
								  float x2, float y2, float z2, float r, float g, float b, float a, float thickness) {
		consumer.vertex(matrix, x1, y1, z1).color(r, g, b, a).next();
		consumer.vertex(matrix, x2, y2, z2).color(r, g, b, a).next();
	}
	
	private static void drawBoxFill(WorldRenderContext context, double x, double y, double z, 
									  double w, double h, double d, float r, float g, float b, float a) {
		VertexConsumer consumer = context.consumers().getBuffer(net.minecraft.client.render.RenderLayer.getTranslucent(false));
		Matrix4f matrix = context.matrixStack().peek().getPositionMatrix();
		
		float x1 = (float)x, y1 = (float)y, z1 = (float)z;
		float x2 = (float)(x+w), y2 = (float)(y+h), z2 = (float)(z+d);
		
		// Front face
		drawQuad(consumer, matrix, x1, y1, z1, x2, y1, z1, x2, y2, z1, x1, y2, z1, r, g, b, a);
		// Back face
		drawQuad(consumer, matrix, x1, y1, z2, x1, y2, z2, x2, y2, z2, x2, y1, z2, r, g, b, a);
		// Top face
		drawQuad(consumer, matrix, x1, y2, z1, x2, y2, z1, x2, y2, z2, x1, y2, z2, r, g, b, a);
		// Bottom face
		drawQuad(consumer, matrix, x1, y1, z1, x1, y1, z2, x2, y1, z2, x2, y1, z1, r, g, b, a);
		// Left face
		drawQuad(consumer, matrix, x1, y1, z1, x1, y2, z1, x1, y2, z2, x1, y1, z2, r, g, b, a);
		// Right face
		drawQuad(consumer, matrix, x2, y1, z1, x2, y1, z2, x2, y2, z2, x2, y2, z1, r, g, b, a);
	}
	
	private static void drawQuad(VertexConsumer consumer, Matrix4f matrix, float x1, float y1, float z1,
								  float x2, float y2, float z2, float x3, float y3, float z3,
								  float x4, float y4, float z4, float r, float g, float b, float a) {
		consumer.vertex(matrix, x1, y1, z1).color(r, g, b, a).next();
		consumer.vertex(matrix, x2, y2, z2).color(r, g, b, a).next();
		consumer.vertex(matrix, x3, y3, z3).color(r, g, b, a).next();
		consumer.vertex(matrix, x4, y4, z4).color(r, g, b, a).next();
	}
}
