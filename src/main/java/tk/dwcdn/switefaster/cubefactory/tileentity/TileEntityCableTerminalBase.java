package tk.dwcdn.switefaster.cubefactory.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import tk.dwcdn.switefaster.cubefactory.api.ICableTerminal;
import tk.dwcdn.switefaster.cubefactory.cable.CableConnectionSet;

import javax.annotation.Nonnull;

/**
 * @author switefaster
 */
public class TileEntityCableTerminalBase extends TileEntity implements ICableTerminal {

    /**
     * Initiated with @link{BlockPos.ORIGIN} (0, 0, 0)
     */
    private final CableConnectionSet connectionSet = new CableConnectionSet(this.pos);

    @Override
    public boolean canConnect(EnumFacing from) {
        return true;
    }

    @Nonnull
    @Override
    public CableConnectionSet getSet() {
        return connectionSet;
    }

    /*
    Set when chunk loaded to keep correct
     */
    @Override
    public void onLoad() {
        super.onLoad();
        this.connectionSet.setIdentifier(this.pos);
    }
}
