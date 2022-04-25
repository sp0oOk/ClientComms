package xyz.ds.clientcomms.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import xyz.ds.clientcomms.ClientCommsAPI;
import xyz.ds.clientcomms.messages.QueuedMessage;

import java.util.LinkedList;

public class ProcessOutgoingMessagesTask extends BukkitRunnable {

    @Override
    public void run() {
        QueuedMessage message;
        LinkedList<QueuedMessage> messages = ClientCommsAPI.getQueuedMessages();
        while (!messages.isEmpty()) {
            message = messages.pop();
            message.send();
        }
    }
}
