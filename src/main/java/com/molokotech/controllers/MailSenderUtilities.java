package com.molokotech.controllers;

import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.molokotech.model.Order;
import com.molokotech.model.PrepaidQR;
import com.molokotech.model.User;
import com.molokotech.service.UserService;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;

@Component
@RestController
public class MailSenderUtilities {

	@Autowired
	public UserService userService;
	
	@Autowired
	public JavaMailSender emailSender;

	@GetMapping("/onlineBuyResponseMail")
	public void placeOrder() {
		Order order = new Order();
		User user = new User();
		user.setName("Arsenio");
		user.setEmail("berlot83@yahoo.com.ar");
		order.setOrderNumber("asdsfsdfsdfsdwerwe4r");
		order.setUser(user);

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setSubject("Compra de Código QR número: " + order.getOrderNumber());
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(order.getUser().getEmail()));
				mimeMessage.setFrom(new InternetAddress("info@molokotech.com"));
				mimeMessage.setText("Hola " + order.getUser().getName()
						+ ",\nGracias por tu compra de código QR, con el siguiente código ingresá a nuestra web <a>https://pet-cloud-service.herokuapp.com/prepaid-qr</a>.\n\n• Tendrás que utilizar el usuario y contraseña que elegiste para hacer la compra.\nEn la pantalla de activación aparecerá un campo para ingresar un código de 22 caractéres adjunto a este mail.\nUna vez colocado el código tocá activar QR y te redirigirá a un formulario para activar el mismo.\n\nSi por alguna razón olvidaste tu usuario y password podes hacer dos cosas:\n 1) Creás un usuario nuevo desde nuestra web y adheris a ese nuevo usuario el nuevo QR.\n2) Nos mandás un mail a info@molokotech.com y lo solucionamos."
						+ "\nTu código QR asignado es " + order.getOrderNumber() + ".");
			}
		};

		try {
			this.emailSender.send(preparator);
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}

	}

	@PostMapping("/sendCoordinatesToMail")
	public void sendCoordinatesToMail(String latitude, String longitude, String mail, String dateTime) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setSubject("Coordenadas de la última lectura");
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
				mimeMessage.setFrom(new InternetAddress("info@molokotech.com"));
				mimeMessage.setText("EL QR de la mascota fue escaneada en:\n\nLatitud: "+ latitude + "\nLongitud: "+ longitude + "\nDia y hora: "+ dateTime);
			}
		};

		try {
			this.emailSender.send(preparator);
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}

	}
	
	@GetMapping("/sendQrCodeToEmail")
	public void sendQrCodeToEmail(PrepaidQR prepaidQR, User user) {
		
		String id = prepaidQR.getId().toString();
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setSubject("Id del QR adquirido.");
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
				mimeMessage.setFrom(new InternetAddress("info@molokotech.com"));
				mimeMessage.setText("EL id del QR es = " + id);
			}
		};

		try {
			this.emailSender.send(preparator);
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}

}
