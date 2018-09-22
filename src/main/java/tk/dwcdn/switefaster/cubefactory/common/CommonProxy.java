package tk.dwcdn.switefaster.cubefactory.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tk.dwcdn.switefaster.cubefactory.block.BlockLoader;
import tk.dwcdn.switefaster.cubefactory.capability.CapabilityLoader;
import tk.dwcdn.switefaster.cubefactory.creativetab.CreativeTabsLoader;
import tk.dwcdn.switefaster.cubefactory.entity.FakePlayerLoader;
import tk.dwcdn.switefaster.cubefactory.inventory.GuiElementLoader;
import tk.dwcdn.switefaster.cubefactory.item.ItemLoader;
import tk.dwcdn.switefaster.cubefactory.potion.PotionLoader;
import tk.dwcdn.switefaster.cubefactory.tileentity.TileEntityLoader;

/**
 * @author switefaster
 */
public class CommonProxy {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        new ConfigLoader(event);
        new CapabilityLoader(event);
        new CreativeTabsLoader(event);
        new ItemLoader(event);
        new BlockLoader(event);
        new TileEntityLoader(event);
        new PotionLoader(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        new EventLoader();
        new GuiElementLoader();
        new FakePlayerLoader();
    }

    @SuppressWarnings("EmptyMethod")
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

}
