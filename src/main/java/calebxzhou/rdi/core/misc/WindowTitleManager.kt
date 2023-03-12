package calebxzhou.rdi.core.misc

import calebxzhou.rdi.core.constant.RdiSharedConstants
import calebxzhou.rdi.core.util.SysInfoUtils
import net.minecraft.client.Minecraft
import org.lwjgl.glfw.GLFW
import java.util.*

/**
 * Created  on 2023-02-25,10:48.
 */
object WindowTitleManager {
    val timer = Timer("RDI-WindowTitleTimer")
    const val baseTitle = "RDi${RdiSharedConstants.CORE_VERSION_DISPLAY}"
    @JvmStatic
    var currentTitle : String = ""
    fun onInit() {
        timer.schedule(object : TimerTask(){
            override fun run() {
                currentTitle = "$baseTitle ${SysInfoUtils.getUsedMemory() / 1024 / 1024}M"
                Minecraft.getInstance().window.setTitle(currentTitle)
            }

        },0,500L)
    }
}
