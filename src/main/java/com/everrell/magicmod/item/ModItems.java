package com.everrell.magicmod.item;

import com.everrell.magicmod.MaxsMagicMod;
import com.everrell.magicmod.item.advanceditems.*;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MaxsMagicMod.MODID);
    public static final DeferredItem<Item> WAND =
            ITEMS.register("wand.json", ()->new Item(new Item.Properties().durability(100).setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("maxsmagicmod:wand")))));

    public static final DeferredItem<Item> WAND2 =
            ITEMS.register("breakingwand.json", () -> new BreakingWandItem(new Item.Properties().durability(32).setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("maxsmagicmod:breakingwand")))));

    public static final DeferredItem<Item> WAND3 =
            ITEMS.register("enderwand.json", () -> new EnderWandItem(new Item.Properties().durability(32).setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("maxsmagicmod:enderwand")))));

    public static final DeferredItem<Item> SACRIFICIALDAGGER =
            ITEMS.register("sacrificialdagger.json", () -> new SacrificialDaggerItem(new Item.Properties().durability(32).setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("maxsmagicmod:sacrificialdagger")))));

    public static final DeferredItem<Item> TELEPORTWAND =
            ITEMS.register("teleportwand.json", () -> new TeleportWandItem(new Item.Properties().durability(32).setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("maxsmagicmod:teleportwand")))));

    public static final DeferredItem<Item> HEALINGWAND =
            ITEMS.register("healingwand.json", () -> new HealingWandItem(new Item.Properties().durability(32).setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("maxsmagicmod:healingwand")))));

    public static final DeferredItem<Item> EXPLOSIONWAND =
            ITEMS.register("explosionwand.json", () -> new ExplosionWandItem(new Item.Properties().durability(32).setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("maxsmagicmod:explosionwand")))));


    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
