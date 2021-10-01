/*package zc.free.acg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zc.free.acg.domain.Role;
import zc.free.acg.domain.User;
import zc.free.acg.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

*//*
 * @auther ZhengCong
 * @date 2020/3/30 20:39
 * spring security认证方式
 *//*

@Service
public class AuthUserServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //痛殴数据库查找用户是否存在
        User record = new User();
        record.setUsername(s);
        User user = userMapper.selectOne(record);
        if(user == null){
            //未查询到用户信息  返回null  有provider来抛出异常
//            throw new UsernameNotFoundException("用户名不存在");
            return null;
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername()).password(user.getPassword()).authorities("admin").build();
        //查询用户的角色列表
        List<Role> listRole = userMapper.findPermissionByUsername(s);
        System.out.println("用户信息权限:" + user.getUsername() + ",角色:" + listRole.toString());
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Role role : listRole) {
            // 添加用户权限
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        // 设置用户权限
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}*/
