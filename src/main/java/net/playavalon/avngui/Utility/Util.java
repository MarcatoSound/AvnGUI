package net.playavalon.avngui.Utility;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Util {

    public static ItemStack getNextButton() {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        assert meta != null;
        meta.setOwningPlayer(Bukkit.getOfflinePlayer("MHF_ArrowRight"));
        meta.setDisplayName(StringUtils.colorize("&a&lNext Page"));
        skull.setItemMeta(meta);
        return skull;
    }

    public static ItemStack getPreviousButton() {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        assert meta != null;
        meta.setOwningPlayer(Bukkit.getOfflinePlayer("MHF_ArrowLeft"));
        meta.setDisplayName(StringUtils.colorize("&a&lPrevious Page"));
        skull.setItemMeta(meta);
        return skull;
    }

}
