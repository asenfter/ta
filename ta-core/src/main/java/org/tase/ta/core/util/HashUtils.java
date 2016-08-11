package org.tase.ta.core.util;

import org.tase.ta.core.aware.FuzzyAware;

/**
 * @author Andreas Senfter (https://github.com/asenfter)
 */
public class HashUtils implements FuzzyAware {

    /*
     * Calculates hashes by ignoring letter ordering after 4 characters
     * "textanalytics" matches "textanaltyics"
     */
    public static int semanticHashOf(char[] chars) {
        final int len = Math.min(LENGTH_THRESHOLD, chars.length);
        final int len1 = Math.min(4, chars.length);
        int hashValue1 = chars[0];
        for (int i = 1; i < len1; i++) {
            hashValue1 = 11 * hashValue1 + chars[i];
        }
        int hashValue = 0;
        if (chars.length >= len1) {
            for (int i = len1; i < len; i += 2) {
                final int value;
                if (i + 1 < len) {
                    value = chars[i] + chars[i + 1];
                } else {
                    value = chars[i];
                }
                hashValue = 17 * hashValue + value;
            }
        }
        return hashValue1 + hashValue;
    }
}