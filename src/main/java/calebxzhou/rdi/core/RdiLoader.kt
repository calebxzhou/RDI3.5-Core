package calebxzhou.rdi.core

import calebxzhou.rdi.core.constant.RdiSounds
import calebxzhou.rdi.core.loader.LoadProgressRecorder
import calebxzhou.rdi.core.misc.HwSpec
import calebxzhou.rdi.core.model.RdiUser
import calebxzhou.rdi.core.sound.RdiSoundPlayer
import calebxzhou.rdi.core.util.UuidUtils
import joptsimple.OptionParser
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.minecraft.client.User
import okhttp3.internal.wait
import org.apache.commons.io.FileUtils
import java.io.IOException
import java.nio.charset.StandardCharsets
import javax.sound.midi.Instrument
import javax.sound.midi.MidiSystem


/**
 * Created by calebzhou on 2022-10-14,20:53.
 */
object RdiLoader {
    @JvmStatic
    fun onMinecraftStart(args: Array<String>) = GlobalScope.launch {
        LoadProgressRecorder.loadStartTime = System.currentTimeMillis()
        logger.info("开始载入客户端 RDI部分 客户端启动参数：${args.contentToString()}")
        launch {
            logger.info("开始读取用户信息")
            initUser(args)
        }
        launch {
            //播放启动音效
            RdiSoundPlayer.playOgg(RdiSounds.Launch.inputStream)
        }
        launch {
            logger.info("载入硬件信息")
            HwSpec.currentHwSpec = HwSpec.loadSystemSpec()
        }
        launch {
            logger.info("载入网络模块")
        }

    }




    private fun initUser(args: Array<String>) {
        val parser = OptionParser()
        parser.allowsUnrecognizedOptions()
        val nameSpec = parser.accepts("username").withRequiredArg()
        val uuidSpec = parser.accepts("uuid").withRequiredArg().defaultsTo("00000000")
        val userTypeSpec = parser.accepts("userType").withRequiredArg().defaultsTo(User.Type.LEGACY.name)
        val specSet = parser.parse(*args)

        val name = nameSpec.value(specSet)
        logger.info("昵称 {}",name)
        //PCL启动器会用00000000开头的uuid，不要让他用这样的，要生成新的
        //mc的uuid刚读出来是不带横线的，给他加上
        val uuid = UuidUtils.uuidAddDash(
            if(uuidSpec.value(specSet).startsWith("00000000"))
                UuidUtils.createUuidByName(name)
            else
                uuidSpec.value(specSet)
        )
        logger.info("UUID {}",uuid)
        val userType = userTypeSpec.value(specSet)
        logger.info("账户类型 {}",userType)
        val passwd = try {
            FileUtils.readFileToString(RdiUser.getUserPasswordFile(uuid),StandardCharsets.UTF_8)
        } catch (e: IOException) {
            logger.warn("没有读取到密码： {}", e.message)
            ""
        }
        logger.info("密码 {}",passwd.length)
        RdiCore.currentRdiUser = RdiUser(uuid, name, passwd, userType)
    }


}
