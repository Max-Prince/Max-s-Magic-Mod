package com.everrell.magicmod.api.attribute;

import com.everrell.magicmod.MaxsMagicMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class AttributeModifiers {
    public static ResourceLocation add = ResourceLocation.fromNamespaceAndPath(MaxsMagicMod.MODID, "api.attribute");
    // The modifier itself.
    public static AttributeModifier addmodifier = new AttributeModifier(add, 20.0, AttributeModifier.Operation.ADD_VALUE);

    public static ResourceLocation subtract = ResourceLocation.fromNamespaceAndPath(MaxsMagicMod.MODID, "api.attribute");
    // The modifier itself.
    public static AttributeModifier subtractmodifier = new AttributeModifier(subtract, -20.0, AttributeModifier.Operation.ADD_VALUE);


}
