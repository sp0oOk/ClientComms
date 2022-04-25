package xyz.ds.clientcomms.packets.out;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.ds.clientcomms.packets.ClientPacket;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class SDefaultSkinnedPlayersPacket extends ClientPacket {

    @SerializedName(value = "uuids")
    private Set<UUID> uuids;

}
