package me.skawke.spoutessentials.config;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SpoutEssentialsPlayerConfig
{
  private static File directory;
  private static File configfile;
  public static YamlConfiguration config = new YamlConfiguration();
  private static Plugin plugin;
  public static Logger log = Logger.getLogger("Minecraft");

  public static void Initialize(Plugin p) throws Exception {
    plugin = p;
    directory = plugin.getDataFolder();
    configfile = new File(directory, "playerOptions.yml");
    if (!directory.exists()) directory.mkdir();
    if (!configfile.exists()) configfile.createNewFile();
    LoadConfig();
  }

  public static void LoadConfig()
    throws Exception
  {
    config.load(configfile);
    config.set("#REMEMBER, Make sure everything is under 26 characters, else your messages will not show", "-skawke");

    config.addDefault("playerOptions.player_name.cape", "example cape url");
    config.addDefault("playerOptions.player_name.skin", "SKIN URL HERE");
    config.addDefault("playerOptions.player_name.title", "a display name here");
    config.addDefault("group1.skin", "URL HERE");
    config.addDefault("group2.skin", "URL HERE");
    config.addDefault("group3.skin", "URL HERE");
    config.addDefault("group4.skin", "URL HERE");
    config.options().copyDefaults(true);

    GetPlayerCape();
    GetPlayerTitle();
    GetPlayerSkin();
    GetGroupSkin1();
    GetGroupSkin2();
    GetGroupSkin3();
    GetGroupSkin4();
    config.save(configfile);
  }

  public static String GetPlayerCape()
  {
    return config.getString("playerOptions.player_name.cape", "example cape url");
  }

  public static String GetPlayerSkin()
  {
    return config.getString("playerOptions.player_name.skin", "SKIN URL HERE");
  }

  public static String GetPlayerTitle()
  {
    return config.getString("playerOptions.player_name.title", "a display name here");
  }

  public static String GetGroupSkin1()
  {
    return config.getString("group1.skin", "URL HERE");
  }

  public static String GetGroupSkin2()
  {
    return config.getString("group2.skin", "URL HERE");
  }

  public static String GetGroupSkin3()
  {
    return config.getString("group3.skin", "URL HERE");
  }

  public static String GetGroupSkin4()
  {
    return config.getString("group4.skin", "URL HERE");
  }

  public static void setPlayerCape(SpoutPlayer player, String url) {
    String myname = player.getName();
    String url1 = url;

    config.set("playerOptions." + myname + ".cape", url1);

    log.info("Added " + url1);
    try {
      config.save(configfile);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void setPlayerTitle(SpoutPlayer player, String title) {
    String myname = player.getName();
    String url1 = title;

    config.set("playerOptions." + myname + ".title", url1);
    try {
      config.save(configfile);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}