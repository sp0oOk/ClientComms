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
public class SVoicePacket extends ClientPacket {

    @SerializedName(value = "label")
    private String label;
    @SerializedName(value = "channel")
    private String channel;
    @SerializedName(value = "audio")
    private byte[] audio;

}
