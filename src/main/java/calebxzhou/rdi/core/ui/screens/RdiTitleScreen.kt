package calebxzhou.rdi.core.ui.screens

import calebxzhou.rdi.core.constant.MessageType
import calebxzhou.rdi.core.constant.RdiFiles
import calebxzhou.rdi.core.constant.RdiSharedConstants
import calebxzhou.rdi.core.loader.LoadProgressRecorder
import calebxzhou.rdi.core.misc.ServerConnector
import calebxzhou.rdi.core.util.DialogUtils
import calebxzhou.rdi.core.util.GraphicUtils
import calebxzhou.rdi.core.util.TimeUtils
import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.platform.InputConstants
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.text2speech.Narrator
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiComponent
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.components.PlayerFaceRenderer
import net.minecraft.client.gui.screens.OptionsScreen
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen
import net.minecraft.client.multiplayer.PlayerInfo
import net.minecraft.client.resources.DefaultPlayerSkin
import net.minecraft.network.chat.Component
import net.minecraft.sounds.SoundSource
import java.io.File
import java.util.*

class RdiTitleScreen : Screen(Component.literal("主界面")) {
    private lateinit var  startBtn : Button
    private lateinit var  settingsBtn: Button
    private lateinit var  aboutBtn: Button
    public override fun init() {


        Minecraft.getInstance().updateTitle()
        //关闭音乐
        Minecraft.getInstance().options.getSoundSourceOptionInstance(SoundSource.MUSIC).set(0.0)
        //检查RDID文件是否存在，不存在则进入注册界面
        if(!RdiFiles.OptionFile.exists()){
       // minecraft!!.setScreen(RdidInitScreen())
        }
    }
    override fun shouldCloseOnEsc(): Boolean {
        return false
    }

    override fun render(matrices: PoseStack, mouseX: Int, mouseY: Int, delta: Float) {
        GraphicUtils.clearGreen()
        RenderSystem.setShaderTexture(0,  DefaultPlayerSkin.getDefaultSkin(UUID.randomUUID()))
        PlayerFaceRenderer.draw(matrices, width/2-40, height/2-5, 16,false,false);
        font.draw(matrices, "${TimeUtils.getTimePeriodOfDay()}好！", width / 2.0f - 18, height / 2f, 0xFFFFFF)

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
            ServerConnector.connect(this)
        }
    }




}
