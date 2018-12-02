/**
 * Author AAB
 * 2018
 */

/* File source Ajax calls */

function advice() {
	$.alert({
		columnClass: 'col-lg-6',
		type: 'red',
		title: 'Instrucciones',
		animation: 'zoom',
		content: '<body><div><ol><li>Utilice el motor de búsqueda de google para encontrar la ubicación ya que toma Latitud y Longitud exacta.</li> <li>Si necesita modificar algún dato vuelva ingresar nuevamente a esta página con el mismo ID.</li> <li>Tiene que completar la mayoria de los campos, menos los de vacunación alimentación etc, los básicos como nombre de la mascota, datos de contacto y dirección son obligatorios para el buen funcionamiento del sistema.</li><li>Una vez terminada la actualización de datos saldrá un QR, léalo con cualquier lector y compruebe que funcione correctamente</li><li>Haga lo mismo con el que tiene físicamente, si algo no funciona correctamente no dude en contactarnos a <a href=mailto:info@molokotech.com>info@molokotech.com</a></li></ol></div></body>',
	});
}