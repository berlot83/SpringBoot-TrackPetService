/**
 * Author AAB
 * 2018
 */

/* File source Ajax calls */

/* Temp disable, it is pretty anoying */
function advice() {
	$.alert({
		columnClass: 'col-lg-6',
		type: 'red',
		title: 'Instrucciones',
		animation: 'zoom',
		containerFluid: true,
		content: '<body><div><ol><li>Utilice el motor de búsqueda de google para encontrar la ubicación ya que toma Latitud y Longitud exacta.</li> <li>Si necesita modificar algún dato vuelva ingresar nuevamente a esta página con el mismo ID.</li> <li>Tiene que completar la mayoria de los campos, menos los de vacunación alimentación etc, los básicos como nombre de la mascota, datos de contacto y dirección son obligatorios para el buen funcionamiento del sistema.</li><li>Una vez terminada la actualización de datos saldrá un QR, léalo con cualquier lector y compruebe que funcione correctamente</li><li>Haga lo mismo con el que tiene físicamente, si algo no funciona correctamente no dude en contactarnos a <a href=mailto:info@molokotech.com><span class="badge badge-lg badge-info">info@molokotech.com</span></a></li></ol></div></body>',
	});
}

/* Complete creation and asimilation of prepaidQR to the user account */
function updateQrDog() {
	var idPrepaidQrCode = document.getElementById("idPrepaidQrCode").textContent;
	var petName = document.getElementById("petName").value;
	var raze = document.getElementById("raze").value;
	var foodBrand = document.getElementById("foodBrand").value;
	var quantityFood = document.getElementById("quantityFood").value;
	var neuteredPet = document.getElementById("neuteredPet").value;
	var user = document.getElementById("user").textContent;
	var age = document.getElementById("age").value;
	var lastVeterinaryVisit = document.getElementById("lastVeterinaryVisit").value;
	var dateAntiRabicVaccine = document.getElementById("dateAntiRabicVaccine").value;
	var datePolivalentVaccine = document.getElementById("datePolivalentVaccine").value;
	var dateSextupleVaccine = document.getElementById("dateSextupleVaccine").value;
	var dateOctupleVaccine = document.getElementById("dateOctupleVaccine").value;
	var status = document.getElementById("status").value;
	var clinicHistory = document.getElementById("clinicHistory").value;
	var illness = document.getElementById("illness").value;
	var medicated = document.getElementById("medicated").value;
	var coockedFood = document.getElementById("coockedFood").value;
	var sex = document.getElementById("sex").value;
	var pregnant = document.getElementById("pregnant").value;
	var weight = document.getElementById("weight").value;
	var videoUrl = document.getElementById("videoUrl").value;

	var xhr = new XMLHttpRequest();
	var url = "/update-qr-dog";

	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("idPrepaidQrCode=" + idPrepaidQrCode +"&raze=" + raze + "&foodBrand=" + foodBrand + "&user=" + user + "&age=" + age + " &lastVeterinaryVisit=" + lastVeterinaryVisit + "&dateAntiRabicVaccine=" + dateAntiRabicVaccine + "&datePolivalentVaccine=" + datePolivalentVaccine + "&dateSextupleVaccine=" + dateSextupleVaccine + "&dateOctupleVaccine=" + dateOctupleVaccine + "&clinicHistory=" + clinicHistory + "&illness=" + illness + "&medicated=" + medicated + "&quantityFood=" + quantityFood + "&neuteredPet=" + neuteredPet + "&coockedFood=" + coockedFood + "&status=" + status + "&pregnant=" + pregnant + "&weight=" + weight + "&petName=" + petName + "&sex=" + sex + "&videoUrl=" + videoUrl);
	console.log(videoUrl);
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			
			data = xhr.responseText;
			var jsonResponse = JSON.parse(data);
			$.alert({type: 'green', 
					title: 'Compruebe el funcionamiento',
					content: "<body><content> <div><p style='color:deeppink'>"+ idPrepaidQrCode +"</p></div><div><img src='data:image/png;base64, "+jsonResponse["strBase64"]+"'></img></div><div><p>Lea este código QR para verificar que los datos se subieron correctamente, si los mismo no se subieron intente lo de nuevo, caso contrario comuníquese con el código ID a info@molokotech.com que gestionamos por usted la carga de datos.</p></div></content></body>"});
		}
		if(xhr.status == 500){
			$.alert({
				
				type: 'red',
				title: 'Error del lado del servidor',
				content:"<p style='text-align:justify'>Muy posiblemente no es posible obtener la latitud y longitud de la dirección, utilice el motor de busqueda de google. Si el mismo no funciona, Coloque la dirección de la siguiente los siguientes ejemplos:</p> <ol><li>Lima 925, Buenos Aires, Argentina</li><li>Avenida de Mayo 725, Villa Adelina, Buenos Aires, Argentina</li></ol><br><p style='text-align:justify'>Si igualmente no funciona ingrese a <a href='google.com'>GoogleMaps</a>, encuentre su dirección y como aparece en la barra de búsqueda asistida y copiela en la nuestra.</p>"});
			
		}
		if(xhr.status == 400){
			$.alert({
				type: "red",
				title: "Error",
				content: "Falta enviar algun campo"});
		}
	}

}

