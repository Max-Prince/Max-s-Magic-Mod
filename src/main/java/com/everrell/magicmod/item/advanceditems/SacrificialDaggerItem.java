package com.everrell.magicmod.item.advanceditems;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class SacrificialDaggerItem extends Item {
    public SacrificialDaggerItem(Item.Properties properties){
        super(properties);
    }
    public InteractionResult use(Player player, Level level) {

        if (!level.isClientSide() && player.isUsingItem()){
            player.hurt(player.damageSources().magic(), 2.0F);
        }
        return InteractionResult.SUCCESS;

    }
}
