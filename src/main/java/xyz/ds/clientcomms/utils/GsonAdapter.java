package xyz.ds.clientcomms.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Base64;

public class GsonAdapter implements JsonSerializer<byte[]>,
        JsonDeserializer<byte[]> {

    public byte[] deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        return Base64.getDecoder().decode(jsonElement.getAsString());
    }

    public JsonElement serialize(byte[] object, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(Base64.getEncoder().withoutPadding().encodeToString(object));
    }

}

