package net.playavalon.avngui.GUI;

import java.util.HashMap;

public class WindowGroupManager {

    private static final HashMap<String, WindowGroup> groups = new HashMap<>();

    protected static void registerGroup(WindowGroup group) {
        groups.put(group.getName(), group);
    }

    public static WindowGroup getGroup(String namespace) {
        return groups.get(namespace);
    }
    protected static void put(WindowGroup group) {
        groups.put(group.getName(), group);
    }

}
