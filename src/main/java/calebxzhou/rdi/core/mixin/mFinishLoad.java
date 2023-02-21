package calebxzhou.rdi.core.mixin;

import calebxzhou.rdi.core.loader.LoadProgressRecorder;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.model.AtlasSet;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ModelManager.class)
public class mFinishLoad {
    @Inject(method = "apply",at=@At("TAIL"))
    private void finishLoad(ModelManager.ReloadState reloadState, ProfilerFiller profilerFiller, CallbackInfo ci){
		LoadProgressRecorder.onFinish();
    }
}
