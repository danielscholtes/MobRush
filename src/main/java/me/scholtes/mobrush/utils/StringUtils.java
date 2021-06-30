package me.scholtes.mobrush.utils;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {

    /**
     * Colors a string
     *
     * @param s The string to color
     * @return The colored string
     */
    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    /**
     * Colors a list of string
     *
     * @param stringList The list to color
     * @return The colored list
     */
    public static List<String> color(List<String> stringList) {
        return stringList.stream()
                        .map(StringUtils::color)
                        .collect(Collectors.toList());
    }

}
