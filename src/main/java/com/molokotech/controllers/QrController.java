package com.molokotech.controllers;

import java.io.IOException;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.zxing.WriterException;
import com.molokotech.base64.QRCodeGenerator;
import com.molokotech.model.PrepaidQR;
import com.molokotech.model.User;
import com.molokotech.service.PetService;
import com.molokotech.service.PrepaidQrService;
import com.molokotech.service.QrService;
import com.molokotech.service.UserService;
import com.molokotech.utilities.PrintName;
import com.molokotech.utilities.TokenCreator;

@Controller
public class QrController {

	@Autowired
	QrService qrService;
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

	@RequestMapping("/pricing")
	public String pricing(Model model) {
		PrintName.printUser(model);
		return "pricing";
	}

	@RequestMapping("/molokoAccess")
	public String molokoAccess(Model model) {
		PrintName.printUser(model);
		return "molokoAccess";
	}

	@RequestMapping("/create-pet-cloud")
	public String createPetCloud(Model model) {
		PrintName.printUser(model);
		return "create-pet-cloud";
	}

	@RequestMapping("/read-pet-cloud")
	public String readPetCloud(Model model) {
		PrintName.printUser(model);
		return "read-pet-cloud";
	}

	@RequestMapping("/update-pet-cloud")
	public String updatePetCloud(Model model) {
		PrintName.printUser(model);
		return "update-pet-cloud";
	}

	@RequestMapping("/delete-pet-cloud")
	public String deletePetCloud(Model model) {
		PrintName.printUser(model);
		return "delete-pet-cloud";
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

	@RequestMapping("/product-pet-qr")
	public String productPetQR(Model model) {
		PrintName.printUser(model);
		return "product-pet-qr";
	}

	@RequestMapping("/product-vet-cloud")
	public String productVetCloud(Model model) {
		PrintName.printUser(model);
		return "product-vet-cloud";
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

	@PostMapping("/sign-up")
	public String greetingSubmit(@ModelAttribute User user) {
		String[] authorities = { "USER" };
		user.setAuthorities(authorities);
		userService.saveUser(user);
		return "success";
	}
	/* End Sign-up */

	/* Prepaid QR Controllers */
	@GetMapping("/prepaid-qr")
	public String prepaidQrForm(Model model, Model modelName) {
		PrintName.printUser(modelName);
		model.addAttribute("prepaidQR", new PrepaidQR());
		return "prepaid-qr";
	}

	@PostMapping("/prepaid-qr")
	public String prepaidQrSubmit(@ModelAttribute PrepaidQR prepaidQR, Model modelName) {
		PrintName.printUser(modelName);
		String result = null;
		String tempSpecialId = prepaidQrService.findBySpecialId(prepaidQR.getSpecialId()).getSpecialId();
		if (prepaidQR.getSpecialId() != null && prepaidQR.getSpecialId().equals(tempSpecialId)) {
			result = "create-prepaid-qr";
		} else {
			result = "error";
		}
		return result;
	}

	/* End prepaid QR controllers */
	public void insertPrepaidQrToDB(PrepaidQR prepaidqr) throws WriterException, IOException {
		/* Create PrepaidQR to insert into mongoDB Start */
		/* Create an object QR */

		/* String token */
		String specialId = "PP".concat(TokenCreator.createSpecialId());
		/* Set the special Id for each one */
		prepaidqr.setSpecialId(specialId);

		/* Create QR only with special ID */
		byte[] imageData = QRCodeGenerator.generateQRCodeImageToByte(prepaidqr.getSpecialId(), 300, 300);
		String strBase64 = QRCodeGenerator.toBase64(imageData);
		prepaidqr.setStrBase64(strBase64);
		System.out.println(strBase64);

		prepaidqr.setId("hola");
		prepaidqr.setUser(new User());
		System.out.println(prepaidqr.getId());
		System.out.println(prepaidqr.getSpecialId());

		/* creamos el objeto y sbimos el mismo a la DB */
		
		
		prepaidQrService.create(prepaidqr);
	}
	/* End */

	public static void main(String[] args) throws WriterException, IOException {
		QrController cont = new QrController();
		cont.insertPrepaidQrToDB(new PrepaidQR());
	}
}
