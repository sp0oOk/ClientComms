package xyz.ds.clientcomms.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.ds.clientcomms.ClientCommsAPI;

public class CommandComms implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(new String[] {
                "",
                ChatColor.AQUA.toString() + ChatColor.BOLD + "Client Comms " + ChatColor.WHITE + "version 1.0",
                ChatColor.WHITE + "- " + ChatColor.DARK_AQUA + "Registered Player(s): " + ChatColor.GRAY + ClientCommsAPI.getRegisteredPlayers().size(),
                ChatColor.WHITE + "- " + ChatColor.DARK_AQUA + "Queued Packet(s): " + ChatColor.GRAY + ClientCommsAPI.getQueuedMessages().size(),
                ""
        });
        return false;
    }
}
