package org.tase.ta.core.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Andreas Senfter (https://github.com/asenfter)
 */
public class HashUtilsTest {

    private static final String[] STRINGS = new String[]{"textanalytics", "textanlayti", "textanaltyics", "textanlaytics"};

    @Test
    public void test() {
        int hashValue = HashUtils.semanticHash(STRINGS[0].toCharArray());
        for (int i = 1; i < STRINGS.length; i++) {
            final int current = HashUtils.semanticHash(STRINGS[i].toCharArray());
            Assert.assertEquals(hashValue, current);
            hashValue = current;
        }
    }

    @Test
    public void testForMap() {
        final Map<TestKey, AtomicInteger> trigger = new HashMap<>();

        for (String string : STRINGS) {
            final TestKey testKey = new TestKey(string);
            AtomicInteger count = trigger.get(testKey);
            if (count == null) {
                count = new AtomicInteger(0);
                trigger.put(testKey, count);
            }
            count.incrementAndGet();
        }

        Assert.assertEquals(1, trigger.size());
        Assert.assertEquals(4, trigger.get(new TestKey(STRINGS[0])).get());
    }

    private static class TestKey {
        private final String data;

        public TestKey(String data) {
            this.data = data;
        }

        @Override
        public int hashCode() {
            return HashUtils.semanticHash(data.toCharArray());
        }

        @Override
        public boolean equals(Object obj) {
            // ignore for hashing test
            return true;
        }
    }
}
