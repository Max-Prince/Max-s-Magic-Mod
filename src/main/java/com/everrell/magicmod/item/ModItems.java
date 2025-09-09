package com.everrell.magicmod.item;

import com.everrell.magicmod.MaxsMagicMod;
import com.everrell.magicmod.item.advanceditems.BreakingWandItem;
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

    public static final DeferredItem<Item> WAND2 = ITEMS.register("breakingwand",
            () -> new BreakingWandItem(new Item.Properties().durability(32).setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("maxsmagicmod:breakingwand")))));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
