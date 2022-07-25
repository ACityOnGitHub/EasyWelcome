package icnetwork.ezwelcome;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import icnetwork.ezwelcome.comandos.LobbyCommand;
import icnetwork.ezwelcome.comandos.MainCommand;
import icnetwork.ezwelcome.eventos.Entrar;

public class EasyWelcome extends JavaPlugin{
	public String rutaConfig;
	public String rutaMessages;
	PluginDescriptionFile pdffile = getDescription();
	public String version = pdffile.getVersion();
	public String nombre = ChatColor.YELLOW+"["+ChatColor.GOLD+pdffile.getName()+ChatColor.BLUE+"]";
	
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(nombre+ChatColor.GREEN+" was Enabled! - Version: "+version);
		Bukkit.getConsoleSender().sendMessage(nombre+ChatColor.GREEN+" Thanks For Using EasyWelcome!"+ChatColor.AQUA+" ~City");
		registerCommands();
		registerEvents();
		registerConfig();
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(nombre+ChatColor.GREEN+" was Disabled - Version: "+version);
		Bukkit.getConsoleSender().sendMessage(nombre+ChatColor.YELLOW+" Goodbye Dudes!");
	}
	
	public void registerCommands() {
		this.getCommand("ezw").setExecutor(new MainCommand(this));
		this.getCommand("lobby").setExecutor(new LobbyCommand(this));
	}
	
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Entrar(this), this);
	}
	
	public void registerConfig(){
		File config = new File(this.getDataFolder(),"config.yml");
		rutaConfig = config.getPath();
		if(!config.exists()){
			this.getConfig().options().copyDefaults(true);
			saveConfig();
		}
	}
}
