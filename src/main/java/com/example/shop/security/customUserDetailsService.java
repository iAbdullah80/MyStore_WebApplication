package com.example.shop.security;

import com.example.shop.entity.Role;
import com.example.shop.repository.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class customUserDetailsService implements UserDetailsService{
    private final UserRepo userRepository;

    public customUserDetailsService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.example.shop.entity.User user = userRepository.findByEmail(email);
        if (user != null) {
            System.out.println("User found with email: " + email);
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));
        }
        else {
            System.out.println("User not found with email: " + email);
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}
