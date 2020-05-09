package ru.petr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {
    private static final String HOME_PAGE_URL = "/";
    private static final String ALL_ADVERTISEMENTS_URL = "/all-advertisements";
    private static final String IMAGE_URL = "/image";
    private static final String SINGLE_ADVERTISEMENT = "/advertisement";
    private static final String GET_CRITERION_URL = "/get-criterion";
    private static final String GET_LAST_URL = "/get-last";
    private static final String JSON_AUTHORIZATION_URL = "/json-authorization";
    private static final String AUTHORIZATION_URL = "/authorization";
    private static final String STATIC_RESOURCES_CSS = "/css/**";
    private static final String STATIC_RESOURCES_JS = "/js/**";
    private static final String STATIC_RESOURCES_IMG = "/img/**";
    private static final String FAVICON_ICO = "/favicon.ico";
    private static final String ENCODING = "UTF-8";
    private static final String LOGIN_PAGE_URL = "/sing-in";
    private static final String LOGIN_PROCESSING_URL = "/authenticateTheUser";
    private static final String CREATE_ADVERTISEMENT_URL = "/create";
    private static final String SING_UP_URL = "/sing-up";
    private static final String SAVE_USER_URL = "/save-user";
    @Autowired
    private DataSource securityDataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(securityDataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding(ENCODING);
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
        http.csrf().disable().authorizeRequests()
                .antMatchers(SING_UP_URL, SAVE_USER_URL, HOME_PAGE_URL, ALL_ADVERTISEMENTS_URL, IMAGE_URL,
                        SINGLE_ADVERTISEMENT, GET_CRITERION_URL, GET_LAST_URL, JSON_AUTHORIZATION_URL,
                        AUTHORIZATION_URL, STATIC_RESOURCES_CSS, STATIC_RESOURCES_JS, STATIC_RESOURCES_IMG, FAVICON_ICO)
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage(LOGIN_PAGE_URL)
                .loginProcessingUrl(LOGIN_PROCESSING_URL)
                .permitAll()
                .defaultSuccessUrl(CREATE_ADVERTISEMENT_URL)
                .and()
                .logout().permitAll();
    }
}
