package org.example.service;

import org.example.models.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public CompletableFuture<String> createUserAsync(List<User> users){
        try{
            Thread.sleep(200);
            userRepository.saveAll(users);
            return CompletableFuture.completedFuture("Success");
        }catch (Exception e){
            return CompletableFuture.completedFuture("Failed: "+e.getMessage());
        }
    }
}
