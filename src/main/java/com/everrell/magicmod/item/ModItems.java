package com.everrell.magicmod.item;

import com.everrell.magicmod.MaxsMagicMod;
import com.everrell.magicmod.item.advanceditems.BreakingWandItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MaxsMagicMod.MODID);
    public static final DeferredItem<Item> WAND =
            ITEMS.register("wand.json", ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> WAND2 = ITEMS.register("breakingwand",
            () -> new BreakingWandItem(new Item.Properties().durability(32)));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
