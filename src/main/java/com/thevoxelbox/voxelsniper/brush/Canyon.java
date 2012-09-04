package com.thevoxelbox.voxelsniper.brush;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.block.Block;

import com.thevoxelbox.voxelsniper.SnipeData;
import com.thevoxelbox.voxelsniper.Message;
import com.thevoxelbox.voxelsniper.Undo;

/**
 * 
 * @author Voxel
 */
public class Canyon extends Brush {

    protected int yLevel = 10;
    protected Undo undo;

    private static int timesUsed = 0;

    public Canyon() {
        this.setName("Canyon");
    }

    private void canyon(final Chunk _c, final SnipeData v) {
        int _yy = 0;

        final Undo _undo = new Undo(_c.getWorld().getName());

        for (int _x = 0; _x < 16; _x++) {
            for (int _z = 0; _z < 16; _z++) {
                _yy = this.yLevel;
                for (int _y = 63; _y < 128; _y++) {
                    final Block _b = _c.getBlock(_x, _y, _z);
                    _undo.put(_b);
                    final Block _bb = _c.getBlock(_x, _yy, _z);
                    _undo.put(_bb);
                    _bb.setTypeId(_b.getTypeId(), false);
                    _b.setTypeId(0);
                    _yy++;
                }
                final Block _b = _c.getBlock(_x, 0, _z);
                _undo.put(_b);
                _b.setTypeId(7);
                for (int _y = 1; _y < 10; _y++) {
                    final Block _bb = _c.getBlock(_x, _y, _z);
                    _undo.put(_bb);
                    _bb.setTypeId(1);
                }
            }
        }

        v.storeUndo(_undo);
    }

    protected final void multiCanyon(final Chunk c, final SnipeData v) {
        int _yy = 0;

        for (int _x = 0; _x < 16; _x++) {
            for (int _z = 0; _z < 16; _z++) {
                _yy = this.yLevel;
                for (int _y = 63; _y < 128; _y++) {
                    final Block _b = c.getBlock(_x, _y, _z);
                    this.undo.put(_b);
                    final Block _bb = c.getBlock(_x, _yy, _z);
                    this.undo.put(_bb);
                    _bb.setTypeId(_b.getTypeId(), false);
                    _b.setTypeId(0);
                    _yy++;
                }
                final Block _b = c.getBlock(_x, 0, _z);
                this.undo.put(_b);
                _b.setTypeId(7);
                for (int _y = 1; _y < 10; _y++) {
                    final Block _bb = c.getBlock(_x, _y, _z);
                    this.undo.put(_bb);
                    _bb.setTypeId(1);
                }
            }
        }
    }
    
    @Override
    protected void arrow(final SnipeData v) {    	
    	this.canyon(this.getWorld().getChunkAt(this.getTargetBlock()), v);
    }

    @Override
    protected void powder(final SnipeData v) {
        this.undo = new Undo(this.getWorld().getChunkAt(this.getTargetBlock()).getWorld().getName());

        this.multiCanyon(this.getWorld().getChunkAt(this.getTargetBlock()), v);
        this.multiCanyon(this.getWorld().getChunkAt(this.clampY(this.getBlockPositionX() + 16, 63, this.getBlockPositionZ())), v);
        this.multiCanyon(this.getWorld().getChunkAt(this.clampY(this.getBlockPositionX() + 16, 63, this.getBlockPositionZ() + 16)), v);
        this.multiCanyon(this.getWorld().getChunkAt(this.clampY(this.getBlockPositionX(), 63, this.getBlockPositionZ() + 16)), v);
        this.multiCanyon(this.getWorld().getChunkAt(this.clampY(this.getBlockPositionX() - 16, 63, this.getBlockPositionZ() + 16)), v);
        this.multiCanyon(this.getWorld().getChunkAt(this.clampY(this.getBlockPositionX() - 16, 63, this.getBlockPositionZ())), v);
        this.multiCanyon(this.getWorld().getChunkAt(this.clampY(this.getBlockPositionX() - 16, 63, this.getBlockPositionZ() - 16)), v);
        this.multiCanyon(this.getWorld().getChunkAt(this.clampY(this.getBlockPositionX(), 63, this.getBlockPositionZ() - 16)), v);
        this.multiCanyon(this.getWorld().getChunkAt(this.clampY(this.getBlockPositionX() + 16, 63, this.getBlockPositionZ() - 16)), v);

        v.storeUndo(this.undo);
    }
    
    @Override
    public void info(final Message vm) {
    	vm.brushName(this.getName());
    	vm.custom(ChatColor.GREEN + "Shift Level set to " + this.yLevel);
    }
    
    @Override
    public final void parameters(final String[] par, final SnipeData v) {
    	if (par[1].equalsIgnoreCase("info")) {
    		v.sendMessage(ChatColor.GREEN + "y[number] to set the Level to which the land will be shifted down");
    	}
    	if (par[1].startsWith("y")) {
    		int _i = Integer.parseInt(par[1].replace("y", ""));
    		if (_i < 10) {
    			_i = 10;
    		} else if (_i > 60) {
    			_i = 60;
    		}
    		this.yLevel = _i;
    		v.sendMessage(ChatColor.GREEN + "Shift Level set to " + this.yLevel);
    	}
    }
    
    @Override
    public int getTimesUsed() {
        return Canyon.timesUsed;
    }
    
    @Override
    public void setTimesUsed(final int tUsed) {
        Canyon.timesUsed = tUsed;
    }
}
