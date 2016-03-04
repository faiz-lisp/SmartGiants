package me.jjm_223.smartgiants;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Messages {
    private static FileConfiguration config = new YamlConfiguration();
    private static boolean isLoaded = false;

    public static String getLang(String section) {
        return ChatColor.translateAlternateColorCodes('&', config.getString(section));
    }

    public static void loadMessages(SmartGiants plugin) {
        File configFile = new File(plugin.getDataFolder(), "lang.yml");
        try {
            config.load(configFile);
            isLoaded = true;
        } catch (IOException e) {
            fallback();
        } catch (InvalidConfigurationException e) {
            fallback();
        }
    }

    private static void fallback() {
        InputStream stream = Messages.class.getClassLoader().getResourceAsStream("lang.yml");
        try {
            config.load(stream);
            stream.close();
            isLoaded = true;
        } catch (IOException e) {
            e.printStackTrace();
            isLoaded = false;
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
            isLoaded = false;
        }
    }
}