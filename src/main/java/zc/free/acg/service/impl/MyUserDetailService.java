//package zc.free.acg.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import zc.free.acg.domain.User;
//import zc.free.acg.domain.UserBo;
//import zc.free.acg.mapper.UserMapper;
//import zc.free.acg.service.auth.WgcUserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
///**
// * @auther ZhengCong
// * @date 2020/4/10 21:42
// */
//@Service
//public class MyUserDetailService implements UserDetailsService {
//    @Autowired
//    private UserMapper userMapper;
//
//    /**
//     * 模拟查询数据库
//     * @param s
//     * @return
//     * @throws UsernameNotFoundException
//     */
//    /*@Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        System.out.println(s);
//        //根据账号去数据库查询
//        UserDetails userDetails = User.withUsername("zc").password(new BCryptPasswordEncoder().encode("1234")).authorities("p1").build();
//        return userDetails;
//    }*/
//
//    /**
//     * 查询数据库进行用户认证
//     * @param s
//     * @return
//     * @throws UsernameNotFoundException
//     */
//    /*@Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        System.out.println(s);
//        //根据账号去数据库查询
//        User record = new User();
//        record.setUsername(s);
//
//        User user = this.userMapper.selectOne(record);
//        if (user == null) {
//            //如果查询不到用户返回null 由provider抛出异常
//            return null;
//        }
//        //UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(new BCryptPasswordEncoder().encode(user.getPassword())).authorities("p1").build();
//        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).authorities("p1").build();
//        System.out.println(userDetails);
//        return userDetails;
//    }*/
//
//    /**
//     * 查询数据库进行认证  并通过扩展用户信息实现对用户id的查询
//     * @param s
//     * @return
//     * @throws UsernameNotFoundException
//     */
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        System.out.println(s);
//        //根据账号去数据库查询
//        User record = new User();
//        record.setUsername(s);
//
//        User user = this.userMapper.selectOne(record);
//        if (user == null) {
//            //如果查询不到用户返回null 由provider抛出异常
//            return null;
//        }
//        WgcUserDetails wgcUserDetails = new WgcUserDetails();
//        wgcUserDetails.setUsername(user.getUsername());
//        wgcUserDetails.setPassword(user.getPassword());
//        wgcUserDetails.setId(user.getId());
//
//        Collection<GrantedAuthority> roleList = new ArrayList<>();
//        roleList.add(new SimpleGrantedAuthority("ROLE_admin"));
//        roleList.add(new SimpleGrantedAuthority("hello"));
//        roleList.add(new SimpleGrantedAuthority("ninin"));
//        wgcUserDetails.setAuthorities(roleList);
//        wgcUserDetails.setPhone(user.getPhone());
//        return wgcUserDetails;
//    }
//
//    /*@Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        System.out.println("username来自网页：" + s);
//        User record = new User();
//        record.setUsername(s);
//        User user = this.userMapper.selectOne(record);
//        if (user == null) {
//            throw new UsernameNotFoundException("您的账户名有误");
//        }
//        UserDetails userDetails = null; //返回给 Spring AuthentionManager
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        Collection<GrantedAuthority> roleList = new ArrayList<>();
//        //        int id = user.getId();
////        String roles = redisService.findRolesByPersonId(id);
//        String roles = "p1";
//        roleList.add(new SimpleGrantedAuthority(roles));
//        WgcUserDetails wgcUserDetails = new WgcUserDetails(roleList,user.getPassword(),user.getUsername(),user.getPhone());
//        System.out.println("wgcUserDetails"+wgcUserDetails);
//        return wgcUserDetails;
//    }*/
//}
//
