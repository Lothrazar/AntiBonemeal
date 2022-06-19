package com.lothrazar.antibonemeal;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DyeEventHandler {

  final static int hours24 = 24000;
  final static int MIDNIGHT = 18;

  @SubscribeEvent
  public void onBone(BonemealEvent event) {
    Level world = event.getWorld();
    double x = event.getPos().getX() + .5;
    double y = event.getPos().getY();
    double z = event.getPos().getZ() + .5;
    if (ConfigHandler.grassMidnight()
        && event.getBlock().getBlock().equals(Blocks.GRASS)) {
      long worldTime = world.getGameTime();
      long timeWithinDay = worldTime % hours24;
      int hour = ((int) timeWithinDay) / 1000;
      if (hour < MIDNIGHT - 2 || hour > MIDNIGHT + 2) {
        //hour 18 is midnight in MC world
        doSmoke(world, z, y, x);
        event.setCanceled(true);
      }
      else {// NOT CANCELLED
        //meaning you can bonemeal grass now wooo
        doFireworks(event.getPlayer(), world, z, y, x);
      }
    }
    else {
      //either im not grass. or i am grass and config is turned off
      doSmoke(world, z, y, x);
      event.setCanceled(true);
    }
  }

  private void doFireworks(Player player, Level world, double z, double y, double x) {
    if (world.random.nextDouble() < 0.05) {
      Entity rocket = new FireworkRocketEntity(world, new ItemStack(Items.FIREWORK_ROCKET), player);
      rocket.setPos(x, y, z);
      world.addFreshEntity(rocket);
    }
  }

  private void doSmoke(Level world, double z, double y, double x) {
    for (int i = 0; i < 20; i++) {
      world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.2D, 0.0D);
    }
  }

  @SubscribeEvent
  public void onItemTooltipEvent(ItemTooltipEvent event) {
    if (ConfigHandler.tooltipsEnabled() &&
        event.getItemStack().getItem() == Items.BONE_MEAL) {
      event.getToolTip().add(Component.translatable(ModAnti.MODID + ".item.bone_meal.tooltip")
          .withStyle(ChatFormatting.GRAY));
    }
  }
}
