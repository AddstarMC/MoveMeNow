package net.craftminecraft.bungee.movemenow;

import net.md_5.bungee.api.plugin.Plugin;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;

public class MoveMeNow extends Plugin {
	MainConfig config;
	@Override
	public void onEnable() {
        loadConfig();
		this.getProxy().getPluginManager().registerListener(this, new PlayerListener(this));
		this.getProxy().getPluginManager().registerCommand(this, new ReloadCommand(this));
	}

	@Override
	public void onDisable() {
		config = null;
	}
	
	public MainConfig getConfig() {
		return this.config;
	}

    public void loadConfig() {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);
        try {
            config = yaml.loadAs(new FileInputStream(new File(getDataFolder(), "config.yml")), MainConfig.class);
        } catch (FileNotFoundException e) {
            config = new MainConfig();
            try {
                yaml.dump(config, new FileWriter(new File(getDataFolder(), "config.yml")));
            } catch (IOException ignored) {
            }
        }
    }
}