/* Complete creation and asimilation of prepaidQR to the user account */
function updateQrCat() {
	var idPrepaidQrCode = document.getElementById("idPrepaidQrCode").textContent;
	var petName = document.getElementById("petName").value;
	var raze = document.getElementById("raze").value;
	var foodBrand = document.getElementById("foodBrand").value;
	var quantityFood = document.getElementById("quantityFood").value;
	var neuteredPet = document.getElementById("neuteredPet").value;
	var user = document.getElementById("user").textContent;
	var age = document.getElementById("age").value;
	var lastVeterinaryVisit = document.getElementById("lastVeterinaryVisit").value;
	var dateAntiRabicVaccine = document.getElementById("dateAntiRabicVaccine").value;
	var datePolivalentVaccine = document.getElementById("datePolivalentVaccine").value;
	var dateSextupleVaccine = document.getElementById("dateSextupleVaccine").value;
	var dateOctupleVaccine = document.getElementById("dateOctupleVaccine").value;
	var status = document.getElementById("status").value;
	var clinicHistory = document.getElementById("clinicHistory").value;
	var illness = document.getElementById("illness").value;
	var medicated = document.getElementById("medicated").value;
	var coockedFood = document.getElementById("coockedFood").value;
	var sex = document.getElementById("sex").value;
	var pregnant = document.getElementById("pregnant").value;
	var weight = document.getElementById("weight").value;
	var videoUrl = document.getElementById("videoUrl").value;

	var xhr = new XMLHttpRequest();
	var url = "/update-qr-cat";

	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("idPrepaidQrCode=" + idPrepaidQrCode + "&raze=" + raze + "&foodBrand=" + foodBrand + "&user=" + user + "&age=" + age + " &lastVeterinaryVisit=" + lastVeterinaryVisit + "&dateAntiRabicVaccine=" + dateAntiRabicVaccine + "&datePolivalentVaccine=" + datePolivalentVaccine + "&dateSextupleVaccine=" + dateSextupleVaccine + "&dateOctupleVaccine=" + dateOctupleVaccine + "&clinicHistory=" + clinicHistory + "&illness=" + illness + "&medicated=" + medicated + "&quantityFood=" + quantityFood + "&neuteredPet=" + neuteredPet + "&coockedFood=" + coockedFood + "&status=" + status + "&pregnant=" + pregnant + "&weight=" + weight + "&petName=" + petName + "&sex=" + sex + "&videoUrl=" + videoUrl);
	console.log(videoUrl);
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			
			data = xhr.responseText;
			var jsonResponse = JSON.parse(data);
			$.alert({type: 'green', 
					title: 'Compruebe el funcionamiento',
					content: "<body><content> <div><p style='color:deeppink'>"+ idPrepaidQrCode +"</p></div><div><img src='data:image/png;base64, "+jsonResponse["strBase64"]+"'></img></div><div><p>Lea este código QR para verificar que los datos se subieron correctamente, si los mismo no se subieron intente lo de nuevo, caso contrario comuníquese con el código ID a info@molokotech.com que gestionamos por usted la carga de datos.</p></div></content></body>"});
		}
		if(xhr.status == 500){
			$.alert({
				
				type: 'red',
				title: 'Error del lado del servidor',
				content:"<p style='text-align:justify'>Muy posiblemente no es posible obtener la latitud y longitud de la dirección, utilice el motor de busqueda de google. Si el mismo no funciona, Coloque la dirección de la siguiente los siguientes ejemplos:</p> <ol><li>Lima 925, Buenos Aires, Argentina</li><li>Avenida de Mayo 725, Villa Adelina, Buenos Aires, Argentina</li></ol><br><p style='text-align:justify'>Si igualmente no funciona ingrese a <a href='google.com'>GoogleMaps</a>, encuentre su dirección y como aparece en la barra de búsqueda asistida y copiela en la nuestra.</p>"});
			
		}
		if(xhr.status == 400){
			$.alert({
				type: "red",
				title: "Error",
				content: "Falta enviar algun campo"});
		}
	}

}

