package tk.dwcdn.switefaster.cubefactory.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import tk.dwcdn.switefaster.cubefactory.CubeFactory;
import tk.dwcdn.switefaster.cubefactory.creativetab.CreativeTabsLoader;
import tk.dwcdn.switefaster.cubefactory.inventory.GuiElementLoader;
import tk.dwcdn.switefaster.cubefactory.tileentity.TileEntityAntimatterReactor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author switefaster
 */
public class BlockAntimatterReactor extends BlockContainer {

    public static final PropertyBool REACTING = PropertyBool.create("reacting");
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public BlockAntimatterReactor() {
        super(Material.IRON);
        this.setUnlocalizedName(CubeFactory.MODID + "." + "antimatter_reactor");
        this.setCreativeTab(CreativeTabsLoader.tabCubeFactory);
        this.setDefaultState(this.blockState.getBaseState()
                .withProperty(REACTING, Boolean.FALSE)
                .withProperty(FACING, EnumFacing.NORTH));
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, REACTING, FACING);
    }

    @SuppressWarnings({"all", "AliDeprecation"})
    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getHorizontal(meta & 3);
        Boolean reacting = Boolean.valueOf((meta & 4) != 0);
        return this.getDefaultState().withProperty(FACING, facing).withProperty(REACTING, reacting);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int facing = state.getValue(FACING).getHorizontalIndex();
        int reacting = state.getValue(REACTING) ? 4 : 0;
        return facing | reacting;
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta) {
        return new TileEntityAntimatterReactor();
    }

    @Override
    public void breakBlock(World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        TileEntityAntimatterReactor te = (TileEntityAntimatterReactor) worldIn.getTileEntity(pos);

        IItemHandler antimatters = te != null ? te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH) : null;

        for (int i = (antimatters != null ? antimatters.getSlots() : 0) - 1; i >= 0; i--) {
            if (antimatters != null) {
                if (antimatters.getStackInSlot(i) != ItemStack.EMPTY) {
                    InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), antimatters.getStackInSlot(i));
                    ((IItemHandlerModifiable) antimatters).setStackInSlot(0, ItemStack.EMPTY);
                }
            }
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Nonnull
    @Override
    public IBlockState getStateForPlacement(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ, int meta, @Nonnull EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            int id = GuiElementLoader.GUI_ANTIMATTER_REACTOR;
            playerIn.openGui(CubeFactory.instance, id, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }
}
