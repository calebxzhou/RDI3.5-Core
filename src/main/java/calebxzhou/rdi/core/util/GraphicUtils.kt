package calebxzhou.rdi.core.util

import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.Minecraft

/**
 * Created  on 2023-02-25,10:33.
 */
object GraphicUtils {
    //清屏，填充默认的绿色
    @JvmStatic
    fun clearGreen(){
        clearColor(50 / 255f, 80 / 255f, 50 / 255f, 1.0f)
    }
    //清屏，填充默认的白色
    @JvmStatic
    fun clearWhite(){
        clearColor(1f, 1f, 1f, 1.0f)
    }
    @JvmStatic
    fun clearColor(red:Float,green:Float,blue:Float){
        clearColor(red, green, blue,1f)
    }
    @JvmStatic
    fun clearColor(red:Float,green:Float,blue:Float,alpha:Float){
        GlStateManager._clearColor(red,green,blue,alpha)
        GlStateManager._clear(16384, Minecraft.ON_OSX)
        RenderSystem.enableBlend()
    }
}
