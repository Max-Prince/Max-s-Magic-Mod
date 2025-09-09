package com.everrell.magicmod.item;

import com.everrell.magicmod.MaxsMagicMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB=
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MaxsMagicMod.MODID);

public static final Supplier<CreativeModeTab> CREATIVE_WANDS_TAB = CREATIVE_MODE_TAB.register("creative_wands_tab",
        () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.WAND.get()))
                .title(Component.translatable("creativetab.tutorialmod.creative_wands"))
                .displayItems((itemDisplayParameters, output) -> {
                    output.accept(ModItems.WAND);
                    output.accept(ModItems.WAND2);
                    output.accept(ModItems.WAND3);
                    output.accept(ModItems.SACRIFICIALDAGGER);
                        }

                )

                .build());
    public static void register(IEventBus eventBus ){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
