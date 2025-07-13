package shaimaa.yelp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.SpringApplication;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
//        (exclude ={
//        DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class,
////        R2dbcAutoConfiguration.class
//                R2dbcAutoConfiguration.class,
//                R2dbcDataAutoConfiguration.class
//        })
//@EnableAutoConfiguration
//@Configuration
//@ComponentScan
@OpenAPIDefinition(
        info = @Info(
                title = "Yelp",
                version = "1.0.0",
                description = "APIs for Yelp-like application."
        )
)
public class YelpApplication {
    public static void main(String[] args)  {
        SpringApplication.run(YelpApplication.class, args);
    }
}
