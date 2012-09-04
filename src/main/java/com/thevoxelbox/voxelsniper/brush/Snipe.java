package com.thevoxelbox.voxelsniper.brush;

import com.thevoxelbox.voxelsniper.Message;
import com.thevoxelbox.voxelsniper.SnipeData;
import com.thevoxelbox.voxelsniper.brush.perform.PerformBrush;

/**
 * 
 * @author Voxel
 */
public class Snipe extends PerformBrush {
    private static int timesUsed = 0;

    public Snipe() {
        this.setName("Snipe");
    }

    @Override
    protected final void arrow(final SnipeData v) {
        this.current.perform(this.getTargetBlock());
        v.storeUndo(this.current.getUndo());
    }

    @Override
    protected final void powder(final SnipeData v) {
        this.current.perform(this.getLastBlock());
        v.storeUndo(this.current.getUndo());
    }
    
    @Override
    public final void info(final Message vm) {
    	vm.brushName(this.getName());
    }
    
    @Override
    public final int getTimesUsed() {
        return Snipe.timesUsed;
    }
    
    @Override
    public final void setTimesUsed(final int tUsed) {
        Snipe.timesUsed = tUsed;
    }
}
