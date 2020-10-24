package com.colordrum;

import com.badlogic.gdx.graphics.Color;

public class ColorUtil {

    public enum Colors {
        GREEN(148, 219, 112), RED(241, 58, 19), BLUE(77, 113, 152);

        float[] rgb;

        Colors(float r, float g, float b) {
            rgb = new float[]{
                    r / 255, g / 255, b / 255
            };
        }

        public float[] getRgb() {
            return rgb;
        }

        public Color getColor() {
            return RGBToColor(getRgb());
        }
    }

    public static Color RGBToColor (float[] rgb) {
        return new Color(
                rgb[0],
                rgb[1],
                rgb[2], 1);
    }

    public static Color darkenColor (Color color, float darken) {
        float[] rgb = ColorToRGB(color);

        for (int i = 0; i < rgb.length; i++) {
            rgb[i] -= darken;
        }

        return RGBToColor(rgb);
    }

    public static float[] ColorToRGB(Color color) {
        return new float[]{
                color.r,
                color.g,
                color.b
        };
    }

    public static boolean colorEquals(Color color, Color color2) {
        return color.r == color2.r &&
                color.g == color2.g &&
                color.b == color.b;
    }
}
