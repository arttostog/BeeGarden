package me.arttostog.beegarden.event;

import me.arttostog.beegarden.BeeGarden;
import me.arttostog.beegarden.gui.Gui;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Event implements Listener {
	@EventHandler
	public void OnPlayerClick(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getItem() == null && event.getClickedBlock() != null) {
			Block block = event.getClickedBlock();
			if (block.getType() == Material.BEEHIVE || block.getType() == Material.BEE_NEST) {
				Gui.OpenGui(event.getPlayer(), block);
			}
		}
	}

	@EventHandler
	public void OnClickEvent(InventoryClickEvent event) {
		String title = event.getView().getTitle();
		if (event.getClickedInventory() != null && title.contains(BeeGarden.config.getString("Localization.Bees") + "") && title.contains(BeeGarden.config.getString("Localization.Honey") + "")) {
			event.setCancelled(true);
		}
	}
}
