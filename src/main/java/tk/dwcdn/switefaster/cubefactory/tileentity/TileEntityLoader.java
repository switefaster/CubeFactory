package tk.dwcdn.switefaster.cubefactory.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tk.dwcdn.switefaster.cubefactory.CubeFactory;

/**
 * @author switefaster
 */
public class TileEntityLoader {

    public TileEntityLoader(FMLPreInitializationEvent event) {
        registerTileEntity(TileEntityAntimatterReactor.class, "antimatter_reactor");
        registerTileEntity(TileEntityAcceleratorCore.class, "accelerator_core");
        registerTileEntity(TileEntityAcceleratorEmitter.class, "accelerator_emitter");
    }

    private static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id) {
        GameRegistry.registerTileEntity(tileEntityClass, CubeFactory.MODID + ":" + id);
    }

}
