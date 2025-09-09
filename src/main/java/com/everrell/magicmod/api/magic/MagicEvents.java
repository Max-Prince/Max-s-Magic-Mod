package com.everrell.magicmod.api.magic;

import com.everrell.magicmod.MaxsMagicMod;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

public class MagicEvents {
    public static final ResourceLocation PLAYER_MAGIC_RESOURCE = ResourceLocation.fromNamespaceAndPath(MaxsMagicMod.MODID, "player_magic");

    public static void onWorldTick(LevelTickEvent.Pre event) {
        // Don't do anything client side
        if (event.getLevel().isClientSide) {
            return;
        }


    }
}
