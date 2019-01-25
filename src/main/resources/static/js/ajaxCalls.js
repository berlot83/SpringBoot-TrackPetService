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
function updateQr() {
	var idPrepaidQrCode = document.getElementById("idPrepaidQrCode").textContent;
	var petName = document.getElementById("petName").value;
	var ownerName = document.getElementById("ownerName").value;
	var ownerLastname = document.getElementById("ownerLastname").value;
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
	var dni = document.getElementById("dni").value;
	var address = document.getElementById("address").value;
	var telephone1 = document.getElementById("telephone1").value;
	var telephone2 = document.getElementById("telephone2").value;
	var facebook = document.getElementById("facebook").value;
	var instagram = document.getElementById("instagram").value;
	var email = document.getElementById("email").value;
	var residence = document.getElementById("residence").value;
	var videoUrl = document.getElementById("videoUrl").value;

	var xhr = new XMLHttpRequest();
	var url = "/update-qr";

	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("idPrepaidQrCode=" + idPrepaidQrCode + "&ownerName=" + ownerName + "&ownerLastname=" + ownerLastname + "&raze=" + raze + "&foodBrand=" + foodBrand + "&user=" + user + "&age=" + age + " &lastVeterinaryVisit=" + lastVeterinaryVisit + "&dateAntiRabicVaccine=" + dateAntiRabicVaccine + "&datePolivalentVaccine=" + datePolivalentVaccine + "&dateSextupleVaccine=" + dateSextupleVaccine + "&dateOctupleVaccine=" + dateOctupleVaccine + "&clinicHistory=" + clinicHistory + "&illness=" + illness + "&medicated=" + medicated + "&quantityFood=" + quantityFood + "&neuteredPet=" + neuteredPet + "&coockedFood=" + coockedFood + "&status=" + status + "&pregnant=" + pregnant + "&weight=" + weight + "&petName=" + petName + "&sex=" + sex + "&dni=" + dni + "&telephone1=" + telephone1 + "&telephone2=" + telephone2 + "&email=" + email + "&address=" + address + "&facebook=" + facebook + "&instagram=" + instagram + "&residence=" + residence + "&videoUrl=" + videoUrl);
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

/* Retrive all pet data to the form */
function retrivePrepaidQR(){
	setTimeout(
		
function(){
	var xhr = new XMLHttpRequest();
	var id = document.getElementById("idPrepaidQrCode").textContent;
	var url = "/retrivePrepaidQR?id="+id;
	
	xhr.onreadystatechange = function () {
		
		if (xhr.readyState == 4 && xhr.status == 200) {
			var data = xhr.responseText;
			var jsonResponse = JSON.parse(data);
			
			document.getElementById("petName").value = jsonResponse.pet.petName;
			document.getElementById("ownerName").value = jsonResponse.owner.ownerName;
			document.getElementById("ownerLastname").value = jsonResponse.owner.ownerLastname;
			document.getElementById("raze").value = jsonResponse.pet.raze;
			document.getElementById("foodBrand").value= jsonResponse.pet.foodBrand;
			document.getElementById("quantityFood").value= jsonResponse.pet.quantityFood;
			document.getElementById("neuteredPet").value = jsonResponse.pet.neuteredPet;
			document.getElementById("age").value = jsonResponse.pet.age;
			document.getElementById("lastVeterinaryVisit").value = jsonResponse.pet.lastVeterinaryVisit;
			document.getElementById("dateAntiRabicVaccine").value = jsonResponse.pet.dateAntiRabicVaccine;
			document.getElementById("datePolivalentVaccine").value= jsonResponse.pet.datePolivalentVaccine;
			document.getElementById("dateSextupleVaccine").value= jsonResponse.pet.dateSextupleVaccine;
			document.getElementById("dateOctupleVaccine").value = jsonResponse.pet.dateOctupleVaccine;
			document.getElementById("status").value = jsonResponse.pet.status;
			document.getElementById("clinicHistory").value = jsonResponse.pet.clinicHistory;
			document.getElementById("illness").value = jsonResponse.pet.illness;
			document.getElementById("medicated").value = jsonResponse.pet.medicated;
			document.getElementById("coockedFood").value = jsonResponse.pet.coockedFood;
			document.getElementById("sex").value = jsonResponse.pet.sex;
			document.getElementById("pregnant").value = jsonResponse.pet.pregnant;
			document.getElementById("weight").value = jsonResponse.pet.weight;
			document.getElementById("dni").value = jsonResponse.owner.dni;
			document.getElementById("address").value = jsonResponse.owner.address;
			document.getElementById("telephone1").value = jsonResponse.owner.telephone1;
			document.getElementById("telephone2").value = jsonResponse.owner.telephone2;
			document.getElementById("facebook").value = jsonResponse.owner.facebook;
			document.getElementById("instagram").value = jsonResponse.owner.instagram;
			document.getElementById("email").value = jsonResponse.owner.email;
			document.getElementById("residence").value = jsonResponse.owner.residence;
			document.getElementById("videoUrl").value = jsonResponse.pet.videoUrl;
					
		}
	}
	xhr.open("GET", url, true);
	xhr.send();
			
	}, 1000);
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

