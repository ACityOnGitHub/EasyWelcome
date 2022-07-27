package icnetwork.ezwelcome.comandos; 

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import icnetwork.ezwelcome.EasyWelcome;

public class MainCommand implements CommandExecutor{
	private EasyWelcome plugin;
	
	public MainCommand(EasyWelcome plugin){
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(plugin.nombre+ChatColor.RED+" You Can't Execute Commands from the Console");
			return false;
		}else {
			Player jugador = (Player) sender;
			if(jugador.hasPermission("ezw.admin")) {
				if(args.length > 0){
					if(args[0].equalsIgnoreCase("help")){
						jugador.sendMessage(plugin.nombre+ChatColor.WHITE+" EasyWelcome by"+ChatColor.GOLD+" ACityOnMinecraft"+ChatColor.WHITE+" -"+ChatColor.GOLD+" Version: "+plugin.version);
						return true;
					}else if(args[0].equalsIgnoreCase("reload")) {
						plugin.reloadConfig();
						FileConfiguration config = plugin.getConfig();
						String path = "Config.reload-message-enabled";
						if(config.getString(path).equals("true")){
							String texto = "Messages.reload-message";
							jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(texto)).replaceAll("%player%", jugador.getName()));
							return true;
						}
					}else if(args[0].equalsIgnoreCase("setlobby")) {
						Location l = jugador.getLocation();
						double x = l.getX();
						double y = l.getY();
						double z = l.getZ();
						String world = l.getWorld().getName();
						float yaw = l.getYaw();
						float pitch = l.getPitch();
						FileConfiguration config = plugin.getConfig();
						config.set("Config.Lobby.x", x);
						config.set("Config.Lobby.y", y);
						config.set("Config.Lobby.z", z);				
						config.set("Config.Lobby.world", world);
						config.set("Config.Lobby.yaw", yaw);
						config.set("Config.Lobby.pitch", pitch);
						plugin.saveConfig();
						String path = "Config.setlobby-enabled";
						if(config.getString(path).equals("true")){
							String texto2 = "Messages.setlobby-message";
							jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(texto2)).replaceAll("%player%", jugador.getName()));
							return true;
						}
					}else{
						jugador.sendMessage(plugin.nombre+ChatColor.RED+" That Command Doesn't Exists :(");
						return true;
					}
				}else{
					jugador.sendMessage(plugin.nombre+ChatColor.WHITE+" Use"+ChatColor.AQUA+" /ezw help"+ChatColor.WHITE+" to see info about the plugin.");
						}
			}else {
				FileConfiguration config = plugin.getConfig();
				String path = "Config.noperms-message-enabled";
				if(config.getString(path).equals("true")){
					String texto = "Messages.noperms-message";
					jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(texto)).replaceAll("%player%", jugador.getName()));
					return true;
				}
			}

		}
				return true;
	}
}