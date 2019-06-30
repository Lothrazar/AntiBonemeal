package com.lothrazar.antibonemeal;

import com.lothrazar.antibonemeal.setup.ClientProxy;
import com.lothrazar.antibonemeal.setup.IProxy;
import com.lothrazar.antibonemeal.setup.ServerProxy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.common.ForgeConfigSpec;
@Mod(ModAnti.MODID)
public class ModAnti {

  public static final String MODID = "antibonemeal";

  public static final IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
  public static final Logger LOGGER = LogManager.getLogger();

  public ModAnti() {
    // Register the setup method for modloading
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
//
//    final ModLoadingContext modLoadingContext = ModLoadingContext.get();
//    // Register Configs
//    ForgeConfigSpec CONFIG = new ForgeConfigSpec.Builder().build();
//    modLoadingContext.registerConfig(ModConfig.Type.COMMON, CONFIG);
//    this.config = new ConfigHandler(CONFIG);

    //only for server starting
    MinecraftForge.EVENT_BUS.register(this);
    MinecraftForge.EVENT_BUS.register(new DyeEventHandler());
  }

  private void setup(final FMLCommonSetupEvent event) {
    // some preinit code
    LOGGER.info("HELLO FROM PREINIT");
  }

}
