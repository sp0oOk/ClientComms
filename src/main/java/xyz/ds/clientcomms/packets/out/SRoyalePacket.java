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
public class SRoyalePacket extends ClientPacket {

    @SerializedName(value = "radius")
    private int radius;
    @SerializedName(value = "centerX")
    private int centerX;
    @SerializedName(value = "centerZ")
    private int centerZ;
    @SerializedName(value = "dRadius")
    private double dRadius;
    @SerializedName(value = "dCenterX")
    private double dCenterX;
    @SerializedName(value = "dCenterZ")
    private double dCenterZ;

}
