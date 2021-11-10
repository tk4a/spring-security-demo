package com.artem.springsecurity.config

import com.artem.springsecurity.model.Role
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http
            ?.csrf()?.disable()
            ?.authorizeRequests()
            ?.anyRequest()
            ?.authenticated()
            ?.and()
            ?.httpBasic()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
        return InMemoryUserDetailsManager(
            User.builder()
                .username("admin")
                .password(getPasswordEncoder().encode("admin"))
                .authorities(Role.ADMIN.getAuthority())
                .build(),
            User.builder()
                .username("user")
                .password(getPasswordEncoder().encode("user"))
                .authorities(Role.USER.getAuthority())
                .build()
        )
    }

    @Bean
    fun getPasswordEncoder() = BCryptPasswordEncoder(12)
}