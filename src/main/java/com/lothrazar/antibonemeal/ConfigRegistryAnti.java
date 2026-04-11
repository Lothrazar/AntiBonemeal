package com.lothrazar.antibonemeal;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ConfigRegistryAnti {

  static final ModConfigSpec CONFIG;
  public static ModConfigSpec.BooleanValue TOOLTIPS;
  public static ModConfigSpec.BooleanValue GRASS_MIDNIGHT;

  static {
    final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
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
}
