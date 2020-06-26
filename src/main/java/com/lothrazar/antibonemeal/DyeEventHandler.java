package com.lothrazar.antibonemeal;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DyeEventHandler {

  final static int hours24 = 24000;
  final static int MIDNIGHT = 18;

  @SubscribeEvent
  public void onBone(BonemealEvent event) {
    World world = event.getWorld();
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

  private void doFireworks(PlayerEntity player, World world, double z, double y, double x) {
    if (world.rand.nextDouble() < 0.05) {
      Entity rocket = new FireworkRocketEntity(world, new ItemStack(Items.FIREWORK_ROCKET), player);
      rocket.setPosition(x, y, z);
      world.addEntity(rocket);
    }
  }

  private void doSmoke(World world, double z, double y, double x) {
    for (int i = 0; i < 20; i++) {
      world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.2D, 0.0D);
    }
  }

  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public void onItemTooltipEvent(ItemTooltipEvent event) {
    if (ConfigHandler.tooltipsEnabled() &&
        event.getItemStack().getItem() == Items.BONE_MEAL) {
      event.getToolTip().add(new TranslationTextComponent(ModAnti.MODID + ".item.bone_meal.tooltip")
          .func_240699_a_(TextFormatting.GRAY)
//          .applyTextStyle(TextFormatting.GRAY)
          );
    }
  }
}
