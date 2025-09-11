package com.everrell.magicmod.item.advanceditems;

import com.everrell.magicmod.api.attribute.AttributeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class TeleportWandItem extends Item {
    public TeleportWandItem(Item.Properties p_41188_) {
        super(p_41188_);
    }
    public InteractionResult use(Level p_41193_, Player p_41194_, InteractionHand p_41195_) {
        if (p_41193_ instanceof ServerLevel serverlevel) {

            AttributeMap attributes = p_41194_.getAttributes();
if(p_41194_.getAttributes().getInstance(AttributeRegistry.MANA).getBaseValue() >= 50){
            p_41194_.getAttributes().getInstance(AttributeRegistry.MANA).setBaseValue(p_41194_.getAttributes().getValue(AttributeRegistry.MANA) - 50);

            p_41194_.teleportTo(p_41194_.pick(50, 1, false).getLocation().x, p_41194_.pick(50, 1, false).getLocation().y, p_41194_.pick(50, 1, false).getLocation().z);
        }
            p_41194_.displayClientMessage(Component.literal("you have " + p_41194_.getAttributes().getValue(AttributeRegistry.MANA) + " mana"), true);
            /*
            nonfunctioning teleport method
            p_41194_.setPos(p_41194_.pick(50, 1, false).getLocation());

             */

        }


        return InteractionResult.SUCCESS;
    }
}
