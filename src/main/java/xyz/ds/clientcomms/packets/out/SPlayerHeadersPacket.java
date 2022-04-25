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
public class SPlayerHeadersPacket extends ClientPacket {

    @SerializedName(value = "playerHeaders")
    private Map<String, List<String>> playerHeaders;
    @SerializedName(value = "patch")
    private boolean patch;

}
