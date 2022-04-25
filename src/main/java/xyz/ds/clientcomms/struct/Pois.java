package xyz.ds.clientcomms.struct;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Pois implements Serializable {

    @SerializedName(value = "name")
    public final String name;
    @SerializedName(value = "nameColor")
    public final int nameColor;
    @SerializedName(value = "nameShadowColor")
    public final int nameShadowColor;
    @SerializedName(value = "nameShadow")
    public final boolean nameShadow;
    @SerializedName(value = "nameScale")
    public final double nameScale;
    @SerializedName(value = "itemName")
    public final String itemName;
    @SerializedName(value = "itemAmount")
    public final int itemAmount;
    @SerializedName(value = "itemMeta")
    public final int itemMeta;
    @SerializedName(value = "itemColor")
    public final int itemColor;
    @SerializedName(value = "itemScale")
    public final double itemScale;
    @SerializedName(value = "minX")
    public final double minX;
    @SerializedName(value = "minY")
    public final double minY;
    @SerializedName(value = "minZ")
    public final double minZ;
    @SerializedName(value = "maxX")
    public final double maxX;
    @SerializedName(value = "maxY")
    public final double maxY;
    @SerializedName(value = "maxZ")
    public final double maxZ;
    @SerializedName(value = "borderThickness")
    public final double borderThickness;
    @SerializedName(value = "borderColor")
    public final int borderColor;
    @SerializedName(value = "borderAccentColor")
    public final int borderAccentColor;
    @SerializedName(value = "item")
    public String item;

}
