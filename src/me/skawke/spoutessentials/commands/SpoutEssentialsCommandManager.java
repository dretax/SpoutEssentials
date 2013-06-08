package me.skawke.spoutessentials.commands;

import java.util.HashMap;
import java.util.UUID;
import me.skawke.spoutessentials.SpoutEssentials;
import me.skawke.spoutessentials.Utils;
import me.skawke.spoutessentials.config.SpoutEssentialsConfig;
import me.skawke.spoutessentials.config.SpoutEssentialsGUI;
import me.skawke.spoutessentials.config.SpoutEssentialsModuleConfig;
import me.skawke.spoutessentials.config.SpoutEssentialsMusicList;
import me.skawke.spoutessentials.config.SpoutEssentialsPlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.RenderDistance;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SpoutEssentialsCommandManager
  implements CommandExecutor
{
  public static SpoutEssentials plugin;
  public static Server server;
  public static HashMap<Object, Object> labels;
  public static HashMap<Object, Object> HUDEnable;
  public static RenderDistance FAR;
  boolean showtext = true;

  public SpoutEssentialsCommandManager(SpoutEssentials instance) { plugin = instance; }


  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    SpoutEssentials plugin = SpoutEssentials.getInstance();

    if (cmd.getName().equalsIgnoreCase("spe")) {
      Player player = (Player)sender;
      SpoutPlayer sOnCommandPlayer = (SpoutPlayer)player;
      if (args.length < 1) {
        return false;
      }
      if (args[0].equalsIgnoreCase("help")) {
        if ((sender instanceof Player)) {
          if (Utils.checkPermissions(player, "spoutessentials.guiHELP"))
          {
            Player helpPlayer = (Player)sender;
            if (HUDEnable.containsKey(player.getName())) {
              if (((Boolean)HUDEnable.get(helpPlayer.getName())).booleanValue()) {
                HUDEnable.put(helpPlayer.getName(), Boolean.valueOf(false));
                UUID labelId = (UUID)labels.get(helpPlayer.getName());

                SpoutPlayer sHelpPlayer = SpoutManager.getPlayer(helpPlayer);
                sHelpPlayer.getMainScreen().getWidget(labelId).setVisible(false).setDirty(true);

                sender.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + ChatColor.GREEN + "Help is hidden.");
              } else {
                HUDEnable.put(helpPlayer.getName(), Boolean.valueOf(true));
                UUID labelId = (UUID)labels.get(helpPlayer.getName());

                SpoutPlayer sHelpPlayer = SpoutManager.getPlayer(helpPlayer);
                sHelpPlayer.getMainScreen().getWidget(labelId).setVisible(true).setDirty(true);

                sender.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + ChatColor.GREEN + "Help is shown..");
              }
            }
            return true;
          }
          sender.sendMessage("You need permissions. Sorry!");
          return false;
        }

        sender.sendMessage("Only a player may use this command.");
      }

      if ((args[0].equalsIgnoreCase("stopmusic")) && (Utils.checkPermissions(player, "spoutessentials.stopmusic"))) {
        plugin.sm.stopMusic(sOnCommandPlayer);
        sender.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "Music stopped!");
      }

      if ((args[0].equalsIgnoreCase("music")) && (Utils.checkPermissions(player, "spoutessentials.music"))) {
        if (args.length == 1) {
          player.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "Provide a song!");
          return true;
        }
        if (SpoutEssentialsMusicList.config.getString("songs." + args[1]) != null) {
          SpoutManager.getSoundManager().playCustomMusic(plugin, sOnCommandPlayer, SpoutEssentialsMusicList.config.getString("songs." + args[1]), true);
          player.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "Music played!");
        } else {
          player.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "The song isn't defined in the config. Sorry!");
        }

      }

      if (args[0].equalsIgnoreCase("playmusic"))
      {
        if (Utils.checkPermissions(player, "spoutessentials.playmusic")) {
          if (args.length == 1) {
            sender.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "You need to provide a URL of some kind...(OGG or MIDI please)");
            return true;
          }

          sender.sendMessage(args[1]);
          plugin.sm.playCustomMusic(plugin, sOnCommandPlayer, args[1], true);
        }
      }

      if (args[0].equalsIgnoreCase("playgmusic"))
      {
        if (Utils.checkPermissions(player, "spoutessentials.playgmusic")) {
          if (args.length == 1) {
            sender.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "You need to provide a URL of some kind...(OGG or MIDI please)");
            return true;
          }
          if (args.length == 3) {
            Player toPlayer = plugin.getServer().getPlayer(args[3]);
            SpoutPlayer sToPlayer = (SpoutPlayer)toPlayer;
            SpoutManager.getSoundManager().playCustomMusic(plugin, sToPlayer, args[3], true);
            sender.sendMessage("Music played!");
            return true;
          }
          sender.sendMessage(args[1]);
          plugin.sm.playGlobalCustomMusic(plugin, args[1], true);
        }
      }
      if (args[0].equalsIgnoreCase("cape")) {
        if (args.length == 2) {
          if (Utils.checkPermissions(player, "spoutessentials.cape"))
          {
            if (args.length < 1) {
              player.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "Provide a URL~!");
              return false;
            }
            if (args[1].contains(".png"))
            {
              sOnCommandPlayer.setCape(args[1]);
              SpoutEssentialsPlayerConfig.setPlayerCape(sOnCommandPlayer, args[1]);
              player.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "Cape Added!");
            } else {
              player.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "It must be a .png!");
            }

            return true;
          }
          return false;
        }
        player.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "Type in a correct URL");
        return true;
      }

      if (args[0].equalsIgnoreCase("capeadmin")) {
        if (args.length == 3) {
          if (Utils.checkPermissions(player, "spoutessentials.capeadmin")) {
            String theURL = args[2];
            Player p = plugin.getServer().getPlayer(args[1]);
            SpoutPlayer sP = (SpoutPlayer)p;
            SpoutEssentialsPlayerConfig.setPlayerCape(sP, theURL);
            try
            {
              sP.setCape(args[2]);

              sender.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "Cape added for player! " + theURL);
            }
            catch (IllegalArgumentException ex) {
              sender.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "Invalid cape URL. Setting to nothing.");
            }
          } else {
            sender.sendMessage("Not enough permissions");
            return false;
          }
          return false;
        }

        player.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "Wrong number of arguments!");
      }

      if ((args[0].equalsIgnoreCase("title")) && (Utils.checkPermissions(player, "spoutessentials.title")))
      {
        if (args[1] == null)
          return false;
        if (args[1].equalsIgnoreCase("none"))
        {
          sOnCommandPlayer.setTitle(sOnCommandPlayer.getName());
          SpoutEssentialsPlayerConfig.setPlayerTitle(sOnCommandPlayer, sOnCommandPlayer.getName());

          sender.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "Title reset!");
        } else if (args[1].equalsIgnoreCase("hidden"))
        {
          sOnCommandPlayer.hideTitle();
          sender.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "Title hidden");
        }
        else
        {
          sOnCommandPlayer.setTitle(args[1]);
          SpoutEssentialsPlayerConfig.setPlayerTitle(sOnCommandPlayer, args[1]);
          sender.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "Title set!");
        }

      }

      if (args[0].equalsIgnoreCase("titleadmin")) {
        if (args[1] == null)
          return false;
        if (args[2] == null) {
          return false;
        }
        if (Utils.checkPermissions(player, "spoutessentials.titleadmin")) {
          if (args[1].equalsIgnoreCase("none")) {
            SpoutPlayer sPlayer = (SpoutPlayer)plugin.getServer().getPlayer(args[1]);

            sPlayer.setTitle(sPlayer.getName());
            SpoutEssentialsPlayerConfig.setPlayerTitle(sPlayer, sPlayer.getName());
            sender.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "Title reset!");
          }
          else {
            SpoutPlayer sPlayer = (SpoutPlayer)plugin.getServer().getPlayer(args[1]);

            sPlayer.setTitle(args[2]);
            SpoutEssentialsPlayerConfig.setPlayerTitle(sPlayer, args[2]);
            sender.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "Title set!");
          }
        }
      }

      if (args[0].equalsIgnoreCase("textures")) {
        sOnCommandPlayer.setTexturePack(SpoutEssentialsConfig.config.getString("texturepack." + player.getWorld().getName()));
        player.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "Textures changed.");
      }
      if (args[0].equalsIgnoreCase("reload"))
      {
        if (Utils.checkPermissions(player, "spoutessentials.reload"))
        {
          try {
            SpoutEssentialsConfig.LoadConfig();
          }
          catch (Exception e) {
            e.printStackTrace();
          }
          try {
            SpoutEssentialsPlayerConfig.LoadConfig();
          }
          catch (Exception e) {
            e.printStackTrace();
          }
          try {
            SpoutEssentialsModuleConfig.LoadConfig();
          }
          catch (Exception e) {
            e.printStackTrace();
          }
          try {
            SpoutEssentialsMusicList.LoadConfig();
          }
          catch (Exception e) {
            e.printStackTrace();
          }
          try {
            SpoutEssentialsGUI.LoadConfig();
          }
          catch (Exception e) {
            e.printStackTrace();
          }
          player.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + "SpE" + ChatColor.RED + "]" + "SpoutEssentials reloaded");
        }

      }

      return true;
    }

    return true;
  }
}