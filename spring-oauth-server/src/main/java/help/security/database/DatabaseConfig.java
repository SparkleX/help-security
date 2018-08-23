package help.security.database;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableJpaRepositories
@PropertySource({ "classpath:persistence.properties" })
public class DatabaseConfig
{
	/*@Bean()
	DataSource dataSource() throws Exception
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setMaxPoolSize(100);
		cpds.setJdbcUrl("jdbc:sqlserver://localhost:1433;databaseName=test;user=sa;password=12345678");
		cpds.getConnection().close();
		return cpds;
	}*/
	
    @Value("classpath:schema.sql")
    private Resource schemaScript;

    @Value("classpath:data.sql")
    private Resource dataScript;
    
    
   @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(schemaScript);
        populator.addScript(dataScript);
        return populator;
    }
    
    @Autowired
    private Environment env;
    @Bean
    public DataSource dataSource() 
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
         dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));
        return dataSource;
    }
    
	@Bean("entityManagerFactory")
	public EntityManagerFactory createEntityManager(DataSource dataSource)
	{
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);

		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setShowSql(true);

		em.setPackagesToScan("help");
		em.setJpaVendorAdapter(jpaVendorAdapter);
		em.afterPropertiesSet();
		return em.getObject();
	}
}
