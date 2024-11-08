package calebxzhou.rdi.core.mixin;

import calebxzhou.rdi.core.misc.WindowSizeManager;
import com.mojang.blaze3d.platform.*;
import kotlin.Pair;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

/**
 * Created by calebzhou on 2022-09-18,20:40.
 */
@Mixin(Window.class)
public class mBiggerWindow {

	@Shadow
	@Final
	private long window;

	@Shadow
	private int windowedWidth;

	@Shadow
	private int windowedHeight;

	@Shadow
	private int width;

	@Shadow
	private int height;

	@Inject(method = "<init>",locals = LocalCapture.CAPTURE_FAILEXCEPTION,
			at = @At(value = "INVOKE",target = "Lorg/lwjgl/glfw/GLFW;glfwDefaultWindowHints()V"))
	private void max(WindowEventHandler windowEventHandler, ScreenManager screenManager, DisplayData displayData, String string, String string2, CallbackInfo ci, Optional optional, Monitor monitor){
		VideoMode mode = monitor.getCurrentMode();
		Pair<Integer, Integer> wh = WindowSizeManager.getWidthHeightByResolution(mode.getWidth(), mode.getHeight());
		windowedWidth=width=wh.getFirst();
		windowedHeight=height=wh.getSecond();
	}
}
