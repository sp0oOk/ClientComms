package xyz.ds.clientcomms.packets;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import xyz.ds.clientcomms.ClientCommsAPI;
import xyz.ds.clientcomms.manager.PacketManager;
import xyz.ds.clientcomms.packets.in.CHandshakePacket;
import xyz.ds.clientcomms.packets.out.SKeepAlive;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CosmicPacket implements Serializable {

    @SerializedName(value = "protocol")
    public short protocol;
    @SerializedName(value = "packetID")
    public short packetID;
    @SerializedName(value = "splitNext")
    public boolean splitNext; // This is if the packet content is too large, so it will send another and then CC will join them together
    @SerializedName(value = "content")
    public String content;

    public void build() {
        PacketManager packetManager = ClientCommsAPI.getPacketManager();
        if (packetManager.getPacketId(SKeepAlive.class) == packetID) return;
        content = Base64.getEncoder().encodeToString(ClientCommsAPI.decrypt.b(content.getBytes(StandardCharsets.UTF_8), true));
    }

    public void unpack() {
        PacketManager packetManager = ClientCommsAPI.getPacketManager();
        if (packetManager.getPacketId(CHandshakePacket.class) == packetID) return;
        content = new String(ClientCommsAPI.decrypt.a(Base64.getDecoder().decode(content.getBytes(StandardCharsets.UTF_8)), true), StandardCharsets.UTF_8);
    }
}
