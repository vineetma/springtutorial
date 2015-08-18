---
layout: docs
overview: true
title: Security & MVC
---

#Security and MVC Implementation
TODO
+ [Inspired from spring.io guides](https://spring.io/guides/gs/securing-web/)
+ [Checkout java configuration](http://docs.spring.io/spring-security/site/docs/current/reference/html/jc.html)

##Security
TODO

###build.gradle
TODO
{% highlight bash %}
dependencies {
    ...
    compile("org.springframework.boot:spring-boot-starter-security")
    ...
}

{% endhighlight %}

###code config
TODO

{% highlight java  %}
package com.dakinegroup.storesApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
}
{% endhighlight %}
##Controller
TODO

**MvcConfig.java**
{% highlight java %}
package com.dakinegroup.storesApp;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/welcome").setViewName("welcome");
        registry.addViewController("/login").setViewName("login");
    }

}
{% endhighlight %}

##View
TODO

###build.gradle
TODO
{% highlight bash %}
dependencies {
    ...
        compile("org.springframework.boot:spring-boot-starter-thymeleaf")
    ...
}
{% endhighlight %}

###Resources
TODO
{% highlight bash %}
src/
`-- main
    |-- ...
    |-- resources
    |   ...
    |   |-- static
    |   |   `-- index.html
    |   `-- templates
    |       |-- home.html
    |       |-- login.html
    |       |-- storeuimain.html
    |       `-- welcome.html

{% endhighlight %}

**home.html**
{% highlight html %}
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
    <head>
        <title>Spring Security Example</title>
    </head>
    <body>
        <h1>Welcome!</h1>

        <p>Click <a th:href="@{/welcome}">here</a> to see a greeting.</p>
    </body>
</html>
{% endhighlight %}

**login.html**
{% highlight html %}
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
    <head>
        <title>Spring Security Example </title>
    </head>
    <body>
        <div th:if="${param.error}">
            Invalid username and password.
        </div>
        <div th:if="${param.logout}">
            You have been logged out.
        </div>
        <form th:action="@{/login}" method="post">
            <div><label> User Name : <input type="text" name="username"/> </label></div>
            <div><label> Password: <input type="password" name="password"/> </label></div>
            <div><input type="submit" value="Sign In"/></div>
        </form>
    </body>
</html>
{% endhighlight %}

**welcome.html**
{% highlight html %}
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
    <head>
        <title>Hello World!</title>
    </head>
    <body>
        <h1 th:inline="text">Hello [[${#httpServletRequest.remoteUser}]]!</h1>
        <form th:action="@{/logout}" method="post">
            <input type="submit" value="Sign Out"/>
        </form>
    </body>
</html>
{% endhighlight %}

###Mapping
TODO

##Revisit Annotations
TODO