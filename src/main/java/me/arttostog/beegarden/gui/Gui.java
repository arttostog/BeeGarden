package me.arttostog.beegarden.gui;

import me.arttostog.beegarden.BeeGarden;
import me.arttostog.beegarden.gui.items.Bee;
import me.arttostog.beegarden.gui.items.Filler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Beehive;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;

public class Gui {
	private final InventoryType inventoryType = InventoryType.DISPENSER;
	private final ItemStack bee;
	private final ItemStack filler;
	private final ItemStack filler2;
	private final Beehive beehive;
	private final int beesCount;
	private final int honeyLvl;

	public Gui(Block block) throws MalformedURLException {
		bee = new Bee().getBeeItem();
		filler = new Filler(Material.YELLOW_STAINED_GLASS_PANE, "§e§l").getItemStack();
		filler2 = new Filler(Material.ORANGE_STAINED_GLASS_PANE, "§6§l").getItemStack();

		beehive = (Beehive) block.getState();
		beesCount = beehive.getEntityCount();
		honeyLvl = Integer.parseInt(block.getBlockData().getAsString().replaceAll("[^-?0-9]+", ""));
	}

	public void OpenGui(Player player) {
		player.openInventory(getInventory(player));
	}

	private Inventory getInventory(Player player) {
		Inventory gui = Bukkit.createInventory(player, inventoryType, getTitle());
		gui.setMaxStackSize(1);
		gui.setContents(getItems());

		return gui;
	}

	private String getTitle() {
		StringBuilder title = new StringBuilder("§e§l")
				.append(BeeGarden.config.getString("Localization.Bees"))
				.append(": ")
				.append(beesCount)
				.append("/")
				.append(beehive.getMaxEntities());
		for (int i = 0; i < BeeGarden.config.getInt("Indent"); i++) {
			title.append(" ");
		}
		title.append("§r§6§l")
				.append(BeeGarden.config.getString("Localization.Honey"))
				.append(": ")
				.append(honeyLvl)
				.append("/5");

		return title.toString();
	}

	private ItemStack[] getItems() {
		return getItemsList().toArray(new ItemStack[0]);
	}

	private ArrayList<ItemStack> getItemsList() {
		ArrayList<ItemStack> Items = new ArrayList<>();

		for (int i = 0; i < inventoryType.getDefaultSize(); i++) {
			if (i < beesCount) {
				Items.add(bee);
				continue;
			}
			if (i < (int) Math.floor(inventoryType.getDefaultSize() / 5f * honeyLvl) + beesCount) {
				Items.add(filler2);
				continue;
			}
			Items.add(filler);
		}
		Collections.shuffle(Items);

		return Items;
	}
}
