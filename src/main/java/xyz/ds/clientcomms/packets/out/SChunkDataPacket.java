package xyz.ds.clientcomms.packets.out;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.ds.clientcomms.packets.ClientPacket;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class SChunkDataPacket extends ClientPacket {

    @SerializedName(value = "chunkDataList")
    private List<ChunkData> chunkDataList;

    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class ChunkData
            implements Serializable {
        @SerializedName(value = "x")
        private int x;
        @SerializedName(value = "z")
        private int z;
        @SerializedName(value = "uuid")
        private String uuid;
        @SerializedName(value = "name")
        private String name;
        @SerializedName(value = "color")
        private String color;
    }
}
