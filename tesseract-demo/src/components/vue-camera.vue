<template>
<div>
  <video ref="video" autoplay></video>
  <button v-on:click="doOcr()">Click me</button>
	<img ref="image" src="../assets/actual.jpeg">
</div>
</template>

<script src="https://webrtc.github.io/adapter/adapter-latest.js"></script>

<script>

export default {
	mounted()
	{
		console.log(this.$el);
      function isMobile(){
        // if we want a more complete list use this: http://detectmobilebrowsers.com/
        // str.test() is more efficent than str.match()
        // remember str.test is case sensitive
        var isMobile = (/iphone|ipod|android|ie|blackberry|fennec/).test
        (navigator.userAgent.toLowerCase());
        return isMobile;
      }
		navigator.mediaDevices.enumerateDevices().then( devices =>
		{
			devices= devices.filter( v => (v.kind=="videoinput"));
			console.log("Found "+devices.length +" video devices");
			let lastDevice= devices[devices.length-1];
			devices= devices.filter( v => (v.label.indexOf("back")>0));
			let device= null;
			if( devices.length > 0 )
			{
				console.log("Taking a 'back' camera");
				device= devices[devices.length-1];
			}
			else
			{
				console.log("Taking last camera");
				device= lastDevice;
			}
			if( !device )
			{
				console.log("No devices!");
				return;
			}
			let mobile = isMobile();
			// if(window.innerHeight > window.innerWidth){
			//   mobile = true;
            // }
			console.log(mobile);
			console.log(window.innerHeight);
			console.log(window.innerWidth);
			let constraints =
			{
				audio: false,
				video: {
					deviceId: { ideal: device.deviceId },
					width: { ideal: mobile ? window.innerHeight : window.innerWidth },
					height: { ideal: mobile ? window.innerWidth : window.innerHeight}
				}
			};
			navigator.mediaDevices.getUserMedia(constraints)
			.then( stream =>
			{
				try {
				  this.$refs.video.srcObject = stream;
				} catch (error) {
				  this.$refs.video.srcObject = URL.createObjectURL(stream);
				}
				//info.innerHTML+= "<pre>DONE</pre>";
				console.log("DONE");
			})
			.catch( err =>
			{
				console.log(err.name + ": " + err.message);
			});
		})
		.catch( err =>
		{
			console.log(err.name + ": " + err.message);
		});
  },
  methods: {
    doOcr() {
      // var video = this.$refs.video
    
      // var canvas = document.createElement('canvas')
      // canvas.width = video.clientWidth;
      // canvas.height = video.clientHeight;
      // canvas.getContext('2d').drawImage(video, 0, 0, canvas.width, canvas.height);
      // var img = document.createElement("img")
      // img.src = canvas.toDataURL();

			var img = this.$refs.image

      Tesseract.recognize(img).then(function(result) {
        console.log(result.text)
      })
    },
  }
}
</script>