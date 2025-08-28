package com.everrell.magicmod.api.events;

import com.everrell.magicmod.api.mana.ManaManager;
import com.everrell.magicmod.player.client.ClientMagicData;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class ChangeManaEvent extends PlayerEvent implements ICancellableEvent {
    private final ClientMagicData clientMagicData;
    private final float oldMana;
    private float newMana;

    public ChangeManaEvent(Player player, ManaManager magicData, float oldMana, float newMana) {
        super(player);
        this.clientMagicData = magicData;
        this.oldMana = oldMana;
        this.newMana = newMana;
    }
    public ClientMagicData getMagicData() {
        return clientMagicData;
    }

    public float getOldMana() {
        return oldMana;
    }

    public float getNewMana() {
        return newMana;
    }

    public void setNewMana(float newMana) {
        this.newMana = newMana;
    }
}
