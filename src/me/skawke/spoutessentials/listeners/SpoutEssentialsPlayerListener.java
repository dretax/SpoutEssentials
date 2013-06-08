package me.skawke.spoutessentials.listeners;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import me.skawke.spoutessentials.SpoutEssentials;
import me.skawke.spoutessentials.Utils;
import me.skawke.spoutessentials.commands.SpoutEssentialsCommandManager;
import me.skawke.spoutessentials.config.SpoutEssentialsConfig;
import me.skawke.spoutessentials.config.SpoutEssentialsModuleConfig;
import me.skawke.spoutessentials.config.SpoutEssentialsPlayerConfig;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.player.RenderDistance;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SpoutEssentialsPlayerListener
  implements Listener
{
	private SpoutEssentials plugin;
	private final Set<String> playerSentTexturePack = new HashSet<String>();

	private HashMap<Player, String> playerInWorld = new HashMap<Player, String>();
	private HashMap<LocalPlayer, String> playerRegion = new HashMap<LocalPlayer, String>();
	private HashMap<Player, Vector> playerLoc = new HashMap<Player, Vector>();


	public SpoutEssentialsPlayerListener(SpoutEssentials plugin)
	{
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		SpoutEssentialsCommandManager.HUDEnable.put(event.getPlayer().getName(), Boolean.valueOf(false));
		this.playerInWorld.remove(event.getPlayer());
		if (SpoutEssentialsModuleConfig.LeaveMessageStatus()) {
			Player LeavePlayer = event.getPlayer();

			if (Utils.checkPermissions(LeavePlayer, "spoutessentials.leavemessage")) {
				for (Player leaveOnlinePlayer : this.plugin.getServer().getOnlinePlayers())
				{
					SpoutPlayer sPlayer = SpoutManager.getPlayer(leaveOnlinePlayer);
					if (LeavePlayer.getName().length() > 26) {
						SpoutEssentials.getInstance().getLogger().info("Player name too long to fit in a notification");
					}
					else {
						if ((SpoutEssentialsModuleConfig.EnableVanishNoPacketSupport()) && (Utils.checkPermissions(event.getPlayer(), "vanish.currentlyVanished"))) {
							return;
						}
						sPlayer.sendNotification(LeavePlayer.getName(), "has left the game", Material.getMaterial(SpoutEssentialsConfig.GetLeaveIcon().toUpperCase()));
					}

				}

			}

		}

		if (SpoutEssentialsModuleConfig.GetWorldTexturePackOption().booleanValue())
			this.playerSentTexturePack.remove(event.getPlayer().getName());
	}

	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event)
	{
		SpoutPlayer sPlayer = SpoutManager.getPlayer(event.getPlayer());
		setPlayerOptions(sPlayer);
		if (SpoutEssentialsModuleConfig.GetWorldTexturePackOption().booleanValue())
		{
			if (this.playerInWorld.get(event.getPlayer()) != event.getPlayer().getWorld().getName())
			{
				doWorldBasedActions(event.getPlayer().getWorld(), sPlayer);
				this.playerInWorld.put(event.getPlayer(), event.getPlayer().getWorld().getName());
			}
		}
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event)
	{
		if ((SpoutEssentialsModuleConfig.WorldGuardStatus()) && (SpoutEssentials.getInstance().isWorldGuardEnabled())) {
			if (event.isCancelled()) {
				return;
			}
			Player player = event.getPlayer();

			SpoutPlayer splayer = SpoutManager.getPlayer(player);
			if (!splayer.isSpoutCraftEnabled()) {
				return;
			}
			Vector position = new Vector(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
			if (!this.playerLoc.containsKey(player)) {
				this.playerLoc.put(player, position);
				return;
			}
			if (!((Vector)this.playerLoc.get(player)).equals(position)) {
				this.playerLoc.put(player, position);
				WorldGuardPlugin wgPlugin = this.plugin.getWorldguard();
				LocalPlayer lp = wgPlugin.wrapPlayer(event.getPlayer());
				Vector vec = lp.getPosition();
				Location loc = event.getTo();
				World world = loc.getWorld();
				RegionManager rm = wgPlugin.getRegionManager(world);

				ApplicableRegionSet appregions = rm.getApplicableRegions(vec);
				if (appregions.size() == 0) {
					if (this.playerRegion.containsKey(lp))
					{
						SpoutManager.getSoundManager().stopMusic(splayer);
						if (SpoutEssentialsConfig.config.getBoolean("WorldGuardRegions." + (String)this.playerRegion.get(lp) + "resetTextureOnPlayerLeave", false)) {
							splayer.resetTexturePack();
						}

					}

					this.playerRegion.remove(lp);
					return;
				}

				String regionName = "";

				for (ProtectedRegion protectedRegion : appregions) {
					regionName = protectedRegion.getId();
				}

				if ((this.playerRegion.containsKey(lp)) && (((String)this.playerRegion.get(lp)).equals(regionName))) {
					return;
				}

				this.playerRegion.put(lp, regionName);
				String EnterMessage = SpoutEssentialsConfig.config.getString("WorldGuardRegions." + (String)this.playerRegion.get(lp) + ".message", "Welcome!");
				String EnterSubMessage = SpoutEssentialsConfig.config.getString("WorldGuardRegions." + (String)this.playerRegion.get(lp) + ".subMessage", "Welcome to the server!");
				String MusicURL = SpoutEssentialsConfig.config.getString("WorldGuardRegions." + (String)this.playerRegion.get(lp) + ".music");
				String theIcon = SpoutEssentialsConfig.config.getString("WorldGuardRegions." + (String)this.playerRegion.get(lp) + ".icon", "ARROW");
				String textureURL = SpoutEssentialsConfig.config.getString("WorldGuardRegions." + (String)this.playerRegion.get(lp) + ".texturepack");
				if ((EnterMessage != null) && (EnterSubMessage != null) && (theIcon != null)) {
					if ((EnterMessage.length() < 27) && (EnterSubMessage.length() < 27))
						splayer.sendNotification(EnterMessage, EnterSubMessage, Material.getMaterial(theIcon));
					else {
						SpoutEssentials.getInstance().getLogger().warning("SpoutEssentials: A region message is greater than 26chars");
					}
				}

				if (MusicURL != null) {
					SpoutManager.getSoundManager().playCustomMusic(this.plugin, splayer, MusicURL, false);
				}

				if (textureURL != null) {
					splayer.setTexturePack(textureURL);
				}
				String fogLevel = SpoutEssentialsConfig.config.getString("WorldGuardRegions." + (String)this.playerRegion.get(lp) + ".fog");

				if ((fogLevel != null) && (fogLevel.equalsIgnoreCase("TINY"))) {
					splayer.setRenderDistance(RenderDistance.TINY);
				}
				if ((fogLevel != null) && (fogLevel.equalsIgnoreCase("SHORT"))) {
					splayer.setRenderDistance(RenderDistance.SHORT);
				}
				if ((fogLevel != null) && (fogLevel.equalsIgnoreCase("NORMAL"))) {
					splayer.setRenderDistance(RenderDistance.NORMAL);
				}
				if ((fogLevel != null) && (fogLevel.equalsIgnoreCase("FAR"))) {
					splayer.setRenderDistance(RenderDistance.FAR);
				}
			}
		}
	}

	@EventHandler
	public void onPlayerRespawn(final PlayerRespawnEvent event)
	{
		this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
		{
			public void run()
			{
				Player player = event.getPlayer();
				SpoutPlayer sPlayer = SpoutManager.getPlayer(player);
				SpoutEssentialsPlayerListener.this.setPlayerOptions(sPlayer);
			}
		}
		, 40L);
	}

	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent event)
	{
		SpoutManager.getFileManager().addToPreLoginCache(this.plugin, SpoutEssentialsConfig.SplashScreenLogo(event.getPlayer()));
		Player player = event.getPlayer();
		SpoutPlayer onJoinPlayer = SpoutManager.getPlayer(player);

		if (SpoutEssentialsModuleConfig.JoinMessageStatus())
		{
			if (Utils.checkPermissions(player, "spoutessentials.joinmessage")) {
				for (Player player1 : this.plugin.getServer().getOnlinePlayers()) {
					SpoutPlayer sPlayer = SpoutManager.getPlayer(player1);
					if (player.getName().length() >= 26) {
						SpoutEssentials.getInstance().getLogger().info("Player name too long to fit in a notification");
					}
					else {
						if ((SpoutEssentialsModuleConfig.EnableVanishNoPacketSupport()) && (Utils.checkPermissions(event.getPlayer(), "vanish.currentlyVanished"))) {
							return;
						}
						sPlayer.sendNotification(player.getName(), "has joined the game", Material.getMaterial(SpoutEssentialsConfig.GetLoginIcon().toUpperCase()));
					}

					if (!SpoutEssentialsModuleConfig.GetOnScreenHelpOption().booleanValue())
					{
						playerInit(sPlayer);
					}
				}

			}

		}

		if (SpoutEssentialsModuleConfig.UseSpoutcraftMessage()) {
			String message = SpoutEssentialsConfig.UseSpoutcraftMessage();
			player.sendMessage(ChatColor.GREEN + "[SPE] " + message);
		}

		this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
		{
			public void run()
			{
				Player player = event.getPlayer();
				SpoutPlayer sPlayer = SpoutManager.getPlayer(player);
				SpoutEssentialsPlayerListener.this.setPlayerOptions(sPlayer);
			}
		}
		, 40L);

		this.playerInWorld.put(event.getPlayer(), event.getPlayer().getWorld().getName());

		if (SpoutEssentialsPlayerConfig.GetPlayerTitle().equalsIgnoreCase("hidden")) {
			onJoinPlayer.hideTitle();
			onJoinPlayer.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "Title hidden");
		}
	}

	protected void doWorldBasedActions(World world, SpoutPlayer player)
	{
		String texturePackUrl = SpoutEssentialsConfig.config.getString("texturepack." + world.getName());

		if (texturePackUrl != null)
			try
		{
				player.setTexturePack(texturePackUrl);
		}
		catch (IllegalArgumentException ex) {
			SpoutEssentials.getInstance().getLogger().severe("[SpoutEssentials] Error with texture pack for world " + player.getWorld().getName() + " : " + ex.getMessage());
		}
	}

	public void playerInit(SpoutPlayer sPlayer)
	{
		GenericLabel label = new GenericLabel("");
		label.setTextColor(new Color(1.0F, 1.0F, 1.0F, 1.0F)).setX(10).setY(40);
		SpoutEssentialsCommandManager.labels.put(sPlayer.getName(), label.getId());
		label.setText(SpoutEssentialsConfig.GetOnScreenHelp4() + "\n" + SpoutEssentialsConfig.GetOnScreenHelp3() + "\n" + SpoutEssentialsConfig.GetOnScreenHelp2() + "\n" + SpoutEssentialsConfig.GetOnScreenHelp());
		label.setWidth(GenericLabel.getStringWidth(label.getText())).setHeight(GenericLabel.getStringHeight(label.getText()));
		label.setVisible(false);

		sPlayer.getMainScreen().attachWidget(this.plugin, label);
		if (!SpoutEssentialsCommandManager.HUDEnable.containsKey(sPlayer.getName())) {
			SpoutEssentialsCommandManager.HUDEnable.put(sPlayer.getName(), Boolean.valueOf(true));
		}
		if (((Boolean)SpoutEssentialsCommandManager.HUDEnable.get(sPlayer.getName())).booleanValue())
			label.setVisible(true);
	}

	protected void setPlayerOptions(SpoutPlayer sPlayer)
	{
		if (SpoutEssentialsPlayerConfig.config.getString("playerOptions." + sPlayer.getName() + ".cape") != null)
		{
			sPlayer.setCape(SpoutEssentialsPlayerConfig.config.getString("playerOptions." + sPlayer.getName() + ".cape"));
		}

		if (SpoutEssentialsPlayerConfig.config.getString("playerOptions." + sPlayer.getName() + ".title") != null)
		{
			sPlayer.setTitle(SpoutEssentialsPlayerConfig.config.getString("playerOptions." + sPlayer.getName() + ".title"));
		}
		if (SpoutEssentialsPlayerConfig.config.getString("playerOptions." + sPlayer.getName() + ".skin") != null)
		{
			sPlayer.setSkin(SpoutEssentialsPlayerConfig.config.getString("playerOptions." + sPlayer.getName() + ".skin"));
		}
	}
  
}