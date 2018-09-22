package tk.dwcdn.switefaster.cubefactory.potion;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import tk.dwcdn.switefaster.cubefactory.CubeFactory;

/**
 * @author switefaster
 */
public class PotionIonizingRadiation extends Potion {

    private final static ResourceLocation RES = new ResourceLocation(CubeFactory.MODID + ":" + "textures/gui/potion.png");

    public PotionIonizingRadiation() {
        super(true, 0x00FF00);
        this.setPotionName(CubeFactory.MODID + "." + "potion.ionizing_radiation");
        this.setIconIndex(0, 0);
    }

}
