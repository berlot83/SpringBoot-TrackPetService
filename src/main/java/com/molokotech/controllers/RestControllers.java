package com.molokotech.controllers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.molokotech.base64.QRCodeGenerator;
import com.molokotech.model.Cat;
import com.molokotech.model.Dog;
import com.molokotech.model.Fish;
import com.molokotech.model.HamsterFishTank;
import com.molokotech.model.Horse;
import com.molokotech.model.Owner;
import com.molokotech.model.PrepaidQR;
import com.molokotech.model.Rat;
import com.molokotech.model.Setup;
import com.molokotech.model.User;
import com.molokotech.service.AnimalService;
import com.molokotech.service.BuyerService;
import com.molokotech.service.CatService;
import com.molokotech.service.DogService;
import com.molokotech.service.FishService;
import com.molokotech.service.HamsterFishTankService;
import com.molokotech.service.HorseService;
import com.molokotech.service.OwnerService;
import com.molokotech.service.PrepaidQrService;
import com.molokotech.service.UserService;
import com.molokotech.utilities.Expiration;
import com.molokotech.utilities.TokenCreator;

@Controller
@RestController
public class RestControllers {

	@Autowired
	UserService userService;
	@Autowired
	PrepaidQrService prepaidQrService;
	@Autowired
	AnimalService animalService;
	@Autowired
	OwnerService ownerService;
	@Autowired
	JavaMailSender emailSender;
	@Autowired
	BuyerService buyerService;
	@Autowired
	FishService fishService;
	@Autowired
	HorseService horseService;
	@Autowired
	DogService dogService;
	@Autowired
	CatService catService;
	@Autowired
	HamsterFishTankService hamsterFishTankService;
	
	
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
					
					prepaidqr.setActivationToken(TokenCreator.createAleatoryToken());
					
					try {
						imageData = QRCodeGenerator.generateQRCodeImageToByte("https://pet-qr.com/id/" + prepaidqr.getId(), 300, 300);
					} catch (WriterException | IOException e) {
						e.printStackTrace();
					}
					String strBase64 = QRCodeGenerator.toBase64(imageData);
					prepaidqr.setTypeAnimal("dog");
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
							prepaidqr.setTypeAnimal("dog");
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

