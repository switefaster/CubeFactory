package tk.dwcdn.switefaster.cubefactory.api.impl.registry;

import net.minecraft.item.Item;
import tk.dwcdn.switefaster.cubefactory.api.registry.AbstractCubeFactoryMassRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author switefaster
 */
public class MassRegistryImpl extends AbstractCubeFactoryMassRegistry {

    public static final AbstractCubeFactoryMassRegistry INSTANCE = new MassRegistryImpl();

    private final Map<Item, Double> itemMassMap = new HashMap<>();

    @Override
    public void registerItemMass(Item item, double mass) {
        itemMassMap.remove(item);
        itemMassMap.put(item, mass);
    }

    @Override
    public double getItemMass(Item item) {
        if (!itemMassMap.containsKey(item)) {
            return -1d;
        }
        return itemMassMap.get(item);
    }

    @Override
    public boolean isItemMassRegistered(Item item) {
        return itemMassMap.containsKey(item);
    }
}
