/*
 * This program was designed for a arduino Uno, or any similar device that can communicate over 
 * serial USB.
 * ---------------------------------------------------------
 * This program is designed to have the arduino itself run custom code, so do not expect
 * that you can run this on any random arduino. The code required to get the arduino to work
 * can be found as a .ino file recognizable by the arduino IDE, within the zip package of this
 * program.
 */
package Unused;

import backend_Models.GeneralSettingsException;
import frontend_View.Settings;
import java.awt.Color;

/**
 *
 * 
 * @author kell-gigabyte
 */
public class AutoPattern extends Pattern {

    public AutoPattern(Settings s) throws GeneralSettingsException {
        super(s);

    }

    /**
     * changes pixel x RGB values to x% to what they were ((color / 100) *
     * percentage).
     *
     * @param percentage
     * @param pixel
     */
    public void smartColorChange(int percentage, int pixel) {
    }

    /**
     * Creates a wave of colors. Use c1-c5 for colors of the wave, null if no
     * color, speed is the speed, and fadeAmoutn determines how much each color
     * mixes in between one another. Each non-null color is equally distant from
     * its neighbors.
     *
     * @param c1
     * @param c2
     * @param c3
     * @param c4
     * @param c5
     * @param speed
     * @param fadeAmount
     */
    public void wave(Color c1, Color c2, Color c3, Color c4, Color c5, int speed, int fadeAmount) {
    }

    /**
     * Causes Color c1 to start a moving wave of Color x wide and y fast going
     * down the Strip. ripple decides if the wave goes down the strip in both
     * directions, and if that is false, then the boolean reverse decides which
     * way the flash will go.
     *
     * @param c1
     * @param speed
     * @param flashWidth
     * @param ripple
     * @param beginPixel
     * @param reverse
     */
    public void flash(Color c1, int speed, int flashWidth, boolean ripple, int beginPixel, boolean reverse) {
    }

    /**
     * sets all pixels to a single color, slowly shifting between Colors c1 and
     * c2 at a rate of x.
     *
     * @param c1
     * @param c2
     * @param speed
     */
    public void breathing(Color c1, Color c2, int speed) {

    }

    /**
     * sets all pixels to a single color, slowly shifting between two Random
     * Colors at a rate of x.
     *
     * @param speed
     */
    public void randomBreathing(int speed) {
    }

    /**
     * Goes through the entire RGB spectrum x fast. Sets all pixels to the same
     * Color.
     *
     * @param speed
     */
    public void rainbow(int speed) {

    }

}
