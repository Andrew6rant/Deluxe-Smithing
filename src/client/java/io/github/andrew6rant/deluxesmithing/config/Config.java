package io.github.andrew6rant.deluxesmithing.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class Config extends MidnightConfig {
    @Comment(centered = true) public static Comment immediate;
    @Entry(min = -1000, max = 1000) public static int armorStandRenderX = 143; //141
    @Entry(min = -1000, max = 1000) public static int armorStandRenderY = 70; //75
    @Entry(min = -1000, max = 1000) public static int armorStandRenderSize = 30; //25
    @Entry(min = -1, max = 1) public static float rotationSpeed = 0.0035f;

    @Entry public static ArmorStandRenderEnum armorStandRenderEnum = ArmorStandRenderEnum.ROTATE;
    public enum ArmorStandRenderEnum {
        ROTATE, FOLLOW_MOUSE
    }

}
