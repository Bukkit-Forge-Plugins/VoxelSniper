package com.thevoxelbox.voxelsniper.brush;

import keepcalm.mods.bukkit.bukkitAPI.BukkitServer;
import keepcalm.mods.bukkit.bukkitAPI.BukkitWorld;
import keepcalm.mods.bukkit.bukkitAPI.entity.BukkitFireball;
import keepcalm.mods.bukkit.bukkitAPI.entity.BukkitPlayer;
import keepcalm.mods.bukkit.bukkitAPI.entity.BukkitSmallFireball;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import com.thevoxelbox.voxelsniper.Message;
import com.thevoxelbox.voxelsniper.SnipeData;

/**
 * 
 * @author Gavjenks Heavily revamped from ruler brush blockPositionY 
 * @author Giltwist
 * @author Monofraps (Merged Meteor brush)
 */
public class CometBrush extends Brush {
	private static int timesUsed = 0;
	private boolean useBigBalls = false;

	/**
	 * 
	 */
	public CometBrush() {
		this.setName("Comet");
	}

	private void doFireball(final SnipeData v) {
		final Vector _targetCoords = new Vector(this.getTargetBlock().getX() + .5 * this.getTargetBlock().getX() / Math.abs(this.getTargetBlock().getX()), this
				.getTargetBlock().getY() + .5, this.getTargetBlock().getZ() + .5 * this.getTargetBlock().getZ() / Math.abs(this.getTargetBlock().getZ()));
		final Location _playerLoc = v.owner().getPlayer().getLocation();
		final Vector _slope = _targetCoords.subtract(_playerLoc.toVector());

		if (useBigBalls) {
			final EntityFireball _ballEntity = new EntityLargeFireball(((BukkitWorld) v.owner().getPlayer().getWorld()).getHandle(), ((BukkitPlayer) v
					.owner().getPlayer()).getHandle(), _slope.getX(), _slope.getY(), _slope.getZ());
			final BukkitFireball _craftBall = new BukkitFireball((BukkitServer) v.owner().getPlayer().getServer(), _ballEntity);
			_craftBall.setVelocity(_slope.normalize());
			((BukkitWorld) v.owner().getPlayer().getWorld()).getHandle().spawnEntityInWorld(_ballEntity);
		} else {
			final EntitySmallFireball _ballEntity = new EntitySmallFireball(((BukkitWorld) v.owner().getPlayer().getWorld()).getHandle(), ((BukkitPlayer) v
					.owner().getPlayer()).getHandle(), _slope.getX(), _slope.getY(), _slope.getZ());
			final BukkitSmallFireball _craftBall = new BukkitSmallFireball((BukkitServer) v.owner().getPlayer().getServer(), _ballEntity);
			_craftBall.setVelocity(_slope.normalize());
			((BukkitWorld) v.owner().getPlayer().getWorld()).getHandle().spawnEntityInWorld(_ballEntity);
		}
	}
	
	@Override
	public final void parameters(final String[] par, final SnipeData v) {
		for (int _i = 0; _i < par.length; ++_i) {
			String _param = par[_i];

			if (_param.equalsIgnoreCase("info")) {
				v.sendMessage("Parameters:");
				v.sendMessage("balls [big|small]  -- Sets your ball size.");
			}
			if (_param.equalsIgnoreCase("balls")) {
				if (_i + 1 >= par.length) {
					v.sendMessage("The balls parameter expects a ball size after it.");
				}

				String _newBallSize = par[++_i];
				if (_newBallSize.equalsIgnoreCase("big")) {
					useBigBalls = true;
					v.sendMessage("Your balls are " + ChatColor.DARK_RED + (useBigBalls ? "BIG" : "small"));
				} else if (_newBallSize.equalsIgnoreCase("small")) {
					useBigBalls = false;
					v.sendMessage("Your balls are " + ChatColor.DARK_RED + (useBigBalls ? "BIG" : "small"));
				} else {
					v.sendMessage("Unknown ball size.");
				}
			}
		}
	}

	@Override
	protected final void arrow(final SnipeData v) {
		this.doFireball(v);
	}

	@Override
	protected final void powder(final SnipeData v) {
		this.doFireball(v);
	}

	@Override
	public final void info(final Message vm) {
		vm.brushName(this.getName());
		vm.voxel();
		vm.custom("Your balls are " + ChatColor.DARK_RED + (useBigBalls ? "BIG" : "small"));
	}

	@Override
	public final int getTimesUsed() {
		return CometBrush.timesUsed;
	}

	@Override
	public final void setTimesUsed(final int tUsed) {
		CometBrush.timesUsed = tUsed;
	}
}
