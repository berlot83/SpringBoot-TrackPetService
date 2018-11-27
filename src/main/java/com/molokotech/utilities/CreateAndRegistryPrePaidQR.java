package com.molokotech.utilities;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.zxing.WriterException;
import com.molokotech.base64.QRCodeGenerator;
import com.molokotech.model.PrepaidQR;
import com.molokotech.model.User;
import com.molokotech.service.PrepaidQrService;

@Controller
public class CreateAndRegistryPrePaidQR {
	
	@Autowired
	PrepaidQrService prepaidQrService;

	public void createRandomQRPrepaid(PrepaidQR prepaidQR) throws WriterException, IOException {
		
				/*  Create an object QR */
				
		
				/* String token */
				String specialId = "PP".concat(TokenCreator.createSpecialId());
				/* Set the special Id for each one */
				prepaidQR.setSpecialId(specialId);

				/* Create QR only with special ID */
				byte[] imageData = QRCodeGenerator.generateQRCodeImageToByte(prepaidQR.getSpecialId(), 300, 300);
				String strBase64 = QRCodeGenerator.toBase64(imageData);
				prepaidQR.setStrBase64(strBase64);
				System.out.println(strBase64);
				
				prepaidQR.setId("hola");
				prepaidQR.setUser(new User());
System.out.println(prepaidQR.getId());		
System.out.println(prepaidQR.getSpecialId());				
				
				/* creamos el objeto y sbimos el mismo a la DB */
				prepaidQrService.create(prepaidQR);		

	}

	public static void main(String args[]) throws WriterException, IOException, InterruptedException {
		CreateAndRegistryPrePaidQR registry = new CreateAndRegistryPrePaidQR();
		registry.createRandomQRPrepaid(new PrepaidQR());
	}

}