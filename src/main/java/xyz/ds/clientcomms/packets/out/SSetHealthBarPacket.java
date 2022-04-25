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
public class SSetHealthBarPacket extends ClientPacket {

    @SerializedName(value = "currentHealth")
    private long currentHealth;
    @SerializedName(value = "maxHealth")
    private long maxHealth;
    @SerializedName(value = "name")
    private String name;

}
