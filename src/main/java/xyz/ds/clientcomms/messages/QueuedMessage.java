package xyz.ds.clientcomms.messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bukkit.entity.Player;
import xyz.ds.clientcomms.ClientComms;
import xyz.ds.clientcomms.ClientCommsAPI;
import xyz.ds.clientcomms.packets.CosmicPacket;

import java.nio.charset.StandardCharsets;

@ToString
@Getter
@Setter
public class QueuedMessage {

    private static GsonBuilder gsonBuilder = new GsonBuilder().disableHtmlEscaping();

    private Player player;
    private CosmicPacket packet;

    public QueuedMessage(Player player, CosmicPacket packet) {
        this.player = player;
        this.packet = packet;
    }

    public void send() {
        packet.build();
        Gson gson = gsonBuilder.create();
        player.sendPluginMessage(ClientComms.getInstance(), ClientCommsAPI.CHANNEL, gson.toJson(packet).getBytes(StandardCharsets.UTF_8));
    }
}
