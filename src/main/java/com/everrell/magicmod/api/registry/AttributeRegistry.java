package com.everrell.magicmod.api.registry;

import com.everrell.magicmod.MaxsMagicMod;
import com.everrell.magicmod.api.attribute.MagicPercentAttribute;
import com.everrell.magicmod.api.attribute.MagicRangedAttribute;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


@EventBusSubscriber(modid = MaxsMagicMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class AttributeRegistry {
    private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, MaxsMagicMod.MODID);

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }

    public static final DeferredHolder<Attribute, Attribute> MAX_MANA = ATTRIBUTES.register("max_mana", () -> (new MagicRangedAttribute("attribute.maxsmagicmod.max_mana", 100.0D, 0.0D, 1000000.0D).setSyncable(true)));
    public static final DeferredHolder<Attribute, Attribute> MANA_REGEN = ATTRIBUTES.register("mana_regen", () -> (new MagicPercentAttribute("attribute.maxsmagicmod.mana_regen", 1.0D, 0.0D, 100.0D).setSyncable(true)));
    public static final DeferredHolder<Attribute, Attribute> COOLDOWN_REDUCTION = ATTRIBUTES.register("cooldown_reduction", () -> (new MagicPercentAttribute("attribute.maxsmagicmod.cooldown_reduction", 1.0D, -100.0D, 100.0D).setSyncable(true)));
    public static final DeferredHolder<Attribute, Attribute> SPELL_POWER = ATTRIBUTES.register("spell_power", () -> (new MagicPercentAttribute("attribute.maxsmagicmod.spell_power", 1.0D, -100, 100.0D).setSyncable(true)));
    public static final DeferredHolder<Attribute, Attribute> SPELL_RESIST = ATTRIBUTES.register("spell_resist", () -> (new MagicPercentAttribute("attribute.maxsmagicmod.spell_resist", 1.0D, -100, 100.0D).setSyncable(true)));
    public static final DeferredHolder<Attribute, Attribute> CAST_TIME_REDUCTION = ATTRIBUTES.register("cast_time_reduction", () -> (new MagicPercentAttribute("attribute.maxsmagicmod.cast_time_reduction", 1.0D, -100, 100.0D).setSyncable(true)));
    public static final DeferredHolder<Attribute, Attribute> SUMMON_DAMAGE = ATTRIBUTES.register("summon_damage", () -> (new MagicPercentAttribute("attribute.maxsmagicmod.summon_damage", 1.0D, -100, 100.0D).setSyncable(true)));
    public static final DeferredHolder<Attribute, Attribute> CASTING_MOVESPEED = ATTRIBUTES.register("casting_movespeed", () -> (new MagicPercentAttribute("attribute.maxsmagicmod.casting_movespeed", 1, 0, 100.0D).setSyncable(true)));

    public static final DeferredHolder<Attribute, Attribute> PLAYER_ALIGNMENT = ATTRIBUTES.register("player_alignment", () ->(new MagicPercentAttribute("attribute.maxsmagicmod.manaalignment", 0.0D, -100.0D, 100.0D).setSyncable(true)));
    public static final DeferredHolder<Attribute, Attribute> GOOD_SPELL_POWER = newPowerAttribute("good");
    public static final DeferredHolder<Attribute, Attribute> NEUTRAL_SPELL_POWER = newPowerAttribute("evil");

    public static final DeferredHolder<Attribute, Attribute> EVIL_SPELL_POWER = newPowerAttribute("evil");

    @SubscribeEvent
    public static void modifyEntityAttributes(EntityAttributeModificationEvent e) {
        e.getTypes().forEach(entity -> ATTRIBUTES.getEntries().forEach(attribute -> e.add(entity, attribute)));
    }

    private static DeferredHolder<Attribute, Attribute> newPowerAttribute(String id) {
        return ATTRIBUTES.register(id + "_spell_power", () -> (new MagicPercentAttribute("attribute.irons_spellbooks." + id + "_spell_power", 1.0D, -100, 100).setSyncable(true)));
    }
}
