package me.skawke.spoutessentials.commands;

import me.skawke.spoutessentials.SpoutEssentials;
import me.skawke.spoutessentials.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.RenderDistance;
import org.getspout.spoutapi.player.SkyManager;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SpoutEssentialsSkyCommands
  implements CommandExecutor
{
  public static SpoutEssentials plugin;
  public static Server server;

  public SpoutEssentialsSkyCommands(SpoutEssentials instance)
  {
    plugin = instance;
  }

  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage(ChatColor.RED + "Only a player may use these commands.");
      return false;
    }
    if (cmd.getName().equalsIgnoreCase("sky"))
    {
      Player player = (Player)sender;
      SpoutPlayer sOnCommandPlayer = (SpoutPlayer)player;
      if (args.length < 1) {
        return false;
      }

      if (args[0].equalsIgnoreCase("cloud")) {
        if (args.length == 1) {
          SpoutManager.getSkyManager().setCloudHeight(sOnCommandPlayer, 128);
          player.sendMessage(ChatColor.GREEN + "Successfully reset your cloud height.");
        } else if (args.length == 2) {
          if (Utils.checkPermissions(player, "spoutessentials.cloud")) {
            int i = Integer.parseInt(args[1]);
            SpoutManager.getSkyManager().setCloudHeight(sOnCommandPlayer, i);
            player.sendMessage("Cloud height changed");
          } else {
            player.sendMessage("No permissions, sorry!");
          }

        }

      }

      if (args[0].equalsIgnoreCase("sunsize")) {
        if (args.length == 1) {
          player.sendMessage("Include a size, 0-100");
          return false;
        }

        if (Utils.checkPermissions(player, "spoutessentials.sunsize")) {
          int i = Integer.parseInt(args[1]);
          SpoutManager.getSkyManager().setSunSizePercent(sOnCommandPlayer, i);
          sOnCommandPlayer.sendMessage("Sun Size changed");
        } else {
          player.sendMessage("No permissions, sorry!");
        }
      }
      Player[] arrayOfPlayer = null;

      if (args[0].equalsIgnoreCase("sunsizeadmin"))
      {
        if (Utils.checkPermissions(player, "spoutessentials.sunadmin")) {
          if (args.length == 2) {
            int i = Integer.parseInt(args[1]);
            for (Player sunsizea : plugin.getServer().getOnlinePlayers()) {
              SpoutPlayer sSunSizeOnlinePlayer1 = (SpoutPlayer)sunsizea;

              SpoutManager.getSkyManager().setSunSizePercent(sSunSizeOnlinePlayer1, i);
              player.sendMessage("Sun Size set for everyone");
            }
          }
          else if (args.length == 3) {
            Player oPlayer = Bukkit.getPlayer(args[2]);
            int i = Integer.parseInt(args[1]);
            SpoutPlayer sSunSizeOnlinePlayer = (SpoutPlayer)oPlayer;
            SpoutManager.getSkyManager().setSunSizePercent(sSunSizeOnlinePlayer, i);
            player.sendMessage(ChatColor.GREEN + "Cloud height successfully changed.");
          } else {
            player.sendMessage(ChatColor.RED + "Invalid number of arguments.");
            return false;
          }
        }
        else player.sendMessage("No permissions, sorry!");

      }

      if (args[0].equalsIgnoreCase("moonsize"))
      {
        if (args.length == 1) {
          player.sendMessage("Include a size.");
        }
        else if (Utils.checkPermissions(player, "spoutessentials.moonsize")) {
          int i = Integer.parseInt(args[1]);
          SpoutManager.getSkyManager().setMoonSizePercent(sOnCommandPlayer, i);
          sOnCommandPlayer.sendMessage("Moon changed");
        } else {
          player.sendMessage("No permissions, sorry!");
        }

      }

      if (args[0].equalsIgnoreCase("moonadmin"))
      {
        if (Utils.checkPermissions(player, "spoutessentials.moonadmin")) {
          int i = Integer.parseInt(args[1]);
          for (Player moona : plugin.getServer().getOnlinePlayers()) {
            SpoutPlayer sMoonOnlinePlayer = (SpoutPlayer)moona;
            SpoutManager.getSkyManager().setMoonSizePercent(sMoonOnlinePlayer, i);
            player.sendMessage("Moon size changed for everyone");
          }
        } else {
          player.sendMessage("No permissions, sorry!");
        }
      }

      if (args[0].equalsIgnoreCase("cloudadmin"))
      {
        if (Utils.checkPermissions(player, "spoutessentials.cloudadmin")) {
          int i = Integer.parseInt(args[1]);
          for (Player clouda : plugin.getServer().getOnlinePlayers()) {
            SpoutPlayer sCloudOnlinePlayer = (SpoutPlayer)clouda;
            SpoutManager.getSkyManager().setCloudHeight(sCloudOnlinePlayer, i);
            player.sendMessage("Cloud height changed for everyone");
          }
        } else {
          player.sendMessage("No permissions, sorry!");
        }

      }

      if (args[0].equalsIgnoreCase("stars"))
      {
        if (args.length == 1) {
          player.sendMessage("Include a number.");
        }
        else if (Utils.checkPermissions(player, "spoutessentials.stars")) {
          int i = Integer.parseInt(args[1]);
          SpoutManager.getSkyManager().setStarFrequency(sOnCommandPlayer, i);
          player.sendMessage("Stars freq changed");
        } else {
          player.sendMessage("No permissions, sorry!");
        }
      }

      if (args[0].equalsIgnoreCase("starsadmin"))
      {
        if (Utils.checkPermissions(player, "spoutessentials.starsadmin")) {
          int i = Integer.parseInt(args[1]);
          Player[] localObject2 = plugin.getServer().getOnlinePlayers();
          for (int localObject1 = 0; localObject1 < localObject2.length; localObject1++) {
            Player starsa = arrayOfPlayer[localObject1];
            SpoutPlayer sstarsOnlinePlayer = (SpoutPlayer)starsa;
            SpoutManager.getSkyManager().setStarFrequency(sstarsOnlinePlayer, i);
            player.sendMessage("Star freq changed for everyone");
          }
        } else {
          player.sendMessage("No permissions, sorry!");
        }

      }

      if (args[0].equalsIgnoreCase("fogadmin"))
      {
        int leng = Bukkit.getServer().getOnlinePlayers().length;
        if ((Utils.checkPermissions(player, "spoutessentials.fogadmin")) && 
          (args[1].equalsIgnoreCase("far"))) {
          Player[] localObject2 = plugin.getServer().getOnlinePlayers();
          int localPlayer1 = localObject2.length;
          for (int starsa1 = 0; starsa1 < localPlayer1; starsa1++) {
            Player fogPlayer = localObject2[starsa1];
            SpoutPlayer sfogOnlinePlayer = (SpoutPlayer)fogPlayer;
            sfogOnlinePlayer.setRenderDistance(RenderDistance.FAR);
            player.sendMessage("Fog distance changed for everyone");
          }

        }

        if (args[1].equalsIgnoreCase("normal")) {
          Player[] localObject2 = plugin.getServer().getOnlinePlayers();
          int localPlayer2 = localObject2.length;
          for (int starsa2 = 0; starsa2 < localPlayer2; starsa2++) {
            Player fogPlayer = localObject2[starsa2];
            SpoutPlayer sfogOnlinePlayer = (SpoutPlayer)fogPlayer;
            sfogOnlinePlayer.setRenderDistance(RenderDistance.NORMAL);
            player.sendMessage("Fog distance changed for everyone");
          }
        }
      }

      if (args[1].equalsIgnoreCase("short")) {
        Player[] localObject2 = plugin.getServer().getOnlinePlayers();
        int localPlayer3 = localObject2.length;
        for (int starsa3 = 0; starsa3 < localPlayer3; starsa3++) {
          Player fogPlayer = localObject2[starsa3];
          SpoutPlayer sfogOnlinePlayer = (SpoutPlayer)fogPlayer;
          sfogOnlinePlayer.setRenderDistance(RenderDistance.SHORT);
          player.sendMessage("Fog distance changed for everyone");
        }

      }

      if (args[1].equalsIgnoreCase("tiny")) {
        Player[] localObject2 = plugin.getServer().getOnlinePlayers();
        int localPlayer4 = localObject2.length;
        for (int starsa4 = 0; starsa4 < localPlayer4; starsa4++) {
          Player fogPlayer = localObject2[starsa4];
          SpoutPlayer sfogOnlinePlayer = (SpoutPlayer)fogPlayer;
          sfogOnlinePlayer.setRenderDistance(RenderDistance.TINY);
          player.sendMessage("Fog distance changed for everyone");
        }

      }

    }

    //Player player = Bukkit.getPlayer(commandLabel);
    Player player = (Player)sender;
    if ((args[0].equalsIgnoreCase("suntextureadmin")) && 
      (Utils.checkPermissions(player, "spoutessentials.suntextureadmin"))) {
      if (args[1] == null) {
        player.sendMessage("Provide a valid sun/moon texture URL");
        return false;
      }
      Player[] localObject2 = plugin.getServer().getOnlinePlayers();
      int localPlayer5 = localObject2.length;
      for (int starsa5 = 0; starsa5 < localPlayer5; starsa5++) {
        Player sunPlayer = localObject2[starsa5];
        SpoutPlayer ssunOnlinePlayer = (SpoutPlayer)sunPlayer;
        SpoutManager.getSkyManager().setSunTextureUrl(ssunOnlinePlayer, args[1]);
      }

      player.sendMessage("Sun texture changed for everyone");
    }

    if ((args[0].equalsIgnoreCase("moontextureadmin")) && 
      (Utils.checkPermissions(player, "spoutessentials.moontextureadmin"))) {
      if (args[1] == null) {
        player.sendMessage("Provide a valid sun/moon texture URL");
        return false;
      }
      Player[] localObject2 = plugin.getServer().getOnlinePlayers();
      int localPlayer6 = localObject2.length;
      for (int starsa6 = 0; starsa6 < localPlayer6; starsa6++) {
        Player moonPlayer = localObject2[starsa6];
        SpoutPlayer smoonOnlinePlayer = (SpoutPlayer)moonPlayer;
        SpoutManager.getSkyManager().setMoonTextureUrl(smoonOnlinePlayer, args[1]);
      }

      player.sendMessage("Moon texture changed for everyone");
    }

    return false;
  }
}