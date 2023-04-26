package com.csi.dao;

import com.csi.model.User;
import com.csi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl {

    @Autowired
    UserRepo userRepoImpl;

    public User saveData(User user){
        return userRepoImpl.save(user);
    }

    public Optional<User> getDataById(int userId){
        return userRepoImpl.findById(userId);
    }

    public User getDataByUserName(String userName){
        return userRepoImpl.findByUserName(userName);
    }

    public List<User> getAllData(){
        return userRepoImpl.findAll();
    }

    public User updateData(User user){
        return userRepoImpl.save(user);
    }

    public void deleteDataById(int userId){
        userRepoImpl.deleteById(userId);
    }
}
