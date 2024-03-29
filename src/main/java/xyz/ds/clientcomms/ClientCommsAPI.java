package xyz.ds.clientcomms;

import com.google.common.collect.Maps;
import lombok.Getter;
import org.bukkit.entity.Player;
import xyz.ds.clientcomms.manager.PacketManager;
import xyz.ds.clientcomms.messages.QueuedMessage;
import xyz.ds.clientcomms.packets.ClientPacket;
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
    public static String sessionKey = "UHF9waNgFRUxz658NIpOj55WQgGrwmIQhtPep9gqW5ECmaHrck2jwhQV6tYGw6s8hQ+pRXaRlBnyImKWLNSWvfhyHnofFBt9h8qJE0XsFhSFb4mquE9xH8stsKPTMAgFy+DZ/ZiokgtBCf5dQHJMmTmgRgtl3XWAfxvGf53Pd6qMKZ/yR4/CVjlCkZE962XDNUhaGrMZB6056ZSiAw3mKEbkqfaFsWZ49jX1n6sADrLJwmga1hUL2dZxE/ZkN3xdIvEY9Atp23S9kQkqPyXZ3Unxz1YGSkikzHVNcHQceHsD1o9bnut+UvFDvn5vSW34bLj8Quuha5DgevRu/rrBvw==";
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
