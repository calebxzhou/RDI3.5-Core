package calebxzhou.rdi.core.mixin;

import calebxzhou.rdi.core.RdiSharedConstants;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class mWindowTitle {

	@Inject(method = "createTitle",at=@At("HEAD"), cancellable = true)
	private void createTitle(CallbackInfoReturnable<String> cir){
		cir.setReturnValue(RdiSharedConstants.MODID_DISPLAY+" "+ RdiSharedConstants.CORE_VERSION_DISPLAY);
	}

    /*@Overwrite
    private String createTitle(){
        return RdiSharedConstants.MODID_DISPLAY+" "+ RdiSharedConstants.CORE_VERSION_DISPLAY;
    }*/
}
