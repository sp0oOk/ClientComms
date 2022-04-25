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
public class CPollResponsePacket extends ClientPacket {

    @SerializedName(value = "pollId")
    private int pollId;
    @SerializedName(value = "responseIndex")
    private int responseIndex;

}
