package calebxzhou.rdi.core.misc

import calebxzhou.rdi.core.constant.RdiSharedConstants
import com.mojang.blaze3d.platform.InputConstants
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.KeyMapping
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientLevel
import org.lwjgl.glfw.GLFW

object KeyBinds {
    private var HOME_KEY: KeyMapping? = null
    fun init() {
        HOME_KEY = KeyBindingHelper.registerKeyBinding(
            KeyMapping(
                "回岛",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                RdiSharedConstants.MODID_DISPLAY
            )
        )
    }

    fun handleKeyActions(world: ClientLevel?) {
        val client = Minecraft.getInstance()
        if (client.isLocalServer || client.currentServer == null || client.player == null)
            return
        //按下H键返回空岛
        while (HOME_KEY!!.consumeClick()) {
            client.player!!.connection.sendCommand("home")
        }
    }
}
