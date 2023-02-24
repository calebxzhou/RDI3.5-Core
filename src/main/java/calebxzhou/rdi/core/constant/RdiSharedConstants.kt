package calebxzhou.rdi.core.constant

import java.io.File

/**
 * Created by calebxzhou on 2022-09-10,15:09.
 */
object RdiSharedConstants {
    //mod id
    const val MODID = "rdi-core"

    //mod id中文名
    const val MODID_DISPLAY = "RDI navtech"

    //是否为调试模式,本地用
    const val DEBUG = true
    const val SERVER_PORT = 19198
    @JvmField
    val SERVER_IP_ADDR = if (DEBUG) "127.0.0.1" else "davisoft.cn"

    //版本号与协议号
    const val PROTOCOL_VERSION = 0x40

    //显示版本
    const val CORE_VERSION_DISPLAY = "4.0α1(2304xx)"
    //核心版本
    const val CORE_VERSION = 0x400


    private val RDI_FOLDER = File("mods/rdi")
    private val RDI_ASSETS_FOLDER = File(RDI_FOLDER,"assets")
    private val RDI_PROFILES_FOLDER = File(RDI_FOLDER,"profiles")
    @JvmField
	val RDI_SOUND_FOLDER = File(RDI_ASSETS_FOLDER, "sound")

    @JvmField
    val RDI_USERS_FOLDER = File(RDI_PROFILES_FOLDER, "users")

}
