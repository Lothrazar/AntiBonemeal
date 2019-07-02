package com.lothrazar.antibonemeal;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class ConfigHandler {

  private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
  // private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
  public static ForgeConfigSpec COMMON_CONFIG;
  // public static ForgeConfigSpec CLIENT_CONFIG;
  public static ForgeConfigSpec.BooleanValue TOOLTIPS;
  public static ForgeConfigSpec.BooleanValue GRASS_MIDNIGHT;

  static {
    initConfig();
  }

  private static void initConfig() {
    COMMON_BUILDER.comment("General settings").push(ModAnti.MODID);
    //    COMMON_BUILDER.pop();
    //    COMMON_BUILDER.comment("Power settings").push(CATEGORY_POWER);
    TOOLTIPS = COMMON_BUILDER.comment("Bonemeal Tooltip").define("itemTooltip", true);
    GRASS_MIDNIGHT = COMMON_BUILDER.comment("Allows bonemeal to work during midnight, "
        + "but only on grass (to grow flowers, maybe a rare firework).  "
        + "If false it just never works on grass just like other blocks. ")
        .define("grassMidnight", true);
    COMMON_BUILDER.pop();
    COMMON_CONFIG = COMMON_BUILDER.build();
    // CLIENT_CONFIG = CLIENT_BUILDER.build();
  }

  public static boolean tooltipsEnabled() {
    return TOOLTIPS.get();
  }

  public static boolean grassMidnight() {
    return GRASS_MIDNIGHT.get();
  }

  @SubscribeEvent
  public static void onLoad(final ModConfig.Loading configEvent) {
  }

  @SubscribeEvent
  public static void onReload(final ModConfig.ConfigReloading configEvent) {
  }

  public static void loadConfig(ForgeConfigSpec spec, Path path) {
    final CommentedFileConfig configData = CommentedFileConfig.builder(path)
        .sync()
        .autosave()
        .writingMode(WritingMode.REPLACE)
        .build();
    configData.load();
    spec.setConfig(configData);
  }
}
