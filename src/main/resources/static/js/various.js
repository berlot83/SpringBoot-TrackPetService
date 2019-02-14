function contact() {
	$.confirm({
	    title: "Contact us:",
	    type: 'purple',
	    columnClass: 'col-md-6',
	    content: '' +
	    "<html xmlns:th='http://www.thymeleaf.org'><body><div id='aboutDiv' style='display:flex;'><div style='flex-grow: 0; flex-shrink: 0; flex-basis: 50%;'><img class='img-fluid' style='' src='../img/qr400moloko.png' alt='Molokotech'><span style='color:LimeGreen; text-shadow:1px 1px black;'><strong style='color:orange'>Molokotech</strong> is an IT agency with a great Development Team.</span></div>"+
	    "<div class='form-group' style='flex-grow: 0; flex-shrink: 0; flex-basis: 50%;'>" +
	    "<form id='actionForm' action='/send-contact-form' th:action='@{/send-contact-form}' th:method='GET' th:object='${formMessage}'>"+
	    '<input type="text" id="name" name="name" placeholder="Your name" class="name form-control" required /><br>' +
	    '<input type="text" id="email" name="email" placeholder="Your email" class="name form-control" required /><br>' +
	    '<input type="text" id="subject" name="subject" placeholder="Subject" class="name form-control" required /><br>' +
	    "<textarea id='description' name='description' placeholder='Description message' rows='5' class='name form-control' required />" +
	    '</div>' +
	    '<div>' +
	    '</body></html>',
	    buttons: {
	        confirm: {
	            text: 'Submit',
	            btnClass: 'btn-purple',
	            action: function () {
	            	var form = document.getElementById("actionForm");
	            	var name = document.getElementById("name").value;
	            	var email = document.getElementById("email").value;
	            	var subject = document.getElementById("subject").value;
	            	var description = document.getElementById("description").value;
	            	
	            	if(name != "" && email != "" && subject != "" && description != ""){
	            		form.submit();
	            	}else{
	            		$.alert({
	            			type: 'red',
	            			title: '<strong>Incomplete data</strong>',
	            			content: 'Please check that all the inputs must be filled and try again.'
	            		});
	            	}
	            }
	        },
	        cancel: function () {
	            //close
	        },
	    },
	});
}

function addBounce(id){
	$(id).addClass('animated jello');
}

function removeBounce(id){
	$(id).removeClass('animated jello');
}

function changeCursorToimages(){
	var herokuLogo = document.getElementById("herokuLogo");
	var logo = document.getElementById("logo");
	
	herokuLogo.style.cursor = "pointer";
	logo.style.cursor = "pointer";
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

	/* Should aplly this code */
//	$.confirm({
//	    title: 'Title',
//	    content: 'url:terms.txt',
//	    onContentReady: function () {
//	        var self = this;
//	        this.setContentPrepend('<div>Prepended text</div>');
//	        setTimeout(function () {
//	            self.setContentAppend('<div>Appended text after 2 seconds</div>');
//	        }, 2000);
//	    },
//	    columnClass: 'medium',
//	});
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

/* QR-Ayuda start */
function showAnswerView(){
	$(document).ready(function() {
		$("#aViewTemplate").click(function() {
			$("#divViewTemplate").fadeToggle(900);
		});
	});
}

function showAnswerModify(link){
	$(document).ready(function() {
		$(link).click(function() {
			$("#divModifyTemplate").fadeToggle(900);
		});
	});
}

function showAnswerDelete(link){
	$(document).ready(function() {
		$(link).click(function() {
			$("#divDeleteCode").fadeToggle(900);
		});
	});	
}

function showAnswerTransfer(link){
	$(document).ready(function() {
		$(link).click(function() {
			$("#divTransferCode").fadeToggle(900);
		});
	});	
}
/* QR-ayuda end */
