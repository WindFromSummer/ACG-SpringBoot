package zc.free.acg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zc.free.acg.mapper.UserMapper;
import zc.free.acg.mapper.UserRoleMapper;
import zc.free.acg.model.User;
import zc.free.acg.model.UserExample;

import java.util.List;

@Service
public class AuthUserServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //痛殴数据库查找用户是否存在
        User record = new User();
        record.setUsername(s);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(s);
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        if(user == null){
            //未查询到用户信息  返回null  有provider来抛出异常
//            throw new UsernameNotFoundException("用户名不存在");
            return null;
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername()).password(user.getPassword()).authorities("admin").build();
        //查询用户的角色列表
//        UserRoleExample userRoleExample = new UserRoleExample();
//        UserRoleExample.Criteria criteria1 = userRoleExample.createCriteria();
//        criteria1.andUserIdEqualTo(user.getId());
//        List<UserRole> userRoles = this.userRoleMapper.selectByExample(userRoleExample);
//        List<Role> roles = BeanUtils.listbean2ListBean(userRoles, Role.class);
//        System.out.println("用户信息权限:" + user.getUsername() + ",角色:" + roles.toString());
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        for (Role role : roles) {
//            // 添加用户权限
//            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//        }
//        // 设置用户权限
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
