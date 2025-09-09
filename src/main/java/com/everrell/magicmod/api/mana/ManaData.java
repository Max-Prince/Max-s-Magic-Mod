package com.everrell.magicmod.api.mana;

import com.everrell.magicmod.api.attribute.AttributeRegistry;
import com.everrell.magicmod.api.capabilities.magic.SyncedSpellData;
import com.everrell.magicmod.api.events.ChangeManaEvent;
import com.everrell.magicmod.api.registries.DataAttachmentRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.NeoForge;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.NeoForge;
import org.jetbrains.annotations.Nullable;

import static com.ibm.icu.impl.CurrencyData.provider;
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

    private static float mana;

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

    //Sync Spell Data
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

    //Systems
    public static ManaData getPlayerMagicData(LivingEntity livingEntity) {
        return livingEntity.getData(DataAttachmentRegistry.MAGIC_DATA);
    }

    public void saveNBTData(CompoundTag compound, HolderLookup.Provider provider) {
        compound.putInt(MANA, (int) mana);



        getSyncedData().saveNBTData(compound, provider);
    }

    public void loadNBTData(CompoundTag compound, HolderLookup.Provider provider) {
        mana = compound.getInt(MANA);

        getSyncedData().loadNBTData(compound, provider);
    }

}
