package calebxzhou.rdi.core.util

import java.nio.charset.StandardCharsets
import java.util.*

/**
 * Created by calebxzhou on 2022-09-16,17:14.
 */
object UuidUtils {
    //把不带横线的uuid转换成带横线的
    fun uuidAddDash(uuidNoDash: String): String {
        return uuidNoDash.replaceFirst(
            "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)".toRegex(),
            "$1-$2-$3-$4-$5"
        )
    }

    fun createUuidByName(playerName: String): String {
        return UUID.nameUUIDFromBytes("OfflinePlayer:$playerName".toByteArray(StandardCharsets.UTF_8)).toString()
    }
}
