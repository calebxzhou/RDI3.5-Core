package calebxzhou.rdi.core.mixin.gameplay;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.CauldronBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//大锅快速收集水
@Mixin(CauldronBlock.class)
public class mCauldronFastWaterCollection {
    @Overwrite
    public static boolean shouldHandlePrecipitation(Level level, Biome.Precipitation precipitation) {
        return precipitation == Biome.Precipitation.RAIN || precipitation == Biome.Precipitation.SNOW;
    }
}
