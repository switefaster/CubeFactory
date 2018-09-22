package tk.dwcdn.switefaster.cubefactory.tileentity;

import cofh.redstoneflux.api.IEnergyProvider;
import cofh.redstoneflux.api.IEnergyReceiver;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import tk.dwcdn.switefaster.cubefactory.api.AbstractCubeFactoryMassRegistry;
import tk.dwcdn.switefaster.cubefactory.block.BlockAntimatterReactor;
import tk.dwcdn.switefaster.cubefactory.capability.CapabilityLoader;
import tk.dwcdn.switefaster.cubefactory.capability.CapabilityMass;
import tk.dwcdn.switefaster.cubefactory.capability.IMass;
import tk.dwcdn.switefaster.cubefactory.item.ItemLoader;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 * @author switefaster
 */
public class TileEntityAntimatterReactor extends TileEntity implements IEnergyProvider, ITickable {

    public static final int MAX_ENERGY_STORAGE = 268435456;
    public static final double REDUCE_MASS_PER_TICK = 5e-6d;
    public static final int MAX_MASS_STORAGE = 2000000000;
    public static final int MAX_ANIMATION_TICK = 66;
    private static final int PRODUCE_ENERGY_PER_TICK = 4493;
    private static final int ENERGY_MAX_EXTRACT_PER_TICK = 5000;
    private final ItemStackHandler matterSlot = new ItemStackHandler();
    private final ItemStackHandler antimatterSlot = new ItemStackHandler();
    private int matterMass = 0;
    private int antimatterMass = 0;
    private int powerStorage = 0;
    private int animationTick = -1;

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability)) {
            T result;
            result = (T) (facing == null ? null : EnumFacing.Plane.HORIZONTAL.test(facing) ? antimatterSlot : matterSlot);
            return result;
        } else if (CapabilityEnergy.ENERGY.equals(capability)) {
            return CapabilityEnergy.ENERGY.cast(new IEnergyStorage() {
                @Override
                public int receiveEnergy(int maxReceive, boolean simulate) {
                    return 0;
                }

                @Override
                public int extractEnergy(int maxExtract, boolean simulate) {
                    return TileEntityAntimatterReactor.this.extractEnergy(facing, maxExtract, simulate);
                }

                @Override
                public int getEnergyStored() {
                    return TileEntityAntimatterReactor.this.getEnergyStored(facing);
                }

                @Override
                public int getMaxEnergyStored() {
                    return TileEntityAntimatterReactor.this.getMaxEnergyStored(facing);
                }

                @Override
                public boolean canExtract() {
                    return true;
                }

                @Override
                public boolean canReceive() {
                    return false;
                }
            });
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability) || CapabilityEnergy.ENERGY.equals(capability) || super.hasCapability(capability, facing);
    }

    @Override
    public int getEnergyStored(EnumFacing from) {
        return powerStorage;
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from) {
        return MAX_ENERGY_STORAGE;
    }

    @Override
    public boolean canConnectEnergy(EnumFacing from) {
        return true;
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("matterMass", this.matterMass);
        compound.setInteger("antimatterMass", this.antimatterMass);
        compound.setInteger("powerStorage", this.powerStorage);
        compound.setTag("matterSlot", this.matterSlot.serializeNBT());
        compound.setTag("antimatterSlot", this.antimatterSlot.serializeNBT());
        compound.setInteger("animationTick", this.animationTick);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.matterMass = compound.getInteger("matterMass");
        this.antimatterMass = compound.getInteger("antimatterMass");
        this.powerStorage = compound.getInteger("powerStorage");
        this.matterSlot.deserializeNBT(compound.getCompoundTag("matterSlot"));
        this.antimatterSlot.deserializeNBT(compound.getCompoundTag("antimatterSlot"));
        this.animationTick = compound.getInteger("animationTick");
        super.readFromNBT(compound);
    }

    @Override
    public void update() {
        if (!this.world.isRemote) {
            if(this.matterMass < MAX_MASS_STORAGE)
            {
                ItemStack matter = this.matterSlot.extractItem(0,1,true);
                if(matter.getItem() != ItemLoader.ITEM_ANTIMATTER) {
                    IMass mass = matter.getCapability(CapabilityLoader.massCapability, null);
                    if (mass != null && mass.getMass() > -1) {
                        if (mass.getMass() <= MAX_MASS_STORAGE - this.matterMass) {
                            this.matterSlot.extractItem(0, 1, false);
                            this.matterMass += mass.getMass() * 100000;
                            this.markDirty();
                        }
                    }
                }
            }
            if(this.antimatterMass < MAX_MASS_STORAGE)
            {
                ItemStack matter = this.antimatterSlot.extractItem(0,1,true);
                if(matter.getItem() == ItemLoader.ITEM_ANTIMATTER) {
                    IMass mass = matter.getCapability(CapabilityLoader.massCapability, null);
                    if (mass != null) {
                        if (mass.getMass() <= MAX_MASS_STORAGE - this.antimatterMass) {
                            this.antimatterSlot.extractItem(0, 1, false);
                            this.antimatterMass += mass.getMass() * 1000000;
                            this.markDirty();
                        }
                    }
                }
            }
            if(this.matterMass > REDUCE_MASS_PER_TICK && this.antimatterMass > REDUCE_MASS_PER_TICK && this.powerStorage < MAX_ENERGY_STORAGE)
            {
                animationTick = ++animationTick % MAX_ANIMATION_TICK;
                this.powerStorage = Math.min(MAX_ENERGY_STORAGE, this.powerStorage + PRODUCE_ENERGY_PER_TICK);
                this.matterMass -= REDUCE_MASS_PER_TICK;
                this.antimatterMass -= REDUCE_MASS_PER_TICK;
                this.world.setBlockState(pos, this.world.getBlockState(this.pos).withProperty(BlockAntimatterReactor.REACTING, Boolean.TRUE));
            }
            else
            {
                animationTick = -1;
                this.world.setBlockState(pos, this.world.getBlockState(this.pos).withProperty(BlockAntimatterReactor.REACTING, Boolean.FALSE));
            }
            this.transmitEnergy();
        }
    }

    @Override
    public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
        int extract = Math.min(this.powerStorage, Math.min(maxExtract, ENERGY_MAX_EXTRACT_PER_TICK));
        this.powerStorage -= simulate ? 0 : extract;
        return extract;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, @Nonnull IBlockState oldState, @Nonnull IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }

    public int getAnimationTick()
    {
        return this.animationTick;
    }

    public int getMatterMass()
    {
        return this.matterMass;
    }

    public int getAntimatterMass()
    {
        return this.antimatterMass;
    }

    private void transmitEnergy() {
        for (final EnumFacing side : EnumFacing.values()) {
            final TileEntity tile = this.world.getTileEntity(this.getPos().offset(side));
            if (tile instanceof IEnergyReceiver) {
                this.extractEnergy(side, ((IEnergyReceiver) tile).receiveEnergy(side.getOpposite(), this.extractEnergy(side, ENERGY_MAX_EXTRACT_PER_TICK, true), false), false);
            }
        }
    }
}
