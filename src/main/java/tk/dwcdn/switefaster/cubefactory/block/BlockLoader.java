package tk.dwcdn.switefaster.cubefactory.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.dwcdn.switefaster.cubefactory.CubeFactory;
import tk.dwcdn.switefaster.cubefactory.api.registry.AbstractCubeFactoryMassRegistry;

import java.util.Objects;

/**
 * @author switefaster
 */
public class BlockLoader {

    public static final Block BLOCK_ANTIMATTER_REACTOR = new BlockAntimatterReactor();
    public static final Block BLOCK_ACCELERATOR_CORE = new BlockAcceleratorCore();

    @SuppressWarnings("unused")
    public BlockLoader(FMLPreInitializationEvent event) {
        register(BLOCK_ANTIMATTER_REACTOR, "antimatter_reactor", 50e6d);
        register(BLOCK_ACCELERATOR_CORE, "accelerator_core", 800e6d);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        registerRender(BLOCK_ANTIMATTER_REACTOR);
        registerRender(BLOCK_ACCELERATOR_CORE);
    }

    private static void register(Block block, String name, double mass) {
        ForgeRegistries.BLOCKS.register(block.setRegistryName(name));
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(name));
        AbstractCubeFactoryMassRegistry.INSTANCE.registerItemMass(ForgeRegistries.ITEMS.getValue(new ResourceLocation(CubeFactory.MODID, name)), mass);
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Block block) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Objects.requireNonNull(block.getRegistryName()), "inventory"));
    }

}
