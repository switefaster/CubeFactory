package tk.dwcdn.switefaster.cubefactory.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author switefaster
 */
public class CapabilityMass {

    public static class Storage implements Capability.IStorage<IMass> {

        @Nullable
        @Override
        public NBTBase writeNBT(Capability<IMass> capability, IMass instance, EnumFacing side) {
            return new NBTTagDouble(instance.getMass());
        }

        @Override
        public void readNBT(Capability<IMass> capability, IMass instance, EnumFacing side, NBTBase nbt) {
            instance.setMass(((NBTTagDouble) nbt).getDouble());
        }
    }

    public static class Implementation implements IMass {

        private double mass;

        public Implementation() {
            this.mass = 0;
        }

        @Override
        public double getMass() {
            return this.mass;
        }

        @Override
        public void setMass(double mass) {
            this.mass = mass;
        }
    }

    public static class ProviderMass implements ICapabilitySerializable<NBTTagCompound> {

        private final IMass mass = new Implementation();
        private final Capability.IStorage<IMass> storage = CapabilityLoader.massCapability.getStorage();

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return CapabilityLoader.massCapability.equals(capability);
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            if (CapabilityLoader.massCapability.equals(capability)) {
                @SuppressWarnings("unchecked")
                T result = (T) mass;
                return result;
            }
            return null;
        }

        @SuppressWarnings("all")
        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setTag("mass", storage.writeNBT(CapabilityLoader.massCapability, mass, null));
            return compound;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            NBTTagDouble mass = (NBTTagDouble) nbt.getTag("mass");
            storage.readNBT(CapabilityLoader.massCapability, this.mass, null, mass);
        }
    }

}
