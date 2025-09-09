package com.everrell.magicmod.player.client;

import com.everrell.magicmod.api.capabilities.magic.SyncedSpellData;
import com.everrell.magicmod.api.mana.ManaData;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.apache.commons.logging.Log;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ClientMagicData {

    /**
     * Current Player's Synced Data
     */
    private static final ManaData playerMagicData = new ManaData();
    private static final Set<UUID> activeSummons = new HashSet<>();

    /**
     * Other Player's Synced Data
     */
    private static final HashMap<Integer, SyncedSpellData> playerSyncedDataLookup = new HashMap<>();
    private static final SyncedSpellData emptySyncedData = new SyncedSpellData(-999);

    /**
     * Local Targeting data
     */
    private static ClientSpellTargetingData spellTargetingData;

    public static void setTargetingData(ClientSpellTargetingData spellTargetingData) {
        ClientMagicData.spellTargetingData = spellTargetingData;
    }

    public static ClientSpellTargetingData getTargetingData() {
        if (spellTargetingData == null)
            setTargetingData(new ClientSpellTargetingData());
        return spellTargetingData;
    }

    public static void resetTargetingData() {
        spellTargetingData = null;
    }









    public static Set<UUID> getActiveSummons() {
        return activeSummons;
    }


    public static int getPlayerMana() {
        return (int) playerMagicData.getMana();
    }

    public static void setMana(int playerMana) {
        ClientMagicData.playerMagicData.setMana(playerMana);
    }




    public static SyncedSpellData getSyncedSpellData(LivingEntity livingEntity) {
        if (livingEntity instanceof Player) {
            return playerSyncedDataLookup.getOrDefault(livingEntity.getId(), emptySyncedData);
        }

        return new SyncedSpellData(null);

    }

    public static void handlePlayerSyncedData(SyncedSpellData playerSyncedData) {
        playerSyncedDataLookup.put(playerSyncedData.getServerPlayerId(), playerSyncedData);
    }


}