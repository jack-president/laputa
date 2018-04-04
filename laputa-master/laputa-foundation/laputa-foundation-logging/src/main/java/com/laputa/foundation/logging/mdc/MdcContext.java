package com.laputa.foundation.logging.mdc;


import org.slf4j.MDC;

import java.util.UUID;

/**
 * Created by jiangdongping on 2017/8/28 0028.
 */
public class MdcContext {
    public static final String TRANSID = "transId";
    public static final String USERID = "userId";

    public static void logTransId() {
        UUID uuid = UUID.randomUUID();
        MDC.put(TRANSID, toNoSplitString(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits()));
    }

    static void logTransId(String transId) {
        MDC.put(TRANSID, transId);
    }

    public static void clear() {
        MDC.clear();
    }

    /**
     * 获取当前调用链事务id
     *
     * @return
     */
    public static String getTransId() {
        Object t = MDC.get(TRANSID);
        return t != null ? (String) t : null;
    }

    public static void setUserId(String userId) {
        MDC.put(USERID, userId);
    }

    /**
     * 获取当前操作用户userId
     *
     * @return
     */
    public static String getUserId() {
        Object t = MDC.get(USERID);
        return t != null ? (String) t : null;
    }

    private static String toNoSplitString(long mostSigBits, long leastSigBits) {
        return (digits(mostSigBits >> 32, 8) +
                digits(mostSigBits >> 16, 4) +
                digits(mostSigBits, 4) +
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
