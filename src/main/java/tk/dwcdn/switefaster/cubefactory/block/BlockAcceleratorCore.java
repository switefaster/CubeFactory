package tk.dwcdn.switefaster.cubefactory.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import tk.dwcdn.switefaster.cubefactory.CubeFactory;
import tk.dwcdn.switefaster.cubefactory.creativetab.CreativeTabsLoader;
import tk.dwcdn.switefaster.cubefactory.tileentity.TileEntityAcceleratorCore;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author switefaster
 */
public class BlockAcceleratorCore extends BlockContainer {

    public BlockAcceleratorCore() {
        super(Material.IRON);
        this.setUnlocalizedName(CubeFactory.MODID + "." + "accelerator_core");
        this.setCreativeTab(CreativeTabsLoader.tabCubeFactory);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta) {
        return new TileEntityAcceleratorCore();
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
