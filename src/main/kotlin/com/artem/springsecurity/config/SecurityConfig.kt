package com.artem.springsecurity.config

import com.artem.springsecurity.model.Permission
import com.artem.springsecurity.model.Role
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http
            ?.csrf()?.disable()
            ?.authorizeRequests()
            ?.antMatchers(HttpMethod.GET,"/**")?.hasAuthority(Permission.PERSON_READ.permission)
            ?.antMatchers(HttpMethod.POST, "/**")?.hasAuthority(Permission.PERSON_WRITE.permission)
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