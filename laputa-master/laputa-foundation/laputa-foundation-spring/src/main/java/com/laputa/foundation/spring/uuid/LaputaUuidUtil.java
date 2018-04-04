package com.laputa.foundation.spring.uuid;

import java.util.UUID;

/**
 * Created by jiangdongping on 2017/8/28 0028.
 */
public final class LaputaUuidUtil {
    public static String randomUuid() {
        UUID uuid = UUID.randomUUID();
        return toNoSplitString(uuid.getMostSignificantBits(), uuid.getMostSignificantBits());
    }


    private static String toNoSplitString(long mostSigBits, long leastSigBits) {
        return (digits(mostSigBits >> 32, 8) +
                digits(mostSigBits >> 16, 4) +
                digits(mostSigBits, 4) + "-" +
                digits(leastSigBits >> 48, 4) +
                digits(leastSigBits, 12));
    }

    /**
     * Returns val represented by the specified number of hex digits.
     */
    private static String digits(long val, int digits) {
        long hi = 1L << (digits * 4);
        return Long.toHexString(hi | (val & (hi - 1))).substring(1);
    }
}
