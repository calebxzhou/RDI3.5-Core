package calebxzhou.rdi.core

import calebxzhou.rdi.core.misc.WindowTitleManager
import calebxzhou.rdi.core.model.RdiUser
import com.mojang.text2speech.Narrator
import net.minecraft.world.level.storage.LevelStorageSource
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer
import org.quiltmc.qsl.lifecycle.api.client.event.ClientLifecycleEvents

/**
 * Created  on 2023-01-26,22:50.
 */
val logger: Logger = LogManager.getLogger("RDI-Core4")
class RdiCore :ModInitializer {
    companion object {
    var currentRdiUser: RdiUser? = null
}

    override fun onInitialize(mod: ModContainer?) {
        ClientLifecycleEvents.READY.register{
            WindowTitleManager.onInit()
        }

    }
}
