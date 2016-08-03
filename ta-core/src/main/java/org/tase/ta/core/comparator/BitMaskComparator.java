package org.tase.ta.core.comparator;

import org.tase.ta.core.util.BitUtils;

import java.util.Comparator;

/**
 * Very fast scorer for equality of char sequences.
 * Ignores ordering and multiple char occurrences.
 */
public class BitMaskComparator implements Comparator<String> {

    private static final float DEFAULT_MATCH_WEIGHT = 2F;
    private static final float DEFAULT_LENGTH_WEIGHT = 1F;
    private static final float DEFAULT_BASE = 100F * DEFAULT_MATCH_WEIGHT * DEFAULT_LENGTH_WEIGHT;

    private final float matchWeight;
    private final float lengthWeight;
    private final float base;

    public BitMaskComparator() {
        matchWeight = DEFAULT_MATCH_WEIGHT;
        lengthWeight = DEFAULT_LENGTH_WEIGHT;
        base = DEFAULT_BASE;
    }

    public BitMaskComparator(float matchWeight, float lengthWeight) {
        this.matchWeight = matchWeight;
        this.lengthWeight = lengthWeight;
        this.base = 100F * matchWeight * lengthWeight;
    }

    @Override
    public int compare(String word1, String word2) {
        final long mask1 = BitUtils.bitMask(word1.toCharArray());
        final long mask2 = BitUtils.bitMask(word2.toCharArray());
        final int diff = Long.bitCount(mask1 ^ mask2);
        int match = Long.bitCount(mask1 & mask2);
        final int len = word1.length() + word2.length();
        int lenDiff = Math.abs(word1.length() - word2.length());
        return (int) (base * len * match / ((matchWeight * match + diff) * (lengthWeight * len + lenDiff)));
    }
}
