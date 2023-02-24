package calebxzhou.rdi.core

import java.io.InputStream

/**
 * Created  on 2023-02-24,22:47.
 */
object LucidFont {
    @JvmStatic
    fun getFontStream():InputStream{
        return javaClass.classLoader.getResourceAsStream("rdi.font.ttf")!!
    }
}
