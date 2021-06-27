package ru.netology.conditionalproperty.Config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.conditionalproperty.Profile.DevProfile;
import ru.netology.conditionalproperty.Profile.ProductionProfile;
import ru.netology.conditionalproperty.Profile.SystemProfile;

@Configuration
public class ProfileConfig {

    @Bean
    @ConditionalOnProperty(prefix = "netology.profile", name = "dev", havingValue = "true")
    public SystemProfile getDevProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnMissingBean(SystemProfile.class) //Этот вариант мне кажется более правильным, потому что будет работать даже если параметр отсутствует в properties
//    @ConditionalOnProperty(prefix = "netology.profile", name = "dev", havingValue = "false")
    public SystemProfile getProductionProfile() {
        return new ProductionProfile();
    }

}
