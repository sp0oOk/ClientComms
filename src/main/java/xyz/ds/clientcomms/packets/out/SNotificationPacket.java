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
public class SNotificationPacket extends ClientPacket {

    @SerializedName(value = "text")
    private String text;
    @SerializedName(value = "timeToDisplay")
    private int timeToDisplay;

}
