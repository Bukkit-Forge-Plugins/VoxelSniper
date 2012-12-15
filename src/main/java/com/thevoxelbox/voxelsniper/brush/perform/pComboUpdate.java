/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thevoxelbox.voxelsniper.brush.perform;

import com.thevoxelbox.voxelsniper.Message;
import com.thevoxelbox.voxelsniper.Sniper;
import net.minecraft.src.Packet53BlockChange;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import keepcalm.mods.bukkit.bukkitAPI.BukkitWorld;
import keepcalm.mods.bukkit.bukkitAPI.entity.BukkitPlayer;

/**
 * 
 * @author Voxel
 */
public class pComboUpdate extends vPerformer {

    private byte d;
    private int i;
    private Sniper s;

    public pComboUpdate() {
        name = "Combo-Update";
    }

    @Override
    public void init(com.thevoxelbox.voxelsniper.SnipeData v) {
        w = v.getWorld();
        d = v.getData();
        i = v.getVoxelId();
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
        h.put(b);
        b.setTypeIdAndData(i, d, true);
        ((BukkitPlayer) s.getPlayer()).getHandle().playerNetServerHandler.sendPacketToPlayer(new Packet53BlockChange(b.getX(), b.getY(), b.getZ(), ((BukkitWorld) s.getPlayer()
                .getWorld()).getHandle()));
    }
}
