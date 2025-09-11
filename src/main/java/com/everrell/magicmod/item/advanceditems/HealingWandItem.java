package com.everrell.magicmod.item.advanceditems;

import com.everrell.magicmod.api.attribute.AttributeRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class HealingWandItem extends Item {
    public HealingWandItem(Item.Properties p_41188_) {
        super(p_41188_);
    }
    public InteractionResult use(Level p_41193_, Player p_41194_, InteractionHand p_41195_) {
        if (p_41193_ instanceof ServerLevel serverlevel) {
            if (p_41194_.getAttributes().getValue(AttributeRegistry.MANA) >= 50) {

                AttributeMap attributes = p_41194_.getAttributes();
                p_41194_.getAttributes().getInstance(AttributeRegistry.MANA).setBaseValue(p_41194_.getAttributes().getValue(AttributeRegistry.MANA) - 200);
                p_41194_.displayClientMessage(Component.literal("you have " + p_41194_.getAttributes().getValue(AttributeRegistry.MANA) + " mana"), true);
                p_41194_.heal(10);
            }
        }


        return InteractionResult.SUCCESS;
    }
    /*public InteractionResult use(Player player, Level p_41190_, Player p_41191_, InteractionHand p_41192_) {

        if (p_41190_ instanceof ServerLevel serverleve){
            p_41190_.playSound((Player) null, p_41191_.getX(), p_41191_.getY(), p_41191_.getZ(), SoundEvents.PLAYER_HURT, SoundSource.NEUTRAL, 0.5F, 0.4F / (p_41190_.getRandom().nextFloat() * 0.4F + 0.8F));
            player.die(player.damageSources().magic());
        }
        return InteractionResult.SUCCESS;

    }

     */
}