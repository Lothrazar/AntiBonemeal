package com.lothrazar.antibonemeal;

import com.lothrazar.library.util.ParticleUtil;
import com.lothrazar.library.util.TimeUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DyeEventHandler {

  @SubscribeEvent
  public void onBone(BonemealEvent event) {
    Level world = event.getLevel();
    double x = event.getPos().getX() + .5;
    double y = event.getPos().getY();
    double z = event.getPos().getZ() + .5;
    if (ConfigRegistryAnti.grassMidnight()
        && event.getBlock().getBlock() == Blocks.GRASS_BLOCK
        && TimeUtil.isWithinHoursOfMidnight(world, 2)) {
      //meaning you can bonemeal grass now wooo
      if (world.random.nextDouble() < 0.05) {
        ParticleUtil.doFireworks(event.getEntity(), world, z, y, x);
      }
    }
    else {
      //cancel the event as normal, since we dont meet exception conditions + config
      ParticleUtil.doSmoke(world, z, y, x);
      event.setCanceled(true);
    }
  }

  @SubscribeEvent
  public void onItemTooltipEvent(ItemTooltipEvent event) {
    if (ConfigRegistryAnti.tooltipsEnabled() && event.getItemStack().is(Items.BONE_MEAL)) {
      event.getToolTip()
          .add(Component.translatable(ModAnti.MODID + ".item.bone_meal.tooltip")
              .withStyle(ChatFormatting.GRAY));
    }
  }
}
