/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thevoxelbox.voxelsniper.brush.perform;

import org.bukkit.World;
import org.bukkit.block.Block;

import com.thevoxelbox.voxelsniper.Message;
import com.thevoxelbox.voxelsniper.Undo;

/**
 *
 * @author Voxel
 */
public abstract class vPerformer {

    public String name = "Performer";
    protected Undo h;
    protected World w;

    public abstract void info(Message vm);

    public abstract void init(com.thevoxelbox.voxelsniper.SnipeData v);

    public void setUndo() {
        h = new Undo(w.getName());
    }

    public abstract void perform(Block b);

    public Undo getUndo() {
        Undo temp = h;
        h = null;
        return temp;
    }
}
