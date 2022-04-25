package xyz.ds.clientcomms.struct;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class HitSplash implements Serializable {
    long time;
    @SerializedName(value = "entityId")
    private int entityId;
    @SerializedName(value = "text")
    private String text;
    @SerializedName(value = "type")
    @Since(value = 1.1)
    private String type;
    @SerializedName(value = "x")
    @Since(value = 1.2)
    private double x;
    @SerializedName(value = "y")
    @Since(value = 1.2)
    private double y;
    @SerializedName(value = "z")
    @Since(value = 1.2)
    private double z;
}