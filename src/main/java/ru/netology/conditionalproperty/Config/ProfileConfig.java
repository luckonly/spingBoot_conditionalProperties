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
    @ConditionalOnProperty(prefix = "netology.profile", name = "dev", havingValue = "false", matchIfMissing = true)
    public SystemProfile getProductionProfile() {
        return new ProductionProfile();
    }

}
