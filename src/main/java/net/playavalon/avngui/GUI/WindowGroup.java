package net.playavalon.avngui.GUI;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class WindowGroup {

    private final ArrayList<Window> windows;
    private final HashMap<String, Window> windowsByName;
    private String namespace;

    private HashMap<Player, Integer> playerPosition;


    public WindowGroup() {
        windows = new ArrayList<>();
        windowsByName = new HashMap<>();

        WindowGroupManager.put(this);
        playerPosition = new HashMap<>();
    }

    public WindowGroup(String namespace) {
        windows = new ArrayList<>();
        windowsByName = new HashMap<>();
        this.namespace = namespace;

        WindowGroupManager.put(this);
        playerPosition = new HashMap<>();
    }


    public final void addWindow(Window window) {
        windows.add(window);
        windowsByName.put(window.getName(), window);
    }

    public final void removeWindow(String namespace) {
        Window window = windowsByName.get(namespace);

        if (window == null) {
            // TODO Error message reporting non-existent GUI Window
            return;
        }

        windowsByName.remove(namespace);
        windows.remove(window);
    }


    public final String getName() {
        return namespace;
    }

    public final ArrayList<Window> getWindows() {
        return windows;
    }

    public final Window getWindow(String namespace) {
        return windowsByName.get(namespace);
    }


    public final void open(Player player) {
        if (windows.isEmpty()) {
            // TODO Debug message for a group being opened but having no windows.
            return;
        }
        Window window = windows.get(0);
        window.open(player);
        playerPosition.put(player, 0);
    }

    public final void next(Player player) {
        int currentWindow = playerPosition.get(player);
        int nextWindow = currentWindow+1;
        if (nextWindow >= windows.size()) return;

        playerPosition.put(player, nextWindow);
        Window window = windows.get(nextWindow);
        window.open(player);
    }
    public final void previous(Player player) {
        int currentWindow = playerPosition.get(player);
        int prevWindow = currentWindow-1;
        if (prevWindow < 0) return;

        playerPosition.put(player, prevWindow);
        Window window = windows.get(prevWindow);
        window.open(player);
    }

}
