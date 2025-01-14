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
public class pMatInk extends vPerformer {

    private int i;
    private byte dr;

    public pMatInk() {
        name = "Mat-Ink";
    }

    @Override
    public void init(com.thevoxelbox.voxelsniper.SnipeData v) {
        w = v.getWorld();
        i = v.getVoxelId();
        dr = v.getReplaceData();
    }

    @Override
    public void info(Message vm) {
        vm.performerName(name);
        vm.voxel();
        vm.replaceData();
    }

    @Override
    public void perform(Block b) {
        if (b.getData() == dr) {
            h.put(b);
            b.setTypeId(i, true);
        }
    }
}
