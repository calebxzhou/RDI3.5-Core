package calebxzhou.rdi.core.mixin;

import calebxzhou.rdi.core.ui.screens.RdiTitleScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created  on 2023-02-08,22:37.
 */
@Mixin(TitleScreen.class)
public class mGoNewTitleScreen {
	@Inject(method = "init",at=@At("HEAD"))
	private void alwaysGoNew(CallbackInfo ci){
		Minecraft.getInstance().setScreen(new RdiTitleScreen());
	}

}
