package xyz.ds.clientcomms.packets.out;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.ds.clientcomms.packets.ClientPacket;
import xyz.ds.clientcomms.struct.Pois;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class SSetWorldNamePacket extends ClientPacket {

    @SerializedName(value = "worldName")
    private String worldName;
    @SerializedName(value = "pois_new")
    private List<Pois> pois_new;
    @SerializedName(value = "skybox")
    private byte skybox;
    @SerializedName(value = "precipitationType")
    private byte precipitationType;

}
