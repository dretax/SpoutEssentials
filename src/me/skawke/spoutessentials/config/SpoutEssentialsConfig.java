package me.skawke.spoutessentials.config;

import java.io.File;
import java.util.logging.Logger;
import me.skawke.spoutessentials.SpoutEssentials;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ru.tehkode.permissions.PermissionGroup;

public class SpoutEssentialsConfig
{
  private static File directory;
  private static File configfile;
  public static YamlConfiguration config = new YamlConfiguration();
  private static Plugin plugin;
  public static Logger log = Logger.getLogger("Minecraft");

  public static void Initialize(Plugin p) throws Exception {
    plugin = p;
    directory = plugin.getDataFolder();
    configfile = new File(directory, "config.yml");
    if (!directory.exists()) directory.mkdir();
    if (!configfile.exists()) configfile.createNewFile();
    LoadConfig();
  }

  public static void LoadConfig()
    throws Exception
  {
    config.load(configfile);

    config.set("#REMEMBER, Make sure everything is under 26 characters, else your messages will not show", "-skawke");
    config.addDefault("NotificationServerMessage", "Welcome to TaylorMC");
    config.addDefault("guiCommandKey", "~");
    config.addDefault("texturepack.default", "In order to add group based texture packs, put the group and then the texture pack.");
    config.addDefault("NotificationSubMessage", "Have a good time!");
    config.addDefault("pokedNotificationIcon", "DIAMOND_ORE");
    config.addDefault("loginNotificationIcon", "GOLDEN_APPLE");
    config.addDefault("loginNotificationIcon", "GOLDEN_APPLE");
    config.addDefault("OnScreenHelp.line1", "This is the first line");
    config.addDefault("OnScreenHelp.line2", "This is the second line");
    config.addDefault("OnScreenHelp.line3", "This is the third line");
    config.addDefault("OnScreenHelp.line4", "This is the fourth line");
    config.addDefault("splashScreenURL.default", "http://www.google.com/logo.png");
    config.addDefault("defaultCloudHeight", Integer.valueOf(128));
    config.addDefault("defaultSunSize", Integer.valueOf(100));
    config.addDefault("defaultMoonSize", Integer.valueOf(100));
    config.addDefault("UseSpoutcraftMessage", "You don't have Spoutcraft? Go download it to get tons of custom content!");
    config.addDefault("defaultAmbientMusic", "www.music.com/welcome.ogg");
    config.addDefault("WorldGuardRegions.exampleName.message", "exampleMessage");
    config.addDefault("WorldGuardRegions.exampleName.music.join", "www.music.com/music.ogg");
    config.addDefault("WorldGuardRegions.exampleName.subMessage", "26 char MAX");
    config.addDefault("WorldGuardRegions.exampleName.icon", "ARROW");
    config.addDefault("WorldGuardRegions.exampleName.texturepack", "www.texturepack.com");
    config.addDefault("WorldGuardRegions.exampleName.resetTextureOnPlayerLeave", Boolean.valueOf(false));
    config.addDefault("WorldGuardRegions.exampleName.fog", "NORMAL");
    config.addDefault("onPlayerJoinMusic", "www.music.com/welcome.ogg");

    GetServerName();

    GetLoginMessage();

    GetPokeIcon();
    GetLoginIcon();
    GetLeaveIcon();
    GetOnScreenHelp();
    GetOnScreenHelp2();
    GetOnScreenHelp3();
    GetOnScreenHelp4();
    GetTexturePacks();
    GetCloudHeight();
    GetSunSize();
    GetMoonSize();
    GetRegionMessage();
    GetRegionMusic();
    GetRegionSubMessage();
    GetRegionIcon();
    fogDistance();
    GetJoinMusic();
    GetRegionTexture();
    resetPlayerTexture();
    SplashScreenLogo(null);
    guiKey();

    config.options().copyDefaults(true);
    config.save(configfile);
  }

  public static String UseSpoutcraftMessage()
  {
    return config.getString("UseSpoutcraftMessage", "You don't have Spoutcraft? Go download it to get tons of custom content!");
  }

  public static String GetServerName()
  {
    return config.getString("NotificationServerMessage", "Welcome to TaylorMC");
  }

  public static String guiKey() {
    return config.getString("guiCommandKey", "~");
  }

  public static String GetTexturePacks() {
    return config.getString("texturepack.default", "LOOK AT THE THREAD FOR MORE INFO. DO NOT CHANGE THIS");
  }

  public static String GetLoginMessage() {
    return config.getString("NotificationSubMessage", "Have a good time!");
  }

  public static String GetPokeIcon()
  {
    return config.getString("pokedNotificationIcon", "DIAMOND_ORE");
  }

  public static String GetLoginIcon() {
    return config.getString("loginNotificationIcon", "GOLDEN_APPLE");
  }

  public static String GetLeaveIcon() {
    return config.getString("LeaveNotificationIcon", "ARROW");
  }

  public static String GetOnScreenHelp()
  {
    return config.getString("OnScreenHelp.line1", "This is the first line");
  }

  public static String GetOnScreenHelp2()
  {
    return config.getString("OnScreenHelp.line2", "This is the second line");
  }

  public static String GetOnScreenHelp3()
  {
    return config.getString("OnScreenHelp.line3", "This is the third line");
  }

  public static String GetOnScreenHelp4()
  {
    return config.getString("OnScreenHelp.line4", "This is the fourth line");
  }

  public static String SplashScreenLogo(Player player) {
    String url = config.getString("splashScreenURL.default", "http://www.google.com/logo.png");
    if (player == null) return url;
    if (SpoutEssentials.getInstance().getPermissions() == null) return config.getString("splashScreenURL.default");
    for (PermissionGroup group : SpoutEssentials.getInstance().getPermissions().getUser(player.getName()).getGroups()) {
      if (!config.getString("splashScreenURL." + group.getName(), "null").equalsIgnoreCase("null")) {
        url = config.getString("splashScreenURL." + group.getName(), "null");
      }
    }
    return url;
  }

  public static Integer GetCloudHeight() {
    return Integer.valueOf(config.getInt("defaultCloudHeight", 128));
  }

  public static Integer GetSunSize() {
    return Integer.valueOf(config.getInt("defaultSunSize", 100));
  }

  public static Integer GetMoonSize() {
    return Integer.valueOf(config.getInt("defaultMoonSize", 100));
  }

  public static String GetRegionMessage() {
    return config.getString("WorldGuardRegions.exampleName.message", "exampleMessage");
  }
  public static String GetRegionMusic() {
    return config.getString("WorldGuardRegions.exampleName.music", "www.music.com/music.ogg");
  }
  public static String GetRegionSubMessage() {
    return config.getString("WorldGuardRegions.exampleName.subMessage", "26 char MAX");
  }
  public static String GetRegionIcon() {
    return config.getString("WorldGuardRegions.exampleName.icon", "ARROW");
  }
  public static String GetRegionTexture() {
    return config.getString("WorldGuardRegions.exampleName.texturepack", "www.texturepack.com");
  }
  public static boolean resetPlayerTexture() {
    return config.getBoolean("WorldGuardRegions.exampleName.resetTextureOnPlayerLeave", false);
  }
  public static String fogDistance() {
    return config.getString("WorldGuardRegions.exampleName.fog", "NORMAL");
  }

  public static String GetJoinMusic() {
    return config.getString("onPlayerJoinMusic", "www.music.com/welcome.ogg");
  }
}