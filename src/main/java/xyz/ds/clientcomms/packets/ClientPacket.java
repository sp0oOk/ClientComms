package xyz.ds.clientcomms.packets;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public abstract class ClientPacket implements Serializable {

    public transient short protocolVersion = 2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientPacket)) return false;
        ClientPacket that = (ClientPacket) o;
        return getProtocolVersion() == that.getProtocolVersion();
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
