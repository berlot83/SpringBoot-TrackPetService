package com.molokotech.controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.coyote.http2.Http2Protocol;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.maps.errors.ApiException;
import com.google.zxing.WriterException;
import com.mercadopago.MP;
import com.mercadopago.MercadoPago;
import com.mercadopago.core.MPApiResponse;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.merchantorder.Payer;
import com.molokotech.base64.QRCodeGenerator;
import com.molokotech.model.Owner;
import com.molokotech.model.Pet;
import com.molokotech.model.PrepaidQR;
import com.molokotech.service.OwnerService;
import com.molokotech.service.PetService;
import com.molokotech.service.PrepaidQrService;
import com.molokotech.service.UserService;
import com.molokotech.utilities.GoogleMapsService;

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
					
					/* It must be this String to change to 'vendido' when payment was released */
					prepaidqr.setSelledOnline("En venta");
					
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
	public @ResponseBody String updateQr(String idPrepaidQrCode, String user, PrepaidQR prepaidQR, @ModelAttribute Pet pet, @ModelAttribute Owner owner) throws ApiException, InterruptedException, IOException {
		
		owner.setIdPrepaidQrOwned(idPrepaidQrCode);
		owner.setLatitude(GoogleMapsService.getLatitude(owner.getAddress()));
		owner.setLongitude(GoogleMapsService.getLongitude(owner.getAddress()));
		/* Upload To mongoDB */
		ownerService.createOwner(owner);
		petService.createPet(pet);
	
		PrepaidQR temp = prepaidQrService.updateQrService(idPrepaidQrCode, prepaidQR, pet, owner, user);
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String strJson = gson.toJson(temp, PrepaidQR.class);
		
		return strJson;
	}
	
	@RequestMapping("/checkPrepaidQrAvailable")
	public PrepaidQR checkPrepaidQrAvailable() {
		PrepaidQR match = null;
		List<PrepaidQR> list = prepaidQrService.findAllPrepaidQR();
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSelledOnline().equals("En venta")) {
				match = list.get(i);
				System.out.println(list.get(i).getSelledOnline());
				System.out.println(list.get(i).getId());
				break;
			}else {
				System.out.println("No match with 'En venta'");
			}
		}
		return match;
	}
	
	@RequestMapping(value = "/notifications", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> notifications(String topic, String id) throws JSONException, Exception {
		MercadoPago.SDK.setClientSecret("uT7N5Y0B5lj9rophOy50yEh3EkEJo7jO");
		MercadoPago.SDK.setClientId("4306840655072811");
		MercadoPago.SDK.configure("4306840655072811", "uT7N5Y0B5lj9rophOy50yEh3EkEJo7jO");
		String accessToken = MercadoPago.SDK.getAccessToken();

		if(topic.equals("payment")) {
			
		}
		
		MPApiResponse api = MercadoPago.SDK.Get("https://api.mercadopago.com/v1/payments/4391970308?access_token="+accessToken);
		MP mp = new MP(accessToken);
		JSONObject json = mp.get("/v1/payments/4391970308");
		
		JSONObject jsonPayer = json.getJSONObject("payer");
		
		System.out.println(json.toString());
		System.out.println();
		System.out.println(jsonPayer.toString(4));
		
		
//		
//		System.out.println(api);
//		System.out.println(api.getJsonElementResponse());
//		
//		System.out.println(api.getPayload());
//		System.out.println(api.getStringResponse());
		
//		JsonElement gson = api.getJsonElementResponse();
//		System.out.println(gson);
//		
//		JsonObject jsonObject = gson.getAsJsonObject();
//		System.out.println(jsonObject);
//		
//		JsonArray jsonArray = jsonObject.getAsJsonArray();
//		System.out.println(jsonArray);
//		
//		
		
		
		
		System.out.println("hello world");
		System.out.println(new ResponseEntity<>(HttpStatus.CREATED));
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}
