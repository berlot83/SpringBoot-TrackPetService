package com.molokotech.controllers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.parser.AcceptLanguage;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.errors.ApiException;
import com.google.zxing.WriterException;
import com.mercadopago.MP;
import com.mercadopago.MercadoPago;
import com.molokotech.base64.QRCodeGenerator;
import com.molokotech.model.Owner;
import com.molokotech.model.Pet;
import com.molokotech.model.PrepaidQR;
import com.molokotech.model.User;
import com.molokotech.service.OwnerService;
import com.molokotech.service.PetService;
import com.molokotech.service.PrepaidQrService;
import com.molokotech.service.UserService;
import com.molokotech.utilities.Expiration;
import com.molokotech.utilities.GoogleMapsService;
import com.paypal.ipn.IPNMessage;
import com.sun.jersey.core.header.AcceptableLanguageTag;

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
	@Autowired
	JavaMailSender emailSender;
	
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
	
	/* Manually create dinamyc QR codes with specialId and Id with strBase64 included */
	@GetMapping("/create-pp-automatic-temp-db")
	public @ResponseBody String insertPrepaidQrTemporalToDB(final PrepaidQR prepaidqr, @RequestParam String userInString) throws WriterException, IOException {
		
		/* Declare the response */
		String result = null;
		User user = userService.findUser(userInString);
		
					/* Check the user doesn't have a prepaidQR  */
					user = userService.findById(user.getId().toString());
					List<PrepaidQR> list = prepaidQrService.findAllPrepaidQR();
					List<PrepaidQR> resultList = new ArrayList<>();
					
					/* Start comprove list exist */
					if(list != null) {
						for (int i = 0; i < list.size(); i++) {
							if (list.get(i).getUserName() != null) {
								if(list.get(i).getUserName().equals(user.getName())) {
									resultList.add(list.get(i));
								}
								
							}else {
								System.out.println("No tiene QR asociados");
							}
						}
						/* End comprove list exist */
						
						if(resultList.isEmpty() && !resultList.contains(prepaidqr)) {
							
							/* Create PrepaidQR to insert into mongoDB Start */
							/* Create an object QR */
							/* String specialId token it is not used right now */
							/* Create a PrepaidQR  and assign it to an User */
							
							/* Upload the object and create the MongoDB ObjectId, so next we catch it and put it on the String Base64 */
				
							/* Create QR MongoDB ObjectId */
							byte[] imageData = null;
							
							try {
								imageData = QRCodeGenerator.generateQRCodeImageToByte("https://pet-qr.com/id/" + prepaidqr.getId(), 300, 300);
							} catch (WriterException | IOException e) {
								e.printStackTrace();
							}
							String strBase64 = QRCodeGenerator.toBase64(imageData);
							prepaidqr.setStrBase64(strBase64);
							
							/* Persist the user to have control */
							prepaidqr.setUserName(user.getName());
							
							/* It must be say diferent String from 'En venta' so put them 'Temporal QR' */
							prepaidqr.setSelledOnline(user.getEmail());
							
							/* Set Expiration Date saving LocalDate variable to the DB */
							prepaidqr.setExpiration(Expiration.calculateExpirationDate());
							
							/* Upload Again but with the String base64 updated */
							PrepaidQR objectToUpload = prepaidQrService.createPrepaidQR(prepaidqr);
							
							if(objectToUpload != null) {
								result = "El código de prueba le ha sido asignado, Id:  " + objectToUpload.getId() + ", la fecha de vencimiento es:  " + prepaidqr.getExpiration() ;
							}else {
								result = "Something is wrong, the object is NULL";
							}
							
						}else {
							result = "Este usuario tiene al menos un Código QR en su cuenta.";
							System.out.println("Este usuario tiene al menos un Código QR en su cuenta.");
						}
						
					}else {
						result = "La lista parece ser nula, puede que tire un excepción.";
						System.out.println("The list appears to be null, so gona throw an exception.");
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
		System.out.println(accessToken);

		MP mp = new MP(accessToken);
		JSONObject json = mp.get("/v1/payments/"+id);
		
		
		if(topic.equals("payment")) {
			String email = json.getJSONObject("response").getJSONObject("payer").getString("email");
			System.out.println(email);
			
			/* Start checking, sending and reemplacing sellecdOnline java attribute*/
			PrepaidQR prepaidQR = null;
			List<PrepaidQR> list = prepaidQrService.findAllPrepaidQR();
			
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getSelledOnline().equals("En venta")) {
					prepaidQR = list.get(i);
					System.out.println(list.get(i).getSelledOnline());
					System.out.println(list.get(i).getId());
					break;
				}else {
					System.out.println("No match with 'En venta'");
				}
			}
			/* End selecting 'En venta match' */

			/* Send email with id start */
			String idPrepaidQR = prepaidQR.getId().toString();
			
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					mimeMessage.setSubject("Código QR adquirido.");
					mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
					mimeMessage.setFrom(new InternetAddress("info@molokotech.com"));
					mimeMessage.setText("Gracias por tu compra, el id del QR es = " + idPrepaidQR +", el mail asignado es "+ email +",  lo que tenés que hacer es ir a nuestra web, ingresar a Pet-QR ==> Activar un QR prepago ==> Ingresá el código que recibiste junto con el mail y listo, fijate de completar todo lo que puedas del formulario. Una vez completo Tocá el botón rojo y cuando te salga el QR probalo desde la PC, si sale todo bien tendrías que ver todos los datos que pusiste.");
				}
			};

			try {
				this.emailSender.send(preparator);
			} catch (MailException ex) {
				System.err.println(ex.getMessage());
			}
			/* Send email with id end */
			
			/* Override "En Venta" for the email to stop resending other Users and to verify buyer*/
			prepaidQR.setSelledOnline(email.trim());
			prepaidQrService.createPrepaidQR(prepaidQR);
			
		}
		
