/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thevoxelbox.voxelsniper.brush.perform;

import keepcalm.mods.bukkit.bukkitAPI.BukkitWorld;
import keepcalm.mods.bukkit.bukkitAPI.entity.BukkitPlayer;
import net.minecraft.network.packet.Packet53BlockChange;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;

import com.thevoxelbox.voxelsniper.Message;
import com.thevoxelbox.voxelsniper.Sniper;

/**
 *
 * @author Voxel
 */
public class pComboNoPhysUpdate extends vPerformer {

    private int i;
    private byte d;
    private Sniper s;

    public pComboNoPhysUpdate() {
        name = "Combo-update-nophys";
    }

    @Override
    public void init(com.thevoxelbox.voxelsniper.SnipeData v) {
        w = v.getWorld();
        i = v.getVoxelId();
        d = v.getData();
        s = v.owner();
    }

    @Override
    public void info(Message vm) {
        vm.performerName(name);
        vm.custom(ChatColor.RED + "USE WITH CAUTION");
        vm.voxel();
        vm.data();
    }

    @Override
    public void perform(Block b) {
        if (b.getTypeId() != i) {
            h.put(b);
            b.setTypeIdAndData(i, d, false);
            ((BukkitPlayer) s.getPlayer()).getHandle().playerNetServerHandler.sendPacketToPlayer(new Packet53BlockChange(b.getX(), b.getY(), b.getZ(), ((BukkitWorld) s.getPlayer().getWorld()).getHandle()));
        }
    }
}
