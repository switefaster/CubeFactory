package tk.dwcdn.switefaster.cubefactory.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.dwcdn.switefaster.cubefactory.api.registry.AbstractCubeFactoryMassRegistry;

/**
 * @author switefaster
 */
public class ItemLoader {

    public static final Item ITEM_ANTIMATTER = new ItemAntimatter();
    public static final Item ITEM_CONTAINER_FILLER = new ItemContainerFiller();
    public static final Item ITEM_CONSTRUCTOR = new ItemConstructor();

    public ItemLoader(FMLPreInitializationEvent event) {
        register(ITEM_ANTIMATTER, "antimatter", 5e-6d);
        register(ITEM_CONTAINER_FILLER, "container_filler", 0d);
        register(ITEM_CONSTRUCTOR, "constructor", 0d);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        registerRender(ITEM_ANTIMATTER);
        registerRender(ITEM_CONTAINER_FILLER);
        registerRender(ITEM_CONSTRUCTOR);
    }

    private static void register(Item item, String name, double mass) {
        ForgeRegistries.ITEMS.register(item.setRegistryName(name));
        AbstractCubeFactoryMassRegistry.INSTANCE.registerItemMass(item, mass);
    }

    @SuppressWarnings("ConstantConditions")
    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

}
