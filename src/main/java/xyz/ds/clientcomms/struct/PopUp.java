package xyz.ds.clientcomms.struct;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.ds.clientcomms.enums.PopupPosition;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class PopUp
        implements Serializable {
    @SerializedName(value = "x")
    private int x;
    @SerializedName(value = "y")
    private int y;
    @SerializedName(value = "imageURL")
    private String imageURL;
    @SerializedName(value = "hoverURL")
    private String hoverURL;
    @SerializedName(value = "URL")
    private String URL;
    @SerializedName(value = "duration")
    private int duration;
    @SerializedName(value = "started")
    private long started;
    @SerializedName(value = "width")
    private int width;
    @SerializedName(value = "height")
    private int height;
    @SerializedName(value = "position")
    private PopupPosition position;

}