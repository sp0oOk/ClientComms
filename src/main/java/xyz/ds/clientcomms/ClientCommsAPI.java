package xyz.ds.clientcomms;

import lombok.Getter;
import xyz.ds.clientcomms.manager.PacketManager;
import xyz.ds.clientcomms.messages.QueuedMessage;
import xyz.ds.clientcomms.utils.DecryptUtils;
import xyz.ds.clientcomms.utils.RSACrypt;

import java.util.Base64;
import java.util.LinkedList;

public class ClientCommsAPI {

    @Getter
    private static final LinkedList<QueuedMessage> queuedMessages = new LinkedList<>();
    public static String CHANNEL = "cosmic";
    public static DecryptUtils decrypt;
    public static String sessionKey =
            "13c9esdEOtLC+JPE+GaWFcF86+MqlLoLaY6yRedTwUp3Fx1NFWDwPnBYjCb3Y83yPV8rujPeaCkARIabBx5Ca+J7FstlvlUN2+j5WpCSFUFDvqjw2KG0k6/WoABk1aHQi0n/sypTUB6ZkG2JnyuaNTQh2ebhc5yMIdeAViwIcyW4t3I5p7+VYjcq9YBX4CfKRJjSZaVyJIFBHouCndqlNicc4PvKcmTnYwj66h6rI4t5Iq97ticrbvXdZifre9IGcVLVhOIpCtiXoLBA06t6beLFhOF8OatYVu86CXvT0TBUaidS2NO+l7Qqk4m9k2fDENna2zpOe237XLBm7uKviA==";
    private static PacketManager packetManager;

    static {
        byte[] b = new byte[0];
        try {
            b = RSACrypt.a(Base64.getDecoder().decode(sessionKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        decrypt = new DecryptUtils(b, true);
    }

    public static void addQueuedMessage(QueuedMessage message) {
        queuedMessages.add(message);
    }

    public static PacketManager getPacketManager() {
        if (packetManager == null) {
            packetManager = new PacketManager();
        }
        return packetManager;
    }

}
