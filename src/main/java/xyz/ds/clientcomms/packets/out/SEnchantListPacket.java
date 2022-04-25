package xyz.ds.clientcomms.packets.out;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.ds.clientcomms.packets.ClientPacket;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class SEnchantListPacket extends ClientPacket {

    @SerializedName(value = "enchantData")
    private List<EnchantData> enchantData;

    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class EnchantData
            implements Serializable {
        @SerializedName(value = "name")
        private String name;
        @SerializedName(value = "description")
        private String description;
        @SerializedName(value = "tier")
        private String tier;
        @SerializedName(value = "color")
        private String color;
        @SerializedName(value = "maxLevel")
        private int maxLevel;
        @SerializedName(value = "itemIDs")
        private List<Integer> itemIDs;
    }
}
