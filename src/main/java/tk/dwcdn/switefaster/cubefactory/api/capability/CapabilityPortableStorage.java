package tk.dwcdn.switefaster.cubefactory.api.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class CapabilityPortableStorage {

    public static class Storage implements Capability.IStorage<IPortableStorage> {

        @Nullable
        @Override
        public NBTBase writeNBT(Capability<IPortableStorage> capability, IPortableStorage instance, EnumFacing side) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setDouble("capacity", instance.getCapacity());
            compound.setDouble("stored", instance.getStored());
            compound.setString("identity", instance.identity().toString());
            return compound;
        }

        @Override
        public void readNBT(Capability<IPortableStorage> capability, IPortableStorage instance, EnumFacing side, NBTBase nbt) {
            NBTTagCompound compound = (NBTTagCompound) nbt;
            instance.setCapacity(compound.getDouble("capacity"));
            instance.setStored(compound.getDouble("stored"));
            instance.setIdentity(new ResourceLocation(compound.getString("identity")));
        }
    }

}
