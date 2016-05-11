function getTable(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
      if (xhttp.readyState == 4 && xhttp.status == 200) {
    	  document.getElementById("table").innerHTML = "TEST CHANGED!" ;
        document.getElementById("table").innerHTML = xhttp.getAllResponseHeaders() ;
      }
    };
    xhttp.open("GET" , "/StudentService/service?id=2&format=xml");
    xhttp.send();
  }  


function test(){
	alert("TEST");
}
