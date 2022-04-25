package xyz.ds.clientcomms.struct;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class Zone
        implements Serializable {
    @SerializedName(value = "startX")
    private int startX;
    @SerializedName(value = "startY")
    private int startY;
    @SerializedName(value = "startZ")
    private int startZ;
    @SerializedName(value = "endX")
    private int endX;
    @SerializedName(value = "endY")
    private int endY;
    @SerializedName(value = "endZ")
    private int endZ;
    @SerializedName(value = "color")
    private String color;
    @SerializedName(value = "duration")
    private int duration;
    @SerializedName(value = "lines")
    private List<String> lines;

}