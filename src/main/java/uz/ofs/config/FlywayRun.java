package uz.ofs.config;

import lombok.Getter;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@Getter
public class FlywayRun implements CommandLineRunner {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Override
    public void run(String... args){

        Flyway flyway = Flyway.configure()
                .dataSource(url, username, password)
                .baselineOnMigrate(true)
                .sqlMigrationPrefix("C")
                .baselineVersion("0")
                .outOfOrder(true)
                .load();
        // Start the migration
        flyway.migrate();
    }
}
