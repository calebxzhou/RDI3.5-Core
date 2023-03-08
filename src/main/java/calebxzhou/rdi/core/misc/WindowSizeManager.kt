package calebxzhou.rdi.core.misc

/**
 * Created  on 2023-02-28,11:06.
 */
object WindowSizeManager {
    //根据屏幕宽度高度，获取窗口宽度高度
    @JvmStatic
    fun getWidthHeightByResolution(rW:Int,rH:Int): Pair<Int,Int>{
        if(rW>1920 && rH > 1080)
            return Pair(1600,900)
        if(rW>=1600 && rH>=900)
            return Pair(1280,720)
        return Pair(800,480)
    }
}
