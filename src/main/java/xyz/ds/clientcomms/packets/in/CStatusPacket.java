package xyz.ds.clientcomms.packets.in;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.ds.clientcomms.packets.ClientPacket;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CStatusPacket extends ClientPacket {

    @SerializedName(value = "fps")
    @Since(value = 1.3)
    private double fps;
    @SerializedName(value = "status")
    @Since(value = 1.3)
    private int statusArray;

}
