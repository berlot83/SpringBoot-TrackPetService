/**
 * Author AAB
 * 2018
 */

/* File source Ajax calls */


function insertQR(){
	    			
/* Start insertPet() */	    			    		
	console.log("Hola mundo");
	var raze = document.getElementById("raze");
	var name = document.getElementById("name");
	var age = document.getElementById("age");
	var ownerName = document.getElementById("ownerName");
	var ownerLastname = document.getElementById("ownerLastname");
	var ownerDni = document.getElementById("ownerDni");
	var street1 = document.getElementById("street1");
	var phone1 = document.getElementById("phone1");
	var phone2 = document.getElementById("phone2");
	var email = document.getElementById("email");
	var facebook = document.getElementById("facebook");
	var instagram = document.getElementById("instagram");
	var dateAntiRabicVaccine = document.getElementById("dateAntiRabicVaccine");
	var datePolivalentVaccine = document.getElementById("datePolivalentVaccine");
	var dateSextupleVaccine = document.getElementById("dateSextupleVaccine");
	var dateOctupleVaccine = document.getElementById("dateOctupleVaccine");
	var clinicHistory = document.getElementById("clinicHistory");
	var illness = document.getElementById("illness");
	var medicated = document.getElementById("medicated");
	var status = document.getElementById("status");
	var subscription = document.getElementById("subscription");
	
	var xhr1 = new XMLHttpRequest();
	var url = "/TrackPetService/rest/pet/insert"; //For Webapp-runner must go this root /rest/pet/insert

	//Send the proper header information along with the request
	xhr1.onreadystatechange = function() {//Call a function when the state changes.
		if(xhr1.readyState == 4 && xhr1.status == 200) {

			/* On Controllers page to show QR tag generated */
			var createImage = document.createElement("img");
			var divResult = document.getElementById("qrResult");	    	
			var attribute = document.createAttribute("src");
			attribute.value = "data:image/png;base64, "+xhr1.responseText;
			createImage.setAttributeNode(attribute);
			divResult.appendChild(createImage);
			
			$.alert("<body><content><div><img src='data:image/png;base64, "+xhr1.responseText+"'></img></div></content></body>");
			
		}
	}
	xhr1.open("POST", url, true);
	xhr1.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr1.send("user="+user+"&raze="+raze.value+"&name="+name.value+"&age="+age.value+"&ownerName="+ownerName.value+"&ownerLastname="+ownerLastname.value+"&ownerDni="+ownerDni.value+"&street1="+street1.value+"&phone1="+phone1.value+"&phone2="+phone2.value+"&email="+email.value+"&facebook="+facebook.value+"&instagram="+instagram.value+"&dateAntiRabicVaccine="+dateAntiRabicVaccine.value+"&datePolivalentVaccine="+datePolivalentVaccine.value+"&dateSextupleVaccine="+dateSextupleVaccine.value+"&dateOctupleVaccine="+dateOctupleVaccine.value+"&clinicHistory="+clinicHistory.value+"&illness="+illness.value+"&medicated="+medicated.value+"&status="+status.value+"&subscription="+subscription.value);
}



