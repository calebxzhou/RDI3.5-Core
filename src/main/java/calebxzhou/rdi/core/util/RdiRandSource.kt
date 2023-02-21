package calebxzhou.rdi.core.util

import net.minecraft.util.RandomSource
import net.minecraft.world.level.levelgen.LegacyRandomSource.LegacyPositionalRandomFactory
import net.minecraft.world.level.levelgen.MarsagliaPolarGaussian
import net.minecraft.world.level.levelgen.PositionalRandomFactory
import java.util.SplittableRandom
import java.util.concurrent.ThreadLocalRandom

/**
 * Created  on 2022-10-24,21:36.
 */
object RdiRandSource:RandomSource {
    private const val seed = 1145141919810L
    private val gaussianSource = MarsagliaPolarGaussian(this)
    private val spRand = SplittableRandom(seed)
    override fun fork(): RandomSource {
        return RdiRandSource
    }

    override fun forkPositional(): PositionalRandomFactory {
        return LegacyPositionalRandomFactory(seed)
    }

    override fun setSeed(seed: Long) {
    }

    override fun nextInt(): Int {
        return ThreadLocalRandom.current().nextInt()
    }

    override fun nextInt(bound: Int): Int {
        return ThreadLocalRandom.current().nextInt(bound)
    }

    override fun nextLong(): Long {
        return ThreadLocalRandom.current().nextLong()
    }

    override fun nextBoolean(): Boolean {
        return ThreadLocalRandom.current().nextBoolean()
    }

    override fun nextFloat(): Float {
        return ThreadLocalRandom.current().nextFloat()
    }

    override fun nextDouble(): Double {
        return ThreadLocalRandom.current().nextDouble()
    }

    override fun nextGaussian(): Double {
        return gaussianSource.nextGaussian()
    }
    fun spNextInt(origin:Int,bound:Int) : Int{
        return spRand.nextInt(origin,bound)
    }

}
