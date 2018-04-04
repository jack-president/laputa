package com.laputa.foundation.configurer.core;

/**
 * Created by jiangdongping on 2018/3/26 0026.
 */
public class LaputaPlaceHolder {

    public static final String CLOUD_SPLIT_FLAG = "@";

    /**
     * Default placeholder prefix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /**
     * Default placeholder suffix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    /**
     * Default value separator: {@value}
     */
    public static final String DEFAULT_VALUE_SEPARATOR = ":";

    /**
     * Defaults to {@value #DEFAULT_PLACEHOLDER_PREFIX}
     */
    public static String placeholderPrefix = DEFAULT_PLACEHOLDER_PREFIX;

    /**
     * Defaults to {@value #DEFAULT_PLACEHOLDER_SUFFIX}
     */
    public static String placeholderSuffix = DEFAULT_PLACEHOLDER_SUFFIX;

    /**
     * Defaults to {@value #DEFAULT_VALUE_SEPARATOR}
     */
    public static String valueSeparator = DEFAULT_VALUE_SEPARATOR;

    private final String src;
    private boolean valid = false;
    private String defaultValue = null;
    private boolean cloudFlag = false;
    private String property;
    private String holder;

    public LaputaPlaceHolder(String src, boolean trimValues) {
        this.src = src;
        this.valid = xmlKeyValid(src);
        if (this.valid) {
            String confKey = xmlKeyParse(src);

            int index = confKey.lastIndexOf(valueSeparator);

            if (index > 0) {
                property = confKey.substring(0, index);
                String tailKey = confKey.substring(index + 1, confKey.length());

                if (tailKey.startsWith(CLOUD_SPLIT_FLAG)) {
                    cloudFlag = true;
                    tailKey = tailKey.substring(CLOUD_SPLIT_FLAG.length(), tailKey.length());

                    if (trimValues) {
                        defaultValue = tailKey.trim();
                    }
                    if (tailKey.length() == 0) {
                        defaultValue = null;
                    }


                    holder = (this.placeholderPrefix + property + this.valueSeparator + tailKey + this.placeholderSuffix);
                }
            }
        }
    }

    private boolean xmlKeyValid(String originKey) {
        boolean start = originKey.startsWith(this.placeholderPrefix);
        boolean end = originKey.endsWith(this.placeholderSuffix);
        if (start && end) {
            return true;
        }
        return false;
    }

    private String xmlKeyParse(String originKey) {
        if (xmlKeyValid(originKey)) {
            // replace by xxl-conf
            String key = originKey.substring(placeholderPrefix.length(), originKey.length() - placeholderSuffix.length());
            return key;
        }
        return null;
    }

    public String getSrc() {
        return src;
    }

    public boolean isValid() {
        return valid;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public boolean isCloudFlag() {
        return cloudFlag;
    }

    public String getProperty() {
        return property;
    }

    public String getHolder() {
        return holder;
    }
}
