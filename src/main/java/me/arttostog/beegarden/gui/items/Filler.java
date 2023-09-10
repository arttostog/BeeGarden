package me.arttostog.beegarden.gui.items;

import me.arttostog.beegarden.BeeGarden;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Filler {
	private final Material material;
	private final String color;

	public Filler(Material material, String color) {
		this.material = material;
		this.color = color;
	}

	public ItemStack getItemStack() {
		ItemStack item = new ItemStack(material);

		item.setItemMeta(getFillerMeta(item, color));

		return item;
	}

	private ItemMeta getFillerMeta(ItemStack item, String color) {
		ItemMeta itemMeta = item.getItemMeta();

		if (itemMeta != null) {
			itemMeta.setDisplayName(color + BeeGarden.config.getString("Localization.Honeycomb"));
		}

		return itemMeta;
	}
}
