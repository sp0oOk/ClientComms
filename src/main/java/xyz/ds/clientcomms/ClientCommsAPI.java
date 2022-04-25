package xyz.ds.clientcomms;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import xyz.ds.clientcomms.struct.ChannelParticipator;
import xyz.ds.clientcomms.struct.QueuedMessage;

import java.util.List;
import java.util.Map;

public class ClientCommsAPI {

    private static final Map<ChannelParticipator, List<QueuedMessage>> queuedMessages = Maps.newHashMap();

    public static Map<ChannelParticipator, List<QueuedMessage>> getQueuedMessages() {
        return queuedMessages;
    }

    public static void addQueuedMessage(ChannelParticipator participator, QueuedMessage message) {
        List<QueuedMessage> messages = Lists.newArrayList();
        if(queuedMessages.containsKey(participator)) messages = queuedMessages.get(participator);
        messages.add(message);
        if(!queuedMessages.containsKey(participator)) queuedMessages.put(participator, messages);
    }

}
