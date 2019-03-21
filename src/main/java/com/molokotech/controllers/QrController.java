package com.molokotech.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.bson.types.ObjectId;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.maps.errors.ApiException;
import com.molokotech.model.Animal;
import com.molokotech.model.Cat;
import com.molokotech.model.Dog;
import com.molokotech.model.Fish;
import com.molokotech.model.FormMessage;
import com.molokotech.model.HamsterFishTank;
import com.molokotech.model.Horse;
import com.molokotech.model.Owner;
import com.molokotech.model.PrepaidQR;
import com.molokotech.model.Rat;
import com.molokotech.model.Setup;
import com.molokotech.model.User;
import com.molokotech.service.AnimalService;
import com.molokotech.service.PrepaidQrService;
import com.molokotech.service.UserService;
import com.molokotech.utilities.EncryptMD5;
import com.molokotech.utilities.GoogleMapsService;
import com.molokotech.utilities.PrintName;
import com.molokotech.utilities.TokenCreator;

@Component
@Controller
public class QrController {

	@Autowired
	UserService userService;
	@Autowired
	AnimalService animalService;
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
		return "login"; /* login */
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

	@RequestMapping("/temporal-qr")
	public String temporalQR(Model model) {
		PrintName.printUser(model);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUser(auth.getName());
		model.addAttribute("user", user);
		return "temporal-qr";
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
		
		/* Search existing users Start */
		PrintName.printUser(model);
		/* This works fine finding the null object by name */
		if(userService.findUser(user.getName()) == null) {
			/* For some reason use.getEmamail not return null, so i needed to take (user.getEmail()).getEmail to take the null value*/ 
			if(userService.findUserByEmail(user.getEmail()).getEmail() == null) {
				try {
					String[] authorities = { "USER" };
					user.setAuthorities(authorities);
					user.setEmailToken(TokenCreator.createAleatoryToken());
					EncryptMD5 emailMD5 = new EncryptMD5();
					String email = user.getEmail();
					String gravatar = emailMD5.encryptToMD5(email); 
					System.out.println(gravatar);
					user.setGravatar(gravatar);
					user.setEmail(user.getEmail().trim().toLowerCase());
					
					Setup setup = new Setup();
					user.setSetup(setup);
					userService.saveUser(user);
					
				} catch (Exception e) {
					System.out.println("Entró en exceptción");
					System.out.println(e.getMessage());
					return "sign-up";
				}
				
				/* Start send email confirmation to admins, delete if exception could be Throw for out of space in email container or other */
				if(user != null) {
					MimeMessagePreparator preparator = new MimeMessagePreparator() {
						public void prepare(MimeMessage mimeMessage) throws Exception {
							mimeMessage.setSubject("Nuevo registro de usuario");
							mimeMessage.setRecipient(Message.RecipientType.TO,
									new InternetAddress("berlot83@yahoo.com.ar", "jaggerloco@hotmail.com"));
							mimeMessage.setFrom(new InternetAddress("info@molokotech.com"));
							mimeMessage
									.setText("El usuario: " + user.getName() + ", ha sido registrado en pet-qr.com y su mail es: " + user.getEmail());
						}
					};

					try {
						this.emailSender.send(preparator);
					} catch (MailException ex) {
						System.err.println(ex.getMessage());
					}
				}
				/* End send email confirmation to admins, delete if exception could be Throw for out of space in email container or other */
				
				return "success";
				
			}else {
				model.addAttribute("errorDuplicateEmailMsg", "Ese mail ya está registrado");	
				System.out.println("parece que el mail no es null");
				return "sign-up";			
			}
		}else {
			model.addAttribute("errorMsg", "Ese usuario ya está registrado");
			System.out.println("parece que el usuario no es null");
			return "sign-up";
		}
		/* Search existing users End */
	}
	/* End Sign-up */
	
	/* Start change pass methods */
	@GetMapping("/change-pass")
	public String changePass(Model model) {
		PrintName.printUser(model);
		model.addAttribute("user", new User());
		return "change-pass";
	}
	
