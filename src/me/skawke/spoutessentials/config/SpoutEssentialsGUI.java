package me.skawke.spoutessentials.config;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class SpoutEssentialsGUI
{
  private static File directory;
  private static File configfile;
  public static YamlConfiguration config = new YamlConfiguration();
  private static Plugin plugin;
  public static Logger log = Logger.getLogger("Minecraft");

  public static void Initialize(Plugin p) throws Exception {
    plugin = p;
    directory = plugin.getDataFolder();
    configfile = new File(directory, "GUIConfig.yml");
    if (!directory.exists()) directory.mkdir();
    if (!configfile.exists()) configfile.createNewFile();
    LoadConfig();
  }

  public static void LoadConfig()
    throws Exception
  {
    config.load(configfile);

    config.addDefault("column1.labelName", "Column One");
    config.addDefault("column1.button1.name", "Online Players");
    config.addDefault("column1.button1.command", "list");
    config.addDefault("column1.button2.name", "Goto Spawn");
    config.addDefault("column1.button2.command", "spawn");
    config.addDefault("column1.button3.name", "Town Map");
    config.addDefault("column1.button3.command", "towny map");
    config.addDefault("column1.button4.name", "Town Big Map");
    config.addDefault("column1.button4.command", "towny map big");
    config.addDefault("column1.button5.name", "Town Spawn");
    config.addDefault("column1.button5.command", "town spawn");
    config.addDefault("column1.button6.name", "Resident Info");
    config.addDefault("column1.button6.command", "resident");
    config.addDefault("column1.button7.name", "Town Info");
    config.addDefault("column1.button7.command", "town");

    config.addDefault("column2.labelName", "Column Two");
    config.addDefault("column2.button1.name", "Online Players");
    config.addDefault("column2.button1.command", "list");
    config.addDefault("column2.button2.name", "Goto Spawn");
    config.addDefault("column2.button2.command", "spawn");
    config.addDefault("column2.button3.name", "Town Map");
    config.addDefault("column2.button3.command", "towny map");
    config.addDefault("column2.button4.name", "Town Big Map");
    config.addDefault("column2.button4.command", "towny map big");
    config.addDefault("column2.button5.name", "Town Spawn");
    config.addDefault("column2.button5.command", "town spawn");
    config.addDefault("column2.button6.name", "Resident Info");
    config.addDefault("column2.button6.command", "resident");
    config.addDefault("column2.button7.name", "Town Info");
    config.addDefault("column2.button7.command", "town");

    config.addDefault("column3.labelName", "Column Three");
    config.addDefault("column3.button1.name", "Online Players");
    config.addDefault("column3.button1.command", "list");
    config.addDefault("column3.button2.name", "Goto Spawn");
    config.addDefault("column3.button2.command", "spawn");
    config.addDefault("column3.button3.name", "Town Map");
    config.addDefault("column3.button3.command", "towny map");
    config.addDefault("column3.button4.name", "Town Big Map");
    config.addDefault("column3.button4.command", "towny map big");
    config.addDefault("column3.button5.name", "Town Spawn");
    config.addDefault("column3.button5.command", "town spawn");
    config.addDefault("column3.button6.name", "Resident Info");
    config.addDefault("column3.button6.command", "resident");
    config.addDefault("column3.button7.name", "Town Info");
    config.addDefault("column3.button7.command", "town");

    config.options().copyDefaults(true);

    GetColumnOneName();
    GetColumnOneButtonOneName();
    GetColumnOneButtonOneCommand();
    GetColumnOneButtonTwoName();
    GetColumnOneButtonTwoCommand();
    GetColumnOneButtonThreeName();
    GetColumnOneButtonThreeCommand();
    GetColumnOneButtonFourName();
    GetColumnOneButtonFourCommand();
    GetColumnOneButtonFiveName();
    GetColumnOneButtonFiveCommand();
    GetColumnOneButtonSixName();
    GetColumnOneButtonSixCommand();
    GetColumnOneButtonSevenName();
    GetColumnOneButtonSevenCommand();

    GetColumnTwoName();
    GetColumnTwoButtonOneName();
    GetColumnTwoButtonOneCommand();
    GetColumnTwoButtonTwoName();
    GetColumnTwoButtonTwoCommand();
    GetColumnTwoButtonThreeName();
    GetColumnTwoButtonThreeCommand();
    GetColumnTwoButtonFourName();
    GetColumnTwoButtonFourCommand();
    GetColumnTwoButtonFiveName();
    GetColumnTwoButtonFiveCommand();
    GetColumnTwoButtonSixName();
    GetColumnTwoButtonSixCommand();
    GetColumnTwoButtonSevenName();
    GetColumnTwoButtonSevenCommand();

    GetColumnThreeName();
    GetColumnThreeButtonOneName();
    GetColumnThreeButtonOneCommand();
    GetColumnThreeButtonTwoName();
    GetColumnThreeButtonTwoCommand();
    GetColumnThreeButtonThreeName();
    GetColumnThreeButtonThreeCommand();
    GetColumnThreeButtonFourName();
    GetColumnThreeButtonFourCommand();
    GetColumnThreeButtonFiveName();
    GetColumnThreeButtonFiveCommand();
    GetColumnThreeButtonSixName();
    GetColumnThreeButtonSixCommand();
    GetColumnThreeButtonSevenName();
    GetColumnThreeButtonSevenCommand();
    config.save(configfile);
  }

  public static String GetColumnTwoName()
  {
    return config.getString("column2.labelName", "Column Two");
  }

  public static String GetColumnTwoButtonOneName() {
    return config.getString("column2.button1.name", "Online Players");
  }

  public static String GetColumnTwoButtonOneCommand() {
    return config.getString("column2.button1.command", "list");
  }

  public static String GetColumnTwoButtonTwoName() {
    return config.getString("column2.button2.name", "Goto Spawn");
  }

  public static String GetColumnTwoButtonTwoCommand() {
    return config.getString("column2.button2.command", "spawn");
  }

  public static String GetColumnTwoButtonThreeName() {
    return config.getString("column2.button3.name", "Town Map");
  }

  public static String GetColumnTwoButtonThreeCommand() {
    return config.getString("column2.button3.command", "towny map");
  }

  public static String GetColumnTwoButtonFourName() {
    return config.getString("column2.button4.name", "Town Big Map");
  }

  public static String GetColumnTwoButtonFourCommand() {
    return config.getString("column2.button4.command", "towny map big");
  }

  public static String GetColumnTwoButtonFiveName() {
    return config.getString("column2.button5.name", "Town Spawn");
  }

  public static String GetColumnTwoButtonFiveCommand() {
    return config.getString("column2.button5.command", "town spawn");
  }

  public static String GetColumnTwoButtonSixName() {
    return config.getString("column2.button6.name", "Resident Info");
  }

  public static String GetColumnTwoButtonSixCommand() {
    return config.getString("column2.button6.command", "resident");
  }

  public static String GetColumnTwoButtonSevenName() {
    return config.getString("column2.button7.name", "Town Info");
  }

  public static String GetColumnTwoButtonSevenCommand() {
    return config.getString("column2.button7.command", "town");
  }

  public static String GetColumnOneName()
  {
    return config.getString("column1.labelName", "Column One");
  }

  public static String GetColumnOneButtonOneName() {
    return config.getString("column1.button1.name", "Online Players");
  }

  public static String GetColumnOneButtonOneCommand() {
    return config.getString("column1.button1.command", "list");
  }

  public static String GetColumnOneButtonTwoName() {
    return config.getString("column1.button2.name", "Goto Spawn");
  }

  public static String GetColumnOneButtonTwoCommand() {
    return config.getString("column1.button2.command", "spawn");
  }

  public static String GetColumnOneButtonThreeName() {
    return config.getString("column1.button3.name", "Town Map");
  }

  public static String GetColumnOneButtonThreeCommand() {
    return config.getString("column1.button3.command", "towny map");
  }

  public static String GetColumnOneButtonFourName() {
    return config.getString("column1.button4.name", "Town Big Map");
  }

  public static String GetColumnOneButtonFourCommand() {
    return config.getString("column1.button4.command", "towny map big");
  }

  public static String GetColumnOneButtonFiveName() {
    return config.getString("column1.button5.name", "Town Spawn");
  }

  public static String GetColumnOneButtonFiveCommand() {
    return config.getString("column1.button5.command", "town spawn");
  }

  public static String GetColumnOneButtonSixName() {
    return config.getString("column1.button6.name", "Resident Info");
  }

  public static String GetColumnOneButtonSixCommand() {
    return config.getString("column1.button6.command", "resident");
  }

  public static String GetColumnOneButtonSevenName() {
    return config.getString("column1.button7.name", "Town Info");
  }

  public static String GetColumnOneButtonSevenCommand() {
    return config.getString("column1.button7.command", "town");
  }

  public static String GetColumnThreeName()
  {
    return config.getString("column3.labelName", "Column Three");
  }

  public static String GetColumnThreeButtonOneName() {
    return config.getString("column3.button1.name", "Online Players");
  }

  public static String GetColumnThreeButtonOneCommand() {
    return config.getString("column3.button1.command", "list");
  }

  public static String GetColumnThreeButtonTwoName() {
    return config.getString("column3.button2.name", "Goto Spawn");
  }

  public static String GetColumnThreeButtonTwoCommand() {
    return config.getString("column3.button2.command", "spawn");
  }

  public static String GetColumnThreeButtonThreeName() {
    return config.getString("column3.button3.name", "Town Map");
  }

  public static String GetColumnThreeButtonThreeCommand() {
    return config.getString("column3.button3.command", "towny map");
  }

  public static String GetColumnThreeButtonFourName() {
    return config.getString("column3.button4.name", "Town Big Map");
  }

  public static String GetColumnThreeButtonFourCommand() {
    return config.getString("column3.button4.command", "towny map big");
  }

  public static String GetColumnThreeButtonFiveName() {
    return config.getString("column3.button5.name", "Town Spawn");
  }

  public static String GetColumnThreeButtonFiveCommand() {
    return config.getString("column3.button5.command", "town spawn");
  }

  public static String GetColumnThreeButtonSixName() {
    return config.getString("column3.button6.name", "Resident Info");
  }

  public static String GetColumnThreeButtonSixCommand() {
    return config.getString("column3.button6.command", "resident");
  }

  public static String GetColumnThreeButtonSevenName() {
    return config.getString("column3.button7.name", "Town Info");
  }

  public static String GetColumnThreeButtonSevenCommand() {
    return config.getString("column3.button7.command", "town");
  }
}