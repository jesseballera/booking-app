package com.purplemango.gms.service;

import com.purplemango.gms.models.iam.User;
import com.purplemango.gms.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserDetailsServiceImpl(@Qualifier("mongoTemplateAuth") MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = mongoTemplate.findOne(new Query(Criteria.where("username").is(username)), User.class, "users");
        return UserDetailsImpl.build(user);
    }
}
