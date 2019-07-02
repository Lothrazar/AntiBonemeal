package com.lothrazar.antibonemeal;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class ConfigHandler {

  private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
  public static ForgeConfigSpec COMMON_CONFIG;
  public static ForgeConfigSpec.BooleanValue TOOLTIPS;
  public static ForgeConfigSpec.BooleanValue GRASS_MIDNIGHT;

  static {
    initConfig();
  }

  private static void initConfig() {
    COMMON_BUILDER.comment("General settings").push(ModAnti.MODID);
    TOOLTIPS = COMMON_BUILDER.comment("Bonemeal Tooltip").define("itemTooltip", true);
    GRASS_MIDNIGHT = COMMON_BUILDER.comment("Allows bonemeal to work during midnight, "
        + "but only on grass (to grow flowers, maybe a rare firework).  "
        + "If false it just never works on grass just like other blocks. ")
        .define("grassMidnight", true);
    COMMON_BUILDER.pop();
    COMMON_CONFIG = COMMON_BUILDER.build();
  }

  public static boolean tooltipsEnabled() {
    return TOOLTIPS.get();
  }

  public static boolean grassMidnight() {
    return GRASS_MIDNIGHT.get();
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
