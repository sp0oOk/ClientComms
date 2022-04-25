package xyz.ds.clientcomms.struct;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bukkit.entity.Player;

import java.util.UUID;

@ToString
@Getter
@Setter
public class QueuedMessage {

    private String channel;
    private String username;
    private UUID uuid;
    private byte[] data;
    private ChannelMessage channelMessage;

    public QueuedMessage(String channel, Player player, byte[] data) {
        this.channel = channel;
        this.username = player.getName();
        this.uuid = player.getUniqueId();
        this.data = data;
    }

    public QueuedMessage(String channel, Player player, ChannelMessage channelMessage) {
        this.channel = channel;
        this.username = player.getName();
        this.uuid = player.getUniqueId();
        this.channelMessage = channelMessage;
    }

}
