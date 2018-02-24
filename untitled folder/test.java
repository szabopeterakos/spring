import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ConfigSec extends WebSecurityConfigurerAdapter{

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder builder) throws Exception{
        builder.inMemoryAuthentication().withUser("user").password("pass").roles("USER").and().withUser("johnsnow").password("admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/").permitAll()
                .antMatchers("/add").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN").and()
                .formLogin().permitAll();
    }
}