/* Do not delete dependency for al Forms */
function changeColorOfAllInputs(className){
	var list = document.getElementsByTagName("input");
	var listSelect = document.getElementsByTagName("select");
	for (var i = 0; i < list.length; i++) {
		 list[i].classList.add(className);
		
	}
	
	for (var i = 0; i < listSelect.length; i++) {
		listSelect[i].classList.add(className);
	}
}

/* Retrive all pet data to the form */
function retrivePrepaidQrDog(){
	setTimeout(
		
function(){
	var xhr = new XMLHttpRequest();
	var id = document.getElementById("idPrepaidQrCode").textContent;
	var url = "/retrivePrepaidQrDog?id="+id;
	
	xhr.onreadystatechange = function () {
		
		if (xhr.readyState == 4 && xhr.status == 200) {
			var data = xhr.responseText;
			var jsonResponse = JSON.parse(data);
			
			changeColorOfAllInputs("text-danger");
			
			document.getElementById("petName").value = jsonResponse.petName;
			document.getElementById("raze").value = jsonResponse.raze;
			document.getElementById("foodBrand").value= jsonResponse.foodBrand;
			document.getElementById("quantityFood").value= jsonResponse.quantityFood;
			document.getElementById("neuteredPet").value = jsonResponse.neuteredPet;
			document.getElementById("age").value = jsonResponse.age;
			document.getElementById("lastVeterinaryVisit").value = jsonResponse.lastVeterinaryVisit;
			document.getElementById("dateAntiRabicVaccine").value = jsonResponse.dateAntiRabicVaccine;
			document.getElementById("datePolivalentVaccine").value= jsonResponse.datePolivalentVaccine;
			document.getElementById("dateSextupleVaccine").value= jsonResponse.dateSextupleVaccine;
			document.getElementById("dateOctupleVaccine").value = jsonResponse.dateOctupleVaccine;
			document.getElementById("status").value = jsonResponse.status;
			document.getElementById("clinicHistory").value = jsonResponse.clinicHistory;
			document.getElementById("illness").value = jsonResponse.illness;
			document.getElementById("medicated").value = jsonResponse.medicated;
			document.getElementById("coockedFood").value = jsonResponse.coockedFood;
			document.getElementById("sex").value = jsonResponse.sex;
			document.getElementById("pregnant").value = jsonResponse.pregnant;
			document.getElementById("weight").value = jsonResponse.weight;
			document.getElementById("videoUrl").value = jsonResponse.videoUrl;
		}
	}
	xhr.open("GET", url, true);
	xhr.send();
			
	}, 3000);
}

/* Retrive all pet data to the form */
function retrivePrepaidQrCat(){
	setTimeout(
		
function(){
	var xhr = new XMLHttpRequest();
	var id = document.getElementById("idPrepaidQrCode").textContent;
	var url = "/retrivePrepaidQrCat?id="+id;
	
	xhr.onreadystatechange = function () {
		
		if (xhr.readyState == 4 && xhr.status == 200) {
			var data = xhr.responseText;
			var jsonResponse = JSON.parse(data);
			
			changeColorOfAllInputs("text-danger");
			
			document.getElementById("petName").value = jsonResponse.petName;
			document.getElementById("raze").value = jsonResponse.raze;
			document.getElementById("foodBrand").value= jsonResponse.foodBrand;
			document.getElementById("quantityFood").value= jsonResponse.quantityFood;
			document.getElementById("neuteredPet").value = jsonResponse.neuteredPet;
			document.getElementById("age").value = jsonResponse.age;
			document.getElementById("lastVeterinaryVisit").value = jsonResponse.lastVeterinaryVisit;
			document.getElementById("dateAntiRabicVaccine").value = jsonResponse.dateAntiRabicVaccine;
			document.getElementById("datePolivalentVaccine").value= jsonResponse.datePolivalentVaccine;
			document.getElementById("dateSextupleVaccine").value= jsonResponse.dateSextupleVaccine;
			document.getElementById("dateOctupleVaccine").value = jsonResponse.dateOctupleVaccine;
			document.getElementById("status").value = jsonResponse.status;
			document.getElementById("clinicHistory").value = jsonResponse.clinicHistory;
			document.getElementById("illness").value = jsonResponse.illness;
			document.getElementById("medicated").value = jsonResponse.medicated;
			document.getElementById("coockedFood").value = jsonResponse.coockedFood;
			document.getElementById("sex").value = jsonResponse.sex;
			document.getElementById("pregnant").value = jsonResponse.pregnant;
			document.getElementById("weight").value = jsonResponse.weight;
			document.getElementById("videoUrl").value = jsonResponse.videoUrl;
					
		}
	}
	xhr.open("GET", url, true);
	xhr.send();
			
	}, 3000);
}

