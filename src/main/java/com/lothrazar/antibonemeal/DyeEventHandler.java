package com.lothrazar.antibonemeal;
import net.minecraft.block.Blocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.FireworkRocketEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
      event.setCanceled(true);
    }
  }

  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public void onItemTooltipEvent(ItemTooltipEvent event) {
    if (ConfigHandler.tooltipsEnabled() &&
        event.getItemStack().getItem() == Items.BONE_MEAL) {
      String tip = I18n.format("item.bone_meal.tooltip");
      event.getToolTip().add(new TranslationTextComponent(tip));
    }
  }
}
