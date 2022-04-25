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
public class SHighlightBlockPacket extends ClientPacket {

    @SerializedName(value = "operation")
    private int operation;
    @SerializedName(value = "color")
    private String color;
    @SerializedName(value = "colorCode")
    private int colorCode;
    @SerializedName(value = "x")
    private int x;
    @SerializedName(value = "y")
    private int y;
    @SerializedName(value = "z")
    private int z;
    @SerializedName(value = "minimumIntensity")
    private double minimumIntensity;
    @SerializedName(value = "maximumIntensity")
    private double maximumIntensity;

}