/* Retrive all pet data to the form */
function retrivePrepaidQrHorse(){
	setTimeout(
		
function(){
	var xhr = new XMLHttpRequest();
	var id = document.getElementById("idPrepaidQrCode").textContent;
	var url = "/retrivePrepaidQrHorse?id="+id;
	
	xhr.onreadystatechange = function () {
		
		if (xhr.readyState == 4 && xhr.status == 200) {
			var data = xhr.responseText;
			var jsonResponse = JSON.parse(data);
			
			changeColorOfAllInputs("text-danger");
			
			document.getElementById("horseName").value = jsonResponse.petName;
			document.getElementById("selectTypeHorse").value = jsonResponse.typeHorse;
			document.getElementById("selectBreedHorse").value= jsonResponse.breed;
			document.getElementById("selectIllnessHorse").value = jsonResponse.illness;
			document.getElementById("selectSexHorse").value = jsonResponse.sex;
			document.getElementById("ageHorse").value = jsonResponse.age;
			document.getElementById("horseWeight").value = jsonResponse.weight;
			document.getElementById("selectMedicatedHorse").value = jsonResponse.medicated;
			document.getElementById("lastVeterinaryVisitHorse").value = jsonResponse.lastVeterinaryVisit;
			document.getElementById("selectNeuteredHorse").value = jsonResponse.neuteredPet;
			document.getElementById("selectPregnantHorse").value = jsonResponse.pregnant;
			document.getElementById("selectStatusHorse").value = jsonResponse.status;
			document.getElementById("vaccineEwt").value= jsonResponse.vaccineEwt;
			document.getElementById("vaccineEhv14").value = jsonResponse.vaccineEhv14;
			document.getElementById("vaccineWestNile").value= jsonResponse.vaccineWestNile;
			document.getElementById("vaccineStrangles").value= jsonResponse.vaccineStrangles;
			document.getElementById("vaccinePhf").value = jsonResponse.vaccinePhf;
			document.getElementById("vaccineFluAvert").value = jsonResponse.vaccineFluAvert;
			document.getElementById("videoUrl").value = jsonResponse.videoUrl;
		
		}
	}
	xhr.open("GET", url, true);
	xhr.send();
			
	}, 3000);
}

/* Retrive all pet data to the form */
function retriveHamsterFishTank(){
	setTimeout(
		
function(){
	var xhr = new XMLHttpRequest();
	var id = document.getElementById("idPrepaidQrCode").textContent;
	var url = "/retrivePrepaidQrHamsterFishTank?id="+id;
	
	xhr.onreadystatechange = function () {
		
		if (xhr.readyState == 4 && xhr.status == 200) {
			var data = xhr.responseText;
			var jsonResponse = JSON.parse(data);
			
			changeColorOfAllInputs("text-danger");
			
			document.getElementById("housing").value = jsonResponse.housing;
			document.getElementById("wheel").value = jsonResponse.wheel;
			document.getElementById("houseCleaned").value= jsonResponse.houseCleaned;
			document.getElementById("typeRat").value = jsonResponse.rat.type;
			document.getElementById("breed").value = jsonResponse.rat.breed;
			document.getElementById("rodentQuantity").value = jsonResponse.rodentQuantity;
			document.getElementById("bite").value = jsonResponse.rat.bite;
			document.getElementById("vitamins").value = jsonResponse.rat.vitamins;
			document.getElementById("medicatedRat").value = jsonResponse.rat.medicated;
			document.getElementById("neuteredRat").value = jsonResponse.rat.neuteredPet;
			document.getElementById("pregnantRat").value = jsonResponse.rat.pregnant;
		
		}
	}
	xhr.open("GET", url, true);
	xhr.send();
			
	}, 3000);
}

/* Retrive all pet data to the form */
function retriveOwner(){
	setTimeout(
		
function(){
	var xhr = new XMLHttpRequest();
	var url = "/retriveOwner";
	
	xhr.onreadystatechange = function () {
		
		if (xhr.readyState == 4 && xhr.status == 200) {
			var data = xhr.responseText;
			var jsonResponse = JSON.parse(data);
			
			changeColorOfAllInputs("text-danger");
			
			document.getElementById("ownerName").value = jsonResponse.ownerName;
			document.getElementById("ownerLastname").value = jsonResponse.ownerLastname;
			document.getElementById("residence").value= jsonResponse.residence;
			document.getElementById("dni").value = jsonResponse.dni;
			document.getElementById("telephone1").value = jsonResponse.telephone1;
			document.getElementById("telephone2").value = jsonResponse.telephone2;
			document.getElementById("email").value = jsonResponse.email;
			document.getElementById("facebook").value = jsonResponse.facebook;
			document.getElementById("instagram").value = jsonResponse.instagram;
			document.getElementById("address").value = jsonResponse.address;
		}
	}
	xhr.open("GET", url, true);
	xhr.send();
			
	}, 3000);
}


