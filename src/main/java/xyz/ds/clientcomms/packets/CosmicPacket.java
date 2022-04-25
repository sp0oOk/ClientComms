package xyz.ds.clientcomms.packets;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;

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
    public boolean splitNext;
    @SerializedName(value = "content")
    public String content;

}
