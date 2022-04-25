package xyz.ds.clientcomms.struct;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@ToString
public class ChannelMessage implements Serializable {

    private static final long serialVersionUID = -9284506830486503L;
    private String version;
    private String operation;
    private Map<String, Object> data;

    public ChannelMessage(String version, String operation, Map<String, Object> data) {
        this.version = version;
        this.operation = operation;
        this.data = data;
    }

    @SneakyThrows
    public byte[] toBytes() {
        final ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream();
        (new ObjectOutputStream(byteArrayOutputStream)).writeObject(this);
        return byteArrayOutputStream.toByteArray();
    }

}
