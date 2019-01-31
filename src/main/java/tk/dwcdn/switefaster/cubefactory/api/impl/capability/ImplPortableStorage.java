package tk.dwcdn.switefaster.cubefactory.api.impl.capability;

import net.minecraft.util.ResourceLocation;
import tk.dwcdn.switefaster.cubefactory.api.capability.IPortableStorage;

public class ImplPortableStorage implements IPortableStorage {

    private double capacity;
    private double stored;
    private ResourceLocation identity;

    @Override
    public double getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Override
    public double getStored() {
        return stored;
    }

    @Override
    public void setStored(double stored) {
        this.stored = stored;
    }

    @Override
    public double extract(double amount, boolean simulate) {
        double extracted = Math.min(getStored(), amount);
        this.stored -= simulate ? 0 : extracted;
        return extracted;
    }

    @Override
    public double insert(double amount, boolean simulate) {
        double rest = getCapacity() - getStored();
        double inserted = Math.min(rest, amount);
        this.stored += simulate ? 0 : inserted;
        return inserted;
    }

    @Override
    public ResourceLocation identity() {
        return identity;
    }

    @Override
    public void setIdentity(ResourceLocation identity) {
        this.identity = identity;
    }
}
