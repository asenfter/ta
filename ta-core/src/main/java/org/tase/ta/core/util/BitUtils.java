package org.tase.ta.core.util;

/**
 * @author Andreas Senfter (https://github.com/asenfter)
 */
public class BitUtils {
    //encode to 64Bit
    public static long bitMask(char[] chars) {
        long mask = 0;
        for (char c : chars) {
            mask |= (1L << (c % 63));
        }
        return mask;
    }
}