	/* Controllers for ajax call forms */
	@RequestMapping(value = "/update-qr-dog", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String updateQrDog(String idPrepaidQrCode, String user, PrepaidQR prepaidQR, @ModelAttribute Dog dog) throws ApiException, InterruptedException, IOException {
		
		/* Upload To mongoDB */
		dogService.createDog(dog);
		PrepaidQR temp = prepaidQrService.updateQrServiceDog(idPrepaidQrCode, prepaidQR, dog, user);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String strJson = gson.toJson(temp, PrepaidQR.class);
		
		return strJson;
	}

	@RequestMapping(value = "/update-qr-cat", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String updateQrCat(String idPrepaidQrCode, String user, PrepaidQR prepaidQR, @ModelAttribute Cat cat) throws ApiException, InterruptedException, IOException {
		
		/* Upload To mongoDB */
		catService.createCat(cat);
		PrepaidQR temp = prepaidQrService.updateQrServiceCat(idPrepaidQrCode, prepaidQR, cat, user);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String strJson = gson.toJson(temp, PrepaidQR.class);
		
		return strJson;
	}
	
	@RequestMapping(value = "/update-qr-horse", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String updateQrHorse(String idPrepaidQrCode, String user, PrepaidQR prepaidQR, @ModelAttribute Horse horse) throws ApiException, InterruptedException, IOException {
		
		/* Upload To mongoDB */
		horseService.createHorse(horse);
		PrepaidQR temp = prepaidQrService.updateQrServiceHorse(idPrepaidQrCode, prepaidQR, horse, user);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String strJson = gson.toJson(temp, PrepaidQR.class);
		
		return strJson;
	}
	
	/* Ajax controllers for App */
	@RequestMapping(value = "/update-qr-fish", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String updateQrFish(String idPrepaidQrCode, String user, PrepaidQR prepaidQR, @ModelAttribute Fish fish) throws ApiException, InterruptedException, IOException {
		
		/* Upload To mongoDB */
		fishService.createFish(fish);
		PrepaidQR temp = prepaidQrService.updateQrServiceFishes(idPrepaidQrCode, prepaidQR, fish, user);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String strJson = gson.toJson(temp, PrepaidQR.class);
		
		return strJson;
	}
	
	/* Controllers for ajax call forms */
	@PostMapping(value = "/update-qr-hamsterFishTank", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String updateQrRat(String idPrepaidQrCode, String user, PrepaidQR prepaidQR, @ModelAttribute HamsterFishTank hamsterFishTank, @ModelAttribute Rat rat) throws ApiException, InterruptedException, IOException {

		hamsterFishTank.setRat(rat);
		hamsterFishTankService.createHamsterFishTank(hamsterFishTank);
		PrepaidQR temp = prepaidQrService.updateQrServiceHamsterFishTank(idPrepaidQrCode, prepaidQR, hamsterFishTank, user);
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

	@RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST,  produces= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_HTML_VALUE, MediaType.TEXT_PLAIN_VALUE, MediaType.ALL_VALUE}, consumes= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_HTML_VALUE, MediaType.TEXT_PLAIN_VALUE, MediaType.ALL_VALUE})
	public @ResponseBody void avatarUpload(String id, String resultBase64Avatar) {
		String result = null;
		PrepaidQR prepaidQR = prepaidQrService.findById(id);
		String typeAnimal = prepaidQR.getTypeAnimal();
		/* For some reason the string arrives without plus sign, and must be added coding */
		String avatar64 = resultBase64Avatar.replaceAll(" ", "+");
		
		if(prepaidQR != null) {
			switch(typeAnimal) {
			case "dog":
				if(prepaidQR.getDog() != null) {
					Dog dog = prepaidQR.getDog();
					dog.setResultBase64Avatar(avatar64);
					prepaidQR.setDog(dog);
					prepaidQrService.createPrepaidQR(prepaidQR);
				}else {
					result = "An error occurred during upload the photo";	
				}
				break;
			case "cat":
				if(prepaidQR.getCat() != null) {
					Cat cat = prepaidQR.getCat();
					cat.setResultBase64Avatar(avatar64);
					prepaidQR.setCat(cat);
					prepaidQrService.createPrepaidQR(prepaidQR);
				}else {
					result = "An error occurred during upload the photo";	
				}
				break;
			case "horse":
				if(prepaidQR.getHorse() != null) {
					Horse horse = prepaidQR.getHorse();
					horse.setResultBase64Avatar(avatar64);
					prepaidQR.setHorse(horse);
					prepaidQrService.createPrepaidQR(prepaidQR);
				}else {
					result = "An error occurred during upload the photo";	
				}
				break;
			}
		}
	}

	@RequestMapping(value = "/uploadBackside", method = RequestMethod.POST,  produces= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_HTML_VALUE, MediaType.TEXT_PLAIN_VALUE, MediaType.ALL_VALUE}, consumes= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_HTML_VALUE, MediaType.TEXT_PLAIN_VALUE, MediaType.ALL_VALUE})
	public @ResponseBody void uploadBackside(@RequestParam String base64Backside) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUser(auth.getName());
		String backside64 = base64Backside.replaceAll(" ", "+");
		user.getOwner().setBase64Backside(backside64);
		userService.saveUser(user);
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
	
	@GetMapping("/get-language")
	public String getLanguage() {
		return Locale.getDefault().getLanguage();
	}
	
	@RequestMapping(value = "/retrivePrepaidQrDog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String retrivePrepaidQrDog(@RequestParam String id) {
		PrepaidQR prepaidQR = prepaidQrService.findById(id);
		String result = null;
		try {
			Dog dog = prepaidQR.getDog();
			Gson gson = new Gson();
			result = gson.toJson(dog);
		}catch(Exception error) {
			System.out.println("Null value on dog, doesn' exist so cannot retrive nothing.");
		}
		
		return result;
	}
	
	@RequestMapping(value = "/retrivePrepaidQrCat", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String retrivePrepaidQrCat(@RequestParam String id) {
		PrepaidQR prepaidQR = prepaidQrService.findById(id);
		String result = null;
		try {
			Cat cat = prepaidQR.getCat();
			Gson gson = new Gson();
			result = gson.toJson(cat);
		}catch(Exception error) {
			System.out.println("Null value on cat, doesn' exist so cannot retrive nothing.");
		}
		
		return result;
	}
	
	@RequestMapping(value = "/retrivePrepaidQrHorse", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String retrivePrepaidQrHorse(@RequestParam String id) {
		PrepaidQR prepaidQR = prepaidQrService.findById(id);
		String result = null;
		try {
			Horse horse = prepaidQR.getHorse();
			Gson gson = new Gson();
			result = gson.toJson(horse);
		}catch(Exception error) {
			System.out.println("Null value on horse, doesn' exist so cannot retrive nothing.");
		}
		
		return result;
	}

	@RequestMapping(value = "/retriveOwner", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String retrivePrepaidQrHorse() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = userService.findUser(auth.getName()).getName();
		User user = userService.findUser(username);
		
		String result = null;
		try {
			Owner owner = user.getOwner();
			Gson gson = new Gson();
			result = gson.toJson(owner);
		}catch(Exception error) {
			System.out.println("Null value on owner, doesn' exist so cannot retrive nothing.");
		}
		
		return result;
	}
	
	@RequestMapping(value = "/retriveSetup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String retriveSetup() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = userService.findUser(auth.getName()).getName();
		User user = userService.findUser(username);
		
		String result = null;
		try {
			Setup setup = user.getSetup();
			Gson gson = new Gson();
			result = gson.toJson(setup);
		}catch(Exception error) {
			System.out.println("Null value on setup, doesn' exist so cannot retrive nothing.");
		}
		
		return result;
	}
	
	@RequestMapping(value = "/retrivePrepaidQrHamsterFishTank", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String retrivePrepaidQrHamsterFishTank(@RequestParam String id) {
		PrepaidQR prepaidQR = prepaidQrService.findById(id);
		String result = null;
		try {
			HamsterFishTank hamsterFishTank = prepaidQR.getHamsterFishTank();
			Gson gson = new Gson();
			result = gson.toJson(hamsterFishTank);
		}catch(Exception error) {
			System.out.println("Null value on dog, doesn' exist so cannot retrive nothing.");
		}
		
		return result;
	}

	
	@GetMapping("/select-animal")
	public void selectAnimal(@RequestParam String id, @RequestParam String typeAnimal) {
		System.out.println(id);
		System.out.println(typeAnimal);
		prepaidQrService.addTypeAnimal(id, typeAnimal);
		
		System.out.println(typeAnimal);
	}
	
	
	@PostMapping("/get-selected-animal")
	public @ResponseBody String getSelectedAnimal(@RequestParam String id) {
		PrepaidQR prepaidQR = prepaidQrService.findById(id);
		String typeAnimal = prepaidQR.getTypeAnimal();
		return typeAnimal;
	}
	
	@GetMapping("/getAllPrepaidQR")
	public @ResponseBody ArrayList<PrepaidQR> getNotificationsPrepaidQR(Model model) throws Exception{
		
		/* Retrive the entire list of PrepaidQR for a User */
		ArrayList<PrepaidQR> resultList = new ArrayList<>();
		
		/*
		 * We capture the name of the logued session to find the user and the we catch
		 * the email and other data on the page
		 */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUser(auth.getName());

		model.addAttribute("user", user);
		
		List<PrepaidQR> list = prepaidQrService.findAllPrepaidQR();
		/* Start comprove list exist */
		if(user == null) {
		}
		/* End comprove list exist */
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUserName() != null) {
				if(list.get(i).getUserName().equals(user.getName())) {
					resultList.add(list.get(i));
				}
				
			}else {
				/* Test all QR codes that are not of the account, use for test only */
				//System.out.println("No tiene QR asociados");
			}
		}
		
		for(PrepaidQR iterate : resultList) {
			/* Test all QR codes on the account, use for test only */
			//System.out.println(iterate.getId());
		}
		
		return resultList;
	}
	
	@GetMapping("/set-avatar-user")
	public @ResponseBody String setAvatarUser(@RequestParam String emailMD5) throws Exception{
		/* Retrive the entire list of PrepaidQR for a User */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUser(auth.getName());
		user.setGravatar(emailMD5);
		userService.saveUser(user);
		return "ok";
	}
	
	@GetMapping("/get-avatar-user")
	public @ResponseBody String getAvatarUser() throws Exception{
		/* Retrive the entire list of PrepaidQR for a User */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUser(auth.getName());
		String avatar = user.getGravatar();
		return avatar;
	}
	
}
