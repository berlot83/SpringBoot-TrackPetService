package com.molokotech.service;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.molokotech.model.PrepaidQR;
import com.molokotech.model.User;
import com.molokotech.repository.UserRepository;

@Service
public class UserService{
	
	@Autowired
	UserRepository userRepository;

	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public User findById(String id){
		Optional<User> opt = userRepository.findById(id);
		User user = new User();
		try {
			if (opt.isPresent()) {
				user = opt.get();
			} else {
				System.out.println("Algo salio mal");
			}
		}catch(Exception error) {
			System.out.println("algo salio mal encontrando el user by email");
		}
		return user;
	}
	
	
	public User findUser(String name) {
		Optional<User> opt = userRepository.findUserByName(name);
		User user = new User();
		try {
			if (opt.isPresent()) {
				user = opt.get();
			} else {
				user = null;
				System.out.println("No se encontró Usuario a menos que sea null");
			}
		}catch(Exception error) {
			System.out.println("algo salio mal encontrando el user by name");
		}
		return user;
	}
	
	
	public User findUserByEmail(String email){
		Optional<User> opt = userRepository.findByEmail(email);
		User user = new User();
		try {
			if (opt.isPresent()) {
				user = opt.get();
			} else {
				System.out.println("Algo salio mal");
			}
		}catch(Exception error) {
			System.out.println("algo salio mal encontrando el user by id");
		}
		return user;
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
