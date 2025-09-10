package com.everrell.magicmod.item.advanceditems;

import com.everrell.magicmod.api.attribute.AttributeRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class BreakingWandItem extends Item {
    public BreakingWandItem(Properties properties){
        super(properties);
    }


    public InteractionResult useOn(UseOnContext context, Level p_41190_, Player p_41191_, InteractionHand p_41192_) {
        Level level = context.getLevel();
        Block clickedBlock= level.getBlockState(context.getClickedPos()).getBlock();
        if (!level.isClientSide()){
            if (p_41191_.getAttributes().getValue(AttributeRegistry.MANA) > 5) {
                level.destroyBlock(context.getClickedPos(), true);
                level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
                p_41191_.getAttributes().getInstance(AttributeRegistry.MANA).setBaseValue(p_41191_.getAttributes().getValue(AttributeRegistry.MANA) - 5);

            }
            p_41191_.displayClientMessage(Component.literal("you have " + p_41191_.getAttributes().getValue(AttributeRegistry.MANA) + " mana"), true);

        }

        return InteractionResult.SUCCESS;

    }
}
