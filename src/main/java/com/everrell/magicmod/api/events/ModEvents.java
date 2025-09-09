package com.everrell.magicmod.api.events;

import com.everrell.magicmod.MaxsMagicMod;
import com.everrell.magicmod.api.mana.ManaData;
import com.everrell.magicmod.network.SyncManaPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.HashSet;
import java.util.Set;

@EventBusSubscriber(modid = MaxsMagicMod.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {


    /* @SubscribeEvent
    public static void livingDamage(LivingDamageEvent.Post event) {
        var entity = event.getEntity();
        if (event.getSource().getDirectEntity() instanceof Player player) {
            var pmg = ManaData.getPlayerMagicData(entity);
            pmg.addMana(player.getMaxHealth() - player.getHealth());
            if (entity instanceof ServerPlayer serverPlayer) {
                PacketDistributor.sendToPlayer(serverPlayer, new SyncManaPacket(pmg));
            }
        }
    }


    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent.Pre event) {
        var entity = event.getEntity();
        if (event.getSource().getDirectEntity() instanceof Player player) {
                player.displayClientMessage(Component.literal("you have " + ManaData.mana + " mana"), true);
                var pmg = ManaData.getPlayerMagicData(entity);
                pmg.addMana(player.getMaxHealth() - player.getHealth());
                if (entity instanceof ServerPlayer serverPlayer) {
                    PacketDistributor.sendToPlayer(serverPlayer, new SyncManaPacket(pmg));

            }
        }
    }

     */
    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent.Post event) {
        var entity = event.getEntity();
        if(event.getSource().getDirectEntity() instanceof Player player) {
            player.displayClientMessage(Component.literal("you have " + ManaData.mana + " mana"), true);
            /*
            var pmg = ManaData.getPlayerMagicData(entity);
            pmg.addMana(player.getMaxHealth() - player.getHealth());
*/


        }
    }
}