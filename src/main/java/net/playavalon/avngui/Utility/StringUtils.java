package net.playavalon.avngui.Utility;

import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static String colorize (String s) {

        return s == null ? null : ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String fullColor(String s) {

        StringBuilder sb = new StringBuilder();

        String[] strs = s.split("(?=(\\{#[a-fA-F0-9]*}))");

        Pattern pat = Pattern.compile("(\\{(#[a-fA-F0-9]*)})(.*)");
        Matcher matcher;

        for (String str : strs) {
            matcher = pat.matcher(str);
            if (matcher.find()) {
                sb.append(ChatColor.of(matcher.group(2)));
                sb.append(matcher.group(3));
            } else {
                sb.append(str);
            }
        }

        return colorize(sb.toString());
    }

    public static String removeFullColor(String s) {

        StringBuilder sb = new StringBuilder();

        String[] strs = s.split("(?=(\\{#[a-fA-F0-9]*}))");

        Pattern pat = Pattern.compile("(\\{(#[a-fA-F0-9]*)})(.*)");
        Matcher matcher;

        for (String str : strs) {
            matcher = pat.matcher(str);
            if (matcher.find()) {
                sb.append(matcher.group(3));
            } else {
                sb.append(str);
            }
        }

        return sb.toString();
    }

    public static String getCleanString(String str) {

        str = str.replaceAll("[!?,'\"():;/<>]", "");
        return str.replaceAll("[&§][0-9a-f]", "");

    }

    public static ArrayList<String> stringToLore(String str, int wordsPerLine) {
        ArrayList<String> lore = new ArrayList<>();
        if (str == null || str.equals("")) {
            return lore;
        }

        List<String> words = Arrays.asList(str.split(" "));
        Iterator wordIt = words.iterator();
        String color;

        String storedWord = "";

        Pattern pattern = Pattern.compile("([&§][0-9a-f])");
        Matcher matcher = pattern.matcher(words.get(0));

        if (words.get(0).matches("([&§][0-9a-f]+.*)")) {

            if (matcher.find()) {
                color = matcher.group(1);
            } else {
                color = "&7";
            }

        } else {
            color = "&7";
        }

        while (wordIt.hasNext()) {
            StringBuilder line = new StringBuilder();

            for (int i = 0; i < wordsPerLine; i++) {

                if (!wordIt.hasNext()) break;
                if (!storedWord.equals("")) {
                    if (storedWord.matches("([&§][0-9a-f]+.*)")) {
                        matcher = pattern.matcher(storedWord);

                        if (matcher.find()) {
                            color = matcher.group(1);
                        } else {
                            color = "&7";
                        }

                    } else {
                        color = "&7";
                    }
                    line.append(storedWord);
                    storedWord = "";
                    continue;
                }
                String next = (String) wordIt.next();
                if (next.contains("/n")) { // If the word includes a "/n" line indicator...
                    next = next.replace("/n", "");
                    storedWord = next;

                    break;
                }

                if (!line.toString().equals("")) line.append(" ");
                line.append(next);

            }

            if (!hasColor(line.toString())) {
                lore.add(colorize(color + line));
            } else {
                lore.add(colorize(line.toString()));
            }

        }

        if (!lore.get(lore.size()-1).equals("")) lore.add("");

        return lore;
    }

    public static ArrayList<String> stringToLore(String str) {
        ArrayList<String> lore = new ArrayList<>();
        if (str == null || str.equals("")) {
            return lore;
        }

        List<String> words = Arrays.asList(str.split(" "));
        Iterator wordIt = words.iterator();
        String color;

        String storedWord = "";

        Pattern pattern = Pattern.compile("([&§][0-9a-f])");
        Matcher matcher = pattern.matcher(words.get(0));

        if (words.get(0).matches("([&§][0-9a-f]+.*)")) {

            if (matcher.find()) {
                color = matcher.group(1);
            } else {
                color = "&7";
            }

        } else {
            color = "&7";
        }

        while (wordIt.hasNext()) {
            String line = "";

            for (int i = 0; i < 100; i++) {

                if (!wordIt.hasNext()) break;
                if (!storedWord.equals("")) {
                    if (storedWord.matches("([&§][0-9a-f]+.*)")) {
                        matcher = pattern.matcher(storedWord);

                        if (matcher.find()) {
                            color = matcher.group(1);
                        } else {
                            color = "&7";
                        }

                    } else {
                        color = "&7";
                    }
                    line += storedWord;
                    storedWord = "";
                    continue;
                }
                String next = (String) wordIt.next();
                if (next.contains("/n")) { // If the word includes a "/n" line indicator...
                    next = next.replace("/n", "");
                    storedWord = next;

                    break;
                }

                if (!line.equals("")) line += " ";
                line += next;

            }

            if (!hasColor(line) && !line.isEmpty()) {
                lore.add(colorize(color + line));
            } else {
                lore.add(colorize(line));
            }

        }

        if (!lore.get(lore.size()-1).equals("")) lore.add("");

        return lore;
    }

    public static boolean hasColor(String str) {
        return str.matches("([&§][0-9a-f]+.*)");
    }
}
