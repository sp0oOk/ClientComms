package xyz.ds.clientcomms.packets.out;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.ds.clientcomms.packets.ClientPacket;
import xyz.ds.clientcomms.struct.HitSplash;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class SHitSplatPacket extends ClientPacket {

    @SerializedName(value = "hitsplat")
    private HitSplash hitsplat;

}
