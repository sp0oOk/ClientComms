package xyz.ds.clientcomms.packets.out;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.ds.clientcomms.packets.ClientPacket;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class SSetEntityProperties extends ClientPacket {

    @SerializedName(value = "entityID")
    private int entityID;
    @SerializedName(value = "entityProperties")
    private EntityProperties entityProperties;

    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    private static class EntityProperties {
        @SerializedName(value = "scale")
        private float scale = 1.0f;
        @SerializedName(value = "boss")
        private boolean boss = false;
        @SerializedName(value = "spooky")
        private boolean spooky = false;
        @SerializedName(value = "showNametags")
        private boolean showNametags = true;
        @SerializedName(value = "customNametag")
        private String customNametag;
        @SerializedName(value = "golemType")
        private byte golemType = 0;
        @SerializedName(value = "type")
        private byte type = 0;
        private transient boolean a;
        @SerializedName(value = "key")//BossKey
        private String key = "";
    }
}
