package me.scholtes.mobrush.kits.manager;

import me.scholtes.mobrush.kits.Kit;
import me.scholtes.mobrush.kits.KitType;

import java.util.HashMap;
import java.util.Map;

public class KitManager {

    private final Map<KitType, Kit> kits;

    public KitManager() {
        kits = new HashMap<>();
        initializeKits();
    }

    /**
     * Clears the map and then adds every kit to it
     */
    private void initializeKits() {
        kits.clear();
    }

    /**
     * Gets the kit according to the KitType
     *
     * @param kit The {@link KitType kit} to find
     * @return Returns the {@link Kit kit}
     */
    public Kit getKit(KitType kit) {
        return kits.get(kit);
    }

}
