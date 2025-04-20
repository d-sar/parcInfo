package ma.enset.infoparc.security;

import lombok.AllArgsConstructor;
import ma.enset.infoparc.security.service.UserDetailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private UserDetailServiceImp userDetailsServiceImp;
    //jdbc authentication
    //@Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page URL
                        .permitAll() // Allow everyone to access the login page
                        .defaultSuccessUrl("/user/computers", true)
                )
                .authorizeHttpRequests(ar-> ar.requestMatchers("/webjars/**").permitAll())
                .authorizeHttpRequests(ar-> ar.requestMatchers("user/computers","/user/components").permitAll())
                .authorizeHttpRequests(ar -> ar.requestMatchers("/deleteComputer/**","/deleteComponent").hasRole("ADMIN"))
                .authorizeHttpRequests(ar -> ar.requestMatchers("/admin/**").hasRole("ADMIN"))
                .authorizeHttpRequests(ar -> ar.requestMatchers("/user/**").hasRole("USER"))
                .authorizeHttpRequests(ar -> ar.anyRequest().authenticated())
                .exceptionHandling(eh -> eh.accessDeniedPage("/NotAuthorized"))
                .userDetailsService(userDetailsServiceImp)// pour UserDetails Service authentification
                .build();
    }
// InMemomy Authentication
    // @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        return new InMemoryUserDetailsManager(
//                User.withUsername("admin").password(passwordEncoder.encode("admin")).authorities("ADMIN").build(),
//                User.withUsername("user").password(passwordEncoder.encode("user")).authorities("USER").build()
//        );
//    }
   // @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().authenticated()
//                )
//                .formLogin(withDefaults())
//                .httpBasic(withDefaults());
//        return http.build();}
}
