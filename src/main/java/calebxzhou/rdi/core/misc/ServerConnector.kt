package calebxzhou.rdi.core.misc

import calebxzhou.rdi.core.constant.RdiSharedConstants
import calebxzhou.rdi.core.logger
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.ConnectScreen
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.multiplayer.ServerData
import net.minecraft.client.multiplayer.resolver.ServerAddress
import net.minecraft.network.Connection
import java.net.InetSocketAddress

/**
 * Created  on 2023-02-21,20:26.
 */
object ServerConnector {
    fun connect(screen: Screen){
        ConnectScreen.startConnecting(
            screen,
            Minecraft.getInstance(),
            ServerAddress(RdiSharedConstants.SERVER_IP_ADDR,RdiSharedConstants.SERVER_PORT),
            ServerData(RdiSharedConstants.MODID_DISPLAY,RdiSharedConstants.SERVER_IP_ADDR,false)
        )
    }
    fun ping(){
        try {
            Connection.connectToServer(InetSocketAddress(RdiSharedConstants.SERVER_IP_ADDR,RdiSharedConstants.SERVER_PORT),true)
        } catch (e: Exception) {
            logger.warn("无法ping服务器：${e.localizedMessage}")
        }
    }
}
