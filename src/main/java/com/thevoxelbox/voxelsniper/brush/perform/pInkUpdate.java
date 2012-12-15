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
public class pInkUpdate extends vPerformer {

    private byte d;
    private Sniper s;

    public pInkUpdate() {
        name = "Ink-Update";
    }

    @Override
    public void init(com.thevoxelbox.voxelsniper.SnipeData v) {
        w = v.getWorld();
        d = v.getData();
        s = v.owner();
    }

    @Override
    public void info(Message vm) {
        vm.performerName(name);
        vm.custom(ChatColor.RED + "USE WITH CAUTION");
        vm.data();
    }

    @Override
    public void perform(Block b) {
        h.put(b);
        b.setData(d);
        ((BukkitPlayer) s.getPlayer()).getHandle().playerNetServerHandler.sendPacketToPlayer(new Packet53BlockChange(b.getX(), b.getY(), b.getZ(), ((BukkitWorld) s.getPlayer().getWorld()).getHandle()));
    }
}
