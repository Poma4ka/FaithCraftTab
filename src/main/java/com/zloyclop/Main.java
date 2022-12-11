package com.zloyclop;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    public static Main instance;
    public FileConfiguration config = this.getConfig();

    public Main() {
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        this.config.options().copyDefaults(true);
        this.saveConfig();

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, Tab::showToAll,0,this.config.getInt("update_interval"));
        Bukkit.getServer().getPluginManager().registerEvents(new Events(),this);
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this,new Lag(),20L,20L);
    }

    public void onLoad() {
        Bukkit.getServer().getConsoleSender().sendMessage(this.getName() + " включен");
    }

    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage(this.getName() + " выключен");
    }


}
