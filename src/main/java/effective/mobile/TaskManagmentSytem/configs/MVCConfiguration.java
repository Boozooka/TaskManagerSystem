package effective.mobile.TaskManagmentSytem.configs;

import effective.mobile.TaskManagmentSytem.interfaces.TaskServiceInterface;
import effective.mobile.TaskManagmentSytem.services.TaskManageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MVCConfiguration {

    @Bean
    public TaskServiceInterface taskServiceInterface() {
        return new TaskManageService();
    }
}
