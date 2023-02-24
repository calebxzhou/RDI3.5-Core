package calebxzhou.rdi.core.ui.screens

import calebxzhou.rdi.core.ui.components.PasswordEditBox
import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.components.EditBox
import net.minecraft.client.gui.components.PlainTextButton
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component

/**
 * Created  on 2023-02-24,20:19.
 */
class RdidInitScreen:Screen(Component.literal("RDID注册与登录")) {
    private val baseWidth = 20;
    private val baseHeight = 20;
    private lateinit var  goRegisterBtn : Button
    private lateinit var  goLoginBtn: Button

    private lateinit var usrBox:EditBox
    private lateinit var pwdBox:EditBox
    private lateinit var submitBtn:Button
    private lateinit var changeModeBtn:Button
    private var isRegisterMode = true

    override fun init() {
        goRegisterBtn= PlainTextButton(baseWidth + 50,baseHeight + 10*8, 50,20, Component.literal("<我没有RDID，去注册>"),::onChangeToRegisterMode,font)
        goLoginBtn  = PlainTextButton(baseWidth + 50,baseHeight + 10*8, 50,20, Component.literal("<我有RDID，去登录>"),::onChangeToLoginMode,font)
        usrBox = EditBox(font, baseWidth, baseHeight + 10 * 3, 200, 10, Component.empty())
        usrBox.setHint(Component.literal("用户名"))
        addRenderableWidget(usrBox)
        pwdBox = PasswordEditBox(font, baseWidth, baseHeight + 10 * 6, 200, 10, Component.empty())
        pwdBox.setHint(Component.literal("密码"))
        addRenderableWidget(pwdBox)
        submitBtn = PlainTextButton(baseWidth,baseHeight + 10*8, 50,20, Component.literal("<立刻注册>"),::onSubmit,font)
        changeModeBtn = goLoginBtn
        addRenderableWidget(submitBtn)
        addRenderableWidget(changeModeBtn)

    }

    private fun onChangeToLoginMode(button: Button) {
        isRegisterMode = false
        changeModeBtn = goRegisterBtn
    }

    private fun onChangeToRegisterMode(button: Button) {
        isRegisterMode = true
        changeModeBtn = goLoginBtn
    }

    private fun onSubmit(button: Button) {
        if(!validateEditBoxes())
            return

        if(isRegisterMode){
            register()
        }else
            login()
    }
    private fun validateEditBoxes():Boolean {
        return true
    }
    private fun login() {


    }

    private fun register() {


    }



    override fun render(poseStack: PoseStack, mouseX: Int, mouseY: Int, partialTick: Float) {
        GlStateManager._clearColor(50 / 255f, 70 / 255f, 50 / 255f, 1.0f)
        GlStateManager._clear(16384, Minecraft.ON_OSX)
        RenderSystem.enableBlend()
        if(isRegisterMode){
            drawString(poseStack, font, "感谢您选择RDI服务器！首先您需要注册一个RDID（RDI账号）。", baseWidth, baseHeight, 0xffffff)
            drawString(poseStack, font, "RDID是您游玩本服务器的唯一凭证。因此，当您注册完成以后，务必牢记用户名与密码。", baseWidth, baseHeight+10, 0xffffff)
            drawString(poseStack, font, "1.输入自己想要的RDID用户名。用户名里只能有0-9数字、大小写字母。", baseWidth, baseHeight+10*2, 0xffffff)
            drawString(poseStack, font, "2.输入自己想要的密码。", baseWidth, baseHeight+10*5, 0xffffff)
        }else{
            drawString(poseStack, font, "请您登录RDID。", baseWidth, baseHeight, 0xffffff)
        }
        super.render(poseStack, mouseX, mouseY, partialTick)
    }
}
