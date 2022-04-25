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
public class CClaimCoordPacket extends ClientPacket {

    @SerializedName(value = "x")
    private final int x;

    @SerializedName(value = "z")
    private final int z;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CClaimCoordPacket)) return false;
        CClaimCoordPacket that = (CClaimCoordPacket) o;
        return getX() == that.getX() && getZ() == that.getZ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getZ());
    }

}
