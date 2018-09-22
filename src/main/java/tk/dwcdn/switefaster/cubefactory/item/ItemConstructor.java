package tk.dwcdn.switefaster.cubefactory.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tk.dwcdn.switefaster.cubefactory.CubeFactory;
import tk.dwcdn.switefaster.cubefactory.creativetab.CreativeTabsLoader;

import javax.annotation.Nonnull;
import java.util.HashSet;

/**
 * @author switefaster
 */
public class ItemConstructor extends ItemTool {

    public ItemConstructor() {
        super(6, 1, ToolMaterial.IRON, new HashSet<>());
        this.setHarvestLevel("pickaxe", 3);
        this.setHarvestLevel("axe", 3);
        this.setUnlocalizedName(CubeFactory.MODID + "." + "constructor");
        this.setCreativeTab(CreativeTabsLoader.tabCubeFactory);
    }

    @Nonnull
    @SuppressWarnings("EmptyMethod")
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
