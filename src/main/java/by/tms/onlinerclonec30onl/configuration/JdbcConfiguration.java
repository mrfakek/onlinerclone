package by.tms.onlinerclonec30onl.configuration;

import by.tms.onlinerclonec30onl.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@PropertySource(Constants.APPLICATION_PROPERTIES_PATH)
public class JdbcConfiguration {

    @Value("${jdbc.url}")
    private String getJdbcUrl;

    @Value("${jdbc.username}")
    private String getJdbcUsername;

    @Value("${jdbc.password}")
    private String getJdbcPassword;

    @Value("${jdbc.schema.path}")
    private String getJdbcSchemaPath;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(getJdbcUrl);
        dataSource.setUsername(getJdbcUsername);
        dataSource.setPassword(getJdbcPassword);
        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
       resourceDatabasePopulator.addScript(new ClassPathResource(getJdbcSchemaPath));
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
