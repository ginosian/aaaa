import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(scanBasePackages = "com.test", exclude = {JacksonAutoConfiguration.class})
@ImportResource("app-context.xml")
public class TestApplication {

    protected TestApplication() {
        super();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(TestApplication.class).bannerMode(Banner.Mode.OFF).run(args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}