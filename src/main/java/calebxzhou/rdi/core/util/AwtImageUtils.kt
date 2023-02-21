package calebxzhou.rdi.core.util

import calebxzhou.rdi.core.RdiCore
import java.awt.Image
import javax.swing.ImageIcon

object AwtImageUtils {
    @JvmStatic
	fun createImage(path: String, description: String?): Image? {
        val imageURL = RdiCore::class.java.getResource(path)
        return if (imageURL == null) {
            System.err.println("Resource not found: $path")
            null
        } else {
            ImageIcon(imageURL, description).image
        }
    }
}