/* delete from account an specific prepaidQR */
function deletePrepaidQR(){
	var idPrepaidQR = document.getElementById("idPrepaidQR").value;
	var xhr = new XMLHttpRequest();
	var url = "/delete";
	
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			$.alert("todo ok");
		}
	}
}


/* ajax fishes function start */
function callFishes(){
	var idPrepaidQrCode = document.getElementById("idPrepaidQrCode").textContent;
	var user = document.getElementById("user").textContent;
	var quantity1 = document.getElementById("quantity1").value;
	var group1 = document.getElementById("group1").value;
	var description1 = document.getElementById("description1").value;
	var quantity2 = document.getElementById("quantity2").value;
	var group2 = document.getElementById("group2").value;
	var description2 = document.getElementById("description2").value;
	var quantity3 = document.getElementById("quantity3").value;
	var group3 = document.getElementById("group3").value;
	var description3 = document.getElementById("description3").value;
	var quantity4 = document.getElementById("quantity4").value;
	var group4 = document.getElementById("group4").value;
	var description4 = document.getElementById("description4").value;
	var quantity5 = document.getElementById("quantity5").value;
	var group5 = document.getElementById("group5").value;
	var description5 = document.getElementById("description5").value;
	var quantity6 = document.getElementById("quantity6").value;
	var group6 = document.getElementById("group6").value;
	var description6 = document.getElementById("description6").value;
	var quantity7 = document.getElementById("quantity7").value;
	var group7 = document.getElementById("group7").value;
	var description7 = document.getElementById("description7").value;
	var quantity8 = document.getElementById("quantity8").value;
	var group8 = document.getElementById("group8").value;
	var description8 = document.getElementById("description8").value;
	var quantity9 = document.getElementById("quantity9").value;
	var group9 = document.getElementById("group9").value;
	var description9 = document.getElementById("description9").value;
	var quantity10 = document.getElementById("quantity10").value;
	var group10 = document.getElementById("group10").value;
	var description10 = document.getElementById("description10").value;
	var quantity11 = document.getElementById("quantity11").value;
	var group11 = document.getElementById("group11").value;
	var description11 = document.getElementById("description11").value;
	var quantity12 = document.getElementById("quantity12").value;
	var group12 = document.getElementById("group12").value;
	var description12 = document.getElementById("description12").value;
	var throwLastMedicine = document.getElementById("throwLastMedicine").value;
	var water = document.getElementById("water").value;
	var lastFilterChange = document.getElementById("lastFilterChange").value;
	var temperature = document.getElementById("temperature").value;
	var lastFishbowlClean = document.getElementById("lastFishbowlClean").value;
	var plantFertilizer = document.getElementById("plantFertilizer").value;
	
	var xhr = new XMLHttpRequest();
	var url =  "/update-qr-fish";
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			
			data = xhr.responseText;
			var jsonResponse = JSON.parse(data);
			$.alert({type: 'green', 
					title: 'Compruebe el funcionamiento',
					content: "<body><content> <div><p style='color:deeppink'>"+ idPrepaidQrCode +"</p></div><div><img src='data:image/png;base64, "+jsonResponse["strBase64"]+"'></img></div><div><p>Lea este código QR para verificar que los datos se subieron correctamente, si los mismo no se subieron intente lo de nuevo, caso contrario comuníquese con el código ID a info@molokotech.com que gestionamos por usted la carga de datos.</p></div></content></body>"});
		}
	}
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("idPrepaidQrCode="+idPrepaidQrCode+"&user="+user+"&quantity1=" + quantity1 + "&group1=" + group1 + "&description1=" + description1 +"&quantity2=" + quantity2 + "&group2=" + group2 + "&description2=" + description2 +"&quantity3=" + quantity3 + "&group3=" + group3 + "&description3=" + description3 + "&quantity4=" + quantity4 + "&group4=" + group4 + "&description4=" + description4 + "&quantity5=" + quantity5 + "&group5=" + group5 + "&description5=" + description5 + "&quantity6=" + quantity6 + "&group6=" + group6 + "&description6=" + description6 + "&quantity7=" + quantity7 + "&group7=" + group7 + "&description7=" + description7 + "&quantity8=" + quantity8 + "&group8=" + group8 + "&description8=" + description8 + "&quantity9=" + quantity9 + "&group=9" + group9 + "&description=9" + description9 + "&quantity=10" + quantity10 + "&group=10" + group10 + "&description=10" + description10 + "&quantity=11" + quantity11 + "&group11=" + group11 + "&description11=" + description11 + "&quantity12=" + quantity12 + "&group12=" + group12 + "&description12=" + description12 + "&throwLastMedicine="+ throwLastMedicine + "&water=" + water + "&lastFilterChange=" + lastFilterChange + "&temperature=" + temperature + "&lastFishbowlClean=" + lastFishbowlClean + "&plantFertilizer=" + plantFertilizer);
}
/* ajax fishes function end */

