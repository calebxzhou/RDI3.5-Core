package calebxzhou.rdi.core.constant

import calebxzhou.rdi.core.util.FileUtils
import java.io.InputStream

/**
 * Created  on 2023-02-24,23:30.
 */
enum class RdiSounds(private val fileName:String) {


    Connect("connect"),
    Disconnect("disconnect"),
    Settings("settings"),
    Startup("startup"),
    Launch("launch"),;


    private val dir = "sounds/"
    private val ext = ".ogg"
    val inputStream: InputStream
        get() {
            return FileUtils.getJarAsset("${dir}${fileName}${ext}")
        }

}
