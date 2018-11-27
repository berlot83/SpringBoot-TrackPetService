
function about(){
		$.confirm({
			type: 'red',
		    title: 'Servicios informativos de mascotas!',
		    content: '<p style="text-align:justify;">Es un producto de alta tecnologia diseñado y desarrollado por Molokotech Solutions, su funcion es crear un codigo QR que asu vez lleva cargado varios datos esenciales sobre la mascota en cuestion, facilitando asi su recuperacion en el caso que la misma se haya perdido.</p><br>Desarrollo y mantenimiento por:<br><a href="http://molokotech.com" style="text-align:center;">Molokotech.com</a> ',
	});
}

function contact(){
	$.confirm({
		type: 'green',
		title: 'Contacto',
		columnClass: 'col-md-6 col-md-offset-3',
		content: 'Para ventas a veterinarias y/o revendedores <a href="mailto:molokotech.com">info@molokotech.com</a><br>Para venta en canal minorista comunicarse con <a href="mailto:molokotech.com">info@molokotech.com</a><br>Para consultas generales sobre el mismo y/o alcances de responsabilidad <a href="mailto:molokotech.com">info@molokotech.com</a>',
		
		buttons: {
	        danger: {
	        	text: 'Continuar',
	            confirmButton: 'btn-red', // multiple classes.
	            confirmButtonClass: 'btn-red',
	            action: function(){}
	        }
	    }
	})
}

function redirectTo(){
	window.location.href="http://molokotech.com";
}

function test(){
	
	var xhr = new XMLHttpRequest();
	var url = "/TrackPetService/rest/pet/tost"; //For Webapp-runner must go this root /rest/pet/insert

	//Send the proper header information along with the request
	xhr.onreadystatechange = function() {//Call a function when the state changes.
	    
		if(xhr.readyState == 4 && xhr.status == 200) {
	    
			if(xhr.responseText){
				xhr.setRequestHeader("token", "acepted");
				console.log(xhr.responseText);
			}else{
				console.log(xhr.responseText);
			}
			
	    }
	}
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("user="+user+"&password="+password);
//		Ajax Call
}
    
function checkVaccines(){
	var checkVaccines = document.getElementById("checkVaccines");
	var antiRabic = document.getElementById("dateAntiRabicVaccine");
	var polivalent = document.getElementById("datePolivalentVaccine");
	var sextuple = document.getElementById("dateSextupleVaccine");
	var octuple = document.getElementById("dateOctupleVaccine");
	
	if(checkVaccines.checked){
		antiRabic.disabled = false;					
		polivalent.disabled = false;
		sextuple.disabled = false;
		octuple.disabled = false;
		
	}else{
		antiRabic.disabled = true;					
		polivalent.disabled = true;
		sextuple.disabled = true;
		octuple.disabled = true;
	
	}	
}


function age(){
	var yearBirthay = document.getElementById("age");
	var actualYear = (new Date()).getFullYear();
	var age = actualYear - yearBirthay.value; 
	
	console.log(age);
	
	var resultAge = document.getElementById("resultAge");
	
	resultAge.innerHTML = "<h5><a href='#'><span class='badge badge-danger'>Edad actual de la mascota:  "+age+" años</span></a></h5>";
}
