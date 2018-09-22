package tk.dwcdn.switefaster.cubefactory.entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import tk.dwcdn.switefaster.cubefactory.CubeFactory;

import java.lang.ref.WeakReference;
import java.util.UUID;

/**
 * @author switefaster
 */
public class FakePlayerLoader {

    private static GameProfile gameProfile;
    private static WeakReference<FakePlayer> fakePlayer;

    public FakePlayerLoader() {
        gameProfile = new GameProfile(UUID.randomUUID(), "[" + CubeFactory.NAME + "]");
        fakePlayer = new WeakReference<>(null);
    }

    @SuppressWarnings("all")
    public static WeakReference<FakePlayer> getFakePlayer(WorldServer server) {
        if (fakePlayer.get() == null) {
            fakePlayer = new WeakReference<>(FakePlayerFactory.get(server, gameProfile));
        } else {
            fakePlayer.get().world = server;
        }
        return fakePlayer;
    }

}
