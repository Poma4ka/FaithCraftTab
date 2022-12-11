package com.zloyclop;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;


public class PlayerList {
    static ConfigurationSection prefixes = Main.getInstance().config.getConfigurationSection("roles");

    public static String getTeamString(Player player) {
        StringBuilder teamString = new StringBuilder();

        for (String key : prefixes.getKeys(false)) {
            if (player.hasPermission("faithcraft-tab.prefix." + key)) {
                teamString.append(Main.getInstance().config.getString("roles." + key)).append(" ");
            }
        }
        return Tab.setColors(teamString.toString());
    }

    public static void setPrefix(Player player) {
        player.setPlayerListName(getTeamString(player) + player.getName());
    }

}
