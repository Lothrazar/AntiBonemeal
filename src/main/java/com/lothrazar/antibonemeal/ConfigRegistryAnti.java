package com.lothrazar.antibonemeal;

import com.lothrazar.library.config.ConfigTemplate;
import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigRegistryAnti extends ConfigTemplate {

  private static final ForgeConfigSpec CONFIG;
  public static ForgeConfigSpec.BooleanValue TOOLTIPS;
  public static ForgeConfigSpec.BooleanValue GRASS_MIDNIGHT;
  static {
    final ForgeConfigSpec.Builder BUILDER = builder();
    BUILDER.comment("General settings").push(ModAnti.MODID);
    TOOLTIPS = BUILDER.comment("Bonemeal Tooltip").define("itemTooltip", true);
    GRASS_MIDNIGHT = BUILDER.comment("Allows bonemeal to work during midnight, "
        + "but only on grass (to grow flowers, maybe a rare firework).  "
        + "If false it just never works on grass just like other blocks. ")
        .define("grassMidnight", true);
    BUILDER.pop();
    CONFIG = BUILDER.build();
  }

  public static boolean tooltipsEnabled() {
    return TOOLTIPS.get();
  }

  public static boolean grassMidnight() {
    return GRASS_MIDNIGHT.get();
  }

  public ConfigRegistryAnti() {
    CONFIG.setConfig(setup(ModAnti.MODID));
  }
}