function insertPet(){
	
	console.log("Hola mundo");
	var raze = document.getElementById("raze");
	var name = document.getElementById("name");
	var age = document.getElementById("age");
	var ownerName = document.getElementById("ownerName");
	var ownerLastname = document.getElementById("ownerLastname");
	var ownerDni = document.getElementById("ownerDni");
	var street1 = document.getElementById("street1");
	var phone1 = document.getElementById("phone1");
	var phone2 = document.getElementById("phone2");
	var clinicHistory = document.getElementById("clinicHistory");
	var illness = document.getElementById("illness");
	var medicated = document.getElementById("medicated");
	var status = document.getElementById("status");
	var subscription = document.getElementById("subscription");
	
	var xhr = new XMLHttpRequest();
	var url = "/qr/create";

	//Send the proper header information along with the request
	xhr.onreadystatechange = function() {//Call a function when the state changes.
	    if(xhr.readyState == 4 && xhr.status == 200) {
	  
	    	/* On Controllers page to show QR tag generated */
	    	var createImage = document.createElement("img");
	    	var divResult = document.getElementById("qrResult");	    	
	    	var attribute = document.createAttribute("src");
	    	attribute.value = "data:image/png;base64, "+xhr.responseText;
	    	createImage.setAttributeNode(attribute);
	    	divResult.appendChild(createImage);
	    	
	    	// var link = document.createElement("a");
	    	// var hrefAttribute = document.createAttribute("href");
	    	// hrefAttribute.value = "google.com.ar";
	    	// link.setAttributeNode(hrefAttribute);
	    	// link.setAttribute('download', 'Filename.jpg');
	    	// link.setAttribute('target', '_blank');
	    	
	    	// var button = document.createElement("button");

	    	// var classAttribute = document.createAttribute("class");
	    	// classAttribute.value = "btn btn-primary";
	    	// button.setAttributeNode(classAttribute);
	    	
	    	// button.innerHTML = "Descarga";
	    	// var downloadButtonDiv = document.getElementById("downloadButtonDiv");
	    	// downloadButtonDiv.appendChild(button);
	    	
	    	// button.appendChild(link);	    	
	    }
	}
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("raze="+raze.value+"&name="+name.value+"&age="+age.value+"&ownerName="+ownerName.value+"&ownerLastname="+ownerLastname.value+"&ownerDni="+ownerDni.value+"&street1="+street1.value+"&phone1="+phone1.value+"&phone2="+phone2.value+"&clinicHistory="+clinicHistory.value+"&illness="+illness.value+"&medicated="+medicated.value+"&status="+status.value+"&subscription="+subscription.value);
}


function advice(){
$.alert({
	type: 'blue',
    title: 'Etiqueta QR',
    content: 'Recuerde que cuando toque crear y enviar debe descargar la imagen QR para luego utilizarla, si no se realiza esa accion y se recarga la pagina hay que cargar los datos de nuevo.',
});
}




function loginToMolokoAccess(){
		
	$.confirm({
		type: 'red',
	    title: 'Super Admin <i class="fas fa-lock" style="color:grey;"></i>',
	    content: '' +
	    '<form action="" class="formName">' +
	    '<div class="form-group">' +
	    '<label>Usuario</label>' +
	    '<input type="text" id="superUser" placeholder="Administrador" class="name form-control" required />' +
	    '</div>' +
	    '<div class="form-group">' +
	    '<label>Password</label>' +
	    '<input type="password" id="superPassword" placeholder="Pass" class="name form-control" required />' +
	    '</div>' +
	    '</form>',
	    buttons: {
	        formSubmit: {
	            text: 'Login',
	            btnClass: 'btn-blue',
	            action: function () {
	            	
	            	//		Ajax call
	    			var user = document.getElementById("superUser");
	    			var password = document.getElementById("superPassword");
	    			console.log(user);
	    			console.log(password);
	    			var xhr = new XMLHttpRequest();
	    			var url = "/TrackPetService/rest/pet/super"; //For Webapp-runner must go this root /rest/pet/insert
	    		
	    			//Send the proper header information along with the request
	    			xhr.onreadystatechange = function() {//Call a function when the state changes.
	    			    if(xhr.readyState == 4 && xhr.status == 200) {
	    			    
	    			    	console.log(xhr.responseText);
	    			    	if(xhr.responseText == "true"){









			                    window.location.replace("/controllers.jsp");
			                }else{
			                	$.alert({	type: "red",
			                				title: "Error de logueo <i class='fas fa-exclamation-triangle' style='color:red'></i>",
			                				content:"Este apartado es de uso interno de la empresa.\nSi desea ingresar datos a la Cloud dirijase a <a href='/TrackPetService/controllers.jsp'>Crear etiqueta QR</a>"});
			                }	
	    			    }
	    			}
	    			xhr.open("POST", url, true);
	    			xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    			xhr.send("user="+user.value+"&password="+password.value);
	    	//		Ajax Call
	    			
		            }
	            
	        },
	        cancel: function () {
	            //close
	        },
	    },
	    onContentReady: function () {
	        // bind to events
	        var jc = this;
	        this.$content.find('form').on('submit', function (e) {
	            // if the user submits the form by pressing enter in the field.
	            e.preventDefault();
	            jc.$$formSubmit.trigger('click'); // reference the button and click it
	        });
	    }
	});
}