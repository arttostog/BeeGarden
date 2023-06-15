package me.arttostog.beegarden.gui;

import me.arttostog.beegarden.BeeGarden;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Items {
	private static ItemStack filler;
	private static ItemStack filler2;
	private static URL BeeHeadURL;

	public static void Init() {
		filler = GetFiller(Material.YELLOW_STAINED_GLASS_PANE, "§e§l");
		filler2 = GetFiller(Material.ORANGE_STAINED_GLASS_PANE, "§6§l");

		try {
			BeeHeadURL = new URL("http://textures.minecraft.net/texture/4420c9c43e095880dcd2e281c81f47b163b478f58a584bb61f93e6e10a155f31");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private static ItemStack GetFiller(Material material, String color) {
		ItemStack filler = new ItemStack(material);
		ItemMeta fillerMeta = filler.getItemMeta();
		
		if (fillerMeta != null) {
			fillerMeta.setDisplayName(color + BeeGarden.config.getString("Localization.Honeycomb"));
		}
		filler.setItemMeta(fillerMeta);

		return filler;
	}

	public static ItemStack[] GetItems(int BeesCount, int HoneyLvl) {
		List<ItemStack> Items = new ArrayList<>();
		int Size = Gui.inventoryType.getDefaultSize();

		for (int i = 0; i < Size; i++) {
			if (i < BeesCount) {
				Items.add(GetBeeItem());
				continue;
			}

			if (i < (int) Math.floor(Size / 5.0 * HoneyLvl) + BeesCount) {
				Items.add(filler2);
				continue;
			}

			Items.add(filler);
		}

		Collections.shuffle(Items);

		return Items.toArray(new ItemStack[0]);
	}

	private static ItemStack GetBeeItem() {
		ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta meta = (SkullMeta) itemStack.getItemMeta();

		if (meta != null) {
			meta.setDisplayName("§e§l" + BeeGarden.config.getString("Localization.Bee"));
			meta.setOwnerProfile(GetBeeProfile());
		}

		itemStack.setItemMeta(meta);

		return itemStack;
	}

	private static PlayerProfile GetBeeProfile() {
		PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());
		profile.getTextures().setSkin(BeeHeadURL);

		return profile;
	}
}
