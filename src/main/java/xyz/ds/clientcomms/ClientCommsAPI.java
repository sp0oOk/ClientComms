package xyz.ds.clientcomms;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.Getter;
import org.bukkit.entity.Player;
import xyz.ds.clientcomms.manager.PacketManager;
import xyz.ds.clientcomms.messages.QueuedMessage;
import xyz.ds.clientcomms.packets.ClientPacket;
import xyz.ds.clientcomms.packets.out.SEmotesResponsePacket;
import xyz.ds.clientcomms.packets.out.SPermissionResponsePacket;
import xyz.ds.clientcomms.utils.ColorUtils;
import xyz.ds.clientcomms.utils.DecryptUtils;
import xyz.ds.clientcomms.utils.RSACrypt;

import java.util.*;

public class ClientCommsAPI {

    public static final List<String> HANDSHAKE_MESSAGE =  ColorUtils.translateList(ClientComms.getInstance().getConfig().getStringList("messages.handshake"));
    @Getter
    private static final LinkedList<QueuedMessage> queuedMessages = new LinkedList<>();
    @Getter
    private static final Map<UUID, String> registeredPlayers = Maps.newHashMap();
    public static String CHANNEL = "cosmic";
    public static DecryptUtils decrypt;
    public static String sessionKey = "13c9esdEOtLC+JPE+GaWFcF86+MqlLoLaY6yRedTwUp3Fx1NFWDwPnBYjCb3Y83yPV8rujPeaCkARIabBx5Ca+J7FstlvlUN2+j5WpCSFUFDvqjw2KG0k6/WoABk1aHQi0n/sypTUB6ZkG2JnyuaNTQh2ebhc5yMIdeAViwIcyW4t3I5p7+VYjcq9YBX4CfKRJjSZaVyJIFBHouCndqlNicc4PvKcmTnYwj66h6rI4t5Iq97ticrbvXdZifre9IGcVLVhOIpCtiXoLBA06t6beLFhOF8OatYVu86CXvT0TBUaidS2NO+l7Qqk4m9k2fDENna2zpOe237XLBm7uKviA==";
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

    /**
     * Adds Message to "Queue" List to be sent to the Player
     * Use {@link PacketManager#sendPacket(Player, ClientPacket)} instead of this
     * to send packets, not this!
     * @param message QueuedMessage Object
     */

    public static void addQueuedMessage(QueuedMessage message) {
        queuedMessages.add(message);
    }

    /**
     * PacketManager for sending and receiving packets via
     * C -> S and S -> C with ease
     * @return {@link PacketManager} PacketManager instance
     */

    public static PacketManager getPacketManager() {
        if (packetManager == null) {
            packetManager = new PacketManager();
        }
        return packetManager;
    }

    /**
     * Force Send Default Client Permissions
     * Allows for the use of Roam, Printer, Breadcrumbs etc.
     * @param player Player Object
     */

    public static void sendDefaultPermissions(Player player) {
        final PacketManager packetManager = getPacketManager();
        packetManager.sendPacket(player, new SPermissionResponsePacket("freeroam", true));
        packetManager.sendPacket(player, new SPermissionResponsePacket("printer", true));
        packetManager.sendPacket(player, new SPermissionResponsePacket("breadcrumbs", true));
        packetManager.sendPacket(player, new SPermissionResponsePacket("op", player.hasPermission("ranks.operator")));
        packetManager.sendPacket(player, new SPermissionResponsePacket("interstellar", player.hasPermission("ranks.interstellar")));
        packetManager.sendPacket(player, new SPermissionResponsePacket("banteam", player.hasPermission("ranks.banteam")));
        //packetManager.sendPacket(player, new SEmotesResponsePacket(Sets.newHashSet("default", "thinking", "infinite_dab", "hype", "take_the_l", "shrug", "twerk", "orange_justice", "electro_shuffle", "poltergeist", "gangnam_style", "star_power", "boneless", "stuffed", "jig", "flirt", "clickclack", "corona", "best_mates", "spooky_skeleton", "turkey_vibes", "salty")));
    }

}
