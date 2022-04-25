package xyz.ds.clientcomms.tasks;

import com.google.common.collect.Sets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.ds.clientcomms.ClientCommsAPI;
import xyz.ds.clientcomms.struct.ChannelParticipator;
import xyz.ds.clientcomms.struct.QueuedMessage;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProcessOutgoingMessagesTask extends BukkitRunnable {

    @Override
    public void run() {
        final Set<ChannelParticipator> removalQueue = Sets.newHashSet();
        for(Map.Entry<ChannelParticipator, List<QueuedMessage>> entry : ClientCommsAPI.getQueuedMessages().entrySet()) {
            final ChannelParticipator participator = entry.getKey();
            final Player player = participator.getPlayer();
            final List<QueuedMessage> queuedMessages = entry.getValue();
            for(int i = queuedMessages.size() - 1; i >= 0; i--) {
                final QueuedMessage message = queuedMessages.get(i);
                final byte[] data = (message.getData() != null) ? message.getData() : message.getChannelMessage().toBytes();
                final String channel = message.getChannel();
                final ByteBuf buf = Unpooled.buffer();
                buf.writeBytes(data);
                buf.writerIndex(data.length);
                final PacketPlayOutCustomPayload packetPlayOutCustomPayload = new PacketPlayOutCustomPayload(channel, new PacketDataSerializer(buf));
                final EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();
                entityPlayer.playerConnection = new PlayerConnection(entityPlayer.server, entityPlayer.playerConnection.networkManager, entityPlayer);
                entityPlayer.playerConnection.sendPacket(packetPlayOutCustomPayload);
                removalQueue.add(participator);
            }
            removalQueue.forEach(removalQueue::remove);
        }
    }
}
