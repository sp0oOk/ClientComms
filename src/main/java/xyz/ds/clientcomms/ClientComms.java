package xyz.ds.clientcomms;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.Nullable;
import xyz.ds.clientcomms.listeners.PlayerListener;
import xyz.ds.clientcomms.packets.ClientPacket;
import xyz.ds.clientcomms.packets.CosmicPacket;
import xyz.ds.clientcomms.tasks.ProcessOutgoingMessagesTask;

public final class ClientComms extends JavaPlugin implements PluginMessageListener {

    private static ClientComms clientComms;
    private static final Gson gson = new GsonBuilder()
            .disableHtmlEscaping()
            .create();

    @Override
    public void onEnable() {
        clientComms = this;
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
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

    public static ClientComms getInstance() {
        return clientComms;
    }

    @Override @SneakyThrows
    public void onPluginMessageReceived(String channel, @Nullable Player player, byte[] message) {
        if (channel.equals(ClientCommsAPI.CHANNEL)) {
            CosmicPacket data = gson.fromJson(new String(message), CosmicPacket.class);
            data.unpack();
            Class<? extends ClientPacket> packetType = ClientCommsAPI.getPacketManager().fromId(data.packetID);
            ClientPacket packet = gson.fromJson(data.content, packetType);

        }
    }
}
