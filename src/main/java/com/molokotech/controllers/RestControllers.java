package com.molokotech.controllers;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.WriterException;
import com.molokotech.base64.QRCodeGenerator;
import com.molokotech.model.Owner;
import com.molokotech.model.Pet;
import com.molokotech.model.PrepaidQR;
import com.molokotech.service.OwnerService;
import com.molokotech.service.PetService;
import com.molokotech.service.PrepaidQrService;
import com.molokotech.service.UserService;

@Controller
@RestController
public class RestControllers {

	@Autowired
	UserService userService;
	@Autowired
	PrepaidQrService prepaidQrService;
	@Autowired
	PetService petService;
	@Autowired
	OwnerService ownerService;
	
	/* Manually create dinamyc QR codes with specialId and Id with strBase64 included */
	@GetMapping("/create-pp-to-db")
	public String insertPrepaidQrToDB(final PrepaidQR prepaidqr) throws WriterException, IOException {
		
					/* Create PrepaidQR to insert into mongoDB Start */
					/* Create an object QR */
					/* String specialId token it is not used right now */
					/* Set the MongoDB ObjectId for each one creating fisrt the space on DataBase */
					
					/* Upload the object and create the MongoDB ObjectId, so next we catch it and put it on the String Base64 */
					PrepaidQR objectToUpload = prepaidQrService.createPrepaidQR(prepaidqr);
					/* Create QR MongoDB ObjectId */
					byte[] imageData = null;
					
					try {
						imageData = QRCodeGenerator.generateQRCodeImageToByte("https://pet-cloud-service.herokuapp.com/id/" + prepaidqr.getId(), 300, 300);
					} catch (WriterException | IOException e) {
						e.printStackTrace();
					}
					String strBase64 = QRCodeGenerator.toBase64(imageData);
					prepaidqr.setStrBase64(strBase64);
					
					/* Upload Again but with the String base64 updated */
					objectToUpload = prepaidQrService.createPrepaidQR(prepaidqr);
					
					/* Declare the response */
					String result = null;
					
					if(objectToUpload != null) {
						result = "Look's like everything goes well. Id:  " + objectToUpload.getId();
					}else {
						result = "Something is wrong, the object is NULL";
					}
	return result;
	}
	/* End */
	
	/* Find an object in mongoDB and if it exist take a strBase64 and shows on html Ajax call */
	@PostMapping("/findPrepaidQrByIdStrBase64")
	public @ResponseBody String findPrepaidQrStrBase64(@ModelAttribute PrepaidQR prepaidQR) {
		PrepaidQR qr = prepaidQrService.findById(prepaidQR.getId().toHexString());
		String base64 = qr.getStrBase64();
		return base64;
	}
	
	@PostMapping("/findPrepaidQrByIdSellPoint")
	public @ResponseBody String findPrepaidQrSellPoint(@ModelAttribute PrepaidQR prepaidQR) {
		PrepaidQR qr = prepaidQrService.findById(prepaidQR.getId().toHexString());
		String sellPoint = qr.getSellPoint();
		return sellPoint;
	}
	/* for the activation ones */
	
	/* Ajax controllers for App */
	@RequestMapping(value = "/update-qr", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String updateQr(String idPrepaidQrCode, String user, PrepaidQR prepaidQR, @ModelAttribute Pet pet, @ModelAttribute Owner owner) {
		
		owner.setIdPrepaidQrOwned(idPrepaidQrCode);
		
		/* Upload To mongoDB */
		ownerService.createOwner(owner);
		petService.createPet(pet);
	
		PrepaidQR temp = prepaidQrService.updateQrService(idPrepaidQrCode, prepaidQR, pet, owner, user);
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		//PrepaidQR tempPrepaidQR = new PrepaidQR(prepaidQR.getStrBase64(), pet, owner);
		String strJson = gson.toJson(temp, PrepaidQR.class);
		
		return strJson;
	}
	
}
