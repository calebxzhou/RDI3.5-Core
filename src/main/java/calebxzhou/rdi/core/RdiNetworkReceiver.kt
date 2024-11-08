package calebxzhou.rdi.core

import calebxzhou.rdi.core.NetworkPackets
import calebxzhou.rdi.core.RdiCore.Companion.currentRdiUser
import calebxzhou.rdi.core.util.DialogUtils.showMessageBox
import calebxzhou.rdi.core.util.DialogUtils.showPopup
import calebxzhou.rdi.core.util.RdiSerializer
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientPacketListener
import net.minecraft.network.FriendlyByteBuf
import org.quiltmc.qsl.networking.api.PacketSender
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking

/**
 * Created by calebxzhou on 2022-09-18,22:56.
 */
object RdiNetworkReceiver {
    fun register() {
        ClientPlayNetworking.registerGlobalReceiver(NetworkPackets.SET_PASSWORD,::onSetPassword)
        ClientPlayNetworking.registerGlobalReceiver(NetworkPackets.DIALOG_INFO,::onReceiveDialogInfo)
        ClientPlayNetworking.registerGlobalReceiver(NetworkPackets.POPUP,::onReceivePopup)
    }

   /* private fun onReceiveWeather(
        minecraft: Minecraft,
        listener: ClientPacketListener,
        buf: FriendlyByteBuf,
        sender: PacketSender
    ) {
        RdiCore.currentWeather=RdiSerializer.gson.fromJson(buf.readUtf(),RdiWeather::class.java)
    }
    private fun onReceiveGeoLocation(
        minecraft: Minecraft,
        listener: ClientPacketListener,
        buf: FriendlyByteBuf,
        sender: PacketSender
    ) {
        RdiCore.currentGeoLocation=RdiSerializer.gson.fromJson(buf.readUtf(),RdiGeoLocation::class.java)
    }*/

    private fun onSetPassword(
        minecraft: Minecraft,
        listener: ClientPacketListener,
        buf: FriendlyByteBuf,
        sender: PacketSender
    ) {
        val pwd = buf.readUtf()
        currentRdiUser!!.pwd = pwd
        RdiCore.currentRdiUser!!.writePasswordToFile()
    }

    //接收服务器的弹框信息
    private fun onReceivePopup(
        minecraft: Minecraft,
        listener: ClientPacketListener,
        buf: FriendlyByteBuf,
        sender: PacketSender
    ) {
        val split = buf.readUtf().split("|")
        val type = split[0]
        val title = split[1]
        val content = split[2]
        showPopup(type, title, content)
    }

    //接收服务器的对话框信息
    private fun onReceiveDialogInfo(
        minecraft: Minecraft,
        listener: ClientPacketListener,
        buf: FriendlyByteBuf,
        sender: PacketSender
    ) {
        val split = buf.readUtf().split("|")
        val type = split[0]
        val title = split[1]
        val content = split[2]
        showMessageBox(type, title, content)
    }
}
