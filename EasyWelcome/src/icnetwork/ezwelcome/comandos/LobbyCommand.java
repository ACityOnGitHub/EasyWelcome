package icnetwork.ezwelcome.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import icnetwork.ezwelcome.EasyWelcome;

public class LobbyCommand implements CommandExecutor{
	private EasyWelcome plugin;
	
	public LobbyCommand(EasyWelcome plugin){
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
		if(!(sender instanceof Player)){
			Bukkit.getConsoleSender().sendMessage(plugin.nombre+ChatColor.RED+" You Must be A Player to Execute this Command!");
			return false;
		}
		Player jugador = (Player) sender;
		FileConfiguration config = plugin.getConfig();
		if(config.contains("Config.Lobby.x")){
			double x = Double.valueOf(config.getString("Config.Lobby.x"));
			double y = Double.valueOf(config.getString("Config.Lobby.y"));
			double z = Double.valueOf(config.getString("Config.Lobby.z"));
			float yaw = Float.valueOf(config.getString("Config.Lobby.yaw"));
			float pitch = Float.valueOf(config.getString("Config.Lobby.pitch"));
			World world = plugin.getServer().getWorld(config.getString("Config.Lobby.world"));
			Location l = new Location(world, x, y, z, yaw, pitch);
			jugador.teleport(l);
			String path = "Config.lobby-message-enabled";
			if(config.getString(path).equals("true")){
				String texto2 = "Messages.lobby-message";
				jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(texto2)).replaceAll("%player%", jugador.getName()));
			}
			return true;
		}else{
			String path = "Config.no-lobby-enabled";
			if(config.getString(path).equals("true")){
				String texto = "Messages.no-lobby-enabled-message";
				jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(texto)).replaceAll("%player%", jugador.getName()));
			}
		}
				return true;
	}
}