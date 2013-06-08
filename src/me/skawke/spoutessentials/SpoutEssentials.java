package me.skawke.spoutessentials;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import me.dretax.metrics.Metrics;
import me.skawke.spoutessentials.APIcommand.SpoutEssentialsKListener;
import me.skawke.spoutessentials.commands.SpoutEssentialsCommandManager;
import me.skawke.spoutessentials.commands.SpoutEssentialsSkyCommands;
import me.skawke.spoutessentials.config.SpoutEssentialsConfig;
import me.skawke.spoutessentials.config.SpoutEssentialsGUI;
import me.skawke.spoutessentials.config.SpoutEssentialsModuleConfig;
import me.skawke.spoutessentials.config.SpoutEssentialsMusicList;
import me.skawke.spoutessentials.config.SpoutEssentialsPlayerConfig;
import me.skawke.spoutessentials.listeners.SpoutEssentialsPlayerListener;
import me.skawke.spoutessentials.listeners.SpoutEssentialsScreenListener;
import me.skawke.spoutessentials.listeners.SpoutEssentialsSpoutListener;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.sound.SoundManager;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class SpoutEssentials extends JavaPlugin
{
	private SpoutEssentialsPlayerListener playerListener;
	private static SpoutEssentials instance;
	private Logger log = Logger.getLogger("Minecraft");
	private boolean UseWorldGuard = true;
	public static final String _prefix = ChatColor.AQUA + "[SpoutEssentials] ";
	public static ConsoleCommandSender _cs;
	public SoundManager sm;
	public String serverName = "";
	public String loginMessage = "";
	public String PermissionsToUse = "default";
	public Metrics metrics;
	private WorldGuardPlugin worldguard;
	private PermissionManager permissionHandler;
	public static SpoutEssentials callback;

	public void onEnable()
	{
		callback = this;
		_cs = getServer().getConsoleSender();
		try
		{
			SpoutEssentialsConfig.Initialize(this);
			SpoutEssentialsPlayerConfig.Initialize(this);
			SpoutEssentialsModuleConfig.Initialize(this);
			SpoutEssentialsMusicList.Initialize(this);
			SpoutEssentialsGUI.Initialize(this);
		}
		catch (Exception e) {
			e.printStackTrace();
			this.log.warning("An error has occurred in loading the config, please go to the Bukkit page and tell what you see below, thank you!");
			sendConsoleMessage(ChatColor.GREEN + "Please Report this error on our bukkit dev page");
		}
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
			sendConsoleMessage(ChatColor.GREEN + "SpoutEssentials Metrics Enabled!");
		} catch (IOException e) {
			// Failed to submit the stats :-(
		}

		instance = this;
		SpoutEssentialsCommandManager.labels = new HashMap<Object, Object>();
		SpoutEssentialsCommandManager.HUDEnable = new HashMap<Object, Object>();
		getCommand("spe").setExecutor(new SpoutEssentialsCommandManager(this));

		getCommand("sky").setExecutor(new SpoutEssentialsSkyCommands(this));

		this.sm = SpoutManager.getSoundManager();

		PluginManager pm = getServer().getPluginManager();
		this.playerListener = new SpoutEssentialsPlayerListener(this);
		pm.registerEvents(this.playerListener, this);
		pm.registerEvents(new SpoutEssentialsScreenListener(this), this);
		pm.registerEvents(new SpoutEssentialsSpoutListener(this), this);
		if (SpoutEssentialsModuleConfig.EnableScreenCommands()) {
			SpoutManager.getKeyBindingManager().registerBinding("SpoutEssentials-", Keyboard.KEY_GRAVE, "Opens the SpoutEssentials command GUI", 
					new SpoutEssentialsKListener(this), this);
			getLogger().info("SpoutEssentials has registered the grave key command.");
		}

		sendConsoleMessage(ChatColor.GREEN + "SpoutEssentials 4.0.2 Enabled!");
		sendConsoleMessage(ChatColor.GREEN + "SpoutEssentials is made by skawke :) !");
		sendConsoleMessage(ChatColor.GREEN + "Fixed By DreTaX");

		if (SpoutEssentialsModuleConfig.WorldGuardStatus()) {
			this.log.info("SpoutEssentials: WorldGuard Module enabled");
			Plugin worldGuard = getServer().getPluginManager().getPlugin("WorldGuard");
			if (worldGuard == null)
				this.log.warning("SpoutEssentials can't detect WorldGuard. Disabling some functions");
		}
		else {
			this.log.info("SpoutEssentials: WorldGuard Module disabled");
		}

		if (this.UseWorldGuard) {
			setWorldguard((WorldGuardPlugin)pm.getPlugin("WorldGuard"));
		}
		if (SpoutEssentialsModuleConfig.EnableVanishNoPacketSupport()) {
			Plugin temp = getServer().getPluginManager().getPlugin("VanishNoPacket");
			if (temp != null)
				this.log.info("SpoutEssentials: VanishNoPacket detected!");
		}
		setupPermissions();
	}

	private void setupPermissions() {
		if (this.permissionHandler != null) {
			return;
		}
		Plugin permissionsPlugin = getServer().getPluginManager().getPlugin("PermissionsEx");

		if (permissionsPlugin == null) {
			this.log.info("Permission system not detected, defaulting to OP");
			return;
		}

		this.permissionHandler = PermissionsEx.getPermissionManager();
		this.log.info("Found and will use plugin " + PermissionsEx.getPlugin().getName());
		this.PermissionsToUse = "PermissionsEx";
	}

	public void onDisable() {
		this.log.info("SpoutEssentials has been disabled. Bye bye!");
	}

	public PermissionManager getPermissions() {
		return this.permissionHandler;
	}

	public void setWorldguard(WorldGuardPlugin worldguard) {
		this.worldguard = worldguard;
	}

	public WorldGuardPlugin getWorldguard() {
		return this.worldguard;
	}

	public boolean isWorldGuardEnabled() {
		return this.UseWorldGuard;
	}

	public String getPermissionsToUse() {
		return this.PermissionsToUse;
	}

	public static SpoutEssentials getInstance() {
		return instance;
	}
	public static void sendConsoleMessage(String msg) {
		_cs.sendMessage(_prefix + ChatColor.AQUA + msg);
	}
}