/* ajax horse without use function start */
function callHorse(){
	var idPrepaidQrCode = document.getElementById("idPrepaidQrCode").textContent;
	var user = document.getElementById("user").textContent;
	var typeHorse = document.getElementById("selectTypeHorse").value;
	var breed = document.getElementById("selectBreedHorse").value;
	var illness = document.getElementById("selectIllnessHorse").value;
	var sex = document.getElementById("selectSexHorse").value;
	var age = document.getElementById("ageHorse").value;
	var petName = document.getElementById("horseName").value;
	var weight = document.getElementById("horseWeight").value;
	var medicated = document.getElementById("selectMedicatedHorse").value;
	var lastVeterinaryVisit = document.getElementById("lastVeterinaryVisitHorse").value;
	var neuteredPet = document.getElementById("selectNeuteredHorse").value;
	var pregnant = document.getElementById("selectPregnantHorse").value;
	var status = document.getElementById("selectStatusHorse").value;
	var vaccineEwt = document.getElementById("vaccineEwt").value;
	var vaccineEhv14 = document.getElementById("vaccineEhv14").value;
	var vaccineWestNile = document.getElementById("vaccineWestNile").value;
	var vaccineStrangles = document.getElementById("vaccineStrangles").value;
	var vaccinePhf = document.getElementById("vaccinePhf").value;
	var vaccineFluAvert = document.getElementById("vaccineFluAvert").value;
	var videoUrl = document.getElementById("videoUrl").value;
	
	var xhr = new XMLHttpRequest();
	var url =  "/update-qr-horse";
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			
			data = xhr.responseText;
			var jsonResponse = JSON.parse(data);
			$.alert({type: 'green', 
					title: 'Compruebe el funcionamiento',
					content: "<body><content> <div><p style='color:deeppink'>"+ idPrepaidQrCode +"</p></div><div><img src='data:image/png;base64, "+jsonResponse["strBase64"]+"'></img></div><div><p>Lea este código QR para verificar que los datos se subieron correctamente, si los mismo no se subieron intente lo de nuevo, caso contrario comuníquese con el código ID a info@molokotech.com que gestionamos por usted la carga de datos.</p></div></content></body>"});
		}
	}
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("idPrepaidQrCode=" + idPrepaidQrCode + "&user=" + user + "&typeHorse=" + typeHorse + "&breed=" + breed + "&illness=" + illness + "&sex="+ sex + "&age=" + age + "&petName=" + petName + "&weight=" + weight + "&medicated=" + medicated + "&lastVeterinaryVisit=" + lastVeterinaryVisit + "&neuteredPet=" + neuteredPet + "&pregnant=" + pregnant + "&status=" + status + "&vaccineEwt=" + vaccineEwt + "&vaccineEhv14=" + vaccineEhv14 + "&vaccinePhf=" + vaccinePhf + "&vaccineWestNile=" + vaccineWestNile + "&vaccineStrangles=" + vaccineStrangles + "&vaccineFluAvert=" + vaccineFluAvert + "&videoUrl=" + videoUrl);
}
/* Account page, ajax horse, without use function end */


/* Account page, transfer function start */
function transferCode(idPrepaidQR){
	console.log(idPrepaidQR);
	$.confirm({
		theme:"bootstrap",
		title:"Tranferir código a otro usuario <i class='fas fa-exchange-alt' style='color:DarkViolet'></i>",
		content:"Cuándo sirve esta opción?<br><ol><li>Cuando querés transferirlo por venta.</li><li>Cuando sabés que no vas a tener más mascota</li><li>Cuando querés darlo en forma de regalo.</li><li>Cuando el que lo quiere no puede pagar por los medios disponibles.</li><li>Esta opción es irreversible como la eliminación.</li></ol><label><i class='fas fa-user' style='color:LimeGreen''></i> Usuario Activo Destinatario:</label><input type='text' id='emailDestinatary' style='background-color:pink; text-align:center; margin: 0 auto;' placeholder='Email del destinatario'>",
		buttons:{
			confirm: function(){
				var xhr = new XMLHttpRequest();
				var emailDestinatary = document.getElementById("emailDestinatary").value;
				var url = "/transfer?emailDestinatary="+emailDestinatary+"&idPrepaidQR="+idPrepaidQR;

					xhr.onreadystatechange = function () {
					if (xhr.readyState == 4 && xhr.status == 200) {
						$.alert({
							title: "Respuesta del servidor",
							theme: "bootstrap",																	
							content: xhr.responseText,
							buttons:{
								confirm: function(){
									location.reload();
								}
							}
						});
					}
				}	
			xhr.open("GET", url, true);
			xhr.send();
			},
			cancel: function(){
			
			}
		}
		
	});
	
}
/* Account page, transfer function end */

