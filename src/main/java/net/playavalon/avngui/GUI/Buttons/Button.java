package net.playavalon.avngui.GUI.Buttons;

import net.kyori.adventure.text.Component;
import net.playavalon.avngui.GUI.Actions.Action;
import net.playavalon.avngui.GUI.Window;
import net.playavalon.avngui.Utility.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Button {

    private String id;
    private ItemStack item;

    private ArrayList<String> commands;
    private HashMap<String, Action<InventoryClickEvent>> actions;

    public Button(@NotNull String id, @NotNull Material mat, @NotNull String display) {
        this.id = id;
        this.item = new ItemStack(mat);

        ItemMeta meta = item.getItemMeta();
        meta.displayName(StringUtils.modernizeColorsComponent(display));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        commands = new ArrayList<>();
        actions = new HashMap<>();

        ButtonManager.put(this);
    }

    public Button(@NotNull String id, @NotNull ItemStack item) {
        this.id = id;
        this.item = item.clone();
        ItemMeta meta = this.item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        this.item.setItemMeta(meta);

        commands = new ArrayList<>();
        actions = new HashMap<>();

        ButtonManager.put(this);
    }

    public Button(Button button) {
        this.id = button.getId();
        this.item = button.item.clone();
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        commands = button.getCommands();
        actions = button.getActions();
    }

    /**
     *
     * @return The namespaced ID of this button.
     */
    public final String getId() {
        return id;
    }

    /**
     *
     * @return The itemstack icon of this button.
     */
    public final ItemStack getItem() {
        return item;
    }

    public final void setItem(ItemStack item) { this.item = item; }


    // AMOUNT MANAGEMENT

    /**
     * Sets the quantity of the item on this button.
     * @param amount
     */
    public final void setAmount(int amount) {
        item.setAmount(amount);
    }
    public final int getAmount() {
        return item.getAmount();
    }


    // ENCHANTMENT MANAGEMENT

    /**
     *
     * @param enchanted Sets whether or not to display this item with the enchanted effect.
     */
    public final void setEnchanted(boolean enchanted) {
        if (enchanted) {
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, 100);
        }
        else {
            item.removeEnchantment(Enchantment.UNBREAKING);
        }
    }
    public final boolean isEnchanted() {
        return item.getEnchantments().containsKey(Enchantment.UNBREAKING);
    }


    // DISPLAY NAME MANAGEMENT

    /**
     * Sets the display name of this button's itemstack.
     * @param display
     */
    public final void setDisplayName(String display) {
        ItemMeta meta = item.getItemMeta();
        meta.displayName(StringUtils.modernizeColorsComponent(display));
        item.setItemMeta(meta);
    }
    public final Component getDisplayName() {
        ItemMeta meta = item.getItemMeta();
        return meta.displayName();
    }


    // LORE MANAGEMENT

    /**
     * Set the entire lore of this button's itemstack.
     * @param lore A collection of lore lines
     */
    public final void setLore(List<String> lore) {
        ItemMeta meta = item.getItemMeta();

        List<Component> loreComponents = new ArrayList<>();
        lore.forEach(l -> loreComponents.add(StringUtils.modernizeColorsComponent(l)));

        meta.lore(loreComponents);

        item.setItemMeta(meta);
    }

    /**
     * Set the entire lore of this button's itemstack directly with components.
     * @param lore A collection of lore lines as components
     */
    public final void setLoreComponent(List<Component> lore) {
        ItemMeta meta = item.getItemMeta();

        meta.lore(lore);

        item.setItemMeta(meta);
    }

    /**
     * Add additional lore lines to the existing lore.
     * @param lines A collection of lore lines
     */
    public final void addLore(List<String> lines) {
        ItemMeta meta = item.getItemMeta();

        List<Component> loreComponents = new ArrayList<>();
        lines.forEach(line -> loreComponents.add(StringUtils.modernizeColorsComponent(line)));

        ArrayList<Component> lore;
        if (meta.lore() == null) {
            lore = new ArrayList<>();
        } else {
            lore = new ArrayList<>(meta.lore());
        }

        lore.addAll(loreComponents);

        meta.lore(lore);

        item.setItemMeta(meta);
    }

    /**
     * Add additional lore lines to the existing lore.
     * @param lines A collection of lore lines as components
     */
    public final void addLoreComponents(List<Component> lines) {
        ItemMeta meta = item.getItemMeta();

        ArrayList<Component> lore;
        if (meta.lore() == null) {
            lore = new ArrayList<>();
        } else {
            lore = new ArrayList<>(meta.lore());
        }

        lore.addAll(lines);

        meta.lore(lore);

        item.setItemMeta(meta);
    }

    /**
     * Add an additional lore line to the existing lore.
     * @param line One additional lore line as a component
     */
    public final void addLoreComponent(Component line) {
        ItemMeta meta = item.getItemMeta();

        ArrayList<Component> lore;
        if (meta.lore() == null) {
            lore = new ArrayList<>();
        } else {
            lore = new ArrayList<>(meta.lore());
        }

        lore.add(line);

        meta.lore(lore);

        item.setItemMeta(meta);
    }

    /**
     * Add an additional lore line to the existing lore.
     * @param line One additional lore line
     */
    public final void addLore(String line) {
        ItemMeta meta = item.getItemMeta();

        ArrayList<Component> lore;
        if (meta.lore() == null) {
            lore = new ArrayList<>();
        } else {
            lore = new ArrayList<>(meta.lore());
        }

        lore.add(StringUtils.modernizeColorsComponent(line));

        meta.lore(lore);

        item.setItemMeta(meta);
    }

    public final List<Component> getLore() {
        ItemMeta meta = item.getItemMeta();
        return meta.lore();
    }

    public final void clearLore() {
        ItemMeta meta = item.getItemMeta();
        meta.lore(new ArrayList<>());

        item.setItemMeta(meta);
    }


    // COMMAND MANAGEMENT

    /**
     * Add a command to execute when this button is clicked.
     * @param command A command to execute (without a '/' slash)
     */
    public final void addCommand(String command) {
        commands.add(command);
    }

    /**
     * Add a list of commands to execute when this button is clicked.
     * @param commands A collection of commands to execute
     */
    public final void addCommands(List<String> commands) {
        this.commands.addAll(commands);
    }

    public final ArrayList<String> getCommands() {
        return commands;
    }

    /**
     * Runs the commands associated with this button.
     */
    public final void runCommands() {
        for (String command : commands) {
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
        }
    }


    // CODE MANAGEMENT

    /**
     * Add a code action to execute when this button is clicked.
     * @see Action
     * @param id A namespaced ID to uniquely identify this action
     * @param action A block of code to execute, express via lambda with '(event) -> { [Your code here] }'
     */
    public final void addAction(String id, Action<InventoryClickEvent> action) {
        actions.put(id, action);
    }

    public final void removeAction(String id) {
        actions.remove(id);
    }
    public final HashMap<String, Action<InventoryClickEvent>> getActions() {
        return actions;
    }

    /**
     * Executes all the button actions associated with this button.
     * @param event The InventoryClickEvent that triggered this method
     */
    public final void runActions(InventoryClickEvent event) {
        for (Map.Entry<String, Action<InventoryClickEvent>> pair : actions.entrySet()) {
            pair.getValue().run(event);
        }
    }


    // CLICK MANAGEMENT

    public void click(InventoryClickEvent event, Window window) {
        runCommands();
        runActions(event);
    }


    // OBJECT MANAGEMENT

    public Button clone() {
        Button button = new Button(this.id, this.item);
        button.addCommands(button.getCommands());
        return button;
    }

}
