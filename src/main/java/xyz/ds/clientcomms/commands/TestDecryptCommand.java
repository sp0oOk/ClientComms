package xyz.ds.clientcomms.commands;

import lombok.SneakyThrows;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TestDecryptCommand implements CommandExecutor {

    @Override @SneakyThrows
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.isOp()) return false;


        return false;
    }
}
