package com.threewater.webserver.webtemplate.config;

import com.threewater.webserver.webtemplate.config.encoder.DefaultPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(new DefaultPasswordEncoder())
//                .withUser("admin").password("123456").roles("USER");
        auth.userDetailsService(userDetailsService).passwordEncoder(new DefaultPasswordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                //不需要身份认证
//                .antMatchers("/", "/home","/toLogin","/**/customer/**").permitAll()
//                .antMatchers("/js/**", "/css/**", "/images/**", "/fronts/**", "/doc/**", "/toLogin").permitAll()
//                .antMatchers("/user/**").hasAnyRole("USER")
//                //.hasIpAddress()//读取配置权限配置
//                .antMatchers("/**").access("hasRole('ADMIN')")
//                .anyRequest().authenticated()
//                //自定义登录界面
//                .and().formLogin().loginPage("/toLogin").loginProcessingUrl("/login").failureUrl("/toLogin?error").permitAll()
//                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .and().exceptionHandling().accessDeniedPage("/toLogin?deny")
//                .and().httpBasic()
//                .and().sessionManagement().invalidSessionUrl("/toLogin")
//                .and().csrf().disable();
        http.authorizeRequests()
                // 如果有允许匿名的url，填在下面
//                .antMatchers().permitAll()
                .anyRequest().authenticated()
                .and()
                // 设置登陆页
                .formLogin().loginPage("/login")
                // 设置登陆成功页
                .defaultSuccessUrl("/").permitAll()
                // 自定义登陆用户名和密码参数，默认为username和password
//                .usernameParameter("username")
//                .passwordParameter("password")
                .and()
                .logout().permitAll();

        // 关闭CSRF跨域
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**");
    }
}
