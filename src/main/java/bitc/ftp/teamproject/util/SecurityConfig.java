package bitc.ftp.teamproject.util;

import bitc.ftp.teamproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	@SuppressWarnings("unused")
	private final UserService uService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						(authz)->authz
								.requestMatchers("/","/register", "/product/**", "/notice").permitAll()
								.anyRequest().authenticated()
				)
				.formLogin((formLogin) -> formLogin
						.loginPage("/login")
						.failureUrl("/login?error=true")
						.defaultSuccessUrl("/")
						.usernameParameter("id")
						.passwordParameter("pw")
				).logout((logout)-> logout
						.logoutUrl("/logout")
						.invalidateHttpSession(true)
						.clearAuthentication(true)
						.logoutSuccessUrl("/")
						.permitAll()
				);

		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer(){
		return (web) -> web
				.ignoring().requestMatchers("/css/**", "/js/**", "/images/**", "/error");
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
