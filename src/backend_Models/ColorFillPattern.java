/*
 * This program was designed for a arduino Uno, or any similar device that can communicate over 
 * serial USB.
 * ---------------------------------------------------------
 * This program is designed to have the arduino itself run custom code, so do not expect
 * that you can run this on any random arduino. The code required to get the arduino to work
 * can be found as a .ino file recognizable by the arduino IDE, within the zip package of this
 * program.
 */
package backend_Models;

import app_Controller.Kaizen_85;
import frontend_ViewController.Settings;

/**
 *
 * @author kell-gigabyte
 */
public class ColorFillPattern extends Pattern {

    private final int CMDNUMBERNODELAY = 3;
    private final int CMDNUMBERDELAY = 4;

    public ColorFillPattern(Settings set, SerialComms serial) {
        super(set, serial);
    }

    @Override
    public void startPattern() {
        Kaizen_85.newEvent("Color Fill Pattern Started.");

        if (this.getDelay() == 0) {
            byte[] b = new byte[4];
            b[0] = (byte) this.CMDNUMBERNODELAY;
            b[1] = (byte) (this.getRed() & 0xFF);
            b[2] = (byte) (this.getGreen() & 0xFF);
            b[3] = (byte) (this.getBlue() & 0xFF);
            getSerialComms().write(b);
        } else {
            byte[] b = new byte[6];
            b[0] = (byte) this.CMDNUMBERDELAY;
            b[1] = (byte) (this.getRed() & 0xFF);
            b[2] = (byte) (this.getGreen() & 0xFF);
            b[3] = (byte) (this.getBlue() & 0xFF);
            b[4] = (byte) ((getDelay() >> 8)& 0xFF);
            b[5] = (byte) (getDelay()& 0xFF);
            getSerialComms().write(b);
        }
    }

}