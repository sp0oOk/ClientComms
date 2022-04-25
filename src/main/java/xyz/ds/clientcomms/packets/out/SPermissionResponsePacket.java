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
public class SPermissionResponsePacket extends ClientPacket {

    @SerializedName(value = "permission")
    private String permission;
    @SerializedName(value = "value")
    private boolean value;

}
