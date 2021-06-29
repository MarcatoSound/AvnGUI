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

    private AvnAPI api;

    @Override
    public void onEnable() {
        plugin = this;

        // Inventory API stuff
        /*api = new AvnAPI(this);

        Window gui = new Window("testui", 18, "&1Test UI");

        Button button = new Button("testbutton", Material.ACACIA_BUTTON, "&6A Casual Test");
        button.addCommand("say This is a test!");
        gui.addButton(0, button);

        button = new Button("actiontest", Material.WOODEN_AXE, "&dInform Console and Player");
        button.addAction("inform2", (event) -> {
            Player player = (Player)event.getWhoClicked();
            player.sendMessage(StringUtils.fullColor("&2[Debug] &aAction fired!"));
            System.out.println("&2[Debug] &aAction fired!");
        });
        gui.addButton(17, button);


        WindowGroup group = new WindowGroup("testgroup");

        gui = new Window("testgroupui", 36, "&2Test Group UI", group);

        button = new Button("testbutton2", Material.ACACIA_BUTTON, "&eAnother Casual Test");
        button.addCommand("say This is ANOTHER test!");
        gui.addButton(13, button);

        button = new ButtonNext("testnext");
        gui.addButton(35, button);

        button = new ButtonPrevious("testprev");
        gui.addButton(27, button);

        group.addWindow(gui);


        gui = new Window("testgroupui2", 36, "&2Test Group UI 2", group);

        button = new ButtonNext("testnext2");
        gui.addButton(35, button);

        button = new ButtonPrevious("testprev2");
        gui.addButton(27, button);

        group.addWindow(gui);*/

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
