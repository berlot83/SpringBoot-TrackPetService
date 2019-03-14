
function lockTemplates(){
	var xhr = new XMLHttpRequest();
	var templates = document.getElementById("templates");
	var locked = false;
	
	if(templates.checked){
		locked = true;
		alertify.success('Templates are enable now');
	}else{
		locked = false;
		alertify.error('Templates are disable now');
	}
	
	var url = "/lock-templates?locked="+locked;
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
	
		}
	}
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();
}
						

function quickMolokotechConnect(){
	var xhr = new XMLHttpRequest();
	var qcMolokotech = document.getElementById("qcMolokotech");
	var showQuickConnectMolokotech = false;
	
	if(qcMolokotech.checked){
		showQuickConnectMolokotech = true;
		alertify.success('Moloko Connect on form is enable now');
	}else{
		showQuickConnectMolokotech = false;
		alertify.error('Moloko Connect on form is disable now');
	}
	
	var url = "/molokotech-connect?showQuickConnectMolokotech="+showQuickConnectMolokotech;
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
	
		}
	}
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();
}


function showSharebuttons(){
	var xhr = new XMLHttpRequest();
	var qcMolokotech = document.getElementById("shareButtons");
	var hideButtons = false;
	
	if(qcMolokotech.checked){
		hideButtons = true;
		alertify.success('Share buttons on templates are enable now');
	}else{
		hideButtons = false;
		alertify.error('Share buttons on templates are disable now');
	}
	
	var url = "/share-buttons?showSharebuttons="+hideButtons;
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
	
		}
	}
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();
}


function enableSendCoordinates(){
	var xhr = new XMLHttpRequest();
	var sendCoordinates = document.getElementById("enableCoordinates");
	var coordinates = false;
	
	if(sendCoordinates.checked){
		coordinates = true;
		alertify.success('Coordinates to your email are enable now');
	}else{
		coordinates = false;
		$.alert({
			type:"red",
			title:"Warning",
			content:"Be aware that you disabling the coordinates to your email. "
		});

		alertify.error('Coordinates to your email are disable now');
	}
	
	var url = "/enable-coordinates?sendCoordinates="+coordinates;
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
	
		}
	}
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();
}


function enableEspecialMessage(){
	var xhr = new XMLHttpRequest();
	var customMessage = document.getElementById("customMessage");
	var showSpecialMessage = false;
	
	if(customMessage.checked){
		showSpecialMessage = true;
		alertify.success('Special message on template is enable now');
	}else{
		showSpecialMessage = false;
		alertify.error('Special message on template is disable now');
	}
	
	var url = "/show-special-message?showSpecialMessage="+showSpecialMessage;
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
	
		}
	}
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();
}

function enableEspecialInputs(){
	var xhr = new XMLHttpRequest();
	var specialInputs = document.getElementById("specialInputs");
	var showSpecialInputs = false;
	
	if(specialInputs.checked){
		showSpecialInputs = true;
		alertify.success('Special inputs on templates are enable now');
	}else{
		showSpecialInputs = false;
		alertify.error('Special inputs on templates are disable now');
	}
	
	var url = "/show-special-inputs?showSpecialInputs="+showSpecialInputs;
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
	
		}
	}
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();
}

function showLocationMap(){
	var xhr = new XMLHttpRequest();
	var showMapInput = document.getElementById("showMap");
	var showMap = false;
	
	if(showMapInput.checked){
		showMap = true;
		alertify.success('Location map on templates is enable now');
	}else{
		showMap = false;
		alertify.error('Location map on templates is disable now');
	}
	
	var url = "/show-map?showMap="+showMap;
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
	
		}
	}
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();
}


function showFooter(){
	var xhr = new XMLHttpRequest();
	var showFooterInput = document.getElementById("showFooter");
	var showFooter = false;
	
	if(showFooterInput.checked){
		showFooter = true;
		alertify.success('Footer on templates are enable now');
	}else{
		showFooter = false;
		alertify.error('Footer on templates are disable now');
	}
	
	var url = "/show-footer?showFooter="+showFooter;
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
	
		}
	}
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();
}

function showYoutubeVideo(){
	var xhr = new XMLHttpRequest();
	var showYoutubeVideoInput = document.getElementById("showYoutubeVideo");
	var showYoutubeVideo = false;
	
	if(showYoutubeVideoInput.checked){
		showYoutubeVideo = true;
		alertify.success('Youtube video is enable now');
	}else{
		showYoutubeVideo = false;
		alertify.error('Youtube video is disable now');

	}
	
	var url = "/show-youtube-video?showYoutubeVideo="+showYoutubeVideo;
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
	
		}
	}
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();
}


function retriveSetup(){
	setTimeout(
			function(){
				var xhr = new XMLHttpRequest();
				var url = "/retriveSetup";
				xhr.onreadystatechange = function () {
					if (xhr.readyState == 4 && xhr.status == 200) {
						var data = xhr.responseText;
						var jsonResponse = JSON.parse(data);
						document.getElementById("templates").checked = jsonResponse.lockTemplate;
						document.getElementById("qcMolokotech").checked = jsonResponse.showQuickConnectMolokotech;
						document.getElementById("shareButtons").checked = jsonResponse.showSharebuttons;
						document.getElementById("enableCoordinates").checked = jsonResponse.sendCoordinates;
						document.getElementById("customMessage").checked = jsonResponse.showSpecialMessage;
						document.getElementById("specialInputs").checked = jsonResponse.showSpecialInputs;
						document.getElementById("showMap").checked = jsonResponse.showMap;
						document.getElementById("showFooter").checked = jsonResponse.showFooter;
						document.getElementById("showYoutubeVideo").checked = jsonResponse.showYoutubeVideo;
					}
				}
				xhr.open("GET", url, true);
				xhr.send();
						
				}, 500);
}