//		System.out.println(json.toString(4));
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST,  produces= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_HTML_VALUE, MediaType.TEXT_PLAIN_VALUE, MediaType.ALL_VALUE}, consumes= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_HTML_VALUE, MediaType.TEXT_PLAIN_VALUE, MediaType.ALL_VALUE})
	public @ResponseBody void avatarUpload(String id, String resultBase64Avatar) {
		PrepaidQR prepaidQR = prepaidQrService.findById(id);
		
		/* For some reason the string arrives without plus sign, and must be added coding */
		String avatar64 = resultBase64Avatar.replaceAll(" ", "+");
		prepaidQR.setResultBase64Avatar(avatar64);
		prepaidQrService.createPrepaidQR(prepaidQR);
	}
	
	@RequestMapping("/transfer")
	public String tranfer(@RequestParam String emailDestinatary, @RequestParam String idPrepaidQR) {
		List<User> list = userService.findAll();
		User userDestinatary = null;
		String result = null;
		
		/* If user exist, assign new variable */
		for(int i = 0; i < list.size(); i++ ) {
			if(list.get(i).getEmail().equals(emailDestinatary)) {
				userDestinatary = list.get(i);
				result = "Usuario encontrado y transferido <i class='fas fa-user-check' style='color:LimeGreen'></i>";
				System.out.println("user found");
			}else {
				System.out.println("user not found");
			}
		}
		
		/* Verify if the prepaid exist, if not just response, if exist override activation email and userName to finde it */
		PrepaidQR prepaidQR = prepaidQrService.findById(idPrepaidQR);
		if(prepaidQR != null) {
			/* override userName and selledOnLine on PrepaidQR object */
			prepaidQR.setUserName(userDestinatary.getName());
			prepaidQR.setSelledOnline(emailDestinatary);
			prepaidQrService.createPrepaidQR(prepaidQR);
		}else {
			result = "Este Código QR parece ser null y no se puede transferir, si el problema persiste comuníquese a info@molokotech.com";
		}
		return result;
	}
	
	/* PayPal listener and sender of PrepaidQR code to new User */
	@RequestMapping(value = "/cgi-bin/webscr", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.ALL_VALUE})
	public ResponseEntity<?> listener(HttpServletRequest request, HttpServletRequest response) {

		Map<String, String> configMap = new HashMap<>();
		configMap.put("mode", "sandbox");
		configMap.put("acct1.UserName", "berlot83_api1.yahoo.com.ar");
		configMap.put("acct1.Password", "DUU3SLX82NFKPSEQ");
		configMap.put("acct1.Signature", "AVO4ngS4QK8KXIH04mEbcSuZHWY4AD2YGHkFDxfa1O13qrWw-RK31nTp");
		
		IPNMessage ipnlistener = new IPNMessage(request, configMap);
		boolean isIpnVerified = ipnlistener.validate();
		String transactionType = ipnlistener.getTransactionType();
		
		Map<String, String> map = ipnlistener.getIpnMap();
		
		System.out.println("******* IPN (name:value) pair : " + map + "  " + "######### TransactionType : "
				+ transactionType + "  ======== IPN verified : " + isIpnVerified);
		
		System.out.println("");
		System.out.println("");
		String payerEmail = map.get("payer_email");
		System.out.println(payerEmail);

		/* Check if is verified */
		if (isIpnVerified) {
			System.out.println("Verified IPN");
		/* End check */

			/* Start checking, sending and reemplacing sellecdOnline java attribute*/
			PrepaidQR prepaidQR = null;
			List<PrepaidQR> list = prepaidQrService.findAllPrepaidQR();
			
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getSelledOnline().equals("En venta")) {
					prepaidQR = list.get(i);
					System.out.println(list.get(i).getSelledOnline());
					System.out.println(list.get(i).getId());
					break;
				}else {
					System.out.println("No match with 'En venta'");
				}
			}
			/* End selecting 'En venta match' */

			/* Send email with id start */
			String idPrepaidQR = prepaidQR.getId().toString();
			
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					mimeMessage.setSubject("Código QR adquirido.");
					mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(payerEmail));
					mimeMessage.setFrom(new InternetAddress("info@molokotech.com"));
					mimeMessage.setText("Gracias por tu compra, el id del QR es = " + idPrepaidQR +", el mail asignado es "+ payerEmail +",  lo que tenés que hacer es ir a nuestra web, ingresar a Pet-QR ==> Activar un QR prepago ==> Ingresá el código que recibiste junto con el mail y listo, fijate de completar todo lo que puedas del formulario. Una vez completo Tocá el botón rojo y cuando te salga el QR probalo desde la PC, si sale todo bien tendrías que ver todos los datos que pusiste.");
				}
			};

			try {
				this.emailSender.send(preparator);
			} catch (MailException ex) {
				System.err.println(ex.getMessage());
			}
			/* Send email with id end */

			/* Override "En Venta" for the email to stop resending other Users and to verify buyer*/
			prepaidQR.setSelledOnline(payerEmail.trim());
			prepaidQrService.createPrepaidQR(prepaidQR);
			
		}		
		else{
			System.out.println("Not a valid IPN Request!");
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/get-language")
	public String getLanguage() {
		return Locale.getDefault().getLanguage();
	}
	
	@RequestMapping(value = "/retrivePrepaidQR", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String retrivePrepaidQR(@RequestParam String id) {
		PrepaidQR prepaidQR = prepaidQrService.findById(id);
		Gson gson = new Gson();
		String result = gson.toJson(prepaidQR);
		
		return result;
	}
	
}
