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
public class CClaimCoordPacket extends ClientPacket {

    @SerializedName(value = "x")
    private final int x;

    @SerializedName(value = "z")
    private final int z;

}
