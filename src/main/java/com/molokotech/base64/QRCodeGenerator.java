package com.molokotech.base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.EnumMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator{

	/* Create a QR-Code white byte[] results */
	public static byte[] generateQRCodeImageToByte(String text, int width, int height)
			throws WriterException, IOException {
		
		/* Border size */
		Map<EncodeHintType, Object> hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 1); /* default = 4 */
		/* Border size */
		
		
		/* ZXing Matrix formatter */ 
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		
		
		/* Manage the bytes for an output in this case byte[] */ 
		ByteArrayOutputStream imageOut = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", imageOut);
		byte[] imageData = imageOut.toByteArray(); 
		return imageData;
	}

	public static String toBase64(byte[] imageData) {
		/* Conevert byte[] to Base64 encode in a simple line, need to return an String to insert DB data */
		String i = Base64.getEncoder().encodeToString(imageData);
		return i;
	}
	
	public static void main(String[] args) throws WriterException, IOException {
	/* For console test porpuse only */
		
		byte[] qr = QRCodeGenerator.generateQRCodeImageToByte("https://pets2018.herokuapp.com/controllers.jsp", 300, 300);
		String str64 = Base64.getEncoder().encodeToString(qr);
		System.out.println(str64);
	}

}
