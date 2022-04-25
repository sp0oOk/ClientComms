package xyz.ds.clientcomms;

import xyz.ds.clientcomms.struct.ChannelMessage;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.*;
import java.util.*;

public class Utils {

    @SneakyThrows
    public static ChannelMessage safeReadObject(Class<?> type, List<Class<?>> safeClasses, long maxObjects, long maxBytes, InputStream in) {
        final InputStream inputStream = new FilterInputStream(in) {
            private long length = 0L;

            @Override @SneakyThrows
            public int read() {
                int value = super.read();
                if(value != -1) {
                    this.length++;
                    checkLength();
                }
                return value;
            }

            @SneakyThrows
            public int read(byte[] b, int off, int len) {
                int val = super.read(b, off, len);
                if (val > 0) {
                    checkLength();
                }
                return val;
            }

            private void checkLength() {
                if(length > maxBytes)
                    throw new SecurityException("Security violation: attempt to deserialize too many bytes from stream. Limit is " + maxBytes);
            }
        };
        final ObjectInputStream ois = new ObjectInputStream(inputStream) {
            private int objCount = 0;

            @SneakyThrows
            protected Object resolveObject(Object obj) {
                if (this.objCount++ > maxObjects)
                    throw new SecurityException("Security violation: attempt to deserialize too many objects from stream. Limit is " + maxObjects);
                return super.resolveObject(obj);
            }

            @SneakyThrows
            protected Class<?> resolveClass(ObjectStreamClass osc) {
                Class<?> clazz = super.resolveClass(osc);
                if (clazz.isArray() || clazz.equals(type) || clazz.equals(String.class) || Number.class.isAssignableFrom(clazz) || safeClasses.contains(clazz))
                    return clazz;
                throw new SecurityException("Security violation: attempt to deserialize unauthorized " + clazz);
            }
        };
        return (ChannelMessage) ois.readObject();
    }

    @SneakyThrows
    public static ChannelMessage fromBytes(byte[] bytes) {
        return safeReadObject(Data.class, safeClasses(), 20L, 100L, new ByteArrayInputStream(bytes));
    }

    public static List<Class<?>> safeClasses() {
        return Lists.newArrayList(
                BitSet.class,
                ArrayList.class,
                ChannelMessage.class,
                Map.class,
                HashMap.class,
                String.class,
                int.class,
                Integer.class,
                double.class,
                Double.class,
                float.class,
                Float.class,
                boolean.class,
                Boolean.class,
                UUID.class,
                byte.class,
                Byte.class
        );
    }

}
