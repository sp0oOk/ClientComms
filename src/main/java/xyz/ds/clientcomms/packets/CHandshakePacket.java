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
public class CHandshakePacket extends ClientPacket {

    @SerializedName(value = "handshake")
    private final String handshake;

    @SerializedName(value = "version")
    private final String version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CHandshakePacket)) return false;
        CHandshakePacket that = (CHandshakePacket) o;
        return Objects.equals(getHandshake(), that.getHandshake()) && Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHandshake(), getVersion());
    }
}
