package com.molokotech.utilities;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

public class PrintName {
	public static Model printUser(Model modelName) {
		try {
			if (modelName != null) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				String name = auth.getName(); // get logged in username				
				if(name.equals("anonymousUser")) {
					name = "Invitado";
				}
				modelName.addAttribute("currentUser", name);
				modelName.addAttribute("fullName", name);
				System.out.println(name);
				
			} else {
				System.out.println("Nulo");
			}			
		}catch(Exception error) {
			error.printStackTrace();
			System.out.println(error.getMessage());
		}
		return modelName;
	}
}
