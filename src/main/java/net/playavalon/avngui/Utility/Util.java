package net.playavalon.avngui.Utility;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class Util {

    public static ItemStack getNextButton() {
        return createHeadByTextures(StringUtils.modernizeColorsComponent("&a&lNext Page"), "79f13daf-4884-40ab-8e35-95e472463321", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjgyYWQxYjljYjRkZDIxMjU5YzBkNzVhYTMxNWZmMzg5YzNjZWY3NTJiZTM5NDkzMzgxNjRiYWM4NGE5NmUifX19");
    }

    public static ItemStack getPreviousButton() {
        return createHeadByTextures(StringUtils.modernizeColorsComponent("&a&lPrevious Page"), "5fecc571-bcbb-4aaa-b53c-b5d8715dbe37", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzdhZWU5YTc1YmYwZGY3ODk3MTgzMDE1Y2NhMGIyYTdkNzU1YzYzMzg4ZmYwMTc1MmQ1ZjQ0MTlmYzY0NSJ9fX0=");
    }

    public static ItemStack createHeadByTextures(Component name, String id, String textures) {
        ItemStack item = ItemStack.of(Material.PLAYER_HEAD, 1);
        Damageable dmgMeta = (Damageable) item.getItemMeta();
        if (dmgMeta != null) dmgMeta.setDamage ((short) 3);

        ItemMeta itemMeta = item.getItemMeta();

        if (itemMeta instanceof SkullMeta skullMeta) {
            itemMeta.displayName(name);

            // Create the GameProfile and set the texture
            PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID());
            profile.getProperties().add(new ProfileProperty("textures", textures));

            // Setting the profile to the SkullMeta
            skullMeta.setPlayerProfile(profile);

            // Apply changes to the ItemStack
            item.setItemMeta(skullMeta);
        } else {
            itemMeta.displayName(name);
            item.setItemMeta(itemMeta);
        }

//        GameProfile gProfile = new GameProfile(UUID.fromString(id), "");
//        gProfile.getProperties().put("texture", new Property("textures", textures));
//        try {
//            Method mtd = itemMeta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
//            mtd.setAccessible(true);
//            mtd.invoke(itemMeta, gProfile);
//        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
//            ex.printStackTrace();
//        }
//
//        itemMeta.displayName(name);
//
//        item.setItemMeta(itemMeta);

        return item;
    }

}
