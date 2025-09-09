package com.everrell.magicmod.api.capabilities.magic;

import com.everrell.magicmod.api.mana.ManaData;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import org.jetbrains.annotations.Nullable;

public class PlayerMagicProvider implements IAttachmentSerializer<CompoundTag, ManaData> {
    @Override
    public ManaData read(IAttachmentHolder holder, CompoundTag tag, HolderLookup.Provider provider) {
        //Entities implement IIAttachmentHolder
        var magicData = holder instanceof ServerPlayer serverPlayer ? new ManaData(serverPlayer.is(serverPlayer)) : new ManaData(true);
        magicData.loadNBTData(tag, provider);
        return magicData;
    }

    @Override
    public @Nullable CompoundTag write(ManaData attachment, HolderLookup.Provider provider) {
        var tag = new CompoundTag();
        attachment.saveNBTData(tag, provider);
        return tag;
    }
}