package com.ls.wod.service.imp;

import com.ls.wod.domain.TypeuserRole;
import com.ls.wod.domain.User;
import com.ls.wod.exception.AuthorizationException;
import com.ls.wod.exception.UsernamePasswordException;
import com.ls.wod.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException  {
        UserDetails userDetails = null;
        User user = userRepository.findByUsername(username).orElseThrow(UsernamePasswordException::new);

        if (user.getTypeuser().getRole() != null || !user.getTypeuser().getRole().isEmpty()) {
            List<GrantedAuthority> rols = new ArrayList<GrantedAuthority>();
            for (TypeuserRole typeuserRole: user.getTypeuser().getTypeuserRoleList()){
                rols.add(new SimpleGrantedAuthority(typeuserRole.getRole().getDescription()));
            }
            userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), rols);
        } else {
            throw new AuthorizationException();
        }
        return userDetails;
    }
}
