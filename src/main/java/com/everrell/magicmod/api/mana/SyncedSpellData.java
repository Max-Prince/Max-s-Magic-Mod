package com.everrell.magicmod.api.mana;

import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;

public class SyncedSpellData {

    private final int serverPlayerId;
    private @Nullable LivingEntity livingEntity;

    private boolean isCasting;
    private String castingSpellId;
    private int castingSpellLevel;

            this.livingEntity = null;
        this.serverPlayerId = serverPlayerId;
        this.isCasting = false;
        this.castingSpellId = "";
}