	@PostMapping("/change-pass")
	public String recoveryPass(@ModelAttribute User user, Model model) {
		PrintName.printUser(model);
		User userTemp = userService.findUser(user.getName());
		
		if(userTemp == null) {
			model.addAttribute("error", "Parece que ese no es un usuario activo.");
			return "change-pass";
		}
		
		System.out.println(userTemp.getEmail() + " " + userTemp.getName());
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setSubject("Cambio de contraseña Pet-QR.");
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(userTemp.getEmail()));
				mimeMessage.setFrom(new InternetAddress("info@molokotech.com"));
				mimeMessage.setText("https://pet-qr.com/recovery-pass?token="+userTemp.getEmailToken()+"&username="+user.getName());
			}
		};
			try {
				this.emailSender.send(preparator);
			} catch (MailException ex) {
				System.err.println(ex.getMessage());
			}
			return "send-link";
	}
	
	@GetMapping("/recovery-pass")
	public String changePass(@RequestParam String token, @RequestParam String username, Model model) {
		PrintName.printUser(model);
		User user = userService.findUser(username);
		System.out.println("get "+user.getId());
		model.addAttribute("user", user);
		return "recovery-pass";
	}
	
	@PostMapping("/recovery-pass")
	public String changePassForm(@ModelAttribute User user, User userPersisted, @RequestBody String password, @RequestBody String copyPassword, Model model) {
		PrintName.printUser(model);
		
		userPersisted = userService.findById(user.getId());
		System.out.println("post "+user.getId());
		if((user != null) && (user.getPassword().equals(user.getCopyPassword()))) {
			
			try {
				userPersisted.setPassword(user.getPassword());
				userService.saveUser(userPersisted);
				
				model.addAttribute("success", "El cambio se realizó con éxito");
				System.out.println("Password changed");

			}catch(Exception error) {
				model.addAttribute("problem", "No se pudo realizar la operación");
				System.out.println(error);
				System.out.println("");
				System.out.println(error.getMessage());
			}
		}else {
			model.addAttribute("problem", "No se pudo realizar la operación");
			System.out.println("El usuario buscado es null");
		}
		return "recovery-pass";
	}
	/* End change pass methods */

	/* Prepaid QR Controllers to enter or not to the form */
	@GetMapping("/prepaid-qr")
	public String prepaidQrForm(Model model, Model modelName) {
		PrintName.printUser(modelName);
		model.addAttribute("prepaidQR", new PrepaidQR());

		return "prepaid-qr";
	}

	@PostMapping("/prepaid-qr")
	public String prepaidQrSubmit(@ModelAttribute PrepaidQR prepaidQR,  Model modelName, Model modelError) {
		PrintName.printUser(modelName);

		prepaidQrService.addTypeAnimal(prepaidQR.getId().toHexString(), prepaidQR.getTypeAnimal());
		
		String selledOnlineVerify = prepaidQrService.findById(prepaidQR.getId().toHexString()).getSelledOnline();
		String tempSpecialId = prepaidQrService.findById(prepaidQR.getId().toHexString()).getId().toHexString();
		String result = null;
		
		/* For quick connect to moloko */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = userService.findUser(auth.getName()).getName();
		User user = userService.findUser(username);
		modelName.addAttribute("user", user);
		/* For quick connect to moloko */
		
		if (prepaidQR.getId().toHexString() != null && prepaidQR.getId().toHexString().equals(tempSpecialId) && prepaidQR.getSelledOnline().equals(selledOnlineVerify)) {
			result = "create-prepaid-qr";
		} else {
			modelError.addAttribute("errorMailAsociated", "El mail parece no estar asociado a este código.");
			result = "prepaid-qr";
		}
		return result;
	}
	/* End prepaidControllers */

	/* get id details */
	@GetMapping(value = "/id/{id}")
	public String readQr(@PathVariable String id, PrepaidQR prepaidQR, Model model, Model modelName, String error) {
		String result = null;
		error = "It appears that this animal is not upload yet, edit this animal from control panel to add it to the MPAT.";
		/* Find the QR code */
		prepaidQR = prepaidQrService.findById(new ObjectId(id).toHexString());
		
		/* Find the user assigned to it to get the contact data */
		User user = userService.findUser(prepaidQR.getUserName());
		model.addAttribute("user", user);
		/* Used to know what animal are active in this moment in DB */
		String typeAnimal = prepaidQR.getTypeAnimal();
		
		
		if(user.getSetup().isLockTemplate()) {
			
			/* Verify the object aren't null */
			switch(typeAnimal) {
			case "dog":
				if(prepaidQR.getDog() != null) {
					model.addAttribute("prepaidQR", prepaidQR);
					modelName.addAttribute("user", user);
					PrintName.printUser(modelName);
					result = "id";
				}else {
					model.addAttribute("errorNoUpload", error);
					PrintName.printUser(modelName);
					result = "account";
				}
				break;
			case "cat":
				if(prepaidQR.getCat() != null) {
					model.addAttribute("prepaidQR", prepaidQR);
					modelName.addAttribute("user", user);
					PrintName.printUser(modelName);
					result = "id";
				}else {
					model.addAttribute("errorNoUpload", error);
					PrintName.printUser(modelName);
					result = "account";
				}
				break;
			case "horse":
				if(prepaidQR.getHorse() != null) {
					model.addAttribute("prepaidQR", prepaidQR);
					modelName.addAttribute("user", user);
					PrintName.printUser(modelName);
					result = "id";
				}else {
					model.addAttribute("errorNoUpload", error);
					PrintName.printUser(modelName);
					result = "account";
				}
				break;
			case "fish":
				if(prepaidQR.getFish() != null) {
					model.addAttribute("prepaidQR", prepaidQR);
					modelName.addAttribute("user", user);
					PrintName.printUser(modelName);
					result = "id";
				}else {
					model.addAttribute("errorNoUpload", error);
					PrintName.printUser(modelName);
					result = "account";
				}
				break;
			case "rat":
				if(prepaidQR.getHamsterFishTank() != null) {
					model.addAttribute("prepaidQR", prepaidQR);
					modelName.addAttribute("user", user);
					
					/* Inner object */
					Rat rat = prepaidQR.getHamsterFishTank().getRat();
					
					if(rat != null) {
						model.addAttribute("rat",rat);
					}
					
					PrintName.printUser(modelName);
					result = "id";
				}else {
					model.addAttribute("errorNoUpload", error);
					PrintName.printUser(modelName);
					result = "account";
				}
				break;
			}
		}else {
			System.out.println("Not allow by the user");
			model.addAttribute("authorized", "The users locks the templates temporally.");
			return "id";
		}
		
		return result;
	}
	
	@RequestMapping("/delete")
	public @ResponseBody void deletePrepaidQr(String id) {
		PrepaidQR prepaidQR = prepaidQrService.findById(id);
		prepaidQrService.deletePrepaidQr(prepaidQR);
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
		/* Start comprove list exist */
		if(user == null) {
			model.addAttribute("error", "Debe tener una cuenta para ingresar a esta sección.");
			return "index";
		}
		/* End comprove list exist */
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUserName() != null) {
				if(list.get(i).getUserName().equals(user.getName())) {
					resultList.add(list.get(i));
				}
				
			}else {
//				System.out.println("No tiene QR asociados");
			}
		}
		model.addAttribute("list", resultList);
		return "account";
	}
	
	@GetMapping("/download")
	public String downloadDesign(Model model) {
		/* Create an PrepaidQR Object */
		PrintName.printUser(model);
		return "download";
	}

	/* Be aware to do not delete @Model Attribute PrepaidQR and Pet they are in dependency */
	@PostMapping("/download")
	public String chooseDesign(@ModelAttribute PrepaidQR prepaidQR, Model model) {
		String result = null;
		PrintName.printUser(model);
		prepaidQR = prepaidQrService.findById(prepaidQR.getId().toHexString());
		String typeAnimal = prepaidQR.getTypeAnimal();

		if(prepaidQR != null) {
			model.addAttribute("prepaidQR", prepaidQR);
			
			switch(typeAnimal) {
			case "dog":
				if(prepaidQR.getDog() != null) {
					model.addAttribute("prepaidQR", prepaidQR);
					Dog dog = prepaidQR.getDog();
					model.addAttribute("dog", dog);
					result = "download";
				}
				else {
					PrintName.printUser(model);
					model.addAttribute("errorNoAnimalAvailable", "No Dog added to this account, just create one from the M.P.A.T.");
					result = "account";
				}
				break;			
			case "cat":
				if(prepaidQR.getCat() != null){
					Cat cat = prepaidQR.getCat();
					model.addAttribute("cat", cat);
					result = "download";
				}else{
					PrintName.printUser(model);
					model.addAttribute("errorNoAnimalAvailable", "No Cat added to this account, just create one from the M.P.A.T.");
					result = "account";
				}
				break;			
			case "horse":
				if(prepaidQR.getHorse() != null) {
					Horse horse = prepaidQR.getHorse();
					model.addAttribute("horse", horse);
					result = "download";	
				}else {
					PrintName.printUser(model);
					model.addAttribute("errorNoAnimalAvailable", "No Horse added to this account, just create one from the M.P.A.T.");
					result = "account";
				}
				break;
			case "rat":
				if(prepaidQR.getHamsterFishTank() != null) {
					HamsterFishTank hamsterFishTank = prepaidQR.getHamsterFishTank();
					model.addAttribute("hamsterFishTank", hamsterFishTank);
					Rat rat = prepaidQR.getRat();
					model.addAttribute("rat", rat);
				}else {
					PrintName.printUser(model);
					model.addAttribute("errorNoAnimalAvailable", "No Rodents added to this account, just create one from the M.P.A.T.");
					result = "account";	
				}
				break;
			case "fish":
				if(prepaidQR.getFish() != null) {
					Fish fish = prepaidQR.getFish();
					model.addAttribute("fish", fish);
				}else {
					PrintName.printUser(model);
					model.addAttribute("errorNoAnimalAvailable", "No Fishes added to this account, just create one from the M.P.A.T.");
					result = "account";	
				}
				break;
			}
			
			User user = userService.findUser(prepaidQR.getUserName());
			Owner owner = user.getOwner();
			model.addAttribute("owner", owner);
		}
		return result;
	}
	
	@RequestMapping("/ch")
	public String ch(Model model, PrepaidQR prepaidQR) {
		PrintName.printUser(model);
		Dog dog = new Dog();
		model.addAttribute("dog", dog);
		return "ch";
	}
	
	@PostMapping("/ch")
	public String chNow(@ModelAttribute Animal animal, Model model) {
		PrintName.printUser(model);
		return "ch";
	}
	
	@GetMapping("/status")
	public @ResponseBody String getStatus(@RequestParam String id, @RequestParam String typeAnimal) {
		String result = null;
		PrepaidQR prepaidQR = prepaidQrService.findById(id);
		
		switch(typeAnimal) {
		case "dog":
			if(prepaidQR.getDog() != null) {
				Dog dog = prepaidQR.getDog();
				if(dog.isStatus()) {
					return result = Boolean.toString(prepaidQR.getDog().isStatus());
				}
				else{
					return result = Boolean.toString(prepaidQR.getDog().isStatus());
				}
			}
			else {
				return result = "Unable to reach the response";
			}
		case "cat":
			if(prepaidQR.getCat() != null){
				Cat cat = prepaidQR.getCat();
				if(cat.isStatus()) {
					return result = Boolean.toString(prepaidQR.getCat().isStatus());
				}
				else{
					return result = Boolean.toString(prepaidQR.getCat().isStatus());
				}
			}else{
				return result = "Unable to reach the response";
			}
		case "horse":
			Horse horse = prepaidQR.getHorse();
			if(prepaidQR.getHorse() != null) {
				if(horse.isStatus()) {
					return result = Boolean.toString(prepaidQR.getHorse().isStatus());
				}
				else{
					return result = Boolean.toString(prepaidQR.getHorse().isStatus());
				}
			}
			else {
				return result = "Unable to reach the response";
			}
		}		
		return result;
	}
	
	@GetMapping("/change-status")
	public @ResponseBody String changeStatus(@RequestParam String id, @RequestParam String typeAnimal, @RequestParam boolean status) {
		PrepaidQR prepaidQR = prepaidQrService.findById(id);
		String result = null;
		
		switch(typeAnimal) {
		case "dog":
			if(prepaidQR.getDog() != null) {
				prepaidQR.getDog().setStatus(status);
				prepaidQrService.createPrepaidQR(prepaidQR);
					if(prepaidQR.getDog().isStatus() == true) {
						result = "Dog was declared in emergency";
					}else {
						result = "Dog was has been rescued";
					}
			}
			else {
				result = "Dog appears to be null, maybe it's not created";
			}
			break;
		case "cat":
			if(prepaidQR.getCat() != null){
				prepaidQR.getCat().setStatus(status);
				prepaidQrService.createPrepaidQR(prepaidQR);
					if(prepaidQR.getCat().isStatus() == true) {
						result = "Cat was declared in emergency";
					}else {
						result = "Cat was has been rescued";
					}
				
			}else{
				result = "Cat appears to be null, maybe it's not created";
			}
			break;
		case "horse":
			if(prepaidQR.getHorse() != null) {
				prepaidQR.getHorse().setStatus(status);
				prepaidQrService.createPrepaidQR(prepaidQR);
					if(prepaidQR.getHorse().isStatus() == true) {
						result = "Horse was declared in emergency";
					}else {
						result = "Horse was has been rescued";
					}
			}
			else {
				result = "Horse appears to be null, maybe it's not created";
			}
			break;
		}
		return result;
	}
	
	@RequestMapping(value = "/send-contact-form", method = RequestMethod.GET)
	public String sendContactFormMail(@ModelAttribute FormMessage formMessage, Model model) {
		PrintName.printUser(model);
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setSubject(formMessage.getSubject());
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("info@molokotech.com"));
				mimeMessage.setFrom(new InternetAddress("info@molokotech.com"));
				mimeMessage.setText("Email of the sender is: "+formMessage.getEmail()+", Body of the message: "+ formMessage.getDescription());
			}
		};

		try {
			this.emailSender.send(preparator);
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
		return "index";
	}
	
	@GetMapping("/user")
	public String userSetUp(Model model) {
		PrintName.printUser(model);
		return "user";
	}
	
	/* Change values from Owner object and insert or update the one who match with the name (username) */
	@PostMapping("/user-setup")
	public @ResponseBody void userSetUp(Model model, User user, @ModelAttribute Owner owner) throws ApiException, InterruptedException, IOException {
		PrintName.printUser(model);
		
		owner.setLatitude(GoogleMapsService.getLatitude(owner.getAddress()));
		owner.setLongitude(GoogleMapsService.getLongitude(owner.getAddress()));
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = userService.findUser(auth.getName());
		user.setOwner(owner);
		userService.saveUser(user);
	}
	
}
