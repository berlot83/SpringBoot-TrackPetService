function chDownload(){
	/* Pet variables start */
	var petName = document.getElementById("petName").value;
	var raze = document.getElementById("raze").value;
	var illness = document.getElementById("illness").value;
	var medicated = document.getElementById("medicated").value;
	var neuteredPet = document.getElementById("neuteredPet").value;
	var dateAntiRabicVaccine = document.getElementById("dateAntiRabicVaccine").value;
	var datePolivalentVaccine = document.getElementById("datePolivalentVaccine").value;
	var dateSextupleVaccine = document.getElementById("dateSextupleVaccine").value;
	var dateOctupleVaccine = document.getElementById("dateOctupleVaccine").value;
	var lastVeterinaryVisit = document.getElementById("lastVeterinaryVisit").value;
	var clinicHistory = document.getElementById("clinicHistory").value;
	/* Pet variables end */

	var doc = new jsPDF();
	var width = doc.internal.pageSize.getWidth();
	var height = doc.internal.pageSize.getHeight();
	
	
	doc.addFont('Mali', "mali", "normal");
	doc.setFont('mali');
	
	/* Calculate the with of the document and then minus 30 px aprox */
	doc.text((width/2)-20, 20, 'Clinic History');

	doc.setFontSize(10);
	doc.text(25, 35, "Basic data: ");
	doc.text(25, 40, "- Name: ");
	doc.text(50, 40, petName);
	doc.text(25, 45, "- Raze: ");
	doc.text(50, 45, raze);
	doc.text(25, 50, "- Illness: ");
	doc.text(50, 50, illness);
	doc.text(25, 55, "- Neutered: ");
	doc.text(50, 55, neuteredPet);
	doc.text(25, 60, "- Medicated: ");
	doc.text(50, 60, medicated);
	doc.text(25, 70, "Diagnostic: ");
//	doc.text(clinicHistory, 27, 70, {maxWidth: 150, align: "justify"});
	
	doc.text((width/2)+30, 35, "Vaccines: ");
	doc.text((width/2)+30, 40, "- Antirabic: ");
	doc.text((width/2)+55, 40, dateAntiRabicVaccine);
	doc.text((width/2)+30, 45, "- Polivalent: ");
	doc.text((width/2)+55, 45, datePolivalentVaccine);
	doc.text((width/2)+30, 50, "- Sextuple: ");
	doc.text((width/2)+55, 50, dateSextupleVaccine);
	doc.text((width/2)+30, 55, "- Octuple: ");
	doc.text((width/2)+55, 55, dateOctupleVaccine);
	doc.save('petQR_ch_en');
}