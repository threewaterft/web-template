package com.threewater.webserver.webtemplate.config;

import com.threewater.webserver.webtemplate.config.encoder.DefaultPasswordEncoder;
import com.threewater.webserver.webtemplate.filter.auth.JWTAuthenticationFilter;
import com.threewater.webserver.webtemplate.filter.auth.JWTLoginFilter;
import com.threewater.webserver.webtemplate.service.TokenAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenAuthService tokenAuthService;
    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(new DefaultPasswordEncoder())
//                .withUser("admin").password("123456").roles("USER");
        auth.userDetailsService(userDetailsService).passwordEncoder(new DefaultPasswordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 如果有允许匿名的url，填在下面
                .antMatchers("/user/register").permitAll()
                .anyRequest().authenticated()
                .and()
//                // 设置登陆页
//                .formLogin().permitAll()
//                // 设置登陆成功页
//                .defaultSuccessUrl("/").permitAll()
//                .and()
                .addFilter(new JWTLoginFilter(authenticationManager(),tokenAuthService))
                .addFilter(new JWTAuthenticationFilter(authenticationManager(),tokenAuthService, handlerExceptionResolver))
                .logout().permitAll();
        // 关闭CSRF跨域
        http.csrf().disable()
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**");
    }
}