/* Account page, delete function start */
function deleteQr(id) {
	$
			.confirm({
				type : "red",
				title : 'Confirme la eliminación!',
				content : "<html><p>Confirma que desea Borrar el QR con id:   <strong>"+ id +"</strong>?</p><p style='text-align:justify'>Tenga en cuenta que está interactuando con nuestro servidor por ende los datos no podrán ser recuperados</p></html>",
				buttons : {
					confirm : {
						text : 'Borrar',
						btnClass : 'btn-red',
						keys : [ 'enter',
								'shift' ],
						action : function() {
							$.alert('Confirmada eliminación de QR!, los datos No podrán ser recuperados}');
							window.location.href = "/delete?id="+id;
							
							$.alert({
								title:"Toque el boton para refrescar",
								content:"<html><body><button class='btn btn-lg btn-primary' onclick='window.location.reload()'>Reload now</button></body></html>"
							});
							
						}
					},
					cancel : function() {
						$
								.alert({
									type : "blue",
									title : "Acción:",
									content : 'Cancelada'
								});
					}

				}
			});
}
/* Account page, delete function end */

/* Account page, getActiveAnimal, withou use function start */
function getSelectedAnimal(id){
	var xhr = new XMLHttpRequest();
	var url = "/get-selected-animal";
	//var buttonSelectAnimal = document.getElementById("buttonSelectAnimal");
	
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("id=" + id);
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var img = document.createElement("img");
			var src = "";
			
			data = xhr.responseText;
			switch(data){
			case "dog":
				src = "../img/dog.png";
				break;
			case "cat":
				src = "../img/cat.png";
				break;
			case "rat":
				src = "../img/rat.png";
				break;
			case "horse":
				src = "../img/horseshoe.png";
				break;
			case "fish":
				src = "../img/fish.png";
				break;
			}
			//var jsonResponse = JSON.parse(data);
			$.alert({type: 'purple', 
					title: 'Type of animal',
					content: "<html><body>The QR code with id: <span style='color:deeppink'>"+id+ " </span> now using<br> " + data + "  <img src="+src+"></body></html>"});
			
		}
	}
}
/* Account page, getActiveAnimal, withou use function end */


