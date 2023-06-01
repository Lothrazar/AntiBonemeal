package com.lothrazar.antibonemeal;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(ModAnti.MODID)
public class ModAnti {

  public static final String MODID = "antibonemeal";

  public ModAnti() {
    MinecraftForge.EVENT_BUS.register(new DyeEventHandler());
    new ConfigRegistryAnti();
  }
}
