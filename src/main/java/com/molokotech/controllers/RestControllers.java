package com.molokotech.controllers;
import java.io.IOException;
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
import com.google.zxing.WriterException;
import com.molokotech.base64.QRCodeGenerator;
import com.molokotech.model.Owner;
import com.molokotech.model.Pet;
import com.molokotech.model.PrepaidQR;
import com.molokotech.service.OwnerService;
import com.molokotech.service.PetService;
import com.molokotech.service.PrepaidQrService;
import com.molokotech.service.UserService;
import com.molokotech.utilities.TokenCreator;


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
					prepaidqr.setSellPoint("Clivet");
					
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
	
		prepaidQrService.updateQrService(idPrepaidQrCode, prepaidQR, pet, owner, user);
		Gson gson = new Gson();
		String strJson = gson.toJson(prepaidQR);
		
		return strJson;
	}
	
}
