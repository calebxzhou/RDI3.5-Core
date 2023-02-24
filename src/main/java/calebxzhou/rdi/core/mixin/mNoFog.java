package calebxzhou.rdi.core.mixin;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created  on 2023-02-22,8:09.
 */
@Mixin(FogRenderer.class)
public class mNoFog {
	@Inject(method = "setupFog",at=@At("RETURN"))
	private static void setupFog(Camera camera, FogRenderer.FogMode fogMode, float farPlaneDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
		RenderSystem.setShaderFogStart(-8.0F);
		RenderSystem.setShaderFogEnd(1_000_000.0F);
		RenderSystem.setShaderFogShape(FogShape.CYLINDER);
	}
	}
