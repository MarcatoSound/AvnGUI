package net.playavalon.avngui.GUI;

import net.playavalon.avngui.GUI.Actions.Action;
import net.playavalon.avngui.GUI.Buttons.Button;
import net.playavalon.avngui.Utility.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.playavalon.avngui.AvnAPI.plugin;
import static net.playavalon.avngui.AvnGUI.debug;

public class Window implements Listener {

    private final String namespace;
    private final int size;
    private String display;
    private boolean cancelClick = true;
    private boolean cancelDrag = true;
    private boolean allowPlayerInventoryClick;

    private WindowGroup group;

    private final HashMap<Player, GUIInventory> inventories;
    private final HashMap<Integer, Button> buttons;

    private final HashMap<String, Action<InventoryOpenEvent>> openActions;
    private final HashMap<String, Action<InventoryCloseEvent>> closeActions;
    private final HashMap<String, Action<InventoryClickEvent>> clickActions;

    private final List<Player> viewers;

    /**
     * Create a GUI window belonging to a window group
     * @param namespace The namespaced ID of this GUI window
     * @param size How many slots this window has (multiple of 9)
     * @param displayname The label that appears on the top of the window
     */
    public Window(String namespace, int size, String displayname) {
        this.namespace = namespace;
        this.size = size;
        this.display = StringUtils.fullColor(displayname);

        inventories = new HashMap<>();
        buttons = new HashMap<>();
        openActions = new HashMap<>();
        closeActions = new HashMap<>();
        clickActions = new HashMap<>();

        viewers = new ArrayList<>();

        Bukkit.getPluginManager().registerEvents(this, plugin);

        // Check if there's already a window with this namespace and, if so, unregister it.
        Window oldWindow = WindowManager.getWindowSilent(namespace);
        if (oldWindow != null) oldWindow.unregister();

        WindowManager.put(this);
        if (debug) System.out.println("Registered GUI Window: " + namespace);
    }

    /**
     * Create a GUI window belonging to a window group
     * @param namespace The namespaced ID of this GUI window
     * @param size How many slots this window has (multiple of 9)
     * @param displayname The label that appears on the top of the window
     * @param group The group this window belongs to
     */
    public Window(String namespace, int size, String displayname, WindowGroup group) {
        this.namespace = namespace;
        this.size = size;
        this.display = StringUtils.fullColor(displayname);

        inventories = new HashMap<>();
        buttons = new HashMap<>();
        openActions = new HashMap<>();
        closeActions = new HashMap<>();
        clickActions = new HashMap<>();
        this.group = group;

        viewers = new ArrayList<>();

        Bukkit.getPluginManager().registerEvents(this, plugin);

        WindowManager.put(this);
        if (debug) System.out.println("Registered GUI Window: " + namespace);
        group.addWindow(this);
    }


    /**
     * Sets whether or not to cancel inventory clicks when the player clicks
     * @param cancel "true" by default, false to allow inventory clicks
     */
    public final void setCancelClick(boolean cancel) {
        this.cancelClick = cancel;
    }

    /**
     * Sets whether or not to cancel inventory drags when the player drags items over slots
     * @param cancel "true" by default, false to allow inventory drags
     */
    public final void setCancelDrag(boolean cancel) {
        this.cancelDrag = cancel;
    }

    /**
     * Sets whether to allow players to click their own inventory while in this GUI.
     * @param allow "false" by default, true to allow player inventory clicks.
     */
    public final void setAllowPlayerInventoryClick(boolean allow) {
        this.allowPlayerInventoryClick = allow;
    }


    public final GUIInventory getPlayersGui(Player player) {
        return inventories.get(player);
    }

    public final List<Player> getViewers() { return viewers; }


    /**
     * Add a button to this GUI window
     * @param slot The inventory slot (starting from 0) to place this button
     * @param button The button to place
     */
    public final void addButton(int slot, Button button) {
        if (slot >= size) {
            // TODO Error message of slot being higher than inventory slots available
            return;
        }

        buttons.put(slot, button);
    }

    /**
     * Modify a button already assigned to a slot.
     * @param slot The slot the button we want is in.
     * @return The button we're editing.
     */
    public final Button editPlayersButton(Player player, int slot) {

        GUIInventory inv = inventories.get(player);

        Button baseButton = inv.getButtons().get(slot);
        if (baseButton == null) return null;

        Button button = new Button(baseButton);
        inv.setButton(slot, button);

        return button;

    }

    public final void removePlayersButton(Player player, int slot) {

        GUIInventory inv = inventories.get(player);

        inv.removeButton(slot);

    }

    public final HashMap<Integer, Button> getButtons() {
        return buttons;
    }

    /**
     * Remove a button from this GUI window.
     * @param slot The inventory slot location of the button we're removing.
     */
    public final void removeButton(int slot) {
        buttons.remove(slot);
    }

    /**
     * Set the label at the top of the GUI window.
     * @param label The label of this GUI window. Works with colour codes.
     */
    public final void setLabel(String label) {
        display = StringUtils.fullColor(label);
    }

