/*
 * Created By Kell Larson for the Computer Science AP program during school year 2016/2017. Please ask before copying code.
 */
package Unused;

import backend_Models.Pixel;
import backend_Models.PixelValueException;
import java.util.Random;

/*
This class is no longer used because of the slow serial communication between arduino and computer. Using
1 byte per color, e.g. 0-255 for red, green, and blue, as well as 2 bytes to address the pixel, results in a max 
baud rate of ~15500. This results in a ~5 Hz refresh rate for 64 LEDs in a strip, growing slower as the LED strip
gets longer. Even using just 3 bytes, the colour values, and assuming it's pixel after pixel, it did not increase the
parse speed of the arduino.

So, a refresh rate of 5 Hz is what could be called "dogsh**", so I changed the pattern creation radically. Instead of
sending 3-5 bytes per pixel, I coded the patterns into the arduino. This meant I had to send 1 string
with a char, then up to 40 bits of arguments in the form of colors and delays, that the ardunio parsed. Then it ran
the pattern 5 times, then checked if the serial buffer had any new commands. This will hopefully lead to a
refresh rate similar to one without serial comms, which was so fast a red to blue to green loop looked white
to the eyes.

Anyway, this all makes this class useless. Left it here if anyones wants it.
*/


/**
 *
 * @author Kell
 */
public abstract class PixelOld extends Pixel {

    /**
     * This class is meant to extend the Pixel object, providing addition utility
 to it. Should be able to do simple math equations for setting RGB values,
     * like in the wave if x = 5 and y = 20, then set the RGB value to 1/3 of y
     * + 2/3 of x.
     *
     * Pretty much just does simple maths, to provide functionality to writing
     * your own patterns. These methods are what you can use.
     */
    private Random rng = new Random();

    public PixelOld(int addressX, int addressY, int addressPwm) {
        super(addressX, addressY, addressPwm);
        throw new UnsupportedOperationException("This class is depreciated, use at your own risk.");
    }

    public PixelOld(int addressX) {
        super(addressX);
        throw new UnsupportedOperationException("This class is depreciated, use at your own risk.");
    }

    /**
     * Sets the average colour of two points intelligently, calculates the
     * distance between them to have a fraction of each related to the distance
     * this pixel is to each point on the X axis
     *
     * @param point1 needs to be behind this pixel (to the left)
     * @param point2 needs to be in front of this pixel (to the right)
     *
     * @nonfuctional
     * @experimental
     */
    public void setAverageXRGB(PixelOld point1, PixelOld point2) {
        int x1 = point1.getPosX();
        int x2 = point2.getPosX();
        int xMe = this.getPosX();

        int distx1x2 = x2 - x1;
        int distx1xMe = xMe - x1;
        int distx2xMe = x2 - xMe;   // x2 is ahead of x1

        double fractionx2xMe = distx2xMe / ((double) distx1x2);
        double fractionx1xMe = distx1xMe / ((double) distx1x2);

        int avgRedx1xMe = (point1.getRed() + this.getRed()) / 2;
        int avgBluex1xMe = (point1.getRed() + this.getRed()) / 2;
        int avgGreenx1xMe = (point1.getRed() + this.getRed()) / 2;

        int avgRedx2xMe = (point2.getRed() + this.getRed()) / 2;
        int avgBluex2xMe = (point2.getRed() + this.getRed()) / 2;
        int avgGreenx2xMe = (point2.getRed() + this.getRed()) / 2;

        int avgRed = (int) (avgRedx1xMe * fractionx1xMe + avgRedx2xMe * fractionx1xMe);
        int avgBlue = (int) (avgBluex1xMe * fractionx1xMe + avgBluex2xMe * fractionx1xMe);
        int avgGreen = (int) (avgGreenx1xMe * fractionx1xMe + avgGreenx2xMe * fractionx1xMe);

        if (avgRed < 256) {
            PixelValueException g = new PixelValueException("red ", avgRed);
            throw g;
        }
        if (avgBlue < 256) {
            PixelValueException g = new PixelValueException("blue ", avgBlue);
            throw g;
        }
        if (avgGreen < 256) {
            PixelValueException g = new PixelValueException("green ", avgGreen);
            throw g;
        }

        this.setRGB(avgRed, avgGreen, avgBlue);

        /**
         * total distance between x1 and x2 not including x1 and x2
         *
         * distance between xMe and x1, xMe and x2
         *
         * fraction of how far each is, for example:
         *
         * if x1 is @ 1 and x2 is @ 10, and xMe is @ 7, then 6 between x1 and
         * xMe and 3 between x2 and xMe. so it'd be 2/3 the colour of x2 and 1/3
         * the colour of x1 inputted onto xMe.
         */
    }

    /**
     * Sets the average colour of two pixels onto this PixelOld
     *
     * @param point1
     * @param point2
     */
    public void setAverageDumbRGB(PixelOld point1, PixelOld point2) {
        this.setRGB(((point1.getRed() + point2.getRed()) / 2), ((point1.getGreen() + point2.getGreen()) / 2), ((point1.getBlue() + point2.getBlue()) / 2));
    }
}
