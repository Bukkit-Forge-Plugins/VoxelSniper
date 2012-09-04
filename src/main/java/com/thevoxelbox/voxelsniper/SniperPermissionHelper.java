package com.thevoxelbox.voxelsniper;

import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author MikeMatrix
 */
public class SniperPermissionHelper {
	public static final String VOXELSNIPER_MISC_UNRESTRICTED = "voxelsniper.misc.unrestricted";
	public static final String VOXELSNIPER_COMMANDS = "voxelsniper.commands";
	public static final String VOXELSNIPER_COMMANDS_GOTO = "voxelsniper.commands.goto";
	public static final String VOXELSNIPER_COMMANDS_LIGHTNING = "voxelsniper.commands.lightning";
	public static final String VOXELSNIPER_COMMANDS_WEATHER = "voxelsniper.commands.weather";
    private final TreeMap<String, Sniper> snipers = new TreeMap<String, Sniper>();

    /**
	 * @param player
	 * @return {@link Sniper}
	 */
	public final Sniper getSniperInstance(final Player player) {
		Sniper _instance = this.snipers.get(player.getName());
		if (_instance != null && !(_instance.getClass() == Sniper.class)) {
			this.snipers.remove(player.getName());
			_instance = null;
		}
		if (_instance == null) {
			_instance = new Sniper(player);
			_instance.setPlayer(player);
			_instance.reset();
			_instance.loadAllPresets();
			this.snipers.put(player.getName(), _instance);
		}
		return _instance;
	}

    /**
     * @param playerName
     * @return {@link Sniper}
     */
    public final Sniper getSniperInstance(final String playerName) {
        return this.getSniperInstance(Bukkit.getPlayer(playerName));
    }
}
