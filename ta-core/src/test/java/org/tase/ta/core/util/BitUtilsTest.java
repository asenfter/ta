package org.tase.ta.core.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tase.ta.core.comparator.BitMaskComparator;

public class BitUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BitUtilsTest.class);

    @Test
    public void test() {
        doTest("textanalytics", "exactly");
        doTest("vienna", "naive");
        doTest("ocean", "canoe");
        doTest("computerscience", "competencies");
    }

    public void doTest(String string1, String string2) {
        final BitMaskComparator comparator = new BitMaskComparator();
        final int score = comparator.compare(string1, string2);
        LOGGER.debug("word1={}, word2={}, score={}", string1, string2, score);
    }
}
