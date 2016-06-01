package com.winterhaven_mc.deathchest;

import com.winterhaven_mc.deathchest.listeners.BlockEventListener;
import com.winterhaven_mc.deathchest.listeners.InventoryEventListener;
import com.winterhaven_mc.deathchest.listeners.PlayerEventListener;
import com.winterhaven_mc.deathchest.storage.ChestManager;
import com.winterhaven_mc.deathchest.storage.DataStore;
import com.winterhaven_mc.deathchest.storage.DataStoreFactory;
import com.winterhaven_mc.deathchest.tasks.TaskManager;
import com.winterhaven_mc.deathchest.util.CommandManager;
import com.winterhaven_mc.deathchest.util.MessageManager;
import com.winterhaven_mc.util.SoundManager;
import com.winterhaven_mc.util.WorldManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PluginMain extends JavaPlugin {

	public static PluginMain instance;
	
	public WorldManager worldManager;
	public MessageManager messageManager;
	public SoundManager soundManager;
	public DataStore dataStore;
	public ChestManager chestManager;
	public TaskManager taskManager;

	public boolean debug = getConfig().getBoolean("debug");

	public void onEnable() {

		// static reference to plugin instance
		instance = this;

		// copy default config from jar if it doesn't exist
		saveDefaultConfig();

		// instantiate world manager
		worldManager = new WorldManager(this);
		
		// instantiate message manager
		messageManager = new MessageManager(this);

		// instantiate sound manager
		soundManager = new SoundManager(this);
		
		// instantiate datastore
		dataStore = DataStoreFactory.create();
		
		// instantiate chest manager
		chestManager = new ChestManager(this);
		
		// instantiate task manager
		taskManager = new TaskManager(this);

		// instantiate command manager
		new CommandManager(this);

		// initialize event listeners
		new PlayerEventListener(this);
		new BlockEventListener(this);
		new InventoryEventListener(this);
		
		// log detected protection plugins
		ProtectionPlugin.reportInstalled();
		
	}
	
	public void onDisable() {
		
		// close datastore
		dataStore.close();
	}

}
