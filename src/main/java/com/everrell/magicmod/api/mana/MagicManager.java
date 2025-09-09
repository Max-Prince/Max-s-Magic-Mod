package com.everrell.magicmod.api.mana;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;

import static com.everrell.magicmod.api.attribute.AttributeRegistry.MANA_REGEN;
import static com.everrell.magicmod.api.attribute.AttributeRegistry.MAX_MANA;

public class MagicManager {
    public static final int MANA_REGEN_TICKS = 10;
    public static final int CONTINUOUS_CAST_TICK_INTERVAL = 10;

    public boolean regenPlayerMana(ServerPlayer serverPlayer, ManaData playerMagicData) {
        int playerMaxMana = (int) serverPlayer.getAttributeValue(MAX_MANA);
        var mana = playerMagicData.getMana();
        if (mana != playerMaxMana) {
            float playerManaRegenMultiplier = (float) serverPlayer.getAttributeValue(MANA_REGEN);
            var increment = playerMaxMana * playerManaRegenMultiplier * .01f;
            playerMagicData.setMana(Mth.clamp(playerMagicData.getMana() + increment, 0, playerMaxMana));
            return true;
        } else {
            return false;
        }
    }
}
