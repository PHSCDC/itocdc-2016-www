package org.iseage.ito.service;

import org.iseage.ito.model.User;
import org.iseage.ito.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        try {
            User user = userRepository.getUserByUsername(s);

            userDetails = new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword().toLowerCase(),
                    true,
                    true,
                    true,
                    true,
                    getAuthorities(user.getAccess())
            );

        } catch (Exception e) {
            System.out.println("Error in retrieving user!");
            System.out.println(e.getMessage());
            throw new UsernameNotFoundException("Error in retrieving user!");
        }
        return userDetails;
    }

    private Collection<GrantedAuthority> getAuthorities(Integer access) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (access.compareTo(1) == 0) {
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return authList;
    }

}
