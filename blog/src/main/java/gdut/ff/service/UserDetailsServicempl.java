package gdut.ff.service;

import gdut.ff.domain.Role;
import gdut.ff.domain.User;
import gdut.ff.mapper.RoleMapper;
import gdut.ff.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author bluesnail95
 * @Date 2020/10/6 10:15
 * @Description
 */
@Component
public class UserDetailsServicempl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.fingUserByName(username);
        if(null == user) {
            throw  new UsernameNotFoundException("user is null");
        }
        List<Role> roleList = roleMapper.findRoleByUser(user.getUserId());
        user.setRoleList(roleList);
        return user;
    }
}
