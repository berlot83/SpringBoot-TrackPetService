package com.molokotech.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.zxing.WriterException;
import com.molokotech.base64.QRCodeGenerator;
import com.molokotech.model.Pet;
import com.molokotech.model.PrepaidQR;
import com.molokotech.model.QR;
import com.molokotech.model.User;
import com.molokotech.model.Veterinary;
import com.molokotech.service.PrepaidQrService;
import com.molokotech.service.QrService;
import com.molokotech.service.UserService;
import com.molokotech.utilities.TokenCreator;


@RestController
public class RestControllers {

	@Autowired
	QrService qrService;
	@Autowired
	UserService userService;
	@Autowired
	PrepaidQrService prepaidQrService;
	
	/* Controller for text exampleon web page */
//	@RequestMapping(value = "/qr/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public @ResponseBody Document example(@ModelAttribute Pet pet, QR qr) {
//		Gson gson = new Gson();
//		Document result = Document.parse(gson.toJson(pet));
//		qr.setPet(pet);
//		qrService.createQr(qr);
//		System.out.println(result);
//		return result;
//	}
	/* End  example */
	
	@PostMapping(value = "/qr/create-qr", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String createQrCode(QR qr, @ModelAttribute User user, @ModelAttribute Pet pet,
			@ModelAttribute Veterinary veterinary) throws IOException {
		
		/* Initialize the result */
		String strBase64 = null;

		/*
		 * Creamos el Gson, atención en este momento sólo devuelve un String en Base 64,
		 * Debe pasarse a Ajax para poder manipular el resultado
		 */
		Gson gson = new Gson();

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

		/*
		 * Seteamos los tres objetos, Pet, User y Veterinary a QR, para subirlos a la
		 * MongoDB
		 */

		qr.setUser(user);
		qr.setPet(pet);
		qr.setVeterinary(veterinary);

		/* Sube a la MongoDB */
		qrService.createQr(qr);

		/*
		 * Devuelve un String en Base64, el cual si se copia se puede ver desde esta
		 * dirección https://codebeautify.org/base64-to-image-converter
		 * 
		 * Una vez creado el código QR en esta web o cualquier otra al momento de leer
		 * el QR debe devolver el ID del mismo, que se utilizará para la llamada desde
		 * la App o desde la web para un update o delete.
		 * 
		 * Este proyecto esta en proceso de deploy en Jelastic sin terminar de deployar,
		 * el anterior esta realizado sobre Jersey 1.19 y deployado en Heroku.
		 * pets2018.herokuapp.com/controllers.html
		 */
		return strBase64;
	}
	/* End prepaid QR controllers */
	
	/* Manually create dinamyc QR codes with specialId and Id with strBase64 included */
	@GetMapping("/create-pp-to-db")
	public String insertPrepaidQrToDB(final PrepaidQR prepaidqr) throws WriterException, IOException {
		
					/* Create PrepaidQR to insert into mongoDB Start */
					/* Create an object QR */
					/* String token */
					String specialId = "PP".concat(TokenCreator.createSpecialId());
					/* Set the special Id for each one */
					prepaidqr.setSpecialId(specialId);
					
					/* Create QR only with special ID */
					byte[] imageData = null;
					try {
						imageData = QRCodeGenerator.generateQRCodeImageToByte(prepaidqr.getSpecialId(), 300, 300);
					} catch (WriterException | IOException e) {
						e.printStackTrace();
					}
					String strBase64 = QRCodeGenerator.toBase64(imageData);
					prepaidqr.setStrBase64(strBase64);
					System.out.println(strBase64);
					
					System.out.println(prepaidqr.getId());
					System.out.println(prepaidqr.getSpecialId());
					
					PrepaidQR objectToUpload = prepaidQrService.createPrepaidQR(prepaidqr);
					
					String result = null;
					
					if(objectToUpload != null) {
						result = "Look's like everything goes well. SpecialId:  " + objectToUpload.getSpecialId() + " | Id:  " + objectToUpload.getId();
					}else {
						result = "Something is wrong, the object is NULL";
					}
					/* creamos el objeto y sbimos el mismo a la DB */
	return result;
	}
	/* End */
	
	/* Find an object in mongoDB and if it exist take a strBase64 and shows on html Ajax call */
	@PostMapping("/findPrepaidQrBySpecialId")
	public @ResponseBody String findPrepaidQr(@ModelAttribute PrepaidQR prepaidQR) {
		PrepaidQR qr = prepaidQrService.findById(prepaidQR.getId().toHexString());
		String base64 = qr.getStrBase64();
		return base64;
	}
	
	
}
