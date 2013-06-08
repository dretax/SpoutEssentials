package me.skawke.spoutessentials.listeners;

import me.skawke.spoutessentials.SpoutEssentials;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.Button;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SpoutEssentialsScreenListener implements Listener {
	public SpoutEssentialsScreenListener(SpoutEssentials plugin) {
	}

	@EventHandler
	public void onButtonClick(ButtonClickEvent event)
	{
	   if (!(event.getButton().getPlugin() instanceof SpoutEssentials)) return;

	   Button button = event.getButton();
	   SpoutPlayer sPlayer = event.getPlayer();
	   String theCommand = button.getTooltip();
	   if (theCommand != null) sPlayer.performCommand(theCommand);
	}
	
}