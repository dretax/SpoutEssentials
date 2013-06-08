package me.skawke.spoutessentials;

import org.bukkit.entity.Player;

public class Utils
{
	public static boolean checkPermissions(Player player, String checkPermissionsString)
	{
		SpoutEssentials plugin = SpoutEssentials.getInstance();

		if (plugin.getPermissionsToUse().equalsIgnoreCase("default")) {
			return player.isOp() ? true : player.hasPermission(checkPermissionsString);
		}

		if (plugin.getPermissionsToUse().equalsIgnoreCase("PermissionsEx")) {
			return player.isOp() ? true : plugin.getPermissions().has(player, checkPermissionsString);
		}

		return false;
	}
}