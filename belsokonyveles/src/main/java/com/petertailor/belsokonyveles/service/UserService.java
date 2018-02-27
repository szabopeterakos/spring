//package com.petertailor.belsokonyveles.service;
//
//import com.petertailor.belsokonyveles.domain.User;
//import com.petertailor.belsokonyveles.repository.RepoRole;
//import com.petertailor.belsokonyveles.repository.RepoUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService implements UserServiceInterface  {
////    public class UserService implements UserServiceInterface, UserDetailsService {
//
//    private RepoUser repoUser;
//    private RepoRole repoRole;
//
//    @Autowired
//    public void setRepoRole(RepoRole repoRole) {
//        this.repoRole = repoRole;
//    }
//
//    @Autowired
//    public void setRepoUser(RepoUser repoUser) {
//        this.repoUser = repoUser;
//    }
//
//    @Override
//    public User findByEmail(String mail) {
//        return repoUser.findByEmail(mail);
//    }
//
//     from UserDetailService
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = findByEmail(s); // here the user will find by my Database
//        if (user == null) {
//            throw new UsernameNotFoundException(s);
//        }
//        return new MyUserDetails(user);
//    }
//
//}
