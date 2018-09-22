package tk.dwcdn.switefaster.cubefactory.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import tk.dwcdn.switefaster.cubefactory.CubeFactory;
import tk.dwcdn.switefaster.cubefactory.client.gui.GuiAntimatterReactor;

import javax.annotation.Nullable;

/**
 * @author switefaster
 */
public class GuiElementLoader implements IGuiHandler {

    public static final int GUI_ANTIMATTER_REACTOR = 1;
    public static final int GUI_CONTAINER_FILLER = 2;

    public GuiElementLoader() {
        NetworkRegistry.INSTANCE.registerGuiHandler(CubeFactory.instance, this);
    }

    @SuppressWarnings("all")
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GUI_ANTIMATTER_REACTOR:
                return new ContainerAntimatterReactor(player, world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }

    @SuppressWarnings("all")
    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GUI_ANTIMATTER_REACTOR:
                return new GuiAntimatterReactor(new ContainerAntimatterReactor(player, world.getTileEntity(new BlockPos(x, y, z))));
            default:
                return null;
        }
    }
}
