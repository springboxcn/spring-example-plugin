package cn.springbox.example.collection.plugin.loadconfig;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

public class Main {

    static {
        ClassLoader classLoader = Main.class.getClassLoader();
        try {
            Enumeration<URL> enumeration = classLoader.getResources("META-INF/config.properties");
            while (enumeration.hasMoreElements()) {
                URL url = enumeration.nextElement();
                InputStream is = new BufferedInputStream(new FileInputStream(new File(url.toURI())));
                Properties p = new Properties();
                p.load(is);
                String configClassName = p.getProperty("key");
                Class<?> clazz = Class.forName(configClassName);
                Field field = clazz.getDeclaredField("key");
                field.setAccessible(true);
                System.out.println(field.get(clazz));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("===");
    }

}
