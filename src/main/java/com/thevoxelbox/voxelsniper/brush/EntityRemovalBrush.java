package com.thevoxelbox.voxelsniper.brush;

import keepcalm.mods.bukkit.bukkitAPI.entity.BukkitEntity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.INpc;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Player;

import com.thevoxelbox.voxelsniper.Message;
import com.thevoxelbox.voxelsniper.SnipeData;

/**
 * 
 * @author Voxel
 */
public class EntityRemovalBrush extends Brush {
    private static int timesUsed = 0;

    /**
     * 
     */
    public EntityRemovalBrush() {
        this.setName("Entity Removal");
    }

    private void radialRemoval(final SnipeData v) {
    	final Chunk _targetChunk = this.getTargetBlock().getChunk();
    	int _entityCount = 0;
        int _chunkCount = 0;
        
        _entityCount += this.removeEntities(_targetChunk);
        
        for (int _x = _targetChunk.getX() - v.getBrushSize(); _x <= _targetChunk.getX() + v.getBrushSize(); _x++) {
            for (int _z = _targetChunk.getZ() - v.getBrushSize(); _z <= _targetChunk.getZ() + v.getBrushSize(); _z++) {
            	_entityCount += removeEntities(this.getWorld().getChunkAt(_x, _z));
                _chunkCount++;
            }
        }
        v.sendMessage(ChatColor.GREEN + "Removed " + ChatColor.RED + _entityCount + ChatColor.GREEN + " entities out of " + ChatColor.BLUE + _chunkCount
                + ChatColor.GREEN + " chunks.");
    }

    private int removeEntities(final Chunk chunk) {
    	int _entityCount = 0;
    	
        for (final Entity _e : chunk.getEntities()) {
            if ((_e instanceof Player) || (_e instanceof Painting)) {
                continue;
            } else {
                if (((BukkitEntity) _e).getHandle() instanceof INpc) {
                    if (!(((BukkitEntity) _e).getHandle() instanceof EntityCreature)) {
                        continue;
                    }
                }
                _e.remove();
                _entityCount++;
            }
        }
        
        return _entityCount;
    }
    
    @Override
    protected final void arrow(final SnipeData v) {
    	this.radialRemoval(v);
    }
    
    @Override
    protected final void powder(final SnipeData v) {
    	this.radialRemoval(v);
    }
    
    @Override
    public final void info(final Message vm) {
    	vm.brushName(this.getName());
    	vm.size();
    }
    
    @Override
    public final int getTimesUsed() {
    	return EntityRemovalBrush.timesUsed;
    }
    
    @Override
    public final void setTimesUsed(final int tUsed) {
    	EntityRemovalBrush.timesUsed = tUsed;
    }
}
