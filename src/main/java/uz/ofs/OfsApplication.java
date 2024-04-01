package uz.ofs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class OfsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfsApplication.class, args);
    }

}
