package com.lothrazar.antibonemeal;
public class ConfigHandler {

  private static boolean tooltips = true;
  private static boolean grassMidnight = true;

  public static boolean tooltipsEnabled() {
    tooltips = true
    ;
    return tooltips;
  }

  public static boolean grassMidnight() {
    return grassMidnight;
  }
  //  public static void loadConfig(Configuration config) {
  //    tooltips = config.getBoolean("ShowTooltips", ModAnti.MODID, true, "Show tooltips from this mod");
  //    grassMidnight = config.getBoolean("GrassMidnight", ModAnti.MODID, true, "Allows bonemeal to work during midnight, but only on grass (to grow flowers, maybe a rare firework).  If false it just never works on grass just like other blocks.");
  //    config.save();
  //  }
}
