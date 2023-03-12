package calebxzhou.rdi.core.util

/**
 * Created  on 2023-03-12,23:06.
 */
object SysInfoUtils {
    fun getUsedMemory():Long{
        val runtime = Runtime.getRuntime()
        return runtime.totalMemory() - runtime.freeMemory()
    }
}
