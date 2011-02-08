package com.afforess.minecartmaniacore;
import java.io.File;
import java.util.logging.Logger;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;

import com.afforess.minecartmaniacore.config.SettingList;

public class MinecartManiaCore extends JavaPlugin {
	
	public MinecartManiaCore(PluginLoader pluginLoader, Server instance,
			PluginDescriptionFile desc, File folder, File plugin,
			ClassLoader cLoader) {
		super(pluginLoader, instance, desc, folder, plugin, cLoader);
		server = instance;
		description = desc;
	}

	public final MinecartManiaCoreListener listener = new MinecartManiaCoreListener(this);
	public final MinecartManiaCoreBlockListener blockListener = new MinecartManiaCoreBlockListener();
	public static Logger log;
	public static Server server;
	public static PluginDescriptionFile description;
	public static final String dataDirectory = "plugins" + File.separator + "MinecartMania";
	
	

	public void onEnable(){
		log = Logger.getLogger("Minecraft");
		Configuration.loadConfiguration(description, SettingList.config);

        getServer().getPluginManager().registerEvent(Event.Type.VEHICLE_UPDATE, listener, Priority.Normal, this);
        getServer().getPluginManager().registerEvent(Event.Type.VEHICLE_COLLISION_ENTITY, listener, Priority.Normal, this);
        getServer().getPluginManager().registerEvent(Event.Type.VEHICLE_DAMAGE, listener, Priority.Monitor, this);
        getServer().getPluginManager().registerEvent(Event.Type.REDSTONE_CHANGE, blockListener, Priority.Monitor, this);

        PluginDescriptionFile pdfFile = this.getDescription();
        log.info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
	}
	
	public void onDisable(){
		
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if (commandLabel.contains("reloadconfig")) {
			Configuration.loadConfiguration(description, SettingList.config);
		}
		return true;
	}
}