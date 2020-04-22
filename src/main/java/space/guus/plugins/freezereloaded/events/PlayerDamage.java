package space.guus.plugins.freezereloaded.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import space.guus.plugins.freezereloaded.FreezeReloaded;

public class PlayerDamage implements Listener {

    private FreezeReloaded plugin;

    public PlayerDamage(FreezeReloaded plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEvent(EntityDamageByEntityEvent e){
        if (!plugin.blockedactions.contains("DAMAGE")) return;
        if(e.getEntity() instanceof Player){
            Player p = (Player)e.getEntity();
            if(plugin.frozen.contains(p)){
                e.setCancelled(true);
                if(e.getDamager() instanceof Player){
                    plugin.sendMsg((Player)e.getDamager(), "no-attack");
                }
            }
        }
        if(e.getDamager() instanceof Player){
            Player p = (Player)e.getDamager();
            if(plugin.frozen.contains(p)){
                e.setCancelled(true);
                plugin.sendMsg(p, "no-attack-frozen");
            }
        }
    }
}
