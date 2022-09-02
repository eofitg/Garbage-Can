package com.eofitg.garbagecan.data;

import com.eofitg.garbagecan.GarbageCan;

public class DataFilter {
    /*
     * String MaterialData to String Material (String Material can be Material)
     * String MaterialData to short damage
     */
    public static String GetMaterial (String itemData) { // itemData is MaterialData and this method is for 1.18
        String data = itemData;
        data = data.substring(7); // filter LEGACY_
        StringBuilder t = new StringBuilder("");
        for (int i = 0; i < data.length(); i ++) { // filter (...)
            if (data.charAt(i) != '(') {
                t.append(data.charAt(i));
            } else break;
        }
        return t.toString();
    }
    public static String getMaterial (String itemData) { // itemData is MaterialData and this method is for 1.12
        String data = itemData;
        StringBuilder t = new StringBuilder("");
        boolean begin = false;
        boolean jumpNBT = false;
        for (int i = data.length() - 1; i >= 0; i --) {
            char thisChar = data.charAt(i);
            if (Character.isDigit(thisChar)) continue;
            if (Character.isUpperCase(thisChar)) jumpNBT = true;
            if (thisChar == ')') begin = true;
            if (thisChar == '(') continue;
            if (thisChar == ' ' && jumpNBT) break;
            if (begin && jumpNBT) t.append(thisChar);
            GarbageCan.getInstance().getLogger().info(t.toString());
        }
        t.reverse();
        return t.toString();
    }
    public static short GetDamage (String itemData) { // itemData is MaterialData and this method is for 1.18
        // assert itemData contains '()'
        // because that mean it really has damage
        StringBuilder sdamage = new StringBuilder("");
        String data = itemData;
        data = data.substring(7); // filter LEGACY_
        for (int i = data.length() - 1; i >= 0; i --) {
            if (data.charAt(i) == ')') continue;
            else if (data.charAt(i) == '(') break;
            else sdamage.append(data.charAt(i));
        }
        sdamage.reverse();
        return Short.parseShort(sdamage.toString());
    }
    public static short getDamage (String itemData) { // itemData is MaterialData and this method is for 1.12
        // assert itemData contains '()'
        // because that mean it really has damage
        String data = itemData;
        StringBuilder sdamage = new StringBuilder("");
        boolean begin = false;
        for (int i = data.length() - 1; i >= 0; i --) {
            if (data.charAt(i) == ')') begin = true;
            else if (data.charAt(i) == '(') break;
            else {
                if (begin) sdamage.append(data.charAt(i));
            }
        }
        sdamage.reverse();
        return Short.parseShort(sdamage.toString());
    }
}
