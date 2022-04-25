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
public class SShowEmotePacket extends ClientPacket {

    @SerializedName(value = "entityID")
    private int entityID;
    @SerializedName(value = "emote")
    private String emote;

}
