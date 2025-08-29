package com.everrell.magicmod.api.mana;

import com.everrell.magicmod.api.events.ChangeManaEvent;
import com.everrell.magicmod.api.registry.AttributeRegistry;
import com.everrell.magicmod.api.registry.SpellRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.common.NeoForge;

import javax.management.Attribute;

// PlayerMana.java
public class PlayerMana implements IPlayerMana {
    private int value = 100;
    private int max = 100;

    @Override public int get() { return value; }
    @Override public void set(int v) { value = Mth.clamp(v, 0, max); }
    @Override public int getMax() { return max; }
    @Override public void setMax(int v) { max = Math.max(1, v); value = Mth.clamp(value, 0, max); }
    @Override public void add(int v) { set(value + v); }
    @Override public void consume(int v) { set(value - v); }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag t = new CompoundTag();
        t.putInt("mana", value);
        t.putInt("max", max);
        return t;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        max = tag.getInt("max");
        value = Mth.clamp(tag.getInt("mana"), 0, max);
    }
}
