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
public class STooltipPacket extends ClientPacket {

    @SerializedName(value = "title")
    private String title;
    @SerializedName(value = "text")
    private String text;
    @SerializedName(value = "item")
    private String item;
    @SerializedName(value = "itemId")
    private int itemId;
    @SerializedName(value = "itemDamage")
    private int itemDamage;

}
