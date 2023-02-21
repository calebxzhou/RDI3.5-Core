package calebxzhou.rdi.core.mixin;

import calebxzhou.rdi.core.util.RdiRandSource;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;


/**
 * Created  on 2022-10-25,21:40.
 */
@Mixin(RandomSource.class)
public interface mUseRdiRandomSource {
	@Overwrite
	static RandomSource create(long seed) {
		return RdiRandSource.INSTANCE;
	}
}
