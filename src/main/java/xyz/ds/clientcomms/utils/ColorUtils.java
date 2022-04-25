package xyz.ds.clientcomms.utils;

import com.google.common.collect.Lists;
import org.bukkit.ChatColor;

import java.util.List;

public class ColorUtils {

    public static String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static List<String> translateList(List<String> list) {
        final List<String> r = Lists.newArrayList();
        list.forEach(s -> r.add(translate(s)));
        return r;
    }

}
