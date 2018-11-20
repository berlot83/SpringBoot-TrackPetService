package com.molokotech.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {


	@Id
    private String id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String address;
    private String dni;
    private List<String> telephone;
    private String facebook;
    private String instagram;
    private String sessionToken;
    private String lostDogToken;
    
    public User(String email, String password, String name, String lastname, String address, String dni,
    		List<String> telephone, String facebook, String instagram, String sessionToken, String lostDogToken) {
    	super();
    	this.email = email;
    	this.password = password;
    	this.name = name;
    	this.lastname = lastname;
    	this.address = address;
    	this.dni = dni;
    	this.telephone = telephone;
    	this.facebook = facebook;
    	this.instagram = instagram;
    	this.sessionToken = sessionToken;
    	this.lostDogToken = lostDogToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public List<String> getTelephone() {
		return telephone;
	}

	public void setTelephone(List<String> telephone) {
		this.telephone = telephone;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public String getLostDogToken() {
		return lostDogToken;
	}

	public void setLostDogToken(String lostDogToken) {
		this.lostDogToken = lostDogToken;
	}

}