    /**
     * Get the label of this GUI window
     * @return The coloured label of the GUI window
     */
    public final String getLabel() {
        return display;
    }


    /**
     * Get the namespaced ID of this GUI window
     * @return The namespaced ID of this GUI window
     */
    public final String getName() {
        return namespace;
    }

    /**
     * Get the inventory size of this GUI window
     * @return The GUI window size
     */
    public final int getSize() {
        return size;
    }


    /**
     * Add a code action to execute when this inventory is opened.
     * @see Action
     * @param id A namespaced ID to uniquely identify this action
     * @param action A block of code to execute, express via lambda with '(event) -> { [Your code here] }'
     */
    public final void addOpenAction(String id, Action<InventoryOpenEvent> action) {
        openActions.put(id, action);
    }

    /**
     * Add a code action to execute when this inventory is closed.
     * @see Action
     * @param id A namespaced ID to uniquely identify this action
     * @param action A block of code to execute, express via lambda with '(event) -> { [Your code here] }'
     */
    public final void addCloseAction(String id, Action<InventoryCloseEvent> action) {
        closeActions.put(id, action);
    }

    /**
     * Add a code action to execute when this inventory is closed.
     * @see Action
     * @param id A namespaced ID to uniquely identify this action
     * @param action A block of code to execute, express via lambda with '(event) -> { [Your code here] }'
     */
    public final void addClickAction(String id, Action<InventoryClickEvent> action) {
        clickActions.put(id, action);
    }



    public final void updateButtons(Player player) {
        GUIInventory gui = inventories.get(player);
        Inventory inv = gui.getInv();
        for (Map.Entry<Integer, Button> pair : gui.getButtons().entrySet()) {
            inv.setItem(pair.getKey(), pair.getValue().getItem());
        }
    }

    /**
     * Opens this GUI window for a player
     * @param player The player to open this GUI window for
     */
    public final void open(Player player) {
        GUIInventory gui;
        if (inventories.containsKey(player)) {
            gui = inventories.get(player);
        } else {
            gui = new GUIInventory(this, Bukkit.createInventory(player, size, display));
            inventories.put(player, gui);
        }

        Inventory inv = gui.getInv();

        for (Map.Entry<Integer, Button> button : buttons.entrySet()) {
            inv.setItem(button.getKey(), button.getValue().getItem());
        }

        player.openInventory(inv);
    }

    /**
     * Move to the next GUI window if this window is part of a group
     * @param player The player we are switching windows of
     */
    public final void next(Player player) {
        if (group == null) return;
        group.next(player);
    }

    /**
     * Move to the previous GUI window if this window is part of a group
     * @param player The player we are switching windows of
     */
    public final void previous(Player player) {
        if (group == null) return;
        group.previous(player);
    }

    /**
     * Unregister this GUI's listeners.
     */
    public final void unregister() {
        HandlerList.unregisterAll(this);
    }


    @EventHandler
    protected void onButtonClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        GUIInventory gui = inventories.get(player);
        if (gui == null) return;
        if (event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT) {
            if (event.getInventory() == gui.getInv() && cancelClick) event.setCancelled(true);
        }
        if (event.getClickedInventory() != gui.getInv() && event.getInventory() != gui.getInv()) return;

        if (cancelClick) event.setCancelled(true);

        int slot = event.getRawSlot();
        Button button = gui.getButtons().get(slot);
        if (button == null) return;

        button.click(event, this);

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    protected void onClick(InventoryClickEvent event) {
        if (allowPlayerInventoryClick && event.getClickedInventory() instanceof PlayerInventory) return;
        Player player = (Player)event.getWhoClicked();
        GUIInventory gui = inventories.get(player);
        if (gui == null) return;
        if (event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT) {
            if (event.getInventory() == gui.getInv() && cancelClick) event.setCancelled(true);
        }
        if (event.getClickedInventory() != gui.getInv() && event.getInventory() != gui.getInv()) return;

        if (cancelClick) event.setCancelled(true);

        for (Action<InventoryClickEvent> action : clickActions.values()) {
            action.run(event);
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    protected void onDrag(InventoryDragEvent event) {
        Player player = (Player)event.getWhoClicked();
        GUIInventory gui = inventories.get(player);
        if (gui == null) return;
        if (event.getInventory() != gui.getInv()) return;

        if (cancelDrag) event.setCancelled(true);
    }

    @EventHandler
    protected void onOpen(InventoryOpenEvent event) {
        Player player = (Player)event.getPlayer();
        GUIInventory gui = inventories.get(player);
        if (gui == null) return;
        if (event.getInventory() != gui.getInv()) return;

        viewers.add(player);

        for (Action<InventoryOpenEvent> action : openActions.values()) {
            action.run(event);
        }

    }

    @EventHandler
    protected void onClose(InventoryCloseEvent event) {
        Player player = (Player)event.getPlayer();
        GUIInventory gui = inventories.get(player);
        if (gui == null) return;
        if (event.getInventory() != gui.getInv()) return;

        viewers.remove(player);

        for (Action<InventoryCloseEvent> action : closeActions.values()) {
            action.run(event);
        }

    }

}
