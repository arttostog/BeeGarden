package me.arttostog.beegarden.gui.items;

import me.arttostog.beegarden.BeeGarden;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class Bee {
	private final URL beeHeadURL;

	public Bee() throws MalformedURLException {
		beeHeadURL = new URL("http://textures.minecraft.net/texture/4420c9c43e095880dcd2e281c81f47b163b478f58a584bb61f93e6e10a155f31");
	}

	public ItemStack getBeeItem() {
		ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
		itemStack.setItemMeta(getBeeSkullMeta(itemStack));

		return itemStack;
	}

	private ItemMeta getBeeSkullMeta(ItemStack itemStack) {
		SkullMeta meta = (SkullMeta) itemStack.getItemMeta();

		if (meta != null) {
			meta.setDisplayName("§e§l" + BeeGarden.config.getString("Localization.Bee"));
			meta.setOwnerProfile(getBeeProfile());
		}

		return meta;
	}

	private PlayerProfile getBeeProfile() {
		PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());
		profile.getTextures().setSkin(beeHeadURL);

		return profile;
	}
}
