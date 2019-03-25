var typeAnimal = ["dog","cat","horse"];

function alerts(id){
	idTemporal = id;
	alertDog = "alertDog"+id;
	alertCat = "alertCat"+id;
	alertHorse = "alertHorse"+id;
	
	$.alert({
		type:"purple",
		title:"Declare Animals as lost or rescued",
		content:"<html xmlns:th='http://www.thymeleaf.org'><head><div th:include='default :: header'></div></head><body>"+
		"<table>"+
		"<tr>"+
		"<td>Dog </td><td>"+
		"<div class='custom-control custom-switch'>"+
		"<input type='checkbox' class='custom-control-input' id="+alertDog+" onchange='sendAlert(idTemporal, typeAnimal[0])'>"+
		"<label class='custom-control-label' for="+alertDog+"></label></div>"+
		"</td><td><img src='../img/dog.png'></td>"+
		"</tr>"+
		"<tr>"+
		"<td>Cat </td><td>"+
		"<div class='custom-control custom-switch'>"+
		"<input type='checkbox' class='custom-control-input' id="+alertCat+" onchange='sendAlert(idTemporal, typeAnimal[1])'>"+
		"<label class='custom-control-label' for="+alertCat+"></label></div>"+
		"</td><td><img src='../img/cat.png'></td>"+
		"</tr>"+
		"<tr>"+
		"<td>Horse </td><td>"+
		"<div class='custom-control custom-switch'>"+
		"<input type='checkbox' class='custom-control-input' id="+alertHorse+" onchange='sendAlert(idTemporal, typeAnimal[2])'>"+
		"<label class='custom-control-label' for="+alertHorse+"></label></div>"+
		"</td><td><img src='../img/horseshoe.png'></td>"+
		"</tr>" +
		"</table>"+
		"</div></body></html>"});
}

function sendAlert(idTemporal, typeAnimal){
		var status = false;

		switch(typeAnimal){
		case "dog":
			var alertDogInput = document.getElementById(alertDog);
			if(alertDogInput.checked){
				status = true;
			}else{
				status = false;
			}
			break;
		case "cat":
			var alertCatInput = document.getElementById(alertCat);
			if(alertCatInput.checked){
				status = true;
			}else{
				status = false;
			}									
			break;
		case "horse":
			var alertHorseInput = document.getElementById(alertHorse);
			if(alertHorseInput.checked){
				status = true;
			}else{
				status = false;
			}									
			break;									
		}
		var xhr = new XMLHttpRequest();
		var url = "/change-status?id="+idTemporal+"&typeAnimal="+typeAnimal+"&status="+status;
		xhr.open("GET", url, true);
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhr.send();
		xhr.onreadystatechange = function () {
			if (xhr.readyState == 4 && xhr.status == 200) {
				$.alert({
					type:"red",
					title:"Alert",
					content: xhr.responseText
					});									
			}
		}
}

