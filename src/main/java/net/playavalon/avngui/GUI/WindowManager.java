package net.playavalon.avngui.GUI;

import java.util.HashMap;

public class WindowManager {

    private static final HashMap<String, Window> windows = new HashMap<>();

    protected static void registerWindow(Window window) {
        windows.put(window.getName(), window);
    }

    public static Window getWindow(String namespace) {
        return windows.get(namespace);
    }

    protected static void put(Window window) {
        windows.put(window.getName(), window);
    }

}
