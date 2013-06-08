package me.skawke.spoutessentials.config;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class SpoutEssentialsModuleConfig
{
  private static File directory;
  private static File configfile;
  public static YamlConfiguration config = new YamlConfiguration();
  private static Plugin plugin;
  public static Logger log = Logger.getLogger("Minecraft");

  public static void Initialize(Plugin p) throws Exception {
    plugin = p;
    directory = plugin.getDataFolder();
    configfile = new File(directory, "modules.yml");
    if (!directory.exists()) directory.mkdir();
    if (!configfile.exists()) configfile.createNewFile();
    LoadConfig();
  }

  public static void LoadConfig()
    throws Exception
  {
    config.load(configfile);

    config.set("#Disable/Enable modules here", "-skawke");

    config.addDefault("enableWorldGuard", Boolean.valueOf(false));
    config.addDefault("EnablePlayerLeaveNotifications", Boolean.valueOf(true));
    config.addDefault("EnablePlayerJoinNotifications", Boolean.valueOf(true));
    config.addDefault("forceTexturePack", Boolean.valueOf(true));
    config.addDefault("disableOnScreenHelpCompletely", Boolean.valueOf(false));
    config.addDefault("enableBoseEconomySupport", Boolean.valueOf(false));
    config.addDefault("enableOnJoinMusic", Boolean.valueOf(false));
    config.addDefault("allowSkawkeSupport", Boolean.valueOf(true));
    config.addDefault("enableModDisguiseSupport", Boolean.valueOf(false));
    config.addDefault("enableGroupSkin", Boolean.valueOf(false));
    config.addDefault("enableMusicPlayList", Boolean.valueOf(false));
    config.addDefault("enableScreenCommands", Boolean.valueOf(false));
    config.addDefault("enableVanishNoPacketSupport", Boolean.valueOf(false));
    config.addDefault("enableSplashScreen", Boolean.valueOf(false));
    config.addDefault("enablePleaseUseSpoutcraft", Boolean.valueOf(false));
    config.addDefault("enableAutoUpdate", Boolean.valueOf(true));
    config.options().copyDefaults(true);

    UseSpoutcraftMessage();
    WorldGuardStatus();
    LeaveMessageStatus();
    JoinMessageStatus();
    GetWorldTexturePackOption();
    GetOnScreenHelpOption();
    EnableScreenCommands();
    EnableBoseSupport();
    enableOnJoinMusic();
    skawkeSupport();
    EnableMobDisguiseSupport();
    EnableGroupSkins();
    EnableMusicPlayList();
    EnableVanishNoPacketSupport();
    EnableSplashScreen();
    enableAutoUpdate();
    config.save(configfile);
  }

  public static boolean UseSpoutcraftMessage() {
    return config.getBoolean("enablePleaseUseSpoutcraft", false);
  }

  public static boolean WorldGuardStatus()
  {
    return config.getBoolean("enableWorldGuard", false);
  }

  public static boolean LeaveMessageStatus() {
    return config.getBoolean("EnablePlayerLeaveNotifications", false);
  }

  public static boolean JoinMessageStatus() {
    return config.getBoolean("EnablePlayerJoinNotifications", false);
  }

  public static Boolean GetWorldTexturePackOption() {
    return Boolean.valueOf(config.getBoolean("forceTexturePack", false));
  }

  public static Boolean GetOnScreenHelpOption()
  {
    return Boolean.valueOf(config.getBoolean("disableOnScreenHelpCompletely", false));
  }

  public static boolean EnableBoseSupport() {
    return config.getBoolean("enableBoseEconomySupport", false);
  }

  public static boolean enableOnJoinMusic() {
    return config.getBoolean("enableOnJoinMusic", false);
  }

  public static boolean skawkeSupport() {
    return config.getBoolean("allowSkawkeSupport", false);
  }

  public static boolean EnableMobDisguiseSupport() {
    return config.getBoolean("enableModDisguiseSupport", false);
  }

  public static boolean EnableGroupSkins() {
    return config.getBoolean("enableGroupSkin", false);
  }

  public static boolean EnableMusicPlayList() {
    return config.getBoolean("enableMusicPlayList", false);
  }

  public static boolean EnableScreenCommands() {
    return config.getBoolean("enableScreenCommands", false);
  }

  public static boolean EnableVanishNoPacketSupport() {
    return config.getBoolean("enableVanishNoPacketSupport", false);
  }

  public static boolean EnableSplashScreen() {
    return config.getBoolean("enableSplashScreen", false);
  }

  public static boolean enableAutoUpdate() {
    return config.getBoolean("enableAutoUpdate", false);
  }
}