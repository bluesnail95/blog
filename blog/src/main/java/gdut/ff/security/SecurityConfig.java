package gdut.ff.security;

import gdut.ff.service.UserDetailsServicempl;
import gdut.ff.service.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author bluesnail95
 * @Date 2020/10/5 14:28
 * @Description
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsServicempl userDetailsServicempl;

    @Resource
    private UserServiceImpl userServiceImpl;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsServicempl);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(StringUtils.join(ignoreRequest())).permitAll()
            .antMatchers("/users","/user/*").hasAuthority("USER")
            .and()
            .csrf().disable();
    }

    public List<String> ignoreRequest() {
        List<String> urls = new ArrayList<String>();
        urls.add("/analysis/**");
        urls.add("/blog/**");
        urls.add("/blogs");
        urls.add("/categorys");
        urls.add("/file/**");
        urls.add("/files");
        urls.add("/upload");
        urls.add("/download");
        urls.add("/**/blogs");
        urls.add("/ips");
        urls.add("/ip/**");
        urls.add("/lastestMessage");
        urls.add("/message/**");
        urls.add("/messages");
        urls.add("/tags");
        urls.add("/tag");
        urls.add("/tagRelations");
        urls.add("/tagRelation/**");
        return urls;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(StringUtils.join(ignoreRequest()));
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 自定义认证过滤器
     */
    private CustomJSONLoginFilter customJSONLoginFilter() {
        CustomJSONLoginFilter customJSONLoginFilter = new CustomJSONLoginFilter("/login", userServiceImpl);
        customJSONLoginFilter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
        customJSONLoginFilter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
        return customJSONLoginFilter;
    }

}
