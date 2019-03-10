package com.molokotech.dao;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/*  Use this Class only for CRUD actions on a Single fields, for CRUD an Object entirely use Spring Framework native methods */
public class DBConnector {
	
	private String uri = "mongodb://berlot83:2911danaa@cluster0-shard-00-00-o59x4.mongodb.net:27017,cluster0-shard-00-01-o59x4.mongodb.net:27017,cluster0-shard-00-02-o59x4.mongodb.net:27017/dbpets?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true";
	MongoClientURI clientURI = null;
	MongoClient mongoClient = null;
	
	public MongoTemplate DBConnectorMongoTemplate(String db) {
		
		clientURI = new MongoClientURI(uri);
		mongoClient = new MongoClient(clientURI);	
		MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, db);
		
		
		return mongoTemplate;
	
	}
	
	public void close() {
		mongoClient.close();
		System.out.println("DB closed");
	}
	
	

}
