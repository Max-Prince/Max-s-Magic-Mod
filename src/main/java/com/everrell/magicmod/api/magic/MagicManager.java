package com.everrell.magicmod.api.magic;

import com.everrell.magicmod.api.mana.ManaData;
import com.everrell.magicmod.network.SyncManaPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.PacketDistributor;

import static com.everrell.magicmod.api.attribute.AttributeRegistry.MANA_REGEN;
import static com.everrell.magicmod.api.attribute.AttributeRegistry.MAX_MANA;

public class MagicManager implements IMagicManager {
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
    public void tick(Level level) {
        boolean doManaRegen = level.getServer().getTickCount() % MANA_REGEN_TICKS == 0;

        level.players().stream().toList().forEach(player -> {
            if (player instanceof ServerPlayer serverPlayer) {
                ManaData playerMagicData = ManaData.getPlayerMagicData(serverPlayer);

                if (doManaRegen) {
                    if (regenPlayerMana(serverPlayer, playerMagicData)) {
                        PacketDistributor.sendToPlayer(serverPlayer, new SyncManaPacket(playerMagicData));
                    }
                }
            }
        });
    }
}
