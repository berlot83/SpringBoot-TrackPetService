/* Load image first on browser, then crop with other function and save it with ajax post */
		function loadFile(event) {
		    var reader = new FileReader();
		    reader.onload = function(){
		      var output = document.getElementById('crop');
		      output.src = reader.result;
		      
				/* Inside LoadFile() function */
				function cropImageAndSave() {
					  
					var resultBase64Avatar = document.getElementById("resultBase64Avatar");
					var $w = $('.basic-width'),
						$h = $('.basic-height'),
						basic = $('#demo-basic').croppie({
						viewport: {
							width: 140,
							height: 140
						},
						boundary: {
							width: 180,
							height: 180
						}
					});
					basic.croppie('bind', {
						url:output.src,
						points: [77,469,280,739]
					});
			
					$('.basic-result').on('click', function() {
						var w = parseInt($w.val(), 10),
							h = parseInt($h.val(), 10),s
							size = 'viewport';
						if (w || h) {
							size = { width: w, height: h };
						}
						basic.croppie('result', {
							type: 'canvas',
							size: size,
							resultSize: {
								width: 40,
								height: 40
							}
						}).then(function (resp) {
							

			
									/* Ajax function to upload avatar */
									function uploadAvatarToDB(){
										var id = document.getElementById("idPrepaidQrCode").textContent;
										var resultBase64Avatar = resp;
										
										console.log(resp);
										
										var xhr = new XMLHttpRequest();
										var url = "/uploadAvatar";
										
										xhr.open("POST", url, true);
										xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
										xhr.send("id="+id+"&resultBase64Avatar="+resultBase64Avatar);
										
										xhr.onreadystatechange = function () {
											if (xhr.readyState == 4 && xhr.status == 200) {
												
												$.confirm({
													type: "blue",
													title: "Confirme la imagen:",
													content: "<html><body><div style='text-align:center'><img src="+ resp +" /></div></body></html>",
													confirm: function(){
														$.alert("Imagen persistida en DB con éxito.");
													},
												    cancel: function(){
												        $.alert({
												        	type:'blue',
												        	title: 'Acción',
												        	content:'Cancelada!'
												        });
												    }
												});		
												
											}
										}
									}
									uploadAvatarToDB();
									/* End upload avatar */
						});
					});
				}
				/* End  */
				cropImageAndSave();
		    };
		    var chargeImage= reader.readAsDataURL(event.target.files[0]);
		}
	