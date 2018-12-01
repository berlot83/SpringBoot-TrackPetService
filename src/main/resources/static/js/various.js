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

function pregnant(){
	var sex = document.getElementById("sex").value;
	var pregnant = document.getElementById("pregnant");
	if (sex == "Macho") {
		pregnant.disabled = true;
	}else{
		pregnant.disabled = false;
	}
}
