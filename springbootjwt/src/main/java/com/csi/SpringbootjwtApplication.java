package com.csi;

import com.csi.model.User;
import com.csi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringbootjwtApplication {

	@Autowired
	UserServiceImpl userServiceImpl;


	public static void main(String[] args) {
		SpringApplication.run(SpringbootjwtApplication.class, args);
	}


	@PostConstruct
	public void saveData(){
		User user = new User(121, "SWARA", "SWARA@CSI.COM", "SWARA@2025");

		userServiceImpl.saveData(user);

	}
}
