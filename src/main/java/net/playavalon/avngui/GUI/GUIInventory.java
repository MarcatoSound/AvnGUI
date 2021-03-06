package net.playavalon.avngui.GUI;

import net.playavalon.avngui.GUI.Buttons.Button;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class GUIInventory {

    private Inventory inv;
    private HashMap<Integer, Button> buttons;

    public GUIInventory(Inventory inv) {
        this.inv = inv;
        this.buttons = new HashMap<>();
    }

    public GUIInventory(Window window, Inventory inv) {
        this.inv = inv;
        this.buttons = new HashMap<>(window.getButtons());
    }

    public Inventory getInv() {
        return inv;
    }

    public HashMap<Integer, Button> getButtons() {
        return buttons;
    }
    public void setButton(int slot, Button button) {
        buttons.put(slot, button);
        inv.setItem(slot, button.getItem());
    }
    public void removeButton(int slot) {
        buttons.remove(slot);
        inv.setItem(slot, null);
    }

    public void sort(int slotStart, int slotEnd) {
        HashMap<Integer, Button> newButtons = new HashMap<>();

        int slot = slotStart;
        for (Button button : buttons.values()) {
            if (slot > slotEnd) break;
            newButtons.put(slot, button);
        }

        this.buttons = newButtons;
    }
}
