package calebxzhou.rdi.core.ui.screens

import calebxzhou.rdi.core.constant.MessageType
import calebxzhou.rdi.core.util.GraphicUtils
import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.Overlay
import net.minecraft.server.packs.resources.ReloadInstance
import java.util.*
import java.util.function.Consumer

/**
 * Created  on 2023-02-25,9:21.
 */
class InfoOverlay(msgType: MessageType,msg:String) : Overlay() {
    private val mc :Minecraft = Minecraft.getInstance()

    override fun render(poseStack: PoseStack, mouseX: Int, mouseY: Int, partialTick: Float) {
        val baseW = mc.window.guiScaledWidth/2
        val baseH = mc.window.guiScaledHeight/5*3
        val w = mc.window.guiScaledWidth
        val h = 100
        GraphicUtils.clearColor(0f,0f,0f,0.5f)
        fill(poseStack,baseW,baseH,baseW+w,baseH+h,0x00FF00FF)
    }
}
