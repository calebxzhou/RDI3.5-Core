package calebxzhou.rdi.core.mixin;

import com.mojang.authlib.minecraft.UserApiService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import net.minecraft.client.Minecraft;
import net.minecraft.client.User;
import net.minecraft.client.main.GameConfig;
import net.minecraft.client.multiplayer.AccountProfileKeyPairManager;
import net.minecraft.client.multiplayer.ProfileKeyPairManager;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.nio.file.Path;

import static net.minecraft.client.multiplayer.ProfileKeyPairManager.EMPTY_KEY_MANAGER;

/**
 * Created  on 2022-11-04,10:42.
 */
@Mixin(MinecraftServer.class)
public class mAlwaysOfflineMode {
	@Overwrite
	public boolean usesAuthentication() {
		return false;
	}
	}
@Mixin(Minecraft.class)
class mFastCreateMojangService {
	@Overwrite
	private UserApiService createUserApiService(YggdrasilAuthenticationService yggdrasilAuthenticationService, GameConfig gameConfig) {
		return UserApiService.OFFLINE;
	}
}
@Mixin(ProfileKeyPairManager.class)
interface mFastCreateMojangService2{
	@Overwrite
	public static ProfileKeyPairManager create(UserApiService userApiService, User user, Path gameDirectory) {
		return (EMPTY_KEY_MANAGER);
	}
}
