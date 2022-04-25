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
public class SScoreboardPacket extends ClientPacket {

    @SerializedName(value = "enabled")
    private boolean enabled;
    @SerializedName(value = "title")
    private String title;
    @SerializedName(value = "subtitle")
    private String subtitle;
    @SerializedName(value = "view")
    private byte view;
    @SerializedName(value = "threshold")
    private int threshold;
    @SerializedName(value = "remainingWinners")
    private int remainingWinners;
    @SerializedName(value = "remainingMs")
    private long remainingMs;
    @SerializedName(value = "item")
    private String item;
    @SerializedName(value = "itemId")
    private int itemId;
    @SerializedName(value = "itemMeta")
    private int itemMeta;
    @SerializedName(value = "participantList")
    private List<Participant> participantList;

    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    private static class Participant {
        @SerializedName(value = "name")
        private String name;
        @SerializedName(value = "score")
        private int score;
        @SerializedName(value = "place")
        private int place;
        @SerializedName(value = "color")
        private String color;
    }
}
