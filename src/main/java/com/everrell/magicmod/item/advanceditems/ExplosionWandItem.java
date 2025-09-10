package com.everrell.magicmod.item.advanceditems;

import com.everrell.magicmod.api.attribute.AttributeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.event.level.ExplosionEvent;

public class ExplosionWandItem extends Item{
    public ExplosionWandItem(Item.Properties p_41188_) {
        super(p_41188_);
    }

    public InteractionResult use(Level p_41190_, Player p_41191_, InteractionHand p_41192_) {
        ItemStack itemstack = p_41191_.getItemInHand(p_41192_);
        if (p_41190_ instanceof ServerLevel serverlevel) {
            if (p_41191_.getAttributes().getValue(AttributeRegistry.MANA) > 100) {

                    p_41190_.playSound((Player) null, p_41191_.getX(), p_41191_.getY(), p_41191_.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (p_41190_.getRandom().nextFloat() * 0.4F + 0.8F));
                p_41190_.explode(null, p_41191_.pick(50, 1, false).getLocation().x, p_41191_.pick(50, 1, false).getLocation().y, p_41191_.pick(50, 1, false).getLocation().z,4.0F,  Level.ExplosionInteraction.BLOCK);

            }
            p_41191_.displayClientMessage(Component.literal("you have " + p_41191_.getAttributes().getValue(AttributeRegistry.MANA) + " mana"), true);
        }
        return InteractionResult.SUCCESS;
    }
}
