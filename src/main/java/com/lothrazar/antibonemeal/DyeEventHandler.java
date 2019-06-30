package com.lothrazar.antibonemeal;

import javafx.geometry.Side;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.entity.item.FireworkRocketEntity;

public class DyeEventHandler {

  final static int hours24 = 24000;

  @SubscribeEvent
  public void onBone(BonemealEvent event) {
    if (ConfigHandler.grassMidnight()
        && event.getBlock().getBlock().equals(Blocks.GRASS)) {
      World world = event.getWorld();
      long worldtime = world.getGameTime();
      long timeWithinDay = worldtime % hours24;
      int hour = ((int) timeWithinDay) / 1000;
      if (hour < 17 || hour > 19) {
        //hour 18 is midnight in MC world
      //  ModAnti.LOGGER.error("YPE");
        event.setCanceled(true);
      }
      else {// NOT CANCELLED
        //meaning you can bonemeal grass now wooo
        //particles
        //fireworks 
        if (world.rand.nextDouble() < 0.05) {
          Entity rocket = new FireworkRocketEntity(world, new ItemStack(Items.FIREWORK_ROCKET), event.getEntityPlayer());
          rocket.setPosition(event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
          world.addEntity(rocket);

        }
      }
    }
    else {
      //either im not grass. or i am grass and config is turned off
    //ModAnti.LOGGER.error("!!asdfasdfYPEasdfsde");
     event.setCanceled(true);
    }
  }
//
//  @SuppressWarnings({ "deprecation", "incomplete-switch" })
//  @OnlyIn(Dist.CLIENT)
//  @SubscribeEvent
//  public void onItemTooltipEvent(ItemTooltipEvent event) {
//    if (ConfigHandler.tooltipsEnabled() &&
//        event.getItemStack().getItem() == Items.DYE) {
//      int meta = event.getItemStack().getMetadata();
//      EnumDyeColor value = EnumDyeColor.byDyeDamage(meta);
//      switch (value) {
//        case BLACK:
//        case GREEN:
//        case RED:
//        case YELLOW:
//        case WHITE:
//        case BROWN:
//        case BLUE:
//          event.getToolTip().add(I18n.translateToLocal("item.dyePowder." + value.name().toLowerCase() + ".tooltip"));
//        break;
//      }
//    }
//  }
}
