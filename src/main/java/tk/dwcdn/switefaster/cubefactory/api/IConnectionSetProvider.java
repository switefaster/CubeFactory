package tk.dwcdn.switefaster.cubefactory.api;

import tk.dwcdn.switefaster.cubefactory.cable.CableConnectionSet;

import javax.annotation.Nonnull;

/**
 * @author switefaster
 */
public interface IConnectionSetProvider {

    /**
     * Get the ConnectionSet of the block
     *
     * @return ConnectionSet
     */
    @Nonnull
    CableConnectionSet getSet();

}
