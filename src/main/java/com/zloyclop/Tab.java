package com.zloyclop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.StringJoiner;

public class Tab {
    static FileConfiguration config = Main.getInstance().config;

    static public void showToAll() {

        Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();

        if (players.size() != 0) {
            for (Player player: players) {
                updateTab(player);
            }
        }
    }

    static public void updateTab(Player player) {
        try {
            StringJoiner headerValue = new StringJoiner("\n", "", "");
            config.getStringList("header").forEach(headerValue::add);

            StringJoiner footerValue = new StringJoiner("\n", "", "");
            config.getStringList("footer").forEach(footerValue::add);

            player.setPlayerListHeader(setColors(replacePing(replaceTps(replaceOnline(headerValue.toString())),player)));
            player.setPlayerListFooter(setColors(replacePing(replaceTps(replaceOnline(footerValue.toString())),player)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public String setColors(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    static private String replaceOnline(String s) {
        return s.replace("%online%", "" + Bukkit.getServer().getOnlinePlayers().size());
    }

    static private String replacePing(String s,Player p) {
        int ping = p.getPing();
        String color = "&a";

        if (ping > 80) color = "&e";
        if (ping > 200) color = "&c";

        return s.replace("%ping%",  (color + ping));
    }

    static private String replaceTps(String s) {
        double tps = Lag.getTPS();
        String color = "&a";

        if (tps < 17) color = "&e";
        if (tps < 13) color = "&c";

        return s.replace("%tps%", (color + (tps > 20 ? 20 : tps)) + (tps > 20 ? "*" : ""));
    }

}
