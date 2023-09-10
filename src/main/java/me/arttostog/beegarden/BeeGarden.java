package me.arttostog.beegarden;

import me.arttostog.beegarden.event.Event;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class BeeGarden extends JavaPlugin {
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        setConfig();

        Bukkit.getServer().getPluginManager().registerEvents(new Event(), this);

        this.getLogger().info("§aBeeGarden successfully enabled!");
    }

    public void setConfig() {
        if (!new File(this.getDataFolder().getAbsolutePath(), "config.yml").exists()) {
            this.saveResource("config.yml", false);
        }
        config = this.getConfig();
    }
}
