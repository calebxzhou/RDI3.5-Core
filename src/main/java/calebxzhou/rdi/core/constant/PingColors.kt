package calebxzhou.rdi.core.constant

import calebxzhou.rdi.core.util.ColorUtil

object PingColors {
    private const val PING_START = 0
    private const val PING_MID = 150
    private const val PING_END = 300
    private const val COLOR_GREY = 0x535353
    private const val COLOR_START = 0x00E676
    private const val COLOR_MID = 0xD6CD30
    private const val COLOR_END = 0xE53935
    fun getColor(ping: Int): Int {
        if (ping < PING_START) {
            return COLOR_GREY
        }
        return if (ping < PING_MID) {
            ColorUtil.interpolate(
                COLOR_START,
                COLOR_MID,
                computeOffset(PING_START, PING_MID, ping)
            )
        } else ColorUtil.interpolate(
            COLOR_MID,
            COLOR_END,
            computeOffset(PING_MID, PING_END, Math.min(ping, PING_END))
        )
    }

    private fun computeOffset(start: Int, end: Int, value: Int): Float {
        val offset = (value - start) / (end - start).toFloat()
        return offset.coerceIn(0.0f,1.0f)
    }
}
