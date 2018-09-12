package cn.springbox.example.collection.plugin.springconfig.springconfig;

import org.apache.ibatis.io.ResolverUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.net.URL;
import java.util.Enumeration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class})
public class SpringConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringConfigApplication.class, args);
    }

    static {
        ClassLoader classLoader = ResolverUtil.Test.class.getClassLoader();
        try {
            Enumeration<URL> enumeration = classLoader.getResources("META-INF/services/javax.servlet.ServletContainerInitializer");
            while (enumeration.hasMoreElements()) {
                System.out.println(enumeration.nextElement());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
