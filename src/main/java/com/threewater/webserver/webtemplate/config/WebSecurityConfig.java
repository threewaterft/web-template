package com.threewater.webserver.webtemplate.config;

import com.threewater.webserver.webtemplate.config.encoder.DefaultPasswordEncoder;
import com.threewater.webserver.webtemplate.filter.auth.JWTAuthenticationFilter;
import com.threewater.webserver.webtemplate.filter.auth.JWTLoginFilter;
import com.threewater.webserver.webtemplate.filter.auth.WeChatLoginFilter;
import com.threewater.webserver.webtemplate.filter.handler.WeChatAuthenticationFailureHandler;
import com.threewater.webserver.webtemplate.filter.handler.WeChatAuthenticationSuccessHandler;
import com.threewater.webserver.webtemplate.security.provider.InMemoryAuthenticationProvider;
import com.threewater.webserver.webtemplate.security.provider.WeChatAuthenticationProvider;
import com.threewater.webserver.webtemplate.service.TokenAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.Arrays;

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
    private WeChatAuthenticationProvider weChatAuthenticationProvider;
    @Autowired
    private WeChatAuthenticationSuccessHandler weChatAuthenticationSuccessHandler;
    @Autowired
    private WeChatAuthenticationFailureHandler weChatAuthenticationFailureHandler;

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication().passwordEncoder(new DefaultPasswordEncoder())
////                .withUser("admin").password("123456").roles("USER");
//        auth.userDetailsService(userDetailsService).passwordEncoder(new DefaultPasswordEncoder());
//    }

//    protected AuthenticationManager authenticationManager() throws Exception {
//        ProviderManager authenticationManager = new ProviderManager(Arrays.asList(inMemoryAuthenticationProvider));
//        System.out.println("there is "+authenticationManager.getProviders().size());
//        //不擦除认证密码，擦除会导致TokenBasedRememberMeServices因为找不到Credentials再调用UserDetailsService而抛出UsernameNotFoundException
//        authenticationManager.setEraseCredentialsAfterAuthentication(false);
//        return authenticationManager;
//    }

    @Bean
    public WeChatLoginFilter weChatLoginFilter() throws Exception{
        WeChatLoginFilter weChatLoginFilter = new WeChatLoginFilter(authenticationManager(), tokenAuthService);
        weChatLoginFilter.setAuthenticationSuccessHandler(weChatAuthenticationSuccessHandler);
        weChatLoginFilter.setAuthenticationFailureHandler(weChatAuthenticationFailureHandler);
        return weChatLoginFilter;
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception{
        return new JWTAuthenticationFilter(authenticationManager(), tokenAuthService, handlerExceptionResolver);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        // auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        // 使用自定义身份验证组件
        auth.authenticationProvider(weChatAuthenticationProvider);
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
                .addFilterBefore(weChatLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(jwtAuthenticationFilter())
                .logout().permitAll();
        // 关闭CSRF跨域
        http.csrf().disable()
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**").antMatchers("/js/**");
    }
}
