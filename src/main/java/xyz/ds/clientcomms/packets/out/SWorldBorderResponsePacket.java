package xyz.ds.clientcomms.packets.out;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.ds.clientcomms.packets.ClientPacket;
import xyz.ds.clientcomms.struct.Pois;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class SWorldBorderResponsePacket extends ClientPacket {

    @SerializedName(value = "radius")
    private int radius;
    @SerializedName(value = "radiusX")
    private int radiusX;
    @SerializedName(value = "radiusZ")
    private int radiusZ;
    @SerializedName(value = "centerX")
    private int centerX;
    @SerializedName(value = "centerZ")
    private int centerZ;
    @SerializedName(value = "pois_new")
    private List<Pois> pois_new;

}
