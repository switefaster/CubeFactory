package tk.dwcdn.switefaster.cubefactory.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * @author switefaster
 */
public class CreativeTabsLoader {

    public static CreativeTabs tabCubeFactory;

    public CreativeTabsLoader(FMLPreInitializationEvent event) {
        tabCubeFactory = new CreativeTabCubeFactory();
    }

}
