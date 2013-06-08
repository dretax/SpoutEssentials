package me.skawke.spoutessentials.APIcommand;

import java.util.HashMap;
import me.skawke.spoutessentials.SpoutEssentials;
import me.skawke.spoutessentials.Utils;
import me.skawke.spoutessentials.config.SpoutEssentialsGUI;
import me.skawke.spoutessentials.config.SpoutEssentialsModuleConfig;
import me.skawke.spoutessentials.listeners.SpoutEssentialsSpoutListener;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.event.input.KeyBindingEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.keyboard.BindingExecutionDelegate;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SpoutEssentialsKListener
  implements BindingExecutionDelegate
{
  public static HashMap<SpoutPlayer, GenericTextField> messageUUID = new HashMap<SpoutPlayer, GenericTextField>();
  public static HashMap<SpoutPlayer, GenericTextField> playerUUID = new HashMap<SpoutPlayer, GenericTextField>();
  public static HashMap<SpoutPlayer, GenericTextField> iConomyUserUUID = new HashMap<SpoutPlayer, GenericTextField>();
  public static HashMap<SpoutPlayer, GenericTextField> iConomyBalanceUUID = new HashMap<SpoutPlayer, GenericTextField>();
  public static HashMap<SpoutPlayer, Boolean> havePopup = new HashMap<SpoutPlayer, Boolean>();
  private SpoutEssentials plugin;

  public SpoutEssentialsKListener(SpoutEssentials plugin)
  {
    this.plugin = plugin;
  }

  public void keyPressed(KeyBindingEvent event)
  {
    Player player = event.getPlayer();

    SpoutPlayer sPlayer = (SpoutPlayer)player;
    if (SpoutEssentialsModuleConfig.EnableSplashScreen()) {
      Boolean hasit = (Boolean)SpoutEssentialsSpoutListener.hasLogo.get(sPlayer);
      if ((hasit != null) && (hasit.booleanValue())) {
        sPlayer.getMainScreen().closePopup();
        SpoutEssentialsSpoutListener.hasLogo.remove(sPlayer);
      }
    }

    if ((havePopup.get(sPlayer) != null) && 
      (((Boolean)havePopup.get(sPlayer)).booleanValue())) {
      havePopup.remove(sPlayer);
      sPlayer.getMainScreen().closePopup();
      return;
    }

    GenericPopup popup = new GenericPopup();

    if ((SpoutEssentialsGUI.GetColumnOneName() != null) && (Utils.checkPermissions(player, "spoutessentials.guiColumnOne"))) {
      GenericContainer box = new GenericContainer();
      GenericLabel ColumnLabel = new GenericLabel(SpoutEssentialsGUI.GetColumnOneName());
      GenericButton buttonOne = new GenericButton(SpoutEssentialsGUI.GetColumnOneButtonOneName());
      GenericButton buttonTwo = new GenericButton(SpoutEssentialsGUI.GetColumnOneButtonTwoName());
      GenericButton buttonThree = new GenericButton(SpoutEssentialsGUI.GetColumnOneButtonThreeName());
      GenericButton buttonFour = new GenericButton(SpoutEssentialsGUI.GetColumnOneButtonFourName());
      GenericButton buttonFive = new GenericButton(SpoutEssentialsGUI.GetColumnOneButtonFiveName());
      GenericButton buttonSix = new GenericButton(SpoutEssentialsGUI.GetColumnOneButtonSixName());
      GenericButton buttonSeven = new GenericButton(SpoutEssentialsGUI.GetColumnOneButtonSevenName());
      ColumnLabel.setWidth(100).setHeight(20);

      buttonOne.setTooltip(SpoutEssentialsGUI.GetColumnOneButtonOneCommand());
      buttonTwo.setTooltip(SpoutEssentialsGUI.GetColumnOneButtonTwoCommand());
      buttonThree.setTooltip(SpoutEssentialsGUI.GetColumnOneButtonThreeCommand());
      buttonFour.setTooltip(SpoutEssentialsGUI.GetColumnOneButtonFourCommand());
      buttonFive.setTooltip(SpoutEssentialsGUI.GetColumnOneButtonFiveCommand());
      buttonSix.setTooltip(SpoutEssentialsGUI.GetColumnOneButtonSixCommand());
      buttonSeven.setTooltip(SpoutEssentialsGUI.GetColumnOneButtonSevenCommand());

      buttonOne.setWidth(100).setHeight(10);
      buttonTwo.setWidth(100).setHeight(20);
      buttonThree.setWidth(100).setHeight(20);
      buttonFour.setWidth(100).setHeight(20);
      buttonFive.setWidth(100).setHeight(20);
      buttonSix.setWidth(100).setHeight(20);
      buttonSeven.setWidth(100).setHeight(20);
      box.addChildren(new Widget[] { ColumnLabel, buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven });
      box.setWidth(100);
      box.setHeight(150);
      box.setY(3);
      popup.attachWidget(this.plugin, box);
    }

    if ((SpoutEssentialsGUI.GetColumnTwoName() != null) && (Utils.checkPermissions(player, "spoutessentials.guiColumnTwo"))) {
      GenericContainer box = new GenericContainer();
      GenericLabel ColumnLabel = new GenericLabel(SpoutEssentialsGUI.GetColumnTwoName());
      GenericButton buttonOne = new GenericButton(SpoutEssentialsGUI.GetColumnTwoButtonOneName());
      GenericButton buttonTwo = new GenericButton(SpoutEssentialsGUI.GetColumnTwoButtonTwoName());
      GenericButton buttonThree = new GenericButton(SpoutEssentialsGUI.GetColumnTwoButtonThreeName());
      GenericButton buttonFour = new GenericButton(SpoutEssentialsGUI.GetColumnTwoButtonFourName());
      GenericButton buttonFive = new GenericButton(SpoutEssentialsGUI.GetColumnTwoButtonFiveName());
      GenericButton buttonSix = new GenericButton(SpoutEssentialsGUI.GetColumnTwoButtonSixName());
      GenericButton buttonSeven = new GenericButton(SpoutEssentialsGUI.GetColumnTwoButtonSevenName());
      ColumnLabel.setWidth(100).setHeight(20);

      buttonOne.setTooltip(SpoutEssentialsGUI.GetColumnTwoButtonOneCommand());
      buttonTwo.setTooltip(SpoutEssentialsGUI.GetColumnTwoButtonTwoCommand());
      buttonThree.setTooltip(SpoutEssentialsGUI.GetColumnTwoButtonThreeCommand());
      buttonFour.setTooltip(SpoutEssentialsGUI.GetColumnTwoButtonFourCommand());
      buttonFive.setTooltip(SpoutEssentialsGUI.GetColumnTwoButtonFiveCommand());
      buttonSix.setTooltip(SpoutEssentialsGUI.GetColumnTwoButtonSixCommand());
      buttonSeven.setTooltip(SpoutEssentialsGUI.GetColumnTwoButtonSevenCommand());

      buttonOne.setWidth(100).setHeight(10);
      buttonTwo.setWidth(100).setHeight(20);
      buttonThree.setWidth(100).setHeight(20);
      buttonFour.setWidth(100).setHeight(20);
      buttonFive.setWidth(100).setHeight(20);
      buttonSix.setWidth(100).setHeight(20);
      buttonSeven.setWidth(100).setHeight(20);
      box.addChildren(new Widget[] { ColumnLabel, buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven });
      box.setWidth(100);
      box.setHeight(150);
      box.setX(101);
      box.setY(3);
      popup.attachWidget(this.plugin, box);
    }
    if ((SpoutEssentialsGUI.GetColumnThreeName() != null) && (Utils.checkPermissions(player, "spoutessentials.guiColumnThree"))) {
      GenericContainer box = new GenericContainer();
      GenericLabel ColumnLabel = new GenericLabel(SpoutEssentialsGUI.GetColumnThreeName());
      GenericButton buttonOne = new GenericButton(SpoutEssentialsGUI.GetColumnThreeButtonOneName());
      GenericButton buttonTwo = new GenericButton(SpoutEssentialsGUI.GetColumnThreeButtonTwoName());
      GenericButton buttonThree = new GenericButton(SpoutEssentialsGUI.GetColumnThreeButtonThreeName());
      GenericButton buttonFour = new GenericButton(SpoutEssentialsGUI.GetColumnThreeButtonFourName());
      GenericButton buttonFive = new GenericButton(SpoutEssentialsGUI.GetColumnThreeButtonFiveName());
      GenericButton buttonSix = new GenericButton(SpoutEssentialsGUI.GetColumnThreeButtonSixName());
      GenericButton buttonSeven = new GenericButton(SpoutEssentialsGUI.GetColumnThreeButtonSevenName());
      ColumnLabel.setWidth(100).setHeight(20);

      buttonOne.setTooltip(SpoutEssentialsGUI.GetColumnThreeButtonOneCommand());
      buttonTwo.setTooltip(SpoutEssentialsGUI.GetColumnThreeButtonTwoCommand());
      buttonThree.setTooltip(SpoutEssentialsGUI.GetColumnThreeButtonThreeCommand());
      buttonFour.setTooltip(SpoutEssentialsGUI.GetColumnThreeButtonFourCommand());
      buttonFive.setTooltip(SpoutEssentialsGUI.GetColumnThreeButtonFiveCommand());
      buttonSix.setTooltip(SpoutEssentialsGUI.GetColumnThreeButtonSixCommand());
      buttonSeven.setTooltip(SpoutEssentialsGUI.GetColumnThreeButtonSevenCommand());

      buttonOne.setWidth(100).setHeight(10);
      buttonTwo.setWidth(100).setHeight(20);
      buttonThree.setWidth(100).setHeight(20);
      buttonFour.setWidth(100).setHeight(20);
      buttonFive.setWidth(100).setHeight(20);
      buttonSix.setWidth(100).setHeight(20);
      buttonSeven.setWidth(100).setHeight(20);
      box.addChildren(new Widget[] { ColumnLabel, buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven });
      box.setWidth(100);
      box.setHeight(150);
      box.setX(202);
      box.setY(3);
      popup.attachWidget(this.plugin, box);
    }

    sPlayer.getMainScreen().attachPopupScreen(popup);
    havePopup.put(sPlayer, Boolean.valueOf(true));
  }

  public void keyReleased(KeyBindingEvent event)
  {
  }
}