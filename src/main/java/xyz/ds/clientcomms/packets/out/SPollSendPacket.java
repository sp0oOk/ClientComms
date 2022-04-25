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
public class SPollSendPacket extends ClientPacket {

    @SerializedName(value = "pollId")
    private int pollId;
    @SerializedName(value = "question")
    private String question;
    @SerializedName(value = "responses")
    private List<String> responses;
    @SerializedName(value = "force")
    private boolean force;

}
