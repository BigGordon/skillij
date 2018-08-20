package com.zyc.skillijserver.config.security;

import com.zyc.skillijcommon.domain.mysql.SkillijPermission;
import com.zyc.skillijcommon.domain.mysql.SkillijRole;
import com.zyc.skillijcommon.domain.mysql.SkillijUser;
import com.zyc.skillijserver.repository.mysql.PermissionRepository;
import com.zyc.skillijserver.repository.mysql.RoleRepository;
import com.zyc.skillijserver.service.AccountService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/7/26.
 * Author: Gordon
 * Email: biggordon@163.com
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //使用name作为用户名:
        SkillijUser user = accountService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + username + " not found");
        }
        // 找出该用户所有的角色，再根据角色找出所有的权限
        List<SkillijRole> roles = user.getRoles();
        List<SkillijPermission> permissions = new ArrayList<>();
        for (SkillijRole role: roles) {
            List<SkillijPermission> permissionList = role.getPermissions();
            permissions.addAll(permissionList);
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (SkillijPermission permission : permissions) {
            if (permission != null && permission.getPermission()!=null) {

                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getPermission());
                //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                System.out.println(permission.getPermission());
                grantedAuthorities.add(grantedAuthority);
            }
        }
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
