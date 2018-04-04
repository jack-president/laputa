package com.laputa.foundation.common.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by jiangdongping on 2018/2/25 0025.
 */
public class GsonUtil {
    public static final Gson gsonInstance;

    static {
        gsonInstance = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    public static Gson getGsonInstance() {
        return gsonInstance;
    }

    public static String toJsonString(Object o) {
        return gsonInstance.toJson(o);
    }
}
