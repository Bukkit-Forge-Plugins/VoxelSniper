/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thevoxelbox.voxelsniper.brush.perform;

import org.bukkit.block.Block;

import com.thevoxelbox.voxelsniper.Message;

/**
 *
 * @author Voxel
 */
public class pMaterial extends vPerformer {

    private int i;

    public pMaterial() {
        name = "Material";
    }

    @Override
    public void init(com.thevoxelbox.voxelsniper.SnipeData v) {
        w = v.getWorld();
        i = v.getVoxelId();
    }

    @Override
    public void info(Message vm) {
        vm.performerName(name);
        vm.voxel();
    }

    @Override
    public void perform(Block b) {
        if (b.getTypeId() != i) {
            h.put(b);
            b.setTypeId(i);
        }
    }
}
