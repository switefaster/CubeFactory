package tk.dwcdn.switefaster.cubefactory.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import tk.dwcdn.switefaster.cubefactory.CubeFactory;

import javax.annotation.Nonnull;

/**
 * @author switefaster
 */
public class TileEntityAcceleratorCore extends TileEntityCableTerminalBase {

    private boolean isAcceleratorOn = false;
    private double speed = 0d;
    private double consumption = 0d;
    private double emitLimit = 1e10d;
    private BlockPos emitter = null;

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setBoolean("isOn", isAcceleratorOn);
        compound.setDouble("speed", speed);
        compound.setDouble("consumption", consumption);
        compound.setDouble("limit", emitLimit);
        if (emitter != null) {
            NBTTagCompound emitterCompound = new NBTTagCompound();
            emitterCompound.setInteger("X", emitter.getX());
            emitterCompound.setInteger("Y", emitter.getY());
            emitterCompound.setInteger("Z", emitter.getZ());
            compound.setTag("eimitter", emitterCompound);
        }
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.isAcceleratorOn = compound.getBoolean("isOn");
        this.speed = compound.getDouble("speed");
        this.consumption = compound.getDouble("consumption");
        this.emitLimit = compound.getDouble("limit");
        if (compound.hasKey("emitter", 10)) {
            NBTTagCompound emitterCompound = compound.getCompoundTag("emitter");
            if (emitterCompound.hasKey("X", 3) && emitterCompound.hasKey("Y", 3) && emitterCompound.hasKey("Z", 3)) {
                this.emitter = new BlockPos(emitterCompound.getInteger("X"), emitterCompound.getInteger("Y"), emitterCompound.getInteger("Z"));
            }
        }
        super.readFromNBT(compound);
    }

    public void sendEmitCommand(EntityPlayer player) {
        TileEntity te = this.world.getTileEntity(this.emitter);
        if (te != null) {
            if (te instanceof TileEntityAcceleratorEmitter) {
                TileEntityAcceleratorEmitter emitter = ((TileEntityAcceleratorEmitter) te);
                if (emitter.emitOut()) {
                    return;
                }
            }
        }
        if (player != null) {
            player.sendMessage(new TextComponentTranslation(CubeFactory.MODID + "." + "emit_failed"));
        }
    }

    private boolean isValidStructure() {
        return true;
    }

    @SuppressWarnings("EmptyMethod")
    public void bootUp(EntityPlayer player) {

    }
}
