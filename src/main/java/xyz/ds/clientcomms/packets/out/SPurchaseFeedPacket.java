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
public class SPurchaseFeedPacket extends ClientPacket {

    @SerializedName(value = "name")
    private String name;
    @SerializedName(value = "text")
    private String text;
    @SerializedName(value = "iconURL")
    private String iconURL;

}
