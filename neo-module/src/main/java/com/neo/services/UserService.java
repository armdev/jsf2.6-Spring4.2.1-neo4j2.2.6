package com.neo.services;

import com.neo.domain.User;
import com.neo.repository.UserRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserService {
    
    @Autowired
    private Neo4jTemplate template;
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public User saveUser(User entity, User entity2) {
        userRepository.save(entity);
        userRepository.save(entity2);
        User retrievedSallyUser = userRepository.findOne(entity2.getNodeId());
        System.out.println("######get user " + retrievedSallyUser.getName() + " " + retrievedSallyUser.getUserId() + " " + retrievedSallyUser.getNodeId());
        return retrievedSallyUser;
    }
    
    @Transactional
    public Long getUserCount() {
        return userRepository.count();
    }
    
    @Transactional
    public List<User> getUserList() {
        return userRepository.findAll().as(List.class);
    }
    
    @Transactional
    public User saveTemplate(User user) {
        User returnedEntity = template.save(user);
        return returnedEntity;
    }
    
    @Transactional
    public List<User> getNeoUserList() {
        return template.findAll(User.class).as(List.class);
    }
    
}
