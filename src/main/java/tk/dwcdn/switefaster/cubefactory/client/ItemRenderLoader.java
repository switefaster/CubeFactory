package tk.dwcdn.switefaster.cubefactory.client;

import tk.dwcdn.switefaster.cubefactory.block.BlockLoader;
import tk.dwcdn.switefaster.cubefactory.item.ItemLoader;

/**
 * @author switefaster
 */
public class ItemRenderLoader {

    public ItemRenderLoader() {
        ItemLoader.registerRenders();
        BlockLoader.registerRenders();
    }

}
