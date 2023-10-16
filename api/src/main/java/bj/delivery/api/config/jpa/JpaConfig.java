package bj.delivery.api.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "bj.delivery.db")
@EnableJpaRepositories(basePackages = "bj.delivery.db")
public class JpaConfig {
}
