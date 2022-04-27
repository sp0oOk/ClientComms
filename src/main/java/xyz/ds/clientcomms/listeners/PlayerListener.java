package xyz.ds.clientcomms.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.ds.clientcomms.ClientComms;
import xyz.ds.clientcomms.ClientCommsAPI;
import xyz.ds.clientcomms.events.PlayerRegisterEvent;
import xyz.ds.clientcomms.manager.PacketManager;
import xyz.ds.clientcomms.packets.out.SFeatureSetPacket;
import xyz.ds.clientcomms.packets.out.SKeepAlive;
import xyz.ds.clientcomms.packets.out.SPlayerHeadersPacket;
import xyz.ds.clientcomms.packets.out.SPlayerOutlineColor;

import java.awt.*;
import java.util.HashMap;

public class PlayerListener implements Listener {

    /**
     * Sends Initial {@link SKeepAlive} packet to ensure communication
     * and sends {@link SFeatureSetPacket} to allow CosmicPvP Mods & Features
     * @param event PlayerJoinEvent Emitted when player joins the server
     */

    @EventHandler(ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent event) {
        PacketManager manager = ClientCommsAPI.getPacketManager();
        Player p = event.getPlayer();
        manager.sendPacket(p, new SKeepAlive(ClientCommsAPI.sessionKey));
        // Event to allow packets to be sent after handshake is sent, probably useless
        Bukkit.getPluginManager().callEvent(new PlayerRegisterEvent(p));
    }

    /**
     * Removes Metadata Key "cosmicClient" from player so they no longer receive boosts
     * if they re-log in this server-instance & removes player from the cached map holding
     * the registered players online
     * @param event PlayerLeaveEvent Emitted when players leave the server
     */

    @EventHandler(ignoreCancelled = true)
    public void onLeave(PlayerQuitEvent event) {
        event.getPlayer().removeMetadata("cosmicClient", ClientComms.getInstance());
        ClientCommsAPI.getRegisteredPlayers().remove(event.getPlayer().getUniqueId());
    }

    /**
     * Sends...
     *
     * @param event PlayerRegisterEvent Emitted when players join and after handshake is sent
     */

    @EventHandler(ignoreCancelled = true)
    public void onRegister(PlayerRegisterEvent event) {
        PacketManager manager = ClientCommsAPI.getPacketManager();
        manager.sendAll(new SPlayerHeadersPacket(new HashMap<>(), false));
        manager.sendAll(new SPlayerOutlineColor(event.getPlayer().getEntityId(), Color.RED.getRGB()));
    }
}
