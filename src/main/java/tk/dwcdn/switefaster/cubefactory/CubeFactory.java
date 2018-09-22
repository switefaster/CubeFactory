package tk.dwcdn.switefaster.cubefactory;

import cofh.redstoneflux.RedstoneFlux;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tk.dwcdn.switefaster.cubefactory.common.CommonProxy;

/**
 * @author switefaster
 */
@Mod(name = CubeFactory.NAME, modid = CubeFactory.MODID, version = CubeFactory.VERSION, acceptedMinecraftVersions = "[1.12,1.12.2]", dependencies = RedstoneFlux.VERSION_GROUP)
public class CubeFactory {

    public static final String NAME = "CubeFactory";
    public static final String MODID = "cubefactory";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(CubeFactory.MODID)
    public static CubeFactory instance;
    @SidedProxy(serverSide = "tk.dwcdn.switefaster.cubefactory.common.CommonProxy", clientSide = "tk.dwcdn.switefaster.cubefactory.client.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
