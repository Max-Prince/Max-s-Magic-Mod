package com.everrell.magicmod.api.registry;

import com.everrell.magicmod.api.mana.IPlayerMana;
import com.everrell.magicmod.api.mana.PlayerMana;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

import static com.everrell.magicmod.MaxsMagicMod.MODID;

// ManaProvider.java
public class ManaProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final ResourceLocation ID = new ResourceLocation(MODID, "player_mana");
    private final PlayerMana impl = new PlayerMana();
    private final LazyOptional<IPlayerMana> opt = LazyOptional.of(() -> impl);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == ModCapabilities.PLAYER_MANA ? opt.cast() : LazyOptional.empty();
    }

    @Override public CompoundTag serializeNBT() { return impl.serializeNBT(); }
    @Override public void deserializeNBT(CompoundTag nbt) { impl.deserializeNBT(nbt); }

    @Override
    public @Nullable Object getCapability(Object o, Object o2) {
        return null;
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        return null;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {

    }
}