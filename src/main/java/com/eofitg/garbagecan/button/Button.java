package com.eofitg.garbagecan.button;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Button{
    public static ItemStack buildButton(ItemStack item, String name, String lore) {
        ItemStack t = setName(item, name);
        if (lore != null) t = setLore(t, lore);
        return t;
    }
    public static ItemStack setName(ItemStack iss, String name) {
        ItemStack tree = iss;
        ItemMeta orange = tree.getItemMeta();
        orange.setDisplayName(name);
        tree.setItemMeta(orange);
        return tree;
    }
    public static ItemStack setLore(ItemStack item, String lore){
        ItemMeta im = item.getItemMeta();
        List<String> ll = new ArrayList<String>();
        ll.add(lore);
        im.setLore(ll);
        item.setItemMeta(im);
        return item;
    }
}
