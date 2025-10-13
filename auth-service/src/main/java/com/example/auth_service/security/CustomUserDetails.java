package com.example.auth_service.security;

import com.example.auth_service.entity.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collections;
import java.util.Collection;


public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Long id;

    public CustomUserDetails(String username, String password, Collection<?extends GrantedAuthority> authorities, Long id) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.id = id;
    }
        public static CustomUserDetails build(AppUser user){
            GrantedAuthority authority=new SimpleGrantedAuthority((user.getRole()));
            return new CustomUserDetails(
                    user.getUsername(),
                    user.getPassword(),
                    Collections.singletonList(authority),
                    user.getId()
            );
        }
        @Override
        public Collection<?extends  GrantedAuthority> getAuthorities() {
            return authorities;
        }
        public Long getId(){
             return id;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
}

































