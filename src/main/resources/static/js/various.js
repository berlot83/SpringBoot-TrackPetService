function contact() {
	$.confirm({
		type: 'blue',
		title: "<h5><strong><img src='../img/iconoPet75.png' style='widh:40px; height:40px;' /> Contacto</strong></h5>",
		columnClass: 'col-md-6 col-md-offset-3',
		content: '<ol><li>En todos los casos abajo declarados enviar a esta dirección de mail <a href="mailto:molokotech.com"><span class="badge badge-warning">info@molokotech.com</span></a></li><li>Para ser "Usuario Proveedor" solicitar boton de pago colocando en asunto <strong style="color:DarkViolet">"Proveedor"</strong>.</li><li>Para consultar por capacidad de distribuir QR físicos a veterinarias indique en el asunto <strong style="color:DarkBlue">"Distribución"</strong>, nuestra intención es incentivar la venta online.</li><li>Para consultas generales sobre el sistema y/o alcances de responsabilidad coloque en el asunto <strong style="color:DarkRed">"Consulta"</strong>.</li><li>Para reclamos de cualquier tipo, soporte para solucionar problemas si los hubiere, coloque en el asunto <strong style="color:DarkGreen">"Reclamo"</strong>.</li></ol>',
		
		buttons: {
			danger: {
				text: 'Continuar',
				confirmButton: 'btn-red', // multiple classes.
				btnClass: 'btn-blue',
				action: function () { }
			}
		}
	})
}

function changeCursorToimages(){
	var herokuLogo = document.getElementById("herokuLogo");
	var logo = document.getElementById("logo");
	var avatarOnDB = 	document.getElementById("profile");
	
	herokuLogo.style.cursor = "pointer";
	logo.style.cursor = "pointer";
	avatarOnDB.style.cursor = "pointer";
}

function redirectTo() {
	window.location.href = "http://molokotech.com";
}

function checkVaccines() {
	var checkVaccines = document.getElementById("checkVaccines");
	var antiRabic = document.getElementById("dateAntiRabicVaccine");
	var polivalent = document.getElementById("datePolivalentVaccine");
	var sextuple = document.getElementById("dateSextupleVaccine");
	var octuple = document.getElementById("dateOctupleVaccine");

	if (checkVaccines.checked) {
		antiRabic.disabled = false;
		polivalent.disabled = false;
		sextuple.disabled = false;
		octuple.disabled = false;

	} else {
		antiRabic.disabled = true;
		polivalent.disabled = true;
		sextuple.disabled = true;
		octuple.disabled = true;

	}
}

function age() {
	var yearBirthay = document.getElementById("age");
	var actualYear = (new Date()).getFullYear();
	var age = actualYear - yearBirthay.value;

	console.log(age);

	var resultAge = document.getElementById("resultAge");

	resultAge.innerHTML = "<h5><a href='#'><span class='badge badge-danger'>Edad actual de la mascota:  " + age + " años</span></a></h5>";
}

function pregnant() {
	var sex = document.getElementById("sex").value;
	var pregnant = document.getElementById("pregnant");
	if (sex == "Macho") {
		pregnant.disabled = true;
	} else {
		pregnant.disabled = false;
	}
}

function termsAndCondition() {
	$.confirm({
		theme: 'supervan',
		type: 'yellow',
		title: 'Términos y condiciones',
		content: 'Molokotech y todos sus representantes no se se hacen responsables de posibles pérdidas de mascotas, la inte'
	});
}

function changeFlag(){
	var english = document.getElementById("english");
	var spanish = document.getElementById("spanish");
	var selectLanguage = document.getElementById("selectLanguage");
	
	if(selectLanguage.value == "ES"){
		english.style.display = "none";
		spanish.style.display = "block";
	}
	
	if(selectLanguage.value == "IN"){
		english.style.display = "block";
		spanish.style.display = "none";
	}
}

function adviceVariablesNull(){
	var id = document.getElementById("id").value;
	var strBase64 = document.getElementById("strBase64").value;

	if(id == "" || strBase64 == ""){
		$.alert({
			type:"red",
			title:"<i style='color: red' class='fas fa-exclamation-triangle'></i> Atención",
			content:"<p style='text-align:justify'>No se registraron datos del <strong>id</strong> de QR o faltan <strong>datos</strong> del código QR, intente comenzar sesión de nuevo o comuníquese con nosotros ya que seguramente no funcionen las funciones de descarga.</p>"
		});
	}
}

function moveToLocation(){
    var latitude = document.getElementById("latitude").value;
    var longitude = document.getElementById("longitude").value;

    var latlng = new google.maps.LatLng(latitude, longitude);
    var mapOptions = {
        zoom: 16,
        center: latlng
    };
    
    var marker = new google.maps.Marker({
    	position: latlng,
    	map: map,
    	title: 'Domicilio declarado por el tutor donde vive la mascota.'
    });
    
    map = new google.maps.Map(document.getElementById("map"), mapOptions);
    marker.setMap(map);
	
}

function getLocalization(){
	//  Html5 Geolocalization
    
	if (navigator.geolocation) {
    	navigator.geolocation.getCurrentPosition(function(position) {
          var pos = {
            lat: position.coords.latitude,
            lng: position.coords.longitude
          };
          console.log(pos);

          /* For mailing */
          var mail = document.getElementById("mail").textContent;
          var latitude = pos.lat;
          var longitude = pos.lng;
          var dateTime = new Date();

          /* Send to endponit and JavaMail sends the mail */
          var xhr = new XMLHttpRequest();
          var url = "/sendCoordinatesToMail";
          
          xhr.open("POST", url, true);
          xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
          xhr.send("latitude="+latitude+"&longitude="+longitude+"&mail="+mail+"&dateTime="+dateTime);
          /* For Mailing*/

        }, function() {
          handleLocationError(true, infoWindow, map.getCenter());
        });
      } else {
        // Browser doesn't support Geolocation
        handleLocationError(false, infoWindow, map.getCenter());
      }
 
}

