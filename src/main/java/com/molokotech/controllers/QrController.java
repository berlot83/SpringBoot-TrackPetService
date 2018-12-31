package com.molokotech.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	PrepaidQrService prepaidQrService;
	@Autowired
	JavaMailSender emailSender;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("errorMsg", "Usuario o Password incorrectos.");

		if (logout != null)
			model.addAttribute("msg", "Saliste.");
		PrintName.printUser(model);
		return "login";
	}
	
	@RequestMapping("/")
	public String firstIndex(Model model) {
		PrintName.printUser(model);
		return "index";
	}

	@RequestMapping("/index")
	public String secondIndex(Model model) {
		PrintName.printUser(model);
		return "index";
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

	@RequestMapping("/faq")
	public String faq(Model model) {
		PrintName.printUser(model);
		return "faq";
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
	
	@RequestMapping("/delete")
	public String deletePrepaidQr(String id) {
		PrepaidQR prepaidQR = prepaidQrService.findById(id);
		System.out.println(prepaidQR.getPet().getPetName());
		prepaidQrService.deletePrepaidQr(prepaidQR);
		return "delete";
	}
	

	/* Get all lost Dog */
	@RequestMapping("/db-lost-pet")
	public String getAllPrepaidQR(Model modelName, Model model) {
		List<PrepaidQR> list = prepaidQrService.findAllPrepaidQR();
		PrintName.printUser(modelName);
		model.addAttribute("list", list);
		return "db-lost-pet";
	}

	@RequestMapping("/online-checkout")
	public String onlineCheckout(Model modelName, Model model) {
		/*
		 * We need to create a checkout page to stack all data to send to, most
		 * important, email to send QR code
		 */
		PrintName.printUser(modelName);

		/*
		 * We capture the name of the logued session to find the user and the we catch
		 * the email and other data on the page
		 */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUser(auth.getName());
		model.addAttribute("user", user);

		return "online-checkout";
	}

	@RequestMapping("/payment-success")
	public String paymentSuccess(Model modelName, Model model) {

		/*
		 * We need to create a checkout page to stack all data to send to, most
		 * important, email to send QR code
		 */
		PrintName.printUser(modelName);

		/*
		 * We capture the name of the logued session to find the user and the we catch
		 * the email and other data on the page
		 */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUser(auth.getName());
		model.addAttribute("user", user);

		return "/payment-success";
	}

	@RequestMapping("/account")
	public String account(Model modelName, Model model) {
		PrintName.printUser(modelName);
		List<PrepaidQR> resultList = new ArrayList<>();

		/*
		 * We capture the name of the logued session to find the user and the we catch
		 * the email and other data on the page
		 */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUser(auth.getName());
		model.addAttribute("user", user);

		List<PrepaidQR> list = prepaidQrService.findAllPrepaidQR();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUserName() != null) {
				if(list.get(i).getUserName().equals(user.getName())) {
					resultList.add(list.get(i));
				}
				
			}else {
				System.out.println("No tiene QR asociados");
			}
		}
		model.addAttribute("list", resultList);
		 return "account";
	}

}
