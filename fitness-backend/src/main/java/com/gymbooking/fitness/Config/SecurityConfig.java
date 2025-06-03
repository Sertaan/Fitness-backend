    package com.gymbooking.fitness.Config;


    import com.gymbooking.fitness.UserAuthService;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.http.HttpMethod;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {

        private final UserAuthService userAuthService;

        public SecurityConfig(UserAuthService userAuthService) {
            this.userAuthService = userAuthService;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable()) // Activate in production
                    .cors(Customizer.withDefaults())
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/login").permitAll()
                            .requestMatchers(HttpMethod.DELETE, "/api/**").hasAnyRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/api/**").hasAnyRole("ADMIN")   // ändrat till att alla kan lägga till
                            .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                            .requestMatchers(HttpMethod.PUT, "/api/**").hasAnyRole("USER", "ADMIN")
                            .anyRequest().authenticated()
                    )
                    .sessionManagement(session -> session  //code from frontend
                            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    );


            http
                    //.formLogin(Customizer.withDefaults()) //This i original from backend, not in updated request from frontend.
                    .logout(logout -> logout
                            .logoutUrl("/logout")
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID")
                    );

            return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public org.springframework.security.authentication.AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
            AuthenticationManagerBuilder authenticationManagerBuilder =
                    http.getSharedObject(AuthenticationManagerBuilder.class);
            authenticationManagerBuilder.userDetailsService(userAuthService)
                    .passwordEncoder(passwordEncoder());

            return authenticationManagerBuilder.build();
        }
    }