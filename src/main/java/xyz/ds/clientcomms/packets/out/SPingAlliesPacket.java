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
public class SPingAlliesPacket extends ClientPacket {

    @SerializedName(value = "name")
    private String name;
    @SerializedName(value = "x")
    private double x;
    @SerializedName(value = "y")
    private double y;
    @SerializedName(value = "z")
    private double z;
    @SerializedName(value = "dimension")
    private int dimension;
    @SerializedName(value = "allies")
    private boolean allies;

}
