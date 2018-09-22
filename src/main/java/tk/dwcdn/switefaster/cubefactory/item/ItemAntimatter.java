package tk.dwcdn.switefaster.cubefactory.item;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.world.Explosion;
import tk.dwcdn.switefaster.cubefactory.CubeFactory;
import tk.dwcdn.switefaster.cubefactory.creativetab.CreativeTabsLoader;

/**
 * @author switefaster
 */
public class ItemAntimatter extends Item {

    public ItemAntimatter() {
        super();
        this.setUnlocalizedName(CubeFactory.MODID + "." + "antimatter");
        this.setCreativeTab(CreativeTabsLoader.tabCubeFactory);
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        if (!entityItem.isDead) {
            Explosion explosion = new Explosion(entityItem.world, entityItem, entityItem.posX, entityItem.posY, entityItem.posZ, 1.2F * entityItem.getItem().getCount(), true, true);
            explosion.doExplosionA();
            explosion.doExplosionB(true);
            entityItem.setDead();
        }
        return false;
    }

}
