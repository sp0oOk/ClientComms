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
public class SSetMapImagePacket extends ClientPacket {

    @SerializedName(value = "imageURL")
    private String imageURL;
    @SerializedName(value = "width")
    private int width;
    @SerializedName(value = "height")
    private int height;
    @SerializedName(value = "topLeftX")
    private int topLeftX;
    @SerializedName(value = "topLeftZ")
    private int topLeftZ;

}
