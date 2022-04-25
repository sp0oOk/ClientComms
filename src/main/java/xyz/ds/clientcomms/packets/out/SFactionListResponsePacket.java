package xyz.ds.clientcomms.packets.out;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.ds.clientcomms.packets.ClientPacket;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class SFactionListResponsePacket extends ClientPacket {

    @SerializedName(value = "factions")
    private List<FactionInfo> factions;

    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    private static class FactionInfo {
        @SerializedName(value = "uuid")
        private String uuid;
        @SerializedName(value = "leader")
        private String leader;
        @SerializedName(value = "leaderName")
        private String leaderName;
        @SerializedName(value = "name")
        private String name;
        @SerializedName(value = "size")
        private int size;
        @SerializedName(value = "land")
        private int land;
        @SerializedName(value = "power")
        private int power;
        @SerializedName(value = "powerRounded")
        private int powerRounded;
        @SerializedName(value = "balance")
        private double balance;
        @SerializedName(value = "wealth")
        private double wealth;
    }
}
