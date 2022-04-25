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
public class SSetBlockProperties extends ClientPacket {

    @SerializedName(value = "x")
    private int x;
    @SerializedName(value = "y")
    private int y;
    @SerializedName(value = "z")
    private int z;
    @SerializedName(value = "blockProperties")
    private BlockProperties blockProperties;

    @ToString
    @AllArgsConstructor
    @Getter
    @Setter
    private static class BlockProperties {
        @SerializedName(value = "heroic")
        private boolean heroic;
    }
}
