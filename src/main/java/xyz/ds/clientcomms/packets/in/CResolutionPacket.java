package xyz.ds.clientcomms.packets.in;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.ds.clientcomms.packets.ClientPacket;

@AllArgsConstructor
@Getter
@ToString
@Setter
public class CResolutionPacket extends ClientPacket {

    @SerializedName(value = "x")
    private int x;
    @SerializedName(value = "y")
    private int y;

}
