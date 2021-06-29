package net.playavalon.avngui;

import net.playavalon.avngui.GUI.Actions.ButtonAction;
import net.playavalon.avngui.GUI.Buttons.Button;
import net.playavalon.avngui.GUI.Buttons.ButtonNext;
import net.playavalon.avngui.GUI.Buttons.ButtonPrevious;
import net.playavalon.avngui.GUI.WindowGroup;
import net.playavalon.avngui.GUI.Window;
import net.playavalon.avngui.Utility.StringUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class AvnGUI extends JavaPlugin {

    public static AvnGUI plugin;

    public static boolean debug = true;

    private AvnAPI api;

    @Override
    public void onEnable() {
        plugin = this;

    }

    @Override
    public void onDisable() {

    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player;

        /*switch (command.getName().toLowerCase()) {
            case "uitest":
                if (!(sender instanceof Player)) return false;
                player = (Player)sender;
                api.openGUI(player, "testui");
                break;
            case "grouptest":
                if (!(sender instanceof Player)) return false;
                player = (Player)sender;
                api.openGUIGroup(player, "testgroup");
                break;
        }*/

        return true;
    }
}