/* Select Animal start */
function chooseAnimal(id, dog, cat, horse, hamsterFishTank, fish){

	var xhr = new XMLHttpRequest();
	var url = "/get-selected-animal";
	window.src;
	
	
//	alert(this.getActiveStatus(id, "dog"));
	
	if(dog == null){
		dog = "<i class='fas fa-times text-danger'></i> <strong class='text-danger'>Not Created</strong>";
	}else{
		dog = "<i class='fas fa-check text-success'></i> <strong class='text-success'>Created</strong>";
		
	}
	
	if(cat == null){
		cat = "<i class='fas fa-times text-danger'></i> <strong class='text-danger'>Not Created</strong>";
	}else{
		cat = "<i class='fas fa-check text-success'></i> <strong class='text-success'>Created</strong>";
	}
	
	if(horse == null){
		horse = "<i class='fas fa-times text-danger'></i> <strong class='text-danger'>Not Created</strong>";
	}else{
		horse = "<i class='fas fa-check text-success'></i> <strong class='text-success'>Created</strong>";
	}
	
	if(hamsterFishTank == null){
		hamsterFishTank = "<i class='fas fa-times text-danger'></i> <strong class='text-danger'>Not Created</strong>";
	}else{
		hamsterFishTank = "<i class='fas fa-check text-success'></i> <strong class='text-success'>Created</strong>";
	}
	
	if(fish == null){
		fish = "<i class='fas fa-times text-danger'></i> <strong class='text-danger'>Not Created</strong>";
	}else{
		fish = "<i class='fas fa-check text-success'></i> <strong class='text-success'>Created</strong>";
	}
	
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("id=" + id);
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var img = document.createElement("img");
			
			data = xhr.responseText;
			switch(data){
			case "dog":
				src = "../img/dog.png";
				break;
			case "cat":
				src = "../img/cat.png";
				break;
			case "rat":
				src = "../img/rabbit.png";
				break;
			case "horse":
				src = "../img/horseshoe.png";
				break;
			case "fish":
				src = "../img/fish.png";
				break;
			}
		}
	}

	/* Retard function to get DB info, need to get the info start */
		setTimeout(function () {  
		/* Start table function - inside getFunction Ajax, be carefull */
		$.confirm({
			type:"purple",
			theme:"bootstrap",
			columnClass:"col-md-4",
			title:"Animal Selector.",
			content:"<html xmlns:th='http://www.thymeleaf.org'><body><div><form id='chooseanimal' action='/select-animal' method='POST' onsubmit='return window.location.reload()'>" +
			"<input type='hidden' value='"+ id +"' id='id' name='id' />" +
			"<table class='bordered rounded table-responsive table'><thead><tr class='table-info'><td>Animal</td><td>Icon</td><td>Created</td></tr><thead>" +
			"<tr><td><label><input type='radio' name='typeAnimal' id='typeAnimal' value='dog' checked> Dog</label></td> <td><img src='../img/dog.png'></td><td>"+ dog +"</td><td></td></tr>" +
			"<tr><td><label><input type='radio' name='typeAnimal' id='typeAnimal' value='cat'> Cat</label></td> <td><img src='../img/cat.png'></td><td>"+ cat +"</td></tr>" +
			"<tr><td><label><input type='radio' name='typeAnimal' id='typeAnimal' value='horse'> Horse</label></td> <td><img src='../img/horseshoe.png'>  </td><td>"+ horse +"</td></tr>" +
			"<tr><td><label><input type='radio' name='typeAnimal' id='typeAnimal' value='rat'> Rodents</label></td> <td><img src='../img/rabbit.png'></td><td>"+ hamsterFishTank +"</td></tr>" +
			"<tr><td><input type='radio' name='typeAnimal' id='typeAnimal' value='fish'> Fish</label></td> <td><img src='../img/fish.png'></td><td>"+ fish +"</td></tr>" +
			"<tr class='table-danger'><td>Active: </td><td><img src="+ src +" /></td></tr>" +
			"</table></form></div></body></html>",
			buttons:{
				confirm:{ 
					btnClass: 'btn-blue',
					action: function(){
						/* No used right now, ajax improve */
						//var chooseanimal = document.getElementById("chooseanimal");
						//chooseanimal.submit();
						/* No used right now, ajax improve */

						
						/* Ajax select animal start */
						
						var xhr1 = new XMLHttpRequest();
						
						/* Create the response variable */
						var typeAnimal = null;
						
						/* Get the selected value */
						var radios = document.getElementsByName("typeAnimal");
						for (var i = 0; i < radios.length; i++) {
							if(radios[i].checked){
								typeAnimal = radios[i].value;
							}
						}
						
						var url = "/select-animal?id="+id+"&typeAnimal="+typeAnimal;
						
						xhr1.open("GET", url, true);
						xhr1.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
						xhr1.send();
						xhr1.onreadystatechange = function () {
							if (xhr1.readyState == 4 && xhr1.status == 200) {
								/* If everything is ok just reload to get the new button on the Control Panel */
								$.alert({
									type:"purple",
									title:"Change",
									content:"Wait a few seconds to improve new Animal."
								});
								
								setTimeout(function(){window.location.reload();}, 2000);
							}else{
								
							}
						}
						/* Ajax select animal end */
						
				}
			},
				cancel:{
						btnClass: 'btn-red',
						text: 'Keep this animal',
						action: function(){
					}
				}
			}
			
		});
		/* Start table function */
	}, 2000);
		/* Retard function to get DB info, need to get the info end*/
	
	}
/* Select Animal end */

function getStatus(id){
	
	var alertButton = document.getElementById("alertButton"+id);
	var xhr = new XMLHttpRequest();
	var url = "/status?id="+ id;
	
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();
	
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var animal = xhr.responseText;
			
			if(animal == "true"){
				alertButton.classList.remove("btn-danger");
				alertButton.classList.add("btn-success");
				alertButton.innerHTML = "rescued";
			}
			
			if(animal == "false"){
				alertButton.classList.remove("btn-success");
				alertButton.classList.add("btn-danger")
				alertButton.innerHTML = "Lost";
			}
		}
	}
}

function getActiveStatus(id, typeAnimal){
	
//	var alertButton = document.getElementById("alertButton"+id);
	var xhr = new XMLHttpRequest();
	var url = "/get-active-status?id="+ id + "&typeAnimal=" + typeAnimal;
	var result = null;
	
	
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();
	
		xhr.onreadystatechange = function () {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var animal = xhr.responseText;
				
				if(animal == "true"){
	//				alertButton.classList.remove("btn-danger");
	//				alertButton.classList.add("btn-success");
	//				alertButton.innerHTML = "rescued";
					result = "rescued";
				return result;
				}
				
				if(animal == "false"){
	//				alertButton.classList.remove("btn-success");
	//				alertButton.classList.add("btn-danger")
	//				alertButton.innerHTML = "Lost";
				result = "Lost";
				return result;
	//			}
			}
		}
	}
}