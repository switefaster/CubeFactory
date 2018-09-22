package tk.dwcdn.switefaster.cubefactory.common;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Logger;
import tk.dwcdn.switefaster.cubefactory.api.AbstractCubeFactoryMassRegistry;

import java.util.Collection;

/**
 * @author switefaster
 */
public class ConfigLoader {

    private static Configuration config;

    private static Logger logger;

    public ConfigLoader(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        config = new Configuration(event.getSuggestedConfigurationFile());

        config.load();
        load();
    }

    @SuppressWarnings("ConstantConditions")
    public static void load() {
        logger.info("Started loading config.");
        String comment;

        Collection<Item> itemRegistered = ForgeRegistries.ITEMS.getValues();
        for (Item item : itemRegistered) {
            if ("minecraft".equalsIgnoreCase(item.getRegistryName().getResourceDomain())) {
                comment = "Mass of " + item.getRegistryName() + ". Weighted as mg";
                if (!config.hasKey(Configuration.CATEGORY_GENERAL, item.getRegistryName().toString()) && !AbstractCubeFactoryMassRegistry.INSTANCE.isItemMassRegistered(item)) {
                    logger.warn("Unregistered item: " + item.getRegistryName() + ". Set to 1mg by default, and added to configuration file.");
                }
                AbstractCubeFactoryMassRegistry.INSTANCE.registerItemMass(item, config.get(Configuration.CATEGORY_GENERAL, item.getRegistryName().toString(), 1d, comment).getDouble());
            }
        }

        config.save();

        logger.info("Finished loading config.");
    }

    public static Logger getLogger() {
        return logger;
    }

}
