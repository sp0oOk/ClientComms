package xyz.ds.clientcomms.manager;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.entity.Player;
import xyz.ds.clientcomms.ClientCommsAPI;
import xyz.ds.clientcomms.messages.QueuedMessage;
import xyz.ds.clientcomms.packets.ClientPacket;
import xyz.ds.clientcomms.packets.CosmicPacket;
import xyz.ds.clientcomms.packets.in.*;
import xyz.ds.clientcomms.packets.out.*;
import xyz.ds.clientcomms.utils.GsonAdapter;

public class PacketManager {

    private final BiMap<Short, Class<? extends ClientPacket>> packets = HashBiMap.create();
    private final Gson gson = new GsonBuilder().disableHtmlEscaping().setVersion(1.3).registerTypeHierarchyAdapter(byte[].class, new GsonAdapter()).create();
    private short counter = 0;

    public PacketManager() {
        register(CChunkDataRequestPacket.class);
        register(CClaimCoordPacket.class);
        register(CCooldownRequestPacket.class);
        counter++;
        register(CFactionInfoRequestPacket.class);
        counter++;
        register(CHandshakePacket.class);
        register(CInfoPacket.class);
        register(CStatusPacket.class);
        register(CModsPacket.class);
        register(CPermissionRequestPacket.class);
        register(CPingAlliesPacket.class);
        register(CPlayerFactionRequestPacket.class);
        register(CResolutionPacket.class);
        register(CSetChannelPacket.class);
        register(CStartEmotePacket.class);
        register(CTimeRequestPacket.class);
        register(CVoicePacket.class);
        register(CWorldBorderRequestPacket.class);
        register(SChunkDataPacket.class);
        register(SClearPromosPacket.class);
        register(SCooldownPacket.class);
        register(SCreateZonePacket.class);
        register(SDefaultSkinnedPlayersPacket.class);
        register(SEmotesResponsePacket.class);
        register(SEnchantListPacket.class);
        register(SEventPacket.class);
        register(SFactionInfoResponsePacket.class);
        register(SFactionListResponsePacket.class);
        register(SFeatureSetPacket.class);
        register(SFocusedPlayerPacket.class);
        register(SItemCooldownsListPacket.class);
        register(SKeepAlive.class);
        register(SNotificationPacket.class);
        register(SPermissionResponsePacket.class);
        register(SPingAlliesPacket.class);
        register(SPlayerFactionResponsePacket.class);
        register(SPlayerHeadersPacket.class);
        register(SPlayerOutlineColor.class);
        register(SPopUpPacket.class);
        register(SRoyalePacket.class);
        register(SSetEntitySizePacket.class);
        register(SSetTeamPacket.class);
        register(SSetWorldNamePacket.class);
        register(SShowEmotePacket.class);
        register(STimeResponsePacket.class);
        register(SVoicePacket.class);
        register(SWorldBorderResponsePacket.class);
        register(SHitSplatPacket.class);
        register(SHideNametags.class);
        register(CWorldNameRequestPacket.class);
        counter++;
        register(SShowXPBarPacket.class);
        register(SAddXPPacket.class);
        register(SXPTextPacket.class);
        register(SSetXPPacket.class);
        register(SFlagBossPacket.class);
        register(SHighlightBlockPacket.class);
        register(SRevealBlockPacket.class);
        register(SSetMapImagePacket.class);
        register(SSetEntityProperties.class);
        register(CMobHelperStatusPacket.class);
        counter++;
        register(SSetBlockProperties.class);
        register(SScoreboardPacket.class);
        register(SPollSendPacket.class);
        register(CPollResponsePacket.class);
        register(SSetWaypointPacket.class);
        register(SPurchaseFeedPacket.class);
        register(STooltipPacket.class);
        register(SClearTooltipPacket.class);
        register(SSetHealthBarPacket.class);
        register(SColorsPacket.class);
        register(SBarAPIPacket.class);

    }

    /**
     * Registers Packets and adds them to a map for
     * referencing and obtaining a certain packet from the packetID
     * @param packet Packet Object
     */

    public void register(Class<? extends ClientPacket> packet) {
        this.counter = (short) (this.counter + 1);
        this.packets.put(this.counter, packet);
    }

    /**
     * Obtains the packetID for a passed {@link ClientPacket} object
     * used for referencing.
     * @param packet Packet Object
     * @return {@link Short} packetID (identifier)
     */

    public short getPacketId(ClientPacket packet) {
        return packets.inverse().get(packet.getClass());
    }

    /**
     * Obtains the packetID for a passed class extending {@link ClientPacket}
     * used for referencing
     * @param klass Class extending {@link ClientPacket}
     * @return {@link Short} packetID (identifier)
     */

    public short getPacketId(Class<? extends ClientPacket> klass) {
        return packets.inverse().get(klass);
    }

    /**
     * Constructs a {@link CosmicPacket} object off of the passed {@link ClientPacket} and
     * proceeds to convert it to JSON, than "chunks" the packet (to prevent rate-limits/data-loss)
     * and passes it to a {@link QueuedMessage} object where it is converted/encrypted to bytes and queued,
     * later sent to the player.
     * @param player Player Object
     * @param packet Packet Object
     */

    public void sendPacket(Player player, ClientPacket packet) {

        CosmicPacket data = new CosmicPacket();

        data.packetID = getPacketId(packet);
        data.protocol = packet.getProtocolVersion();
        data.content = gson.toJson(packet);

        if (data.content.length() > 3000) {
            String[] chunks = data.content.split("(?<=\\G.{3000})");
            for (int i = 0; i < chunks.length; i++) {
                CosmicPacket sub = new CosmicPacket();
                sub.packetID = data.packetID;
                sub.protocol = data.protocol;
                sub.content = chunks[i];
                sub.splitNext = i != chunks.length - 1;

                QueuedMessage qm = new QueuedMessage(player, sub);
                ClientCommsAPI.addQueuedMessage(qm);
            }

        } else {
            QueuedMessage qm = new QueuedMessage(player, data);
            ClientCommsAPI.addQueuedMessage(qm);
        }

    }

    /**
     * Obtains a Custom Packet Class from a provided packetID (identifier)
     * @param packetID packetID {identifier}
     * @return Class extending {@link ClientPacket}
     */

    public Class<? extends ClientPacket> fromId(short packetID) {
        return packets.get(packetID);
    }
}
