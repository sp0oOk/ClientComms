package xyz.ds.clientcomms.packets.out;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.ds.clientcomms.packets.ClientPacket;
import xyz.ds.clientcomms.struct.PopUp;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class SPopUpPacket extends ClientPacket {

    @SerializedName(value = "popUp")
    private PopUp popUp;

}
