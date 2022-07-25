package icnetwork.ezwelcome.eventos;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.connorlinfoot.titleapi.TitleAPI;
import icnetwork.ezwelcome.EasyWelcome;

public class Entrar implements Listener{
	private EasyWelcome plugin;
	
	public Entrar(EasyWelcome plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void Enter(PlayerJoinEvent event){
		Player jugador = event.getPlayer();
		
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
		}
		
		String tpath = "Config.title-enabled";
		if(config.getString(tpath).equals("true")){
			String title = config.getString("Config.title");
			String subtitle = config.getString("Config.subtitle");
			int fadein = config.getInt("Config.fadein");
			int stay = config.getInt("Config.stay");
			int fadeout = config.getInt("Config.fadeout");
			TitleAPI.sendTitle(jugador, ChatColor.translateAlternateColorCodes('&', title.replaceAll("%player%", jugador.getName())), ChatColor.translateAlternateColorCodes('&', subtitle.replaceAll("%player%", jugador.getName())), fadein, stay, fadeout);
		}
		
		String path = "Config.welcome-message-enabled";
		if(config.getString(path).equals("true")){
			List<String> mensaje = config.getStringList("Messages.welcome-message");
			for(int i=0;i<mensaje.size();i++){
				String texto = mensaje.get(i);
				jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName())));
			}
		}
		
		return;
		
	}
}
