package com.molokotech.service;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.molokotech.model.User;
import com.molokotech.repository.UserRepository;

@Service
public class UserService{
	
	@Autowired
	UserRepository userRepository;

	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public User findUser(String name) {
		return userRepository.findUserByName(name);
	}
	
	public List<User> read(List<User> list) {
		return list;
	}

	public void update(@PathParam("id") User user) {
		userRepository.save(user);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}
}
