package tk.dwcdn.switefaster.cubefactory.cable;

import net.minecraft.util.math.BlockPos;

/**
 * @author switefaster
 */
public class CableConnectionSet extends UnionSearchSet<BlockPos> {

    public CableConnectionSet(BlockPos blockpos) {
        super(blockpos);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CableConnectionSet) {
            return ((CableConnectionSet) obj).getIdentifier().equals(this.getIdentifier());
        }
        return false;
    }
}
