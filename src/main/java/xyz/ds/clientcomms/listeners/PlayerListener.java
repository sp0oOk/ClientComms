package xyz.ds.clientcomms.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.ds.clientcomms.ClientComms;
import xyz.ds.clientcomms.ClientCommsAPI;
import xyz.ds.clientcomms.manager.PacketManager;
import xyz.ds.clientcomms.packets.out.SFeatureSetPacket;
import xyz.ds.clientcomms.packets.out.SKeepAlive;

public class PlayerListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onEvent(PlayerJoinEvent event) {
        PacketManager manager = ClientCommsAPI.getPacketManager();
        Player p = event.getPlayer();
        manager.sendPacket(p, new SKeepAlive(ClientCommsAPI.sessionKey));
        Bukkit.getScheduler().scheduleSyncDelayedTask(ClientComms.getInstance(), () -> {
            manager.sendPacket(p, new SFeatureSetPacket("COSMICPVP"));
        }, 40L);
    }
}
