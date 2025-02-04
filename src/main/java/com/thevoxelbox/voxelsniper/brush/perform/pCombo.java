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
public class pCombo extends vPerformer {

    private int i;
    private byte d;

    public pCombo() {
        name = "Combo";
    }

    @Override
    public void info(Message vm) {
        vm.performerName(name);
        vm.voxel();
        vm.data();
    }

    @Override
    public void init(com.thevoxelbox.voxelsniper.SnipeData v) {
        w = v.getWorld();
        i = v.getVoxelId();
        d = v.getData();
    }

    @Override
    public void perform(Block b) {
        h.put(b);
        b.setTypeIdAndData(i, d, true);
    }
}
