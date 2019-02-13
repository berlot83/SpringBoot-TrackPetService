package com.molokotech.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.molokotech.model.User;
import com.mongodb.MongoClient;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private MongoClient mongoClient;
	@Autowired
	UserService userService;
	
	/* Implements email login, but only email login, no username  */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		MongoDatabase database = mongoClient.getDatabase("dbpets");
		MongoCollection<Document> collection = database.getCollection("users");
		
		/* Catch the entire user */
		User userT = userService.findUserByEmail(username);
		
		/* Get his name */
		String name = userT.getName();
		
		/* Find the document into DB */
		Document document = collection.find(Filters.eq("name", name)).first();
		
		/* Get the name of the doc */
		String nameTemp = document.get("name").toString();
		
		/* Put it into Mongo details user, no original implementation */
		if(document!=null) {
			            String password = document.getString("password");
			            List<String> authorities = (List<String>) document.get("authorities");
			            MongoUserDetails mongoUserDetails = new MongoUserDetails(nameTemp,password,authorities.toArray(new String[authorities.size()]));
			            return mongoUserDetails;
			        }
		
		return null;
		
	}
	

	/* Backup to login with username only, original implementation do not delete */
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		MongoDatabase database = mongoClient.getDatabase("dbpets");
//		MongoCollection<Document> collection = database.getCollection("users");
//		
//		Document document = collection.find(Filters.eq("name", username)).first();
//		
//		
//		if(document!=null) {
//			 
//			            String password = document.getString("password");
//			            List<String> authorities = (List<String>) document.get("authorities");
//			            MongoUserDetails mongoUserDetails = new MongoUserDetails(username,password,authorities.toArray(new String[authorities.size()]));
//			            return mongoUserDetails;
//			        }
//		return null;
//		
//	}

}
