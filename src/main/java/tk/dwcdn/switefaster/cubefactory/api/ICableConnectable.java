package tk.dwcdn.switefaster.cubefactory.api;

import net.minecraft.util.EnumFacing;

/**
 * @author switefaster
 */
public interface ICableConnectable {

    /**
     * Returns if the connection object from @link{from} is connectable
     *
     * @param from The direction the cable from
     * @return If the connection object is connectable
     */
    boolean canConnect(EnumFacing from);

}
