package xyz.ds.clientcomms.packets.in;

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
public class CVoicePacket extends ClientPacket {

    @SerializedName(value = "channel")
    private String channel;
    @SerializedName(value = "audio")
    private byte[] audio;

}
