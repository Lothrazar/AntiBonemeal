package com.lothrazar.simpledyes;

import org.apache.logging.log4j.Logger;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModDyes.MODID, useMetadata = true
//, updateJSON = "https://raw.githubusercontent.com/LothrazarMinecraftMods/SaplingGrowthControl/master/update.json"
)
public class ModDyes {

  public static final String MODID = "simpledyes";
  public static Logger logger;
  @Instance(value = MODID)
  public static ModDyes instance;

  @EventHandler
  public void onPreInit(FMLPreInitializationEvent event) {
    logger = event.getModLog();
    //    ModConfig.loadConfig(new Configuration(event.getSuggestedConfigurationFile()));
    //
    //    GrowthHandler grower = new GrowthHandler();
    //    MinecraftForge.TERRAIN_GEN_BUS.register(grower);
    MinecraftForge.EVENT_BUS.register(new DyeEventHandler());
  }

  public static void log(String string) {
    //    if (ModConfig.spam()) {
    //      logger.info(string);
    //    }
  }
}
