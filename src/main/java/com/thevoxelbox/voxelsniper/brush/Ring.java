package com.thevoxelbox.voxelsniper.brush;

import org.bukkit.ChatColor;

import com.thevoxelbox.voxelsniper.SnipeData;
import com.thevoxelbox.voxelsniper.Message;
import com.thevoxelbox.voxelsniper.brush.perform.PerformBrush;

/**
 * 
 * @author Voxel
 */
public class Ring extends PerformBrush {
    private double trueCircle = 0;
    private double innerSize = 0;

    private static int timesUsed = 0;

    public Ring() {
        this.setName("Ring");
    }

    private final void ring(final SnipeData v) {
        final int _brushSize = v.getBrushSize();
        final double _outerpow = Math.pow(_brushSize + this.trueCircle, 2);
        final double _innerpow = Math.pow(this.innerSize, 2);
        for (int _x = _brushSize; _x >= 0; _x--) {
            final double _xpow = Math.pow(_x, 2);
            for (int _y = _brushSize; _y >= 0; _y--) {
                final double _ypow = Math.pow(_y, 2);
                if ((_xpow + _ypow) <= _outerpow && (_xpow + _ypow) >= _innerpow) {
                    this.current.perform(this.clampY(this.getBlockPositionX() + _x, this.getBlockPositionY(), this.getBlockPositionZ() + _y));
                    this.current.perform(this.clampY(this.getBlockPositionX() + _x, this.getBlockPositionY(), this.getBlockPositionZ() - _y));
                    this.current.perform(this.clampY(this.getBlockPositionX() - _x, this.getBlockPositionY(), this.getBlockPositionZ() + _y));
                    this.current.perform(this.clampY(this.getBlockPositionX() - _x, this.getBlockPositionY(), this.getBlockPositionZ() - _y));
                }
            }
        }

        v.storeUndo(this.current.getUndo());
    }

    @Override
    protected final void arrow(final SnipeData v) {
        this.ring(v);
    }

    @Override
    protected final void powder(final SnipeData v) {
    	this.setBlockPositionX(this.getLastBlock().getX());
        this.setBlockPositionY(this.getLastBlock().getY());
        this.setBlockPositionZ(this.getLastBlock().getZ());
        this.ring(v);
    }
    
    @Override
    public final void info(final Message vm) {
    	vm.brushName(this.getName());
    	vm.size();
    	vm.custom(ChatColor.AQUA + "The inner radius is " + ChatColor.RED + this.innerSize);
    }
    
    @Override
    public final void parameters(final String[] par, final SnipeData v) {
    	if (par[1].equalsIgnoreCase("info")) {
    		v.sendMessage(ChatColor.GOLD + "Ring Brush Parameters:");
    		v.sendMessage(ChatColor.AQUA
    				+ "/b ri true -- will use a true circle algorithm instead of the skinnier version with classic sniper nubs. /b ri false will switch back. (false is default)");
    		v.sendMessage(ChatColor.AQUA + "/b ri ir2.5 -- will set the inner radius to 2.5 units");
    		return;
    	}
    	for (int _i = 1; _i < par.length; _i++) {
    		if (par[_i].startsWith("true")) {
    			this.trueCircle = 0.5;
    			v.sendMessage(ChatColor.AQUA + "True circle mode ON.");
    			continue;
    		} else if (par[_i].startsWith("false")) {
    			this.trueCircle = 0;
    			v.sendMessage(ChatColor.AQUA + "True circle mode OFF.");
    			continue;
    		} else if (par[_i].startsWith("ir")) {
    			try {
    				final double d = Double.parseDouble(par[_i].replace("ir", ""));
    				this.innerSize = d;
    				v.sendMessage(ChatColor.AQUA + "The inner radius has been set to " + ChatColor.RED + this.innerSize);
    			} catch (final Exception e) {
    				v.sendMessage(ChatColor.RED + "The parameters included are invalid");
    			}
    		} else {
    			v.sendMessage(ChatColor.RED + "Invalid brush parameters! use the info parameter to display parameter info.");
    		}
    	}
    }
    
    @Override
    public final int getTimesUsed() {
    	return Ring.timesUsed;
    }
    
    @Override
    public final void setTimesUsed(final int tUsed) {
    	Ring.timesUsed = tUsed;
    }
}
