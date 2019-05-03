package com.lothrazar.antibonemeal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
//import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DyeEventHandler {

  final static int hours24 = 24000;

  @SubscribeEvent
  public void onBone(BonemealEvent event) {
    if (ConfigHandler.grassMidnight()
        && event.getBlock().getBlock().equals(Blocks.GRASS)) {
      World world = event.getWorld();
      long worldtime = world.getWorldTime();
      long timeWithinDay = worldtime % hours24;
      int hour = ((int) timeWithinDay) / 1000;
      if (hour < 17 || hour > 19) {
        //hour 18 is midnight in MC world 
        event.setCanceled(true);
      }
      else {// NOT CANCELLED
        //meaning you can bonemeal grass now wooo
        //particles
        //fireworks 
        if (world.rand.nextDouble() < 0.05) {
          Entity rocket = new EntityFireworkRocket(world);
          rocket.setPosition(event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
          world.spawnEntity(rocket);
        }
      }
    }
    else {
      //either im not grass. or i am grass and config is turned off
      event.setCanceled(true);
    }
  }

  @SuppressWarnings({ "deprecation", "incomplete-switch" })
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onItemTooltipEvent(ItemTooltipEvent event) {
    if (ConfigHandler.tooltipsEnabled() &&
        event.getItemStack().getItem() == Items.DYE) {
      int meta = event.getItemStack().getMetadata();
      EnumDyeColor value = EnumDyeColor.byDyeDamage(meta);
      switch (value) {
        case BLACK:
        case GREEN:
        case RED:
        case YELLOW:
        case WHITE:
        case BROWN:
        case BLUE:
          event.getToolTip().add(I18n.translateToLocal("item.dyePowder." + value.name().toLowerCase() + ".tooltip"));
        break;
      }
    }
  }
}
