package xyz.ds.clientcomms.packets;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@AllArgsConstructor
@Getter
@ToString
public class CInfoPacket extends ClientPacket {

    @SerializedName(value = "system")
    private final String system;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CInfoPacket)) return false;
        CInfoPacket that = (CInfoPacket) o;
        return Objects.equals(getSystem(), that.getSystem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSystem());
    }

}
