package tk.dwcdn.switefaster.cubefactory.common;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tk.dwcdn.switefaster.cubefactory.CubeFactory;
import tk.dwcdn.switefaster.cubefactory.api.AbstractCubeFactoryMassRegistry;
import tk.dwcdn.switefaster.cubefactory.capability.CapabilityLoader;
import tk.dwcdn.switefaster.cubefactory.capability.CapabilityMass;
import tk.dwcdn.switefaster.cubefactory.potion.PotionLoader;
import tk.dwcdn.switefaster.cubefactory.utils.CubeFactoryUtilities;

/**
 * @author switefaster
 */
public class EventLoader {

    public EventLoader() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public void onAttachCapabilitiesItemStack(AttachCapabilitiesEvent<ItemStack> event) {
        ICapabilitySerializable<NBTTagCompound> provider = new CapabilityMass.ProviderMass();
        event.addCapability(new ResourceLocation(CubeFactory.MODID + ":" + "mass"), provider);
        provider.getCapability(CapabilityLoader.massCapability, null).setMass(event.getObject().getCount() * AbstractCubeFactoryMassRegistry.INSTANCE.getItemMass(event.getObject().getItem()));
    }

    @SubscribeEvent
    public void onItemTooltipsRender(ItemTooltipEvent event) {
        String massInter = I18n.format("tooltip.common.mass");
        @SuppressWarnings("ConstantConditions") String massString = !event.getItemStack().hasCapability(CapabilityLoader.massCapability, null) ? "?" : CubeFactoryUtilities.doubleToFormattenString(event.getItemStack().getCapability(CapabilityLoader.massCapability, null).getMass() * event.getItemStack().getCount());
        event.getToolTip().add(massInter + " " + massString);
    }

    @SubscribeEvent
    public void onRenderHUD(RenderGameOverlayEvent.Pre event) {
        if (Minecraft.getMinecraft().world != null) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
                PotionEffect effect = Minecraft.getMinecraft().player.getActivePotionEffect(PotionLoader.POTION_IONIZING_RADIATION);
                if (effect != null) {
                    event.setCanceled(true);
                }
            }
        }
    }
}
