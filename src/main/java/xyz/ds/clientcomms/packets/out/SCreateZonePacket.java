package xyz.ds.clientcomms.packets.out;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.ds.clientcomms.packets.ClientPacket;
import xyz.ds.clientcomms.struct.Zone;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class SCreateZonePacket extends ClientPacket {

    @SerializedName(value = "zone")
    private Zone zone;

}
