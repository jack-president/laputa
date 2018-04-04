package com.laputa.foundation.spring.config;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

/**
 * Created by JiangDongPing on 2016/12/19.
 */
public class LaputaSpringConfigAutoLoad {

    public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/laputa-spring.factories";

    //public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/NOTICE.txt";

    private static Class<?>[] springConfigArray;

    private static ArrayList<String> loadFailClazz = new ArrayList<>();

    public static void init(ClassLoader classLoader) {
        ArrayList<Class<?>> arrayList = new ArrayList<>();
        try {
            List<String> result = new ArrayList<>();
            // classLoader = Thread.currentThread().getContextClassLoader();
            //classLoader = ClassLoader.getSystemClassLoader();
            Enumeration<URL> urls = classLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                Properties properties = PropertiesLoaderUtils.loadProperties(new UrlResource(url));
                String factoryClassNames = properties.getProperty(LaputaSpringConfigAutoLoad.class.getName());
                result.addAll(Arrays.asList(StringUtils.commaDelimitedListToStringArray(factoryClassNames)));
            }

            if (result.size() > 0) {
                for (String clazzName : result) {
                    try {
                        arrayList.add(Class.forName(clazzName));
                    } catch (ClassNotFoundException e) {
                        loadFailClazz.add(clazzName);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        springConfigArray = arrayList.toArray(new Class[arrayList.size()]);

    }

    public static Class<?>[] getsSringConfigArray() {
        init(LaputaSpringConfigAutoLoad.class.getClassLoader());
        return springConfigArray;
    }

    public static void main(String[] args) {
        Class<?>[] array = getsSringConfigArray();
        array.toString();
    }

}
