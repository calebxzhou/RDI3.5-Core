@file:JvmName("DialogUtilsKt")
package calebxzhou.rdi.core.util


import calebxzhou.rdi.core.NetworkPackets
import calebxzhou.rdi.core.RdiCore
import com.mojang.blaze3d.platform.GLX
import com.mojang.blaze3d.platform.Window
import net.minecraft.client.Minecraft
import org.lwjgl.glfw.GLFW
import org.lwjgl.util.tinyfd.TinyFileDialogs

object DialogUtils {
    /*public static void showInfoIngame(String msg, MessageType type){
        Color color= Color.WHITE;
        switch (type){
            case INFO -> color=Color.WHITE;
            case ERROR -> color= Color.RED.brighter();
            case SUCCESS -> color= Color.GREEN.brighter();
        }
        showInfoIngame(Component.literal(msg),color);
    }*/
    /*public static void showInfoIngame(Component msg,Color color){
        Toast toast = SystemToast.create(msg,null,color.getRGB());
        ToastComponent toastManager = Minecraft.getInstance().getToasts();
        toastManager.addToast(toast);
    }*/
	@JvmStatic
	fun showYesNoBox(msg: String): Boolean {
        return TinyFileDialogs.tinyfd_messageBox("提示", msg, "yesno", "question", false)
    }
    @JvmStatic
    fun showMessageBox(type: String, title: String, msg: String) {
        TinyFileDialogs.tinyfd_messageBox(title, msg, "ok", type, true)
    }
    @JvmStatic
    fun showMessageBox(type: String, msg: String) {
        showMessageBox(type, "", msg)
    }
    @JvmStatic
    fun showPopup(type: String, msg: String) {
        showPopup(type, "", msg)
    }
    @JvmStatic
    fun shouldClose(window: Window):Boolean{
       if (GLX._shouldClose(window)) {
            //val rain: String = RdiCore.currentWeather?.realTimeWeather?.rainDesc?:""
            return if (showYesNoBox("真的要退出RDI客户端吗？\n")) {
                GLFW.glfwSetWindowShouldClose(Minecraft.getInstance().window.window, true)
                true
            } else {
                GLFW.glfwSetWindowShouldClose(Minecraft.getInstance().window.window, false)
                false
            }
        }
        return false
    }
    //info warning error c
    @JvmStatic
	fun showPopup(type: String, title: String, msg: String) {
        TinyFileDialogs.tinyfd_notifyPopup(title,msg,type);
    }
}
