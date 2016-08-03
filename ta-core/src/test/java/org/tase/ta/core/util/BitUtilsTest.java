package org.tase.ta.core.util;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tase.ta.core.comparator.BitMaskComparator;

public class BitUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BitUtilsTest.class);

    @Test
    public void test() {
        doTest("textanalytics", "exactly", 63);
        doTest("vienna", "naive", 91);
        doTest("ocean", "canoe", 100);
        doTest("computerscience", "competencies", 81);
    }

    public void doTest(String string1, String string2, int value) {
        final BitMaskComparator comparator = new BitMaskComparator();
        final int score = comparator.compare(string1, string2);
        Assert.assertEquals(value, score);
        LOGGER.debug("word1={}, word2={}, score={}", string1, string2, score);
    }
}
