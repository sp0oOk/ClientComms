package xyz.ds.clientcomms.packets.out;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.ds.clientcomms.packets.ClientPacket;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class SColorsPacket extends ClientPacket {

    @SerializedName(value = "colorMap")
    private Map<Integer, Integer> colorMap;

}
