package calebxzhou.rdi.core.mixin;

import calebxzhou.rdi.core.ui.components.PasswordEditBox;
import com.google.common.base.Strings;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.FormattedCharSink;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Created  on 2023-02-24,21:57.
 */
@Mixin(value = EditBox.class,priority = 1989)
public class mUsePasswordEditBox {/*
	@Shadow
	private String value;

	@Redirect(method = "renderButton",at=@At(value = "INVOKE",target = "Lnet/minecraft/client/gui/Font;drawShadow(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/util/FormattedCharSequence;FFI)I"))
	private int passwordRender(Font font, PoseStack poseStack, FormattedCharSequence text, float x, float y, int color){
		if(((EditBox)(Object)this) instanceof PasswordEditBox){
			font.drawShadow(poseStack, Strings.repeat("*",value.length()),x,y,color);
		}else{
			font.drawShadow(poseStack,text,x,y,color);
		}
		return 0;
	}*/
}
