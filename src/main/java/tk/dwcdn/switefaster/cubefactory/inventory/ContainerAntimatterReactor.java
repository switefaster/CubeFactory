package tk.dwcdn.switefaster.cubefactory.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import tk.dwcdn.switefaster.cubefactory.item.ItemLoader;
import tk.dwcdn.switefaster.cubefactory.tileentity.TileEntityAntimatterReactor;

import javax.annotation.Nonnull;

/**
 * @author switefaster
 */
public class ContainerAntimatterReactor extends Container {

    private final TileEntityAntimatterReactor tileEntity;

    private int matterMass = 0;
    private int antimatterMass = 0;
    private int energyStoage = 0;
    private int animationTick = -1;

    ContainerAntimatterReactor(EntityPlayer player, TileEntity tileEntity) {
        super();

        IItemHandler antimatters;
        antimatters = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.EAST);
        IItemHandler matters;
        matters = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        this.addSlotToContainer(new SlotItemHandler(antimatters, 0, 32, 110) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return stack.getItem() == ItemLoader.ITEM_ANTIMATTER;
            }
        });
        this.addSlotToContainer(new SlotItemHandler(matters, 0, 32, 27)
        {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return true;
            }
        });

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 12 + j * 18, 151 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(player.inventory, i, 12 + i * 18, 209));
        }

        this.tileEntity = (TileEntityAntimatterReactor) tileEntity;

    }

    @Override
    @Nonnull
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        Slot slot = inventorySlots.get(index);

        if (slot == null || !slot.getHasStack()) {
            return ItemStack.EMPTY;
        }

        ItemStack newStack = slot.getStack(), oldStack = newStack.copy();

        boolean isMerged;

        if (index == 0) {
            isMerged = mergeItemStack(newStack, 1, 37, true);
        } else if (index < 28) {
            isMerged = mergeItemStack(newStack, 0, 1, false) || mergeItemStack(newStack, 28, 37, false);
        } else {
            isMerged = mergeItemStack(newStack, 0, 1, false) || mergeItemStack(newStack, 1, 28, false);
        }

        if (!isMerged) {
            return ItemStack.EMPTY;
        }

        if (newStack.getCount() == 0) {
            slot.putStack(ItemStack.EMPTY);
        } else {
            slot.onSlotChanged();
        }

        slot.onTake(playerIn, newStack);

        return oldStack;
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return playerIn.getDistanceSq(this.tileEntity.getPos()) <= 64;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        this.matterMass = tileEntity.getMatterMass();
        this.antimatterMass = tileEntity.getAntimatterMass();
        this.energyStoage = tileEntity.getEnergyStored(EnumFacing.UP);
        this.animationTick = tileEntity.getAnimationTick();

        for (IContainerListener i : this.listeners) {
            i.sendWindowProperty(this, 0, this.matterMass);
            i.sendWindowProperty(this, 1, this.antimatterMass);
            i.sendWindowProperty(this, 2, this.energyStoage);
            i.sendWindowProperty(this, 3, this.animationTick);
        }
    }

    @Override
    public void updateProgressBar(int id, int data) {
        super.updateProgressBar(id, data);

        switch (id) {
            case 0:
                this.matterMass = data;
                break;
            case 1:
                this.antimatterMass = data;
                break;
            case 2:
                this.energyStoage = data;
                break;
            case 3:
                this.animationTick = data;
                break;
            default:
                break;
        }
    }

    public int getMatterMass() {
        return matterMass;
    }

    public int getAntimatterMass() {
        return antimatterMass;
    }

    public int getEnergyStorage() {
        return energyStoage;
    }

    public int getAnimationTick() {
        return animationTick;
    }
}
