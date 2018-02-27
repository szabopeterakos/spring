//package com.petertailor.belsokonyveles.service;
//
//
//import com.petertailor.belsokonyveles.domain.Role;
//import com.petertailor.belsokonyveles.domain.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
//public class MyUserDetails implements UserDetails { // from spring security
//
//    private User user;
//
//    public MyUserDetails(User user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() { // jogkörök hogyan jöttek létre
//        Collection<GrantedAuthority> authorities = new HashSet<>();
//        Set<Role> roles = user.getRoleSet();
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getRole()));
//        }
//
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() { // password
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() { // username
//        return user.getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() { // lejárt e at account
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() { // nincs lezárva
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() { // letiltva
//        return true;
//    }
//}
