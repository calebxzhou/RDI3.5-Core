package calebxzhou.rdi.core.mixin;
import calebxzhou.rdi.core.ui.screens.RdiLoadingOverlay;
import calebxzhou.rdi.core.ui.screens.RdiTitleScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.LoadingOverlay;
import net.minecraft.client.gui.screens.Overlay;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.server.packs.resources.ReloadInstance;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;
import java.util.function.Consumer;

@Mixin(Minecraft.class)
public abstract class mNewScreens {

    @Shadow public abstract void setScreen(@Nullable Screen screen);

    //不显示mc本身的加载画面
    @Redirect(method = "<init>",
    at=@At(value = "INVOKE",target = "Lnet/minecraft/client/Minecraft;setOverlay(Lnet/minecraft/client/gui/screens/Overlay;)V"))
    private void notDisplayOverlay(Minecraft instance, Overlay overlay){
        LoadingOverlay splashOverlay = (( LoadingOverlay) overlay);
        AccessSplashOverlay accessSplashOverlay = ((AccessSplashOverlay) splashOverlay);
        instance.setOverlay(new RdiLoadingOverlay(
                instance,
                accessSplashOverlay.getReload(),
                accessSplashOverlay.getOnFinish())
        );
    }
    //不进入mc本身的标题界面
    @Redirect(method = "<init>",at=@At(value = "INVOKE",target = "Lnet/minecraft/client/Minecraft;setScreen(Lnet/minecraft/client/gui/screens/Screen;)V"))
    private void goRdiTitleScreen(Minecraft instance, Screen screen){
        setScreen(new RdiTitleScreen());
    }
}

@Mixin(LoadingOverlay.class)
interface AccessSplashOverlay{
    @Accessor
    ReloadInstance getReload();
    @Accessor
    Consumer<Optional<Throwable>> getOnFinish();
}
