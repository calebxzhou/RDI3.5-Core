package calebxzhou.rdi.core.screen

import calebxzhou.rdi.core.RdiSharedConstants
import calebxzhou.rdi.core.loader.LoadProgressRecorder
import calebxzhou.rdi.core.misc.MusicPlayer
import calebxzhou.rdi.core.util.DialogUtils
import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.platform.InputConstants
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.ConnectScreen
import net.minecraft.client.gui.screens.OptionsScreen
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen
import net.minecraft.client.multiplayer.ServerData
import net.minecraft.client.multiplayer.resolver.ServerAddress
import net.minecraft.network.chat.Component
import net.minecraft.sounds.SoundSource
import org.lwjgl.opengl.GL11.*
import java.io.File

class RdiTitleScreen : Screen(Component.literal("主界面")) {

    override fun shouldCloseOnEsc(): Boolean {
        return false
    }

    override fun render(matrices: PoseStack, mouseX: Int, mouseY: Int, delta: Float) {
        GlStateManager._clearColor(50/255f, 130/255f, 235/255f,1f);
        GlStateManager._clear(16384, Minecraft.ON_OSX)
        RenderSystem.enableBlend()
        font.draw(matrices, "——>Enter", width / 2.0f - 30, height / 2f, 0x00FFFFFF)
        //font.draw(matrices,  "△t=${"%.2f".format(LoadProgressRecorder.getLoadTimeSeconds())}s >${"%.2f".format(LoadProgressRecorder.getLoadTimePercentBeyondPlayers())}%", 0f, 0f, 0x00FFFFFF)
    }

    override fun tick() {
        val handle = Minecraft.getInstance().window.window
        if (InputConstants.isKeyDown(handle, InputConstants.KEY_0)) {
            if (RdiSharedConstants.DEBUG) minecraft!!.setScreen(JoinMultiplayerScreen(this))
            return
        }
        if (InputConstants.isKeyDown(handle, InputConstants.KEY_K)) {
            minecraft!!.setScreen(SelectWorldScreen(this))
            return
        }
        if (InputConstants.isKeyDown(handle, InputConstants.KEY_M)) {
            try {
                minecraft!!.setScreen(
                    Class.forName("com.terraformersmc.modmenu.gui.ModsScreen").getConstructor(
                        Screen::class.java
                    ).newInstance(this) as Screen
                )
            } catch (e: Exception) {
                DialogUtils.showMessageBox("error", "必须安装ModMenu模组以使用本功能！！")
                e.printStackTrace()
            }
            return
        }
        if (InputConstants.isKeyDown(handle, InputConstants.KEY_O)) {
            MusicPlayer.playOggAsync(File(RdiSharedConstants.RDI_SOUND_FOLDER, "settings.ogg"))
            minecraft!!.setScreen(OptionsScreen(this, minecraft!!.options))
            return
        }
        if (InputConstants.isKeyDown(handle, InputConstants.KEY_P)) {
            minecraft!!.setScreen(PasswordScreen())
            return
        }
        if (InputConstants.isKeyDown(handle, InputConstants.KEY_0)) {
            minecraft!!.setScreen(JoinMultiplayerScreen(this))
            return
        }
        if (InputConstants.isKeyDown(handle, InputConstants.KEY_RETURN) || InputConstants.isKeyDown(
                handle,
                InputConstants.KEY_NUMPADENTER
            )
        ) {
            try {
                if(LoadProgressRecorder.musicPlayJob!=null)
                    LoadProgressRecorder.musicPlayJob!!.stop()
            }catch (_:ThreadDeath){}
            catch (e:Exception) {
                e.printStackTrace()
            }
            ConnectScreen.startConnecting(
                this,
                minecraft,
                ServerAddress.parseString(if(RdiSharedConstants.DEBUG)"127.0.0.1:19198" else ""),
                ServerData("1",if(RdiSharedConstants.DEBUG)"127.0.0.1:19198" else "",false)
            )
        }
    }

    public override fun init() {
        Minecraft.getInstance().updateTitle()
        Minecraft.getInstance().options.getSoundSourceOptionInstance(SoundSource.MUSIC).set(0.0)
    }


}
