package tk.dwcdn.switefaster.cubefactory.utils;

import javax.vecmath.Vector2d;
import java.math.BigDecimal;

/**
 * @author switefaster
 */
public class CubeFactoryUtilities {

    public static String doubleToFormattenString(double in) {
        if (in < 0) {
            return "?";
        } else if (in == 0) {
            return "0.0 ng";
        }
        int log = (int) Math.log10(in);
        if (log <= -4) {
            return Double.toString(in * 1e6) + " ng";
        } else if (log <= -1) {
            return Double.toString(in * 1e3) + " μg";
        } else if (log <= 2) {
            return Double.toString(in) + " mg";
        } else if (log <= 5) {
            return Double.toString(in / 1e3) + " g";
        } else if (log <= 8) {
            return Double.toString(in / 1e6) + " kg";
        } else {
            return Double.toString(in / 1e9) + " t";
        }
    }

    public static boolean isMouseInRect(int mouseX, int mouseY, int left, int top, int right, int bottom, int offsetX, int offsetY)
    {
        return mouseX >= offsetX + left && mouseY >= offsetY + top && mouseX <= offsetX + right && mouseY <= offsetY + bottom;
    }

}
