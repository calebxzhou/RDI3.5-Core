package calebxzhou.rdi.core.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * Created by calebzhou on 2022-10-02,12:15.
 */
@Mixin(Screen.class)
public class mNoDirtBackground {
	@Overwrite
	public void renderDirtBackground(int vOffset) {
		GlStateManager._clearColor(50/255f, 70/255f, 50/255f, 1.0F);
		GlStateManager._clear(16384, Minecraft.ON_OSX);
		RenderSystem.enableBlend();
	}
}
