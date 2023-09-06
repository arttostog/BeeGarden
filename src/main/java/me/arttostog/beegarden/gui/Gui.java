package me.arttostog.beegarden.gui;

import me.arttostog.beegarden.BeeGarden;
import org.bukkit.Bukkit;
import org.bukkit.block.Beehive;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class Gui {
	public static final InventoryType inventoryType = InventoryType.DISPENSER;

	public static void OpenGui(Player player, Block block) {
		Beehive beehive = (Beehive) block.getState();
		int BeesCount = beehive.getEntityCount();
		int HoneyLvl = Integer.parseInt(block.getBlockData().getAsString().replaceAll("[^-?0-9]+", ""));

		StringBuilder title = new StringBuilder("§e§l" + BeeGarden.config.getString("Localization.Bees") + ": " + BeesCount + "/" + beehive.getMaxEntities());
		for (int i = 0; i < BeeGarden.config.getInt("Indent"); i++) {
			title.append(" ");
		}
		title.append("§r§6§l").append(BeeGarden.config.getString("Localization.Honey")).append(": ").append(HoneyLvl).append("/5");

		Inventory gui = Bukkit.createInventory(player, inventoryType, title.toString());

		gui.setMaxStackSize(1);
		gui.setContents(Items.GetItems(BeesCount, HoneyLvl));

		player.openInventory(gui);
	}
}
