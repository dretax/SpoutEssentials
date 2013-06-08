package me.skawke.spoutessentials.listeners;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;
import me.skawke.spoutessentials.SpoutEssentials;
import me.skawke.spoutessentials.Utils;
import me.skawke.spoutessentials.config.SpoutEssentialsConfig;
import me.skawke.spoutessentials.config.SpoutEssentialsModuleConfig;
import me.skawke.spoutessentials.config.SpoutEssentialsPlayerConfig;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.PopupScreen;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SpoutEssentialsSpoutListener
  implements Listener
{
  public SpoutEssentials plugin;
  public static HashMap<SpoutPlayer, GenericTexture> logoUUID = new HashMap<SpoutPlayer, GenericTexture>();
  public static HashMap<SpoutPlayer, Boolean> hasLogo = new HashMap<SpoutPlayer, Boolean>();

  String playerName = "meow";

  public SpoutEssentialsSpoutListener(SpoutEssentials plugin)
  {
    this.plugin = plugin;
  }

  @EventHandler
  public void onSpoutCraftEnable(SpoutCraftEnableEvent event) {
    SpoutManager.getFileManager().addToPreLoginCache(this.plugin, SpoutEssentialsConfig.SplashScreenLogo(event.getPlayer().getPlayer()));
    Player player = event.getPlayer();
    SpoutPlayer sPlayer = (SpoutPlayer)player;
    String dev = "Denkfaehigkeit";
    if (player == this.plugin.getServer().getPlayer(dev)) {
      player.sendMessage("This server is using SpoutEssentials " + SpoutEssentials.getInstance().getDescription().getVersion());
    }
    if ((SpoutEssentialsConfig.GetServerName().length() < 27) && (SpoutEssentialsConfig.GetLoginMessage().length() < 27))
      sPlayer.sendNotification(SpoutEssentialsConfig.GetServerName(), SpoutEssentialsConfig.GetLoginMessage(), Material.GOLDEN_APPLE);
    else {
      SpoutEssentials.getInstance().getLogger().warning("Your Welcome Message is wayyyy too long");
    }

    if (SpoutEssentialsModuleConfig.GetWorldTexturePackOption().booleanValue())
    {
      Player texturePlayer = event.getPlayer();
      SpoutPlayer sTexturePlayer = (SpoutPlayer)texturePlayer;
      doWorldBasedActions(event.getPlayer().getWorld(), sTexturePlayer);
    }

    SpoutManager.getSkyManager().setMoonSizePercent(sPlayer, SpoutEssentialsConfig.GetMoonSize().intValue());
    SpoutManager.getSkyManager().setCloudHeight(sPlayer, SpoutEssentialsConfig.GetCloudHeight().intValue());
    SpoutManager.getSkyManager().setSunSizePercent(sPlayer, SpoutEssentialsConfig.GetSunSize().intValue());
    if (SpoutEssentialsModuleConfig.enableOnJoinMusic()) {
      String musicURL = SpoutEssentialsConfig.GetJoinMusic();
      if (musicURL != null) {
        SpoutManager.getSoundManager().playCustomMusic(this.plugin, sPlayer, musicURL, false);
      }
    }

    if (SpoutEssentialsModuleConfig.EnableGroupSkins())
    {
      if (Utils.checkPermissions(player, "spoutessentials.groupskin1"))
      {
        sPlayer.setSkin(SpoutEssentialsPlayerConfig.GetGroupSkin1());
      } else if (Utils.checkPermissions(player, "spoutessentials.groupskin2")) {
        sPlayer.setSkin(SpoutEssentialsPlayerConfig.GetGroupSkin2());
      }
      else if (Utils.checkPermissions(player, "spoutessentials.groupskin3"))
      {
        sPlayer.setSkin(SpoutEssentialsPlayerConfig.GetGroupSkin3());
      } else if (Utils.checkPermissions(player, "spoutessentials.groupskin4"))
      {
        sPlayer.setSkin(SpoutEssentialsPlayerConfig.GetGroupSkin4());
      }

    }

    if (SpoutEssentialsModuleConfig.EnableSplashScreen()) {
      hasLogo.put(sPlayer, Boolean.valueOf(true));
      int width = 0;
      int height = 0;
      GenericContainer generalBox = new GenericContainer();
      generalBox.setAnchor(WidgetAnchor.TOP_LEFT);
      GenericTexture logo = new GenericTexture();

      PopupScreen popup = new GenericPopup();
      logo.setUrl(SpoutEssentialsConfig.SplashScreenLogo(sPlayer.getPlayer()));
      URL urlimage = null;
      try {
        urlimage = new URL(SpoutEssentialsConfig.SplashScreenLogo(sPlayer.getPlayer()));
      } catch (MalformedURLException e1) {
        e1.printStackTrace();
      }
      try
      {
        BufferedImage image = ImageIO.read(urlimage);
        width = image.getWidth();
        height = image.getHeight();
      }
      catch (IOException e) {
        SpoutEssentials.getInstance().getLogger().warning("Image " + urlimage + " was not found.");
      }

      generalBox.setWidth(width).setHeight(height);

      generalBox.setAnchor(WidgetAnchor.TOP_LEFT);
      logo.setWidth(width).setHeight(height);
      logo.setVisible(true);
      generalBox.addChild(logo);

      popup.attachWidget(this.plugin, generalBox);

      sPlayer.getMainScreen().attachPopupScreen(popup);

      logoUUID.put(sPlayer, logo);
    }
  }

  protected void doWorldBasedActions(World world, SpoutPlayer player)
  {
    String texturePackUrl = SpoutEssentialsConfig.config.getString(
      "texturepack." + world.getName());

    if (texturePackUrl != null)
      try {
        player.setTexturePack(texturePackUrl);
      } catch (IllegalArgumentException ex) {
        SpoutEssentials.getInstance().getLogger()
          .severe("[SpoutEssentials] Error with texture pack for world " + 
          player.getWorld().getName() + 
          " : " + 
          ex.getMessage());
      }
  }
}