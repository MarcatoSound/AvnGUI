package net.playavalon.avngui.GUI.Actions;

import org.bukkit.event.inventory.InventoryOpenEvent;

/**
 * An object used for creating and storing blocks of code to execute upon a GUI window is closed.
 * Can be treated similarly to a BukkitRunnable, or used in a lambda expression like so: (event) -> { [Your code here] }
 * The InventoryCloseEvent associated with this action is passed to it when executed.
 */
public interface OpenAction {

    /**
     * A block of code to execute, provided with an InventoryClickEvent
     * @param event The InventoryClickEvent that triggered this button action
     */
    void run(InventoryOpenEvent event);

}
