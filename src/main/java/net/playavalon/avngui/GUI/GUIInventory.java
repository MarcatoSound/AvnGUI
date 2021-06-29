package net.playavalon.avngui.GUI;

import org.bukkit.inventory.Inventory;

public class GUIInventory {

    private Inventory inv;

    public GUIInventory(Inventory inv) {
        this.inv = inv;
    }

    public Inventory getInv() {
        return inv;
    }

}
