package tk.dwcdn.switefaster.cubefactory.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tk.dwcdn.switefaster.cubefactory.api.capability.CapabilityPortableStorage;
import tk.dwcdn.switefaster.cubefactory.api.capability.IPortableStorage;
import tk.dwcdn.switefaster.cubefactory.api.impl.capability.ImplPortableStorage;

/**
 * @author switefaster
 */
public class CapabilityLoader {

    @SuppressWarnings("unused")
    @CapabilityInject(IPortableStorage.class)
    public static Capability<IPortableStorage> portableStorage;

    @SuppressWarnings("unused")
    public CapabilityLoader(FMLPreInitializationEvent event) {
        CapabilityManager.INSTANCE.register(IPortableStorage.class, new CapabilityPortableStorage.Storage(), ImplPortableStorage::new);
    }

}
