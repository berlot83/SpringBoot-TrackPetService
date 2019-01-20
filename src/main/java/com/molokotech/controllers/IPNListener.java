package com.molokotech.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paypal.core.Constants;
import com.paypal.core.rest.APIContext;
import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalResource;
import com.paypal.ipn.IPNMessage;
import com.paypal.sdk.openidconnect.Session;
import com.paypal.sdk.openidconnect.Tokeninfo;

public class IPNListener {

	private OAuthTokenCredential oAuthTokenCredential;
	
	
	@RequestMapping(value = "/paypal-listener", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.ALL_VALUE})
	public void listen(HttpServletRequest request) {
		//Loger.startLogger();
		// For a full list of configuration parameters refer in wiki page.
		// (https://github.com/paypal/sdk-core-java/wiki/SDK-Configuration-Parameters)

		Map<String, String> configMap = new HashMap<>();
		configMap.put("mode", "sandbox");
		//configMap.put("acct1.UserName", "jb-us-seller_api1.paypal.com");
		//configMap.put("acct1.Password", "WX4WTU3S8MY44S7F");
		//configMap.put("acct1.Signature", "AYeQQfJjGppIxobZXMrXM1SVL-QEIAaDSZRT1KIkgYRzvsT-j8HzT5kFX_1aik4XRpNmTgW88d4FRa6Q");
		
	    Properties properties = new Properties();
	    PayPalResource.initConfig(properties);

	    configMap.put("clientId", "AYeQQfJjGppIxobZXMrXM1SVL-QEIAaDSZRT1KIkgYRzvsT-j8HzT5kFX_1aik4XRpNmTgW88d4FRa6Q");
	    configMap.put("clientSecret", "EJiKr-GV63FopxeSpkpEUMnz7ys9balWNFUNznq0K5VXjqkcexf-E9ZUx75KgH8zMkh2X0ZB4NnwzNU_");
	    properties.putAll(configMap);

	    oAuthTokenCredential = new OAuthTokenCredential(
	            configMap.get(Constants.CLIENT_ID),
	            configMap.get(Constants.CLIENT_SECRET),
	            configMap
	    );
	    
	    System.out.println(oAuthTokenCredential);
	    
		
		
//		String clientId = "AYeQQfJjGppIxobZXMrXM1SVL-QEIAaDSZRT1KIkgYRzvsT-j8HzT5kFX_1aik4XRpNmTgW88d4FRa6Q";
//		String clientSecret = "EJiKr-GV63FopxeSpkpEUMnz7ys9balWNFUNznq0K5VXjqkcexf-E9ZUx75KgH8zMkh2X0ZB4NnwzNU_";

//		APIContext context = new APIContext(clientId, clientSecret);
//		context.setConfigurationMap(configMap);

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
	}
}
