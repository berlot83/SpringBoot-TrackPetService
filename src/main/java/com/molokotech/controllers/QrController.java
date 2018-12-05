package com.molokotech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.molokotech.model.PrepaidQR;
import com.molokotech.model.User;
import com.molokotech.service.PetService;
import com.molokotech.service.PrepaidQrService;
import com.molokotech.service.UserService;
import com.molokotech.utilities.PrintName;
import com.mongodb.MongoWriteException;

@Component
@Controller
public class QrController {

	@Autowired
	UserService userService;
	@Autowired
	PetService petService;
	@Autowired
	UserService userservice;
	@Autowired
	PrepaidQrService prepaidQrService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("errorMsg", "Usuario o Password incorrectos.");

		if (logout != null)
			model.addAttribute("msg", "Saliste.");
		PrintName.printUser(model);
		return "login";
	}

	@RequestMapping("/index")
	public String index(Model model) {
		PrintName.printUser(model);
		return "index";
	}
	
	@RequestMapping("/test")
	public String test(Model model) {
		PrintName.printUser(model);
		return "test";
	}

	@RequestMapping("/pricing")
	public String pricing(Model model) {
		PrintName.printUser(model);
		return "pricing";
	}

	@RequestMapping("/default")
	public String goToDefault(Model model) {
		PrintName.printUser(model);
		return "default";
	}

	@RequestMapping("/create-qr")
	public String createQr(Model model) {
		PrintName.printUser(model);
		return "create-qr";
	}

	@RequestMapping("/about")
	public String about(Model model) {
		PrintName.printUser(model);
		return "about";
	}

	/*
	 * Sign-up form controller Not-Rest commands Spring and Thymeleaf
	 * SubmitTemplateTech
	 */
	@GetMapping("/sign-up")
	public String greetingForm(Model model) {
		model.addAttribute("user", new User());
		PrintName.printUser(model);
		return "sign-up";
	}

	@GetMapping("/vet-cloud")
	public String vetCloud(Model model) {
		model.addAttribute("user", new User());
		PrintName.printUser(model);
		return "vet-cloud";
	}

	@PostMapping("/sign-up")
	public String greetingSubmit(@ModelAttribute User user, Model model) {
		try {
			String[] authorities = { "USER" };
			user.setAuthorities(authorities);
			userService.saveUser(user);
			return "success";
		} catch (Exception e) {
			model.addAttribute("errorMsg", "Ese usuario ya está registrado");
			System.out.println("Entró en exceptción");
			System.out.println(e.getMessage());
			return "sign-up";
		}
	}
	/* End Sign-up */

	/* Prepaid QR Controllers to enter or not to the form */
	@GetMapping("/prepaid-qr")
	public String prepaidQrForm(Model model, Model modelName) {
		PrintName.printUser(modelName);
		model.addAttribute("prepaidQR", new PrepaidQR());
		return "prepaid-qr";
	}

	@PostMapping("/prepaid-qr")
	public String prepaidQrSubmit(@ModelAttribute PrepaidQR prepaidQR, Model modelName) {
		PrintName.printUser(modelName);
		String tempSpecialId = prepaidQrService.findById(prepaidQR.getId().toHexString()).getId().toHexString();
		String result = null;
		if (prepaidQR.getId().toHexString() != null && prepaidQR.getId().toHexString().equals(tempSpecialId)) {
			result = "create-prepaid-qr";
		} else {
			result = "error";
		}
		return result;
	}
	/* End prepaidControllers */

	/* get id details */
	@GetMapping(value = "/id/{id}")
	public String readQr(@PathVariable String id, PrepaidQR prepaidQR, Model model, Model modelName) {
		prepaidQR = prepaidQrService.findById(id);
		String result = null;
		if (prepaidQR != null) {
			model.addAttribute("prepaidQR", prepaidQR);
			modelName.addAttribute("user", new User());
			PrintName.printUser(modelName);
			result = "id";
		} else {
			System.out.println("nulo");
			result = "empty";
		}
		return result;
	}
	
	/* Get all lost Dog */
	@RequestMapping("/db-lost-pet")
	public String getAllPrepaidQR(Model modelName, Model model){
		List<PrepaidQR> list = prepaidQrService.findAllPrepaidQR();
		PrintName.printUser(modelName);
		model.addAttribute("list",list);
		return "db-lost-pet";
	}

}
