package calebxzhou.rdi.core.mixin;

import calebxzhou.rdi.core.util.GraphicUtils;
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
		GraphicUtils.clearGreen();
	}
}
