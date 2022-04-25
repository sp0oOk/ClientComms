package xyz.ds.clientcomms.struct;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

@Getter
@Setter
public class ChannelParticipator {

    private final Player player;
    private String channel;
    private String version;
    private String system;

    public ChannelParticipator(Player player, String channel, String version) {
        this.player = player;
        this.channel = channel;
        this.version = version;
        this.system = null;
    }

}
