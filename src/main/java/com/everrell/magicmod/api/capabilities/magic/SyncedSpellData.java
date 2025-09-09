package com.everrell.magicmod.api.capabilities.magic;

import com.everrell.magicmod.network.SyncPlayerDataPacket;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

public class SyncedSpellData {
    //TODO: may want to switch this to ServerPlayer.UUID
    private final int serverPlayerId;
    private @Nullable LivingEntity livingEntity;

    private boolean isCasting;
    private String castingSpellId;
    private int castingSpellLevel;
    private float heartStopAccumulatedDamage;
    private int evasionHitsRemaining;

    private String castingEquipmentSlot;

    //Use this on the client
    public SyncedSpellData(int serverPlayerId) {
        this.livingEntity = null;
        this.serverPlayerId = serverPlayerId;
        this.isCasting = false;
        this.castingSpellId = "";
        this.castingEquipmentSlot = "";
        this.castingSpellLevel = 0;
        this.heartStopAccumulatedDamage = 0f;
        this.evasionHitsRemaining = 0;
    }

    public static void write(FriendlyByteBuf buffer, SyncedSpellData data) {
        buffer.writeInt(data.serverPlayerId);
        buffer.writeBoolean(data.isCasting);
        buffer.writeUtf(data.castingSpellId);
        buffer.writeInt(data.castingSpellLevel);
        buffer.writeFloat(data.heartStopAccumulatedDamage);
        buffer.writeInt(data.evasionHitsRemaining);
        buffer.writeUtf(data.castingEquipmentSlot);
    }

    public static SyncedSpellData read(FriendlyByteBuf buffer) {
        var data = new SyncedSpellData(buffer.readInt());
        data.isCasting = buffer.readBoolean();
        return data;
    }

    //Use this on the server
    public SyncedSpellData(LivingEntity livingEntity) {
        this(livingEntity == null ? -1 : livingEntity.getId());
        this.livingEntity = livingEntity;
    }

//    public static final EntityDataSerializer<SyncedSpellData> SYNCED_SPELL_DATA = new EntityDataSerializer.ForValueType<SyncedSpellData>() {
//        public void write(FriendlyByteBuf buffer, SyncedSpellData data) {
//            buffer.writeInt(data.serverPlayerId);
//            buffer.writeBoolean(data.isCasting);
//            buffer.writeUtf(data.castingSpellId);
//            buffer.writeInt(data.castingSpellLevel);
//            buffer.writeLong(data.syncedEffectFlags);
//            buffer.writeFloat(data.heartStopAccumulatedDamage);
//            buffer.writeInt(data.evasionHitsRemaining);
//            buffer.writeEnum(data.spinAttackType);
//            buffer.writeUtf(data.castingEquipmentSlot);
//            data.learnedSpellData.writeToBuffer(buffer);
//            data.spellSelection.writeToBuffer(buffer);
//        }
//
//        public SyncedSpellData read(FriendlyByteBuf buffer) {
//            var data = new SyncedSpellData(buffer.readInt());
//            data.isCasting = buffer.readBoolean();
//            data.castingSpellId = buffer.readUtf();
//            data.castingSpellLevel = buffer.readInt();
//            data.syncedEffectFlags = buffer.readLong();
//            data.heartStopAccumulatedDamage = buffer.readFloat();
//            data.evasionHitsRemaining = buffer.readInt();
//            data.spinAttackType = buffer.readEnum(SpinAttackType.class);
//            data.castingEquipmentSlot = buffer.readUtf();
//            data.learnedSpellData.readFromBuffer(buffer);
//            data.spellSelection.readFromBuffer(buffer);
//            return data;
//        }
//    };

    public void saveNBTData(CompoundTag compound, HolderLookup.Provider provider) {
        compound.putBoolean("isCasting", this.isCasting);

    }

    public void loadNBTData(CompoundTag compound, HolderLookup.Provider provider) {
        this.isCasting = compound.getBoolean("isCasting");

    }

    public int getServerPlayerId() {
        return serverPlayerId;
    }

    public String getCastingEquipmentSlot() {
        return castingEquipmentSlot;
    }

    public float getHeartstopAccumulatedDamage() {
        return heartStopAccumulatedDamage;
    }

    public void setHeartstopAccumulatedDamage(float damage) {
        heartStopAccumulatedDamage = damage;
        doSync();
    }











    public void doSync() {
        if (livingEntity instanceof ServerPlayer serverPlayer) {
            PacketDistributor.sendToPlayersTrackingEntityAndSelf(serverPlayer, new SyncPlayerDataPacket(this));
        }
    }

    public void syncToPlayer(ServerPlayer serverPlayer) {
        PacketDistributor.sendToPlayer(serverPlayer, new SyncPlayerDataPacket(this));
    }

    public void setIsCasting(boolean isCasting, String castingSpellId, int castingSpellLevel, String castingEquipmentSlot) {
        this.isCasting = isCasting;
        this.castingSpellId = castingSpellId;
        this.castingSpellLevel = castingSpellLevel;
        this.castingEquipmentSlot = castingEquipmentSlot;
        doSync();
    }

    public boolean isCasting() {
        return isCasting;
    }

    public String getCastingSpellId() {
        return castingSpellId;
    }

    public int getCastingSpellLevel() {
        return castingSpellLevel;
    }

    @Override
    protected SyncedSpellData clone() {
        return new SyncedSpellData(this.livingEntity);
    }

    @Override
    public String toString() {
        return String.format("isCasting:%s, spellID:%s, spellLevel:%d",
                isCasting,
                castingSpellId,
                castingSpellLevel);
    }

    /**
     * @param serverPlayer New entity to own the copied data
     * @return Retuns a copy of this SyncedSpellData, but with only data for things that should be persisted after death.
     */
    public SyncedSpellData getPersistentData(ServerPlayer serverPlayer) {
        //This updates the reference while keeping the id the same (because we are in the middle of cloning logic, where id has not been set yet)
        SyncedSpellData persistentData = new SyncedSpellData(livingEntity);
        persistentData.livingEntity = serverPlayer;

        return persistentData;
    }
}