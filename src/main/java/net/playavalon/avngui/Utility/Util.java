package net.playavalon.avngui.Utility;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Set;
import java.util.UUID;

public class Util {

    public static ItemStack getNextButton() {
        ItemStack skull = createHeadByTextures(StringUtils.colorize("&a&lNext Page"), "79f13daf-4884-40ab-8e35-95e472463321", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjgyYWQxYjljYjRkZDIxMjU5YzBkNzVhYTMxNWZmMzg5YzNjZWY3NTJiZTM5NDkzMzgxNjRiYWM4NGE5NmUifX19");
        return skull;
    }

    public static ItemStack getPreviousButton() {
        ItemStack skull = createHeadByTextures(StringUtils.colorize("&a&lPrevious Page"), "5fecc571-bcbb-4aaa-b53c-b5d8715dbe37", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzdhZWU5YTc1YmYwZGY3ODk3MTgzMDE1Y2NhMGIyYTdkNzU1YzYzMzg4ZmYwMTc1MmQ1ZjQ0MTlmYzY0NSJ9fX0=");
        return skull;
    }

    public static ItemStack createHeadByTextures(String name, String id, String textures) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        SkullMeta itemMeta = (SkullMeta) item.getItemMeta();

        PlayerProfile profile = Bukkit.createProfile(UUID.fromString(id), "");
        Set<ProfileProperty> properties = profile.getProperties();
        properties.add(new ProfileProperty("textures", textures));
        itemMeta.setPlayerProfile(profile);
        itemMeta.setDisplayName(name);
        item.setItemMeta(itemMeta);
        return item;
    }

}
