package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.AuthRequest;
import com.csi.model.User;
import com.csi.service.UserServiceImpl;
import com.csi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping("/savedata")
    public ResponseEntity<User> saveData(@RequestBody User user){
        return ResponseEntity.ok(userServiceImpl.saveData(user));
    }

    @GetMapping("/getdatabyid/{userId}")
    public ResponseEntity<Optional<User>> getDataById(@PathVariable int userId){
        return ResponseEntity.ok(userServiceImpl.getDataById(userId));
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<User>> getAllData(){
        return ResponseEntity.ok(userServiceImpl.getAllData());
    }

    @PutMapping("/updatedata/{userId}")
    public ResponseEntity<User> updateData(@PathVariable int userId, @RequestBody User user){
        //
        User user1= userServiceImpl.getDataById(userId).orElseThrow(()-> new RecordNotFoundException("User Id Does Not Exist"));

        user1.setUserName(user.getUserName());
        user1.setUserPassword(user.getUserPassword());
        user1.setUserEmailId(user.getUserEmailId());
        return ResponseEntity.ok(userServiceImpl.updateData(user1));
    }

    @DeleteMapping("/deletebyid/{userId}")
    public ResponseEntity<String> deleteById(@PathVariable int userId){
        userServiceImpl.deleteDataById(userId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getUserPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Incorrect un/pwd");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }


}
