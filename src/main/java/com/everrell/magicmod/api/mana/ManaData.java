package com.everrell.magicmod.api.mana;

import com.everrell.magicmod.api.attribute.AttributeRegistry;
import com.everrell.magicmod.api.events.ChangeManaEvent;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.common.NeoForge;

import static com.ibm.icu.text.PluralRules.Operand.e;

public class ManaData {
    private boolean isMob = false;

    public ManaData(boolean isMob) {
        this.isMob = isMob;
    }

    public ManaData() {
        this(false);
    }



    public void setServerPlayer(ServerPlayer serverPlayer) {
        if (this.serverPlayer == null && serverPlayer != null) {
            this.serverPlayer = serverPlayer;
        }
    }

    private ServerPlayer serverPlayer = null;
    public static final String MANA = "mana";
    public static final String COOLDOWNS = "cooldowns";
    public static final String RECASTS = "recasts";


    //mana

    private float mana;

    public float getMana() {
        return mana;
    }

    public void setMana(float mana) {
        //Event will not get posted if the server player is null
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
}
