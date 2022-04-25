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
public class CFactionInfoRequestPacket extends ClientPacket {

    @SerializedName(value = "name")
    private final String name;

    @SerializedName(value = "formatted")
    private final boolean formatted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CFactionInfoRequestPacket)) return false;
        CFactionInfoRequestPacket that = (CFactionInfoRequestPacket) o;
        return isFormatted() == that.isFormatted() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), isFormatted());
    }

}
