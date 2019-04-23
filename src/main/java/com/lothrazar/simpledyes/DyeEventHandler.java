package com.lothrazar.simpledyes;

import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DyeEventHandler {

  @SubscribeEvent
  public void onBone(BonemealEvent event) {
    event.setCanceled(true);
  }
}
