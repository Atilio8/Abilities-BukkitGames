package com.pvpkillz.plugins.Abilities;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager extends JavaPlugin {

	private Main plugin;

private File configFile;
private FileConfiguration config;

	public ConfigManager(Main plugin) {
		
		this.plugin = plugin;
		manageConfig();
	}
	
	private void manageConfig() {
		
		configFile = new File(plugin.getDataFolder() + "/config.yml");
		config = YamlConfiguration.loadConfiguration(configFile);
		LinkedHashMap<String , String> configMap = new LinkedHashMap<String, String>();
		config.options().header("Config and description file for the Extra-Abilities.\nFor this plugin to work, you must have\nthe latest BukkitGames found at:\nhttp://dev.bukkit.org/server-mods/bukkitgames/\nCreated By: Undeadkillz\nOriginal Coding: Galodystic");
		
		configMap.put("Abilities.102.Desc", "Player can capture hostile mobs. With an Egg.");
		configMap.put("Abilities.101.Desc", "Player can stay underwater, and receive strength meanwhile underwater.");
		configMap.put("Abilities.101.Level", "1");
		configMap.put("Abilities.100.Desc", "Player has the chance to drop X amount of iron, when it's mined.");
		configMap.put("Abilities.100.Chance", "30");
		configMap.put("Abilities.100.Amount", "2");
		
		for (Entry<String, String> entry : configMap.entrySet()) {
			String path = entry.getKey();
			Object value = entry.getValue();
			
			if (!config.contains(path)) {
				config.set(path, value);
			}
		}
		
		try{
			config.save(configFile);
		}catch (Exception e) {
			e.printStackTrace();
			plugin.log.info("[Abilities] Unable to save config.yml!");
		}
	}
	
	public String readString(String path) {
		
		return config.getString(path);
	}
	
	public int readInt(String path) {
		
		String string = config.getString(path);
		int zahl;
		try{
			zahl = Integer.parseInt(string);
			return zahl;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
