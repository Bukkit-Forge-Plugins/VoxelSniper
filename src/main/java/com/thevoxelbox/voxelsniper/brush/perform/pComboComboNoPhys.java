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
public class pComboComboNoPhys extends vPerformer {

    private byte d;
    private byte dr;
    private int i;
    private int ir;

    public pComboComboNoPhys() {
        name = "Combo-Combo No-Physics";
    }

    @Override
    public void init(com.thevoxelbox.voxelsniper.SnipeData v) {
        w = v.getWorld();
        d = v.getData();
        dr = v.getReplaceData();
        i = v.getVoxelId();
        ir = v.getReplaceId();
    }

    @Override
    public void info(Message vm) {
        vm.performerName(name);
        vm.voxel();
        vm.replace();
        vm.data();
        vm.replaceData();
    }

    @Override
    public void perform(Block b) {
        if (b.getTypeId() == ir && b.getData() == dr) {
            h.put(b);
            b.setTypeId(i, false);
            b.setData(d);
        }
    }
}