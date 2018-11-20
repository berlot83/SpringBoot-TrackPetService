package com.molokotech.service;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.molokotech.model.Role;
import com.molokotech.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public void create(Role role) {
		roleRepository.save(role);
	}

	public List<Role> read(List<Role> list) {
		return list;
	}

	public void update(@PathParam("id") Role role) {
		roleRepository.save(role);
	}

	public void delete(Role role) {
		roleRepository.delete(role);
	}
	
}
