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
				  useCORS: true,
				  onrendered: function(canvas)
				  {
				    var dataUrl= canvas.toDataURL('image/png');
				    location.href=dataUrl //for testing I never get window.open to work
				    document.getElementById("put").src= dataUrl;
				    $(".gm-style>div:first>div:first>div:last>div").css({
				      left:0,
				      top:0,
				      "transform":transform
				    })
				    
				  }
				});
			}