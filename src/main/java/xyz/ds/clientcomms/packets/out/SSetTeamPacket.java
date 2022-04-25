package xyz.ds.clientcomms.packets.out;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.ds.clientcomms.packets.ClientPacket;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class SSetTeamPacket extends ClientPacket {

    @SerializedName(value = "members")
    private Set<String> members;
    @SerializedName(value = "voiceEnabled")
    private Set<String> voiceEnabled;

}
