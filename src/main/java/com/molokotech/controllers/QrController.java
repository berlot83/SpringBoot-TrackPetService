package com.molokotech.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.zxing.WriterException;
import com.molokotech.base64.QRCodeGenerator;
import com.molokotech.model.Pet;
import com.molokotech.model.QR;
import com.molokotech.model.User;
import com.molokotech.model.Veterinary;
import com.molokotech.service.PetService;
import com.molokotech.service.QrService;
import com.molokotech.service.UserService;
import com.molokotech.utilities.TokenCreator;

@RestController
public class QrController {

	@Autowired
	QrService qrService;
	@Autowired
	UserService userService;
	@Autowired
	PetService petService;

	@PostMapping(value = "/qr/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody String createQrCode(QR qr, @ModelAttribute  User user, @ModelAttribute  Pet pet, @ModelAttribute Veterinary veterinary) throws IOException {
		/* Initialize the result */
		String strBase64 = null;
		
		/* Datos mockeados */
		String telephone1 = "1549735711	";
		String telephone2 = "1153189339";
		
		/* Creamos el Gson, atención en este momento sólo devuelve un String en Base 64,
		 * Debe pasarse a Ajax para poder manipular el resultado
		 */
		Gson gson = new Gson();
				
		user.setName(user.getName());
		user.setAddress(user.getAddress());
		user.setDni(user.getDni());
		user.setFacebook(user.getFacebook());
		user.setInstagram(user.getInstagram());
		user.setLastname(user.getLastname());
		
		/* Este token no es creado por MongoDB, es un Token customizado */
		user.setLostDogToken(TokenCreator.sixCharToken());
		
		user.setPassword(user.getPassword());
		user.setSessionToken("123456");
		List<String> telephones = new ArrayList<>();
			telephones.add(telephone1);
			telephones.add(telephone2);
		user.setTelephone(telephones);

		pet.setRaze(pet.getRaze());
		pet.setPetName(pet.getPetName());
		pet.setAge(pet.getAge());
		pet.setClinicHistory(pet.getClinicHistory());
		pet.setDateOctupleVaccine(pet.getDateOctupleVaccine());
		pet.setDatePolivalentVaccine(pet.getDatePolivalentVaccine());
		pet.setDateSextupleVaccine(pet.getDateSextupleVaccine());
		pet.setDateAntiRabicVaccine(pet.getDateAntiRabicVaccine());
		pet.setIllness(pet.getIllness());
		
		/* Usamos LocalDate para fechas, hay que bindearlo con el form */
		LocalDate lastVeterinaryVisit = LocalDate.of(2001, 03, 03);
		pet.setLastVeterinaryVisit(lastVeterinaryVisit);
				
		pet.setMedicated(pet.isMedicated());
		pet.setStatus(pet.isStatus());
		pet.setSubscription(pet.isSubscription());
		
		/* Usamos LocalDate para fechas, hay que bindearlo con el form */
		LocalDate dateLost = LocalDate.of(2018, 11, 9);
		pet.setDateLost(dateLost);
		
		/* Generador de Código QR en una sola línea, estos datos no pueden ser iguales en la MongoDB*/
		try {
			user.setEmail(user.getEmail());
			qr.setSpecialId();
			strBase64 = QRCodeGenerator.toBase64(QRCodeGenerator.generateQRCodeImageToByte(gson.toJson(qr.getSpecialId()), 300, 300));
		} catch (WriterException e) {
			e.printStackTrace();
			strBase64 = "Hay un error en la creación del código QR o algún dato ya está creado en la DB";
		}
		
		veterinary.setUsername(veterinary.getUsername());
		
		/* Seteamos el String en Base64 al objeto */
		qr.setStrBase64(strBase64);
		
		/* Seteamos los tres objetos, Pet, User y Veterinary a QR, para subirlos a la MongoDB */
		qr.setUser(user);
		qr.setPet(pet);
		qr.setVeterinary(veterinary);
		
		/* Sube a la MongoDB */
		qrService.createQr(qr);
		
		/* Devuelve un String en Base64, el cual si se copia se puede ver desde esta dirección
		 * https://codebeautify.org/base64-to-image-converter
		 * 
		 * Una vez creado el código QR en esta web o cualquier otra al momento de leer el QR
		 * debe devolver el ID del mismo, que se utilizará para la llamada desde la App o desde la web
		 * para un update o delete. 
		 * 
		 * Este proyecto esta en proceso de deploy en Jelastic sin terminar de deployar, el anterior esta realizado
		 * sobre Jersey 1.19 y deployado en Heroku.
		 * pets2018.herokuapp.com/controllers.html
		 */
		return strBase64;
	}
	
	/* Mapping de prueba */
	@RequestMapping(value = "/testtwo", method = RequestMethod.GET)
	public String testTwo() {
		return "TestTwo";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Usuario o Password incorrectos.");

        if (logout != null)
            model.addAttribute("msg", "Saliste.");   
        return "login";
    }

}
