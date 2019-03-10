function takePhoto(){
            //get transform value
            var transform=$(".gm-style>div:first>div:first>div:last>div").css("transform")
            var comp=transform.split(",") //split up the transform matrix
            var mapleft=parseFloat(comp[4]) //get left value
            var maptop=parseFloat(comp[5])  //get top value
            $(".gm-style>div:first>div:first>div:last>div").css({ //get the map container. not sure if stable
              "transform":"none",
              "left":mapleft,
              "top":maptop,
            })

            html2canvas($('#testcanvas'),
            {   
              /* Html2canvas properties */
              useCORS: true,
              onrendered: function(canvas)
              {

				    var extra_canvas = document.createElement('canvas');
                		extra_canvas.setAttribute('width', 235);
                		extra_canvas.setAttribute('height', 235);
					var ctx = extra_canvas.getContext("2d");
						ctx.drawImage(canvas, (canvas.width/2)-117.5, (canvas.height/2)-117.5, 235, 235, 0, 0, 235, 235);
				    var finalimage = extra_canvas.toDataURL('image/jpg'); /* Must stay as JPG */

				    /* Start Ajax call  */
				    if(finalimage != null){
						var base64Backside = finalimage;
						
						console.log(finalimage);
						
						var xhr = new XMLHttpRequest();
						var url = "/uploadBackside";
						
						xhr.open("POST", url, true);
						xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
						xhr.send("base64Backside="+base64Backside);
						
						xhr.onreadystatechange = function () {
							if (xhr.readyState == 4 && xhr.status == 200) {
								
								$.confirm({
									type: "blue",
									title: "Confirme la imagen:",
									content: "<html><body><div style='text-align:center'><img src="+ base64Backside +" /></div></body></html>",
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
		    	/* End Ajax call */
				    
				
                $(".gm-style>div:first>div:first>div:last>div").css({
                  left:0,
                  top:0,
                  "transform":transform
                })

              }
            });
        }