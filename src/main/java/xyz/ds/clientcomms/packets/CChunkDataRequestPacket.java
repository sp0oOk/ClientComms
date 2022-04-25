package xyz.ds.clientcomms.packets;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CChunkDataRequestPacket extends ClientPacket {

    @SerializedName(value = "x")
    private final int x;

    @SerializedName(value = "z")
    private final int z;

    @SerializedName(value = "bulk")
    private final boolean bulk;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CChunkDataRequestPacket)) return false;
        CChunkDataRequestPacket that = (CChunkDataRequestPacket) o;
        return getX() == that.getX() && getZ() == that.getZ() && isBulk() == that.isBulk();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getZ(), isBulk());
    }
}
