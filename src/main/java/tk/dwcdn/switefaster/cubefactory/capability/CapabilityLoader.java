package tk.dwcdn.switefaster.cubefactory.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * @author switefaster
 */
public class CapabilityLoader {

    @CapabilityInject(IMass.class)
    public static Capability<IMass> massCapability;

    public CapabilityLoader(FMLPreInitializationEvent event) {
        CapabilityManager.INSTANCE.register(IMass.class, new CapabilityMass.Storage(), CapabilityMass.Implementation::new);
    }

}
