package tk.dwcdn.switefaster.cubefactory.api.capability;

import net.minecraft.util.ResourceLocation;

/**
 * @author switefaster
 * The ordinary interface for any portable storage
 * Is used in capabilities(e.g. {@link CapabilityPortableStorage})
 */

public interface IPortableStorage {

    /**
     * Get the capacity of the storage
     *
     * @return The capacity of the storage
     */
    double getCapacity();

    /**
     * Set the capacity of the storage
     *
     * @param capacity The capacity of the storage to be set
     */
    void setCapacity(double capacity);

    /**
     * Get currently stored amount
     *
     * @return The current stored amount of the storage
     */
    double getStored();

    /**
     * Set currently stored amount
     *
     * @param stored The currently stored amount to be set
     */

    void setStored(double stored);

    /**
     * Extract specified amount of content from the storage
     *
     * @param amount   The amount to extract
     * @param simulate If true, the operation will not really affect the storage
     * @return The amount of the content that is extracted
     */
    double extract(double amount, boolean simulate);

    /**
     * Insert specified amount of content into the storage
     *
     * @param amount   The amount to insert
     * @param simulate If true, the operation will not really affect the storage
     * @return The amount of the content that is inserted
     */
    double insert(double amount, boolean simulate);

    /**
     * The identity to distinguish different storage
     * It claims what mod it belongs to, and what it is storing
     * It could be anything you want, however, you should try to make it unique
     *
     * @return The identity
     */
    ResourceLocation identity();

    /**
     * Set the identity of the storage
     *
     * @param identity The identity of the storage to be set
     */
    void setIdentity(ResourceLocation identity);

}
