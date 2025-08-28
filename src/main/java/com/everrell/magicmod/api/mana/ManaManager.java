package com.everrell.magicmod.api.mana;

import com.everrell.magicmod.api.events.ChangeManaEvent;
import com.everrell.magicmod.api.registry.AttributeRegistry;
import com.everrell.magicmod.api.registry.SpellRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.common.NeoForge;

import javax.management.Attribute;

public class ManaManager {
    public static int MANA_REGEN_TICKS = 10;
    private ServerPlayer serverPlayer = null;
    public static final String MANA = "mana";
    public static final String COOLDOWNS = "cooldowns";
    public static final String RECASTS = "recasts";

    private float mana;
    public float getMana(){
        return mana;
    }
    public void setMana(float mana){
        ChangeManaEvent e = new ChangeManaEvent(this.serverPlayer, this, this.mana, mana);
        if (this.serverPlayer == null || !NeoForge.EVENT_BUS.post(e).isCanceled()) {
            this.mana = e.getNewMana();
        }
        if (this.serverPlayer != null) {
            float maxMana = (float) serverPlayer.getAttributeValue(AttributeRegistry.MAX_MANA);
            if (this.mana > maxMana) {
                this.mana = maxMana;
            }
        }
    }
    public void addMana(float mana) {
        setMana(this.mana + mana);
    }

    /********* SYNC DATA *******************************************************/

    private SyncedSpellData syncedSpellData;

    public SyncedSpellData getSyncedData() {
        if (syncedSpellData == null) {
            syncedSpellData = new SyncedSpellData(serverPlayer);
        }

        return syncedSpellData;
    }

    public void setSyncedData(SyncedSpellData syncedSpellData) {
        this.syncedSpellData = syncedSpellData;
    }
}
