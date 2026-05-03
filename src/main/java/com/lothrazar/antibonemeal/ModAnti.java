package com.lothrazar.antibonemeal;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;

@Mod(ModAnti.MODID)
public class ModAnti {

  public static final String MODID = "antibonemeal";

  public ModAnti(IEventBus modEventBus, ModContainer modContainer) {
    NeoForge.EVENT_BUS.register(new DyeEventHandler());
    modContainer.registerConfig(ModConfig.Type.COMMON, ConfigRegistryAnti.CONFIG);
  }
}
