package tk.dwcdn.switefaster.cubefactory.potion;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import tk.dwcdn.switefaster.cubefactory.CubeFactory;

/**
 * @author switefaster
 */
public class PotionLoader {

    public static final Potion POTION_IONIZING_RADIATION = new PotionIonizingRadiation();

    public PotionLoader(FMLPreInitializationEvent event) {
        register(POTION_IONIZING_RADIATION, "ionizing_radiation");
    }

    private static void register(Potion potion, String name) {
        ForgeRegistries.POTIONS.register(potion.setRegistryName(new ResourceLocation(CubeFactory.MODID + ":" + name)));
    }

}
