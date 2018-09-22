package tk.dwcdn.switefaster.cubefactory.api;

import net.minecraft.item.Item;
import tk.dwcdn.switefaster.cubefactory.api.impl.MassRegistryImpl;

/**
 * @author switefaster
 * <p>
 * The mass capability registry of CubeFactory
 * Note that {@link MassRegistryImpl} is the default implementation
 * To register the mass with default implementation, use the INSTANCE field of {@link AbstractCubeFactoryMassRegistry}
 */

public abstract class AbstractCubeFactoryMassRegistry {

    /**
     * The instance of the default implementation
     */
    public static final AbstractCubeFactoryMassRegistry INSTANCE;

    static {
        try {
            Class<?> implClass = Class.forName("tk.dwcdn.switefaster.cubefactory.api.impl.MassRegistryImpl");
            INSTANCE = (AbstractCubeFactoryMassRegistry) implClass.getField("INSTANCE").get(null);
        } catch (Exception e) {
            throw new RuntimeException("Cannot find implementation", e);
        }
    }

    /**
     * Register the mass of the item. The final mass of itemstack is the product of this and the stack size
     *
     * @param item The item need to be registered
     * @param mass The mass of the item
     */
    public abstract void registerItemMass(Item item, double mass);

    /**
     * Get the registered mass of item
     *
     * @param item The item to be got the mass
     * @return The mass of the item, will return -1 if is not registered
     */
    public abstract double getItemMass(Item item);

    /**
     * Check whether the mass of item is registered
     *
     * @param item The item to be checked
     * @return TRUE if registered, or not
     */
    public abstract boolean isItemMassRegistered(Item item);

}
