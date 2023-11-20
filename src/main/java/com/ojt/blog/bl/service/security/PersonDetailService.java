package com.ojt.blog.bl.service.security;

import com.ojt.blog.bl.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

//public class PersonDetailService implements UserDetailsService {
//    @Autowired
//    PersonService personService;
//    @Override
//    // username is from Login Form
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        var person = personService.getByPersonName(username);
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username(person.getName())
//                .password(person.getPassword())
//                .roles("USER")
//                .build();
//        return userDetails;
//    }
//}
