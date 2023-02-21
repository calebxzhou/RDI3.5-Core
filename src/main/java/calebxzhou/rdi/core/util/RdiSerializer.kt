package calebxzhou.rdi.core.util

import com.google.gson.GsonBuilder

object RdiSerializer {
    @JvmField
	val gson = GsonBuilder().serializeNulls().create()
}
