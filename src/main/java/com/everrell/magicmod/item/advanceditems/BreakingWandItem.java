package com.everrell.magicmod.item.advanceditems;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class BreakingWandItem extends Item {
    public BreakingWandItem(Properties properties){
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock= level.getBlockState(context.getClickedPos()).getBlock();
        if (!level.isClientSide()){
            level.destroyBlock(context.getClickedPos(), true);
            level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);

        }

        return InteractionResult.SUCCESS;

    }
}
