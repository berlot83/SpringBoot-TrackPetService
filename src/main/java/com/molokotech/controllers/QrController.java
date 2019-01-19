package com.molokotech.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
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
import org.springframework.web.bind.annotation.RestController;

import com.molokotech.model.PrepaidQR;
import com.molokotech.model.User;
import com.molokotech.service.PetService;
import com.molokotech.service.PrepaidQrService;
import com.molokotech.service.UserService;
import com.molokotech.utilities.PrintName;
import com.molokotech.utilities.TokenCreator;
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
					userService.saveUser(user);
				} catch (Exception e) {
					System.out.println("Entró en exceptción");
					System.out.println(e.getMessage());
					return "sign-up";
				}			
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
	public String prepaidQrSubmit(@ModelAttribute PrepaidQR prepaidQR, Model modelName, Model modelError) {
		PrintName.printUser(modelName);
		
		String selledOnlineVerify = prepaidQrService.findById(prepaidQR.getId().toHexString()).getSelledOnline();
		String tempSpecialId = prepaidQrService.findById(prepaidQR.getId().toHexString()).getId().toHexString();
		String result = null;
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
				System.out.println("No tiene QR asociados");
			}
		}
		model.addAttribute("list", resultList);
		return "account";
	}
	
	@GetMapping("/download")
	public String downloadDesign(Model model) {
		PrepaidQR prepaidQR = new PrepaidQR();
		PrintName.printUser(model);
		model.addAttribute("prepaidQR", prepaidQR);
		return "download";
	}
	
	@PostMapping("/download")
	public String chooseDesign(@ModelAttribute PrepaidQR prepaidQR, Model model) {
		PrintName.printUser(model);
		System.out.println(prepaidQR.getId());
		System.out.println(prepaidQR.getSelledOnline());
		
		return "download";
	}
	
	@GetMapping("/emergency")
	public String emergency(@RequestParam String id, Model model) {
		PrintName.printUser(model);
		PrepaidQR prepaidQR = prepaidQrService.findById(id);
		prepaidQR.getPet().setStatus(false);
		prepaidQrService.createPrepaidQR(prepaidQR);
		model.addAttribute("emergency","Perdido");
		return "index";
	}
	
	@GetMapping("/rescued")
	public String rescued(@RequestParam String id, Model model) {
		PrintName.printUser(model);
		PrepaidQR prepaidQR = prepaidQrService.findById(id);
		prepaidQR.getPet().setStatus(true);
		prepaidQrService.createPrepaidQR(prepaidQR);
		model.addAttribute("rescued","Rescatado");
		return "index";
	}
	
}
