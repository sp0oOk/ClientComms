package xyz.ds.clientcomms.packets.out;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.ds.clientcomms.enums.BarState;
import xyz.ds.clientcomms.packets.ClientPacket;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class SBarAPIPacket extends ClientPacket {

    @SerializedName(value = "barState")
    private BarState barState;
    @SerializedName(value = "title")
    private String title;
    @SerializedName(value = "item")
    private String item;
    @SerializedName(value = "min")
    private long min;
    @SerializedName(value = "max")
    private long max;
    @SerializedName(value = "value")
    private long value;

}
