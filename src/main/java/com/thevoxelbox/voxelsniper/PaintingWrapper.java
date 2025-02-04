package com.thevoxelbox.voxelsniper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import keepcalm.mods.bukkit.bukkitAPI.BukkitWorld;
import keepcalm.mods.bukkit.bukkitAPI.entity.BukkitPlayer;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumArt;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
//.BukkitWorld;

/**
 * Painting state change handler.
 * 
 * @author Piotr
 */
public final class PaintingWrapper {

    private PaintingWrapper() {
    }

    /**
     * ArrayList of Notch's paintings from the EnumArt enum Stored here for easy access, also EnumArt doesn't have a .get.
     */
    public static final ArrayList<EnumArt> PAINTINGS = new ArrayList<EnumArt>(Arrays.asList(EnumArt.values()));

    /**
     * The paint method used to scroll or set a painting to a specific type.
     * 
     * @param p
     *            The player executing the method
     * @param auto
     *            Scroll automatically? If false will use 'choice' to try and set the painting
     * @param back
     *            Scroll in reverse?
     * @param choice
     *            Chosen index to set the painting to
     */
    public static void paint(final Player p, final boolean auto, final boolean back, final int choice) {
        final boolean _auto = auto;

        final Location _loc = p.getTargetBlock(null, 4).getLocation();
        final Location _loc2 = p.getLocation();
        final BukkitWorld _craftWorld = (BukkitWorld) p.getWorld();
        final double _x1 = _loc.getX() + 0.4D;
        final double _y1 = _loc.getY() + 0.4D;
        final double _z1 = _loc.getZ() + 0.4D;
        final double _x2 = _loc2.getX();
        final double _y2 = _loc.getY() + 0.6D;
        final double _z2 = _loc2.getZ();

        final AxisAlignedBB _bb = AxisAlignedBB.getBoundingBox(Math.min(_x1, _x2), _y1, Math.min(_z1, _z2), Math.max(_x1, _x2), _y2, Math.max(_z1, _z2));

        final List<?> _entities = _craftWorld.getHandle().getEntitiesWithinAABB(((BukkitPlayer) p).getHandle().getClass(), _bb);
        if ((_entities.size() == 1) && ((_entities.get(0) instanceof EntityPainting))) {
            final EntityPainting _oldPainting = (EntityPainting) _entities.get(0);
            final EntityPainting _newPainting = new EntityPainting(_craftWorld.getHandle(), _oldPainting.serverPosX, _oldPainting.serverPosY, _oldPainting.serverPosZ,
                    _oldPainting.hangingDirection % 4);

            _newPainting.art = _oldPainting.art;
            _oldPainting.setDead();

            if (_auto) {
                final int _i = (PaintingWrapper.PAINTINGS.indexOf(_newPainting.art) + (back ? -1 : 1) + PaintingWrapper.PAINTINGS.size()) % PaintingWrapper.PAINTINGS.size();
                _newPainting.art = (PaintingWrapper.PAINTINGS.get(_i));
                _newPainting.setDirection(_newPainting.hangingDirection);
                _newPainting.worldObj.spawnEntityInWorld(_newPainting);
                p.sendMessage(ChatColor.GREEN + "Painting set to ID: " + (_i));
            } else {
                try {
                    _newPainting.art = (PaintingWrapper.PAINTINGS.get(choice));
                    _newPainting.setDirection(_newPainting.hangingDirection);
                    _newPainting.worldObj.spawnEntityInWorld(_newPainting);
                    p.sendMessage(ChatColor.GREEN + "Painting set to ID: " + choice);
                } catch (final Exception _e) {
                    p.sendMessage(ChatColor.RED + "Your input was invalid somewhere.");
                }
            }
        }
    }
}
