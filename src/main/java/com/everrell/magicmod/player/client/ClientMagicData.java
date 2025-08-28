package com.everrell.magicmod.player.client;

public class ClientMagicData {

    public static SyncedSpellData getSyncedSpellData(LivingEntity livingEntity) {
        if (livingEntity instanceof Player) {
            return playerSyncedDataLookup.getOrDefault(livingEntity.getId(), emptySyncedData);
        }
        if (livingEntity instanceof IMagicEntity abstractSpellCastingMob) {
            return abstractSpellCastingMob.getMagicData().getSyncedData();
        }
        return new SyncedSpellData(null);

    }

    public static void handlePlayerSyncedData(SyncedSpellData playerSyncedData) {
        if (Log.SPELL_SELECTION) {
            IronsSpellbooks.LOGGER.debug("ClientMagicData.handlePlayerSyncedData {}", playerSyncedData.getSpellSelection());
        }
        playerSyncedDataLookup.put(playerSyncedData.getServerPlayerId(), playerSyncedData);
    }
}
