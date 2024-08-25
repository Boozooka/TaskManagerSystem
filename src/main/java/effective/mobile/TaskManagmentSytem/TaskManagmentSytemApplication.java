package effective.mobile.TaskManagmentSytem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class,
        SecurityAutoConfiguration.class})
public class TaskManagmentSytemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagmentSytemApplication.class, args);
    }

}
