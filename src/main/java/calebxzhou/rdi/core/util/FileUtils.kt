package calebxzhou.rdi.core.util

import calebxzhou.rdi.core.RdiCore
import java.io.File
import java.io.InputStream

object FileUtils {
    val GAME_FOLDER = File(".")
    val MOD_FOLDER = File(GAME_FOLDER, "mods")
    fun getJarResourceFile(fileInJar: String?): File {
        return File(getJarResourceFileUrl(fileInJar))
    }

    fun getJarResourceFileUrl(fileInJar: String?): String {
        return FileUtils::class.java.classLoader.getResource(fileInJar)!!.file.replace("%20", " ")
    }

    fun getJarAsset(fileInJar: String): InputStream {
        return RdiCore::class.java.getResourceAsStream("/assets/rdi-core/$fileInJar")!!
    }
}
