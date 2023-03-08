package calebxzhou.rdi.core.mixin;

import calebxzhou.rdi.core.util.GraphicUtils;
import dev.lambdaurora.spruceui.util.RenderUtil;
import net.minecraft.client.gui.components.AbstractSelectionList;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Created by calebzhou on 2022-10-02,12:15.
 */
@Mixin(Screen.class)
public class mNoDirtBackground {
	@Overwrite
	public void renderDirtBackground(int vOffset) {
		GraphicUtils.clearGreen();
	}
}
@Mixin(AbstractSelectionList.class)
class mNoDirtBackground2{
	@Mutable
	@Shadow
	private boolean renderBackground = false;
	@Mutable
	@Shadow
	private boolean renderTopAndBottom = false;
}
