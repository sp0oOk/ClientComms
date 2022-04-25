package xyz.ds.clientcomms.packets.out;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.ds.clientcomms.packets.ClientPacket;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class SPlayerFactionResponsePacket extends ClientPacket {

    @SerializedName(value = "uuid")
    private String uuid;
    @SerializedName(value = "factionID")
    private String factionID;

}
