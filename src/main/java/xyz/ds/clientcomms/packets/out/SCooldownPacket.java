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
public class SCooldownPacket extends ClientPacket {

    @SerializedName(value = "key")
    private String key;
    @SerializedName(value = "category")
    private String category;
    @SerializedName(value = "infinite")
    private boolean infinite;
    @SerializedName(value = "color")
    private int color;
    @SerializedName(value = "name")
    private String name;
    @SerializedName(value = "duration")
    private int duration;
    @SerializedName(value = "max")
    private int max;
    @SerializedName(value = "item")
    private String item;
    @SerializedName(value = "itemID")
    private int itemID;

}
