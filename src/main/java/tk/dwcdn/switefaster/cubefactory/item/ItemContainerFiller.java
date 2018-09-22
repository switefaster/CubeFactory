package tk.dwcdn.switefaster.cubefactory.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tk.dwcdn.switefaster.cubefactory.CubeFactory;
import tk.dwcdn.switefaster.cubefactory.creativetab.CreativeTabsLoader;

import javax.annotation.Nonnull;

/**
 * @author switefaster
 */
public class ItemContainerFiller extends Item {

    public ItemContainerFiller() {
        super();
        this.setUnlocalizedName(CubeFactory.MODID + "." + "container_filler");
        this.setCreativeTab(CreativeTabsLoader.tabCubeFactory);
    }

    @Nonnull
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (player.isCreative()) {
            if (worldIn.getTileEntity(pos) != null) {
                TileEntity te = worldIn.getTileEntity(pos);
                if (te instanceof IInventory) {
                    int count = ((IInventory) te).getSizeInventory();
                    ItemStack content = ((IInventory) te).getStackInSlot(0);
                    content.setCount(((IInventory) te).getInventoryStackLimit());
                    for (int i = 1; i < count; i++) {
                        if (((IInventory) te).isItemValidForSlot(i, content)) {
                            ((IInventory) te).setInventorySlotContents(i, content.copy());
                        }
                    }
                }
            }
        }
        return EnumActionResult.PASS;
    }
}
