package net.playavalon.avngui;

import net.playavalon.avngui.GUI.WindowGroup;
import net.playavalon.avngui.GUI.Window;
import net.playavalon.avngui.GUI.WindowGroupManager;
import net.playavalon.avngui.GUI.WindowManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AvnAPI {

    public static JavaPlugin plugin;

    public AvnAPI(JavaPlugin plugin) {
        AvnAPI.plugin = plugin;
    }


    public void openGUI(Player player, String gui) {
        Window window = WindowManager.getWindow(gui);
        if (window == null) return;
        window.open(player);
    }

    public void openGUIGroup(Player player, String guiGroup) {
        WindowGroup group = WindowGroupManager.getGroup(guiGroup);
        if (group == null) return;
        group.open(player);
    }

}
