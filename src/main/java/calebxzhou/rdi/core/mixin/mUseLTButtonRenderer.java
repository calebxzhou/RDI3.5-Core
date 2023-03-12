package calebxzhou.rdi.core.mixin;

import calebxzhou.libertorch.mc.gui.LTButtonRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Created  on 2023-03-08,9:25.
 */

@Mixin(AbstractWidget.class)
public abstract class mUseLTButtonRenderer {

	@Shadow
	protected float alpha;

	@Shadow
	protected abstract void renderBg(PoseStack poseStack, Minecraft minecraft, int mouseX, int mouseY);

	@Overwrite
	public void renderButton(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
		LTButtonRenderer.render(poseStack,(AbstractWidget) (Object)this,alpha,()-> renderBg(poseStack, Minecraft.getInstance(), mouseX, mouseY));
	}
}
