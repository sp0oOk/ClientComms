package xyz.ds.clientcomms;

import org.jetbrains.annotations.Nullable;
import xyz.ds.clientcomms.commands.TestDecryptCommand;
import xyz.ds.clientcomms.struct.ChannelMessage;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import xyz.ds.clientcomms.tasks.ProcessOutgoingMessagesTask;

import java.util.Map;

public final class ClientComms extends JavaPlugin implements Listener, PluginMessageListener {

    private static ClientComms clientComms;

    @Override
    public void onEnable() {
        clientComms = this;
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "cosmic");
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "cosmic", this);
        getCommand("decrypt").setExecutor(new TestDecryptCommand());
        new ProcessOutgoingMessagesTask().runTaskTimerAsynchronously(this, 1L ,1L);
    }

    @Override
    public void onDisable() {
        Bukkit.getMessenger().unregisterIncomingPluginChannel(this, "cosmic");
        Bukkit.getMessenger().unregisterOutgoingPluginChannel(this, "cosmic");
        clientComms = null;
    }

    public static ClientComms getInstance() {
        return clientComms;
    }

    @Override @SneakyThrows
    public void onPluginMessageReceived(String channel, @Nullable Player player, byte[] message) {
        if(!channel.equalsIgnoreCase("cosmic"))
            return;
        ChannelMessage channelMessage;
        channelMessage = Utils.fromBytes(message);
        final Map<String, Object> data = channelMessage.getData();
    }
}
