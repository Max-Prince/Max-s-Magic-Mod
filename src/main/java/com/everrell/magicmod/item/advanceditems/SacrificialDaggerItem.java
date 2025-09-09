package com.everrell.magicmod.item.advanceditems;

import com.everrell.magicmod.api.capabilities.magic.SyncedSpellData;
import com.everrell.magicmod.api.mana.ManaData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import static net.minecraft.world.item.ThrowablePotionItem.PROJECTILE_SHOOT_POWER;

public class SacrificialDaggerItem extends Item {
    public SacrificialDaggerItem(Item.Properties p_41188_) {
        super(p_41188_);
    }
    public InteractionResult use(Level p_41190_, Player p_41191_, InteractionHand p_41192_) {
        ItemStack itemstack = p_41191_.getItemInHand(p_41192_);
        p_41190_.playSound((Player) null, p_41191_.getX(), p_41191_.getY(), p_41191_.getZ(), SoundEvents.PLAYER_HURT, SoundSource.NEUTRAL, 0.5F, 0.4F / (p_41190_.getRandom().nextFloat() * 0.4F + 0.8F));

        /*if (p_41190_ instanceof ServerLevel serverlevel) {

        }

         */
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
