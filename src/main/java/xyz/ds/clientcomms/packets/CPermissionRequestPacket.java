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
public class CPermissionRequestPacket extends ClientPacket {

    @SerializedName(value = "permission")
    private final String permission;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CPermissionRequestPacket)) return false;
        if (!super.equals(o)) return false;
        CPermissionRequestPacket that = (CPermissionRequestPacket) o;
        return Objects.equals(getPermission(), that.getPermission());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPermission());
    }
}
