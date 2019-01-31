package tk.dwcdn.switefaster.cubefactory.common;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tk.dwcdn.switefaster.cubefactory.potion.PotionLoader;

/**
 * @author switefaster
 */
public class EventLoader {

    public EventLoader() {
        MinecraftForge.EVENT_BUS.register(this);
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
