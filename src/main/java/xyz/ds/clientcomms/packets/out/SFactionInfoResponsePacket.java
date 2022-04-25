package xyz.ds.clientcomms.packets.out;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.ds.clientcomms.packets.ClientPacket;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class SFactionInfoResponsePacket extends ClientPacket {

    @SerializedName(value = "uuid")
    private String uuid;
    @SerializedName(value = "name")
    private String name;
    @SerializedName(value = "powerString")
    private String powerString;
    @SerializedName(value = "relationNames")
    private Map<String, List<String>> relationNames;
    @SerializedName(value = "membersOnline")
    private List<String> membersOnline;
    @SerializedName(value = "membersOffline")
    private List<String> membersOffline;
    @SerializedName(value = "wealth")
    private double wealth;
    @SerializedName(value = "spawnerWealth")
    private double spawnerWealth;
    @SerializedName(value = "rank")
    private int rank;

}
