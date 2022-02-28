package uz.pdp.finaltask.config;

import org.springframework.core.io.ClassPathResource;

import javax.swing.*;
import java.io.IOException;
import java.util.Properties;

public class InitConfig {

    public static boolean isStart() {
        Properties props = new Properties();

        try {
            props.load(new ClassPathResource("/application.properties").getInputStream());
            if (props.getProperty("spring.jpa.hibernate.ddl-auto").equals("update")
                    && props.getProperty("spring.sql.init.mode").equals("never")) {
                return true;
            } else {
                String key = JOptionPane.showInputDialog("Application propertiesni to'g'rlamasdan run qilmoqchimisiz: y/n");
                if (key.equals("y")) {
                    return true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
