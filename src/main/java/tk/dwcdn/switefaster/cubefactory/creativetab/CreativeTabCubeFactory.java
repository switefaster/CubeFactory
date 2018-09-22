package tk.dwcdn.switefaster.cubefactory.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import tk.dwcdn.switefaster.cubefactory.CubeFactory;
import tk.dwcdn.switefaster.cubefactory.item.ItemLoader;

import javax.annotation.Nonnull;

/**
 * @author switefaster
 */
public class CreativeTabCubeFactory extends CreativeTabs {

    CreativeTabCubeFactory() {
        super(CubeFactory.MODID);
    }

    @Nonnull
    @Override
    public ItemStack getTabIconItem() {
        return ItemLoader.ITEM_ANTIMATTER.getDefaultInstance();
    }
}
