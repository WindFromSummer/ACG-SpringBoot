//package zc.free.acg.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import zc.free.acg.service.impl.MyUserDetailService;
//
///**
// * @auther ZhengCong
// * @date 2020/4/14 17:23
// * Spring Security配置
// */
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class NewSevurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //定制请求的授权规则
//        //所有人可访问
//        http.authorizeRequests()
//                .antMatchers("/").permitAll(); //所有人可访问
//                //.antMatchers("/identity/apply/query").hasRole("admin")admin角色可访问
//        //开启自动配置的登录功能
//        //1 login请求来到登陆页
//        //2 重定向到/login?error表示登录失败
//        //3 更多详细规则
//        //4 默认post形式的/login代表处理登录
//        //5 一旦定制loginPage 那么loginPage的 post请求就是登录
////        http.formLogin();
//        http.csrf().disable()   //允许跨域请求
//                .formLogin()
//                .loginPage("/login") //跳转登录页面的url
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/")//处理登录成功的url
//                .permitAll();
//        // 开启自动配置的注销功能
//        // 1 访问 /logout 表示用户注销 清空session
//        // 2 注销成功会返回 /login?logout 页面
//        // http.logout(); //注销成功返回默认页面
//        // 默认页面是拦截get /logout方法跳转到注销页面 再拦截post 的/logout跳出
//        // 如果自定义登录页面，需要将注销的请求方法设置为post类型的/logout
//        http.logout().logoutSuccessUrl("/");//注销成功请求/返回主页面
//        //开启记住我功能
//        //1 登陆成功后 将cookie发送给浏览器保存 以后登录带上cookie 以后通过检查即可登录
//        //3 点击注销会删除cookie
//        http.rememberMe().rememberMeParameter("remeber");
//
//    }
//
//    @Autowired
//    private MyUserDetailService myUserDetailService;
//    //通过查询数据库进行认证
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /*@Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manger = new InMemoryUserDetailsManager();
//        manger.createUser(User.withUsername("zhangsan").password(new BCryptPasswordEncoder().encode("123456")).authorities("p1").build());
//        return manger;
//    }*/
//
//    /**
//     * 静态资源访问权限
//     * @param web
//     */
//    @Override
//    public void configure(WebSecurity web){
//        // 忽视 js 、css 、 images 后缀访问
//        web.ignoring().antMatchers("/js/**","/css/**","/images/**");
//        web.ignoring().antMatchers("/asserts/**");
//    }
//
//}
