package com.everrell.magicmod.api.mana;

import net.minecraft.nbt.CompoundTag;

// IPlayerMana.java
public interface IPlayerMana {
    int get();
    void set(int v);
    int getMax();
    void setMax(int v);
    void add(int v);      // add (clamped)
    void consume(int v);  // reduce (clamped)
    CompoundTag serializeNBT();
    void deserializeNBT(CompoundTag tag);
}
