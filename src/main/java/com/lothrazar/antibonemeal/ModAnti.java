package com.lothrazar.antibonemeal;

import org.apache.logging.log4j.Logger;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModAnti.MODID, useMetadata = true
//, updateJSON = "https://raw.githubusercontent.com/LothrazarMinecraftMods/SaplingGrowthControl/master/update.json"
)
public class ModAnti {

  public static final String MODID = "antibonemeal";
  public static Logger logger;
  @Instance(value = MODID)
  public static ModAnti instance;

  @EventHandler
  public void onPreInit(FMLPreInitializationEvent event) {
    logger = event.getModLog();
    ConfigHandler.loadConfig(new Configuration(event.getSuggestedConfigurationFile()));

    MinecraftForge.EVENT_BUS.register(this);
    MinecraftForge.EVENT_BUS.register(new DyeEventHandler());
  }


}
