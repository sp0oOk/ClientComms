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
public class SSetXPPacket extends ClientPacket {

    @SerializedName(value = "level")
    private int level;
    @SerializedName(value = "xp")
    private long xp;
    @SerializedName(value = "total")
    private long total;
    @SerializedName(value = "color")
    private String color;
    @SerializedName(value = "animation")
    private boolean animation;

}
