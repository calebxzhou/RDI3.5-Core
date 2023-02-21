package calebxzhou.rdi.core

import calebxzhou.rdi.core.model.RdiUser
import net.minecraft.world.level.storage.LevelStorageSource
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer

/**
 * Created  on 2023-01-26,22:50.
 */
val logger: Logger = LogManager.getLogger("RDI-Core4")
class RdiCore :ModInitializer {
    companion object {
    lateinit var rdiLevelSource: LevelStorageSource
    var currentRdiUser: RdiUser? = null
    var gameReady = false
        private set
}

    override fun onInitialize(mod: ModContainer?) {


    }
}
