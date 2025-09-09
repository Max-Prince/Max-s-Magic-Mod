package com.everrell.magicmod.api.registries;

import com.everrell.magicmod.MaxsMagicMod;
import com.everrell.magicmod.api.capabilities.magic.PlayerMagicProvider;
import com.everrell.magicmod.api.mana.ManaData;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;


public class DataAttachmentRegistry {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, MaxsMagicMod.MODID);

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<ManaData>> MAGIC_DATA = ATTACHMENT_TYPES.register("magic_data",
            () -> AttachmentType.builder((holder) -> holder instanceof ServerPlayer serverPlayer ? new ManaData(serverPlayer.is(serverPlayer)) : new ManaData()).serialize(new PlayerMagicProvider()).build());
}