package me.skawke.spoutessentials.config;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class SpoutEssentialsMusicList
{
  private static File directory;
  private static File configfile;
  public static YamlConfiguration config = new YamlConfiguration();
  private static Plugin plugin;
  public static Logger log = Logger.getLogger("Minecraft");

  public static void Initialize(Plugin p) throws Exception {
    plugin = p;
    directory = plugin.getDataFolder();
    configfile = new File(directory, "musicList.yml");
    if (!directory.exists()) directory.mkdir();
    if (!configfile.exists()) configfile.createNewFile();
    LoadConfig();
  }

  public static void LoadConfig() throws Exception
  {
    config.load(configfile);
    config.set(
      "#DON'T put spaces in any of the songs. Use underscores instead!", 
      "-skawke");
    config.set(
      "#DON'T use HTTPS link, use only direct links!", 
      "-DreTaX");
    config.addDefault("songs.MySong", "http://dl.dropbox.com/u/136953717/SandwichEat09.ogg");
    config.options().copyDefaults(true);
    exampleMusicList();
    config.save(configfile);
  }

  static String exampleMusicList()
  {
    return config.getString("songs.MySong", "http://dl.dropbox.com/u/136953717/SandwichEat09.ogg");
  }
}