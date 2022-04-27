package xyz.ds.clientcomms;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.Nullable;
import xyz.ds.clientcomms.commands.CommandComms;
import xyz.ds.clientcomms.listeners.PlayerListener;
import xyz.ds.clientcomms.manager.PacketManager;
import xyz.ds.clientcomms.packets.ClientPacket;
import xyz.ds.clientcomms.packets.CosmicPacket;
import xyz.ds.clientcomms.packets.in.CHandshakePacket;
import xyz.ds.clientcomms.packets.in.CPermissionRequestPacket;
import xyz.ds.clientcomms.packets.out.*;
import xyz.ds.clientcomms.tasks.ProcessOutgoingMessagesTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class ClientComms extends JavaPlugin implements PluginMessageListener {

    private static ClientComms clientComms;
    private final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @Override
    public void onEnable() {
        clientComms = this;

        final File config = new File(getDataFolder(), "config.yml");
        if(!config.exists()) saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        getCommand("comms").setExecutor(new CommandComms());
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "cosmic");
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "cosmic", this);
        new ProcessOutgoingMessagesTask().runTaskTimerAsynchronously(this, 1L, 1L);

    }

    @Override
    public void onDisable() {
        Bukkit.getMessenger().unregisterIncomingPluginChannel(this, "cosmic");
        Bukkit.getMessenger().unregisterOutgoingPluginChannel(this, "cosmic");
        clientComms = null;
        HandlerList.unregisterAll(this);
    }

    /**
     * Our Cute Plugin Instance
     * @return ClientComms instance
     */

    public static ClientComms getInstance() {
        return clientComms;
    }

    /**
     * Emitted when the plugin receives a packet or "message"
     * Following that, it is decrypted and interpreted into sub-methods for convenience
     * @param channel Channel the message was sent on.
     * @param player Player Object (can be null in certain instances)
     * @param message Byte Message Received
     */

    @Override @SneakyThrows
    public void onPluginMessageReceived(String channel, @Nullable Player player, byte[] message) {
        if (channel.equals(ClientCommsAPI.CHANNEL)) {
            CosmicPacket data = gson.fromJson(new String(message), CosmicPacket.class);
            data.unpack();
            Class<? extends ClientPacket> packetType = ClientCommsAPI.getPacketManager().fromId(data.packetID);
            ClientPacket packet = gson.fromJson(data.content, packetType);
            final PacketManager packetManager = ClientCommsAPI.getPacketManager();
            switch (packetType.getSimpleName()) {
                case "CHandshakePacket":
                    assert player != null;
                    if(!ClientCommsAPI.getRegisteredPlayers().containsKey(player.getUniqueId())) handleJoin(player.getUniqueId(), (CHandshakePacket) packet);
                    break;
                case "CPermissionRequestPacket":
                    handlePermission(player, (CPermissionRequestPacket) packet);
                    break;
                case "CStatusPacket":
                case "CWorldNameRequestPacket":
                    assert player != null;
                    packetManager.sendPacket(player, new SSetWorldNamePacket(player.getWorld().getName(), new ArrayList<>(), (byte) 0, (byte) 0));
                    break;
                case "CWorldBorderRequestPacket":
                    assert player != null;
                    final double sizeOfBorder = player.getWorld().getWorldBorder().getSize();
                    packetManager.sendPacket(player, new SWorldBorderResponsePacket((int) sizeOfBorder, (int) sizeOfBorder, (int) sizeOfBorder, player.getWorld().getWorldBorder().getCenter().getBlockX(), player.getWorld().getWorldBorder().getCenter().getBlockZ(),
                            new ArrayList<>()));
                    break;
                case "CTimeRequestPacket":
                    assert player != null;
                    packetManager.sendPacket(player, new STimeResponsePacket(System.currentTimeMillis()));
                    break;
                case "CChunkDataRequestPacket":
                    // no clue
                    assert player != null;
                    break;
            }
        }
    }

    /**
     * Handles CHandshakePacket emitted when a handshake is registered (C -> S)
     * Handles pre-requisite items for external usage and permissions.
     * @param uuid Player UUID
     * @param packet Handshake Packet
     */

    private void handleJoin(UUID uuid, CHandshakePacket packet) {
        final Player player = Bukkit.getPlayer(uuid);
        if(player == null || !(player.isOnline())) return;
        ClientCommsAPI.getRegisteredPlayers().put(uuid, packet.version);
        final PacketManager manager = ClientCommsAPI.getPacketManager();
        Bukkit.getScheduler().scheduleSyncDelayedTask(ClientComms.getInstance(), () -> manager.sendPacket(player, new SFeatureSetPacket("COSMICPVP")), 40L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
            ClientCommsAPI.HANDSHAKE_MESSAGE.forEach(player::sendMessage);
            player.setMetadata("cosmicClient", new FixedMetadataValue(this, packet.version));
            ClientCommsAPI.sendDefaultPermissions(player);
            manager.sendAll(new SPlayerHeadersPacket(new HashMap<>(), false));
        }, 60L);
    }

    /**
     * Handles CPermissionRequestPacket emitted when the client requests permissions from the server
     * Can also be force sent via {@link PacketManager} with {@link ClientCommsAPI#sendDefaultPermissions(Player)}
     * Allows for use of Roam, Printer, etc.
     * @param player Player Object
     * @param permissionRequestPacket Permission Request Packet
     */

    private void handlePermission(Player player, CPermissionRequestPacket permissionRequestPacket) {
        if(player == null || !(player.isOnline())) {
            getLogger().warning("An unknown entity requested permissions, aborting response protocol!");
            return;
        }
        PacketManager manager = ClientCommsAPI.getPacketManager();
        switch(permissionRequestPacket.getPermission()) {
            case "printer":
                manager.sendPacket(player, new SPermissionResponsePacket("printer", true));
                break;
            case "freeroam":
                manager.sendPacket(player, new SPermissionResponsePacket("freeroam", true));
                break;
            case "breadcrumbs":
                manager.sendPacket(player, new SPermissionResponsePacket("breadcrumbs", true));
                break;
            case "op":
                manager.sendPacket(player, new SPermissionResponsePacket("op", player.hasPermission("ranks.operator")));
                break;
            case "interstellar":
                manager.sendPacket(player, new SPermissionResponsePacket("interstellar", player.hasPermission("ranks.interstellar")));
                break;
            case "banteam":
                manager.sendPacket(player, new SPermissionResponsePacket("banteam", player.hasPermission("ranks.banteam")));
                break;
            default:
                manager.sendPacket(player, new SPermissionResponsePacket(permissionRequestPacket.getPermission(), false));
        }
    }

}
