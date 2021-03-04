package fa.training.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import fa.training.utils.CustomAccessDeniedHandler;

/**
 * To configure Spring security.
 * 
 * @author ThanhDT19
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  /**
   * To encode password.
   * 
   * @return A bcrypt password encryptor
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * The configuration sets up a basic in-memory authentication config.
   * 
   * @param auth account from database
   * @throws Exception declare exception
   */
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth)
      throws Exception {
    auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
  }

  /**
   * To configure ignoring request.
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/**");
  }

  /**
   * To configure authorization account to access request.
   * 
   * @param http request
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
        .antMatchers("/login", "/logout", "/landing-page/**",
            "/trainee-management/trainee-listing")
        .permitAll().antMatchers("/trainee-management/view/**")
        .hasAnyAuthority("MANAGER", "DELIVERY_MANAGER", "CLASS_ADMIN",
            "SYSTEM_ADMIN", "TRAINER")
        .antMatchers("/trainee-management/update/**")
        .hasAnyAuthority("MANAGER", "DELIVERY_MANAGER", "CLASS_ADMIN",
            "SYSTEM_ADMIN")
        .antMatchers("/trainee-management/delete/**")
        .hasAnyAuthority("MANAGER", "DELIVERY_MANAGER", "SYSTEM_ADMIN").and()
        .formLogin().loginPage("/login").usernameParameter("username")
        .passwordParameter("password")
        .loginProcessingUrl("/authenticateTheUser")
        .defaultSuccessUrl("/landing-page")
        .failureUrl("/login?notify=login-faile").and().exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler());
  }

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }
}
