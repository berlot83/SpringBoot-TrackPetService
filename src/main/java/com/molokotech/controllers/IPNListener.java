package com.molokotech.controllers;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.ipn.IPNMessage;

@RestController
@Controller
public class IPNListener {
	
	@RequestMapping(value = "/cgi-bin/webscr", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.ALL_VALUE})
	public ResponseEntity<?> listener(HttpServletRequest request, HttpServletRequest response) {
		System.out.println(request);
//		BraintreeGateway gateway = new BraintreeGateway("access_token$sandbox$8gjw3gq25x766s85$61879bce8e0595ef46bf338b28870833");
		
		
//		String clientId = "AYeQQfJjGppIxobZXMrXM1SVL-QEIAaDSZRT1KIkgYRzvsT-j8HzT5kFX_1aik4XRpNmTgW88d4FRa6Q";
//		String clientSecret = "EJiKr-GV63FopxeSpkpEUMnz7ys9balWNFUNznq0K5VXjqkcexf-E9ZUx75KgH8zMkh2X0ZB4NnwzNU_";

		/* berlot83-facilitator@yahoo.com.ar (sandbox account) */
		/* access_token$sandbox$8gjw3gq25x766s85$61879bce8e0595ef46bf338b28870833 (access token) */
		/* 20 Jan 2029 (expiry date) */

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

		if (isIpnVerified) {
			System.out.println("Verified IPN");
			System.out.println(transactionType);
			System.out.println(map);
		}
		else{
			System.out.println("Not a valid IPN Request!");
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
