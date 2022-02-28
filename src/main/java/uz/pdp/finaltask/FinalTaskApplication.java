package uz.pdp.finaltask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uz.pdp.finaltask.config.InitConfig;

@SpringBootApplication
public class FinalTaskApplication {

    public static void main(String[] args) {
        if (InitConfig.isStart()) {
            SpringApplication.run(FinalTaskApplication.class, args);
        }
    }

}
