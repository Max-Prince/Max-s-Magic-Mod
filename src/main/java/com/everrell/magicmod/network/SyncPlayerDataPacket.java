package com.everrell.magicmod.network;

import com.everrell.magicmod.MaxsMagicMod;
import com.everrell.magicmod.api.capabilities.magic.SyncedSpellData;
import com.everrell.magicmod.player.client.ClientMagicData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SyncPlayerDataPacket implements CustomPacketPayload {
    SyncedSpellData syncedSpellData;
    public static final CustomPacketPayload.Type<SyncPlayerDataPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MaxsMagicMod.MODID, "sync_player_data"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SyncPlayerDataPacket> STREAM_CODEC = CustomPacketPayload.codec(SyncPlayerDataPacket::write, SyncPlayerDataPacket::new);

    public SyncPlayerDataPacket(SyncedSpellData playerSyncedData) {
        this.syncedSpellData = playerSyncedData;
    }

    public SyncPlayerDataPacket(FriendlyByteBuf buf) {
        syncedSpellData = SyncedSpellData.read(buf);
    }

    public void write(FriendlyByteBuf buf) {
        SyncedSpellData.write(buf, syncedSpellData);
    }

    public static void handle(SyncPlayerDataPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientMagicData.handlePlayerSyncedData(packet.syncedSpellData);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
