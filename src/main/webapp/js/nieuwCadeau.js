document.querySelector("#nieuwCadeauForm").addEventListener("submit",function(){
        var formData = new FormData(document.querySelector("#nieuwCadeauForm"));
        var encData = new URLSearchParams(formData.entries());
        fetch("/restservices/planners", {method: 'POST', body: encData})
        .then(response => response.json())
        .then(function () {
            alert(myJson);
        })

   }
);

var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1;

var yyyy = today.getFullYear();
if(dd<10){
    dd='0'+dd;
} 
if(mm<10){
    mm='0'+mm;
} 
var today = yyyy+'-'+mm+'-'+dd;
var max = (yyyy+1)+'-'+mm+'-'+dd;
//var today = dd+'-'+mm+'-'+yyyy;
document.getElementById("deadlineCadeau").setAttribute("min", today);
document.getElementById("deadlineCadeau").setAttribute("max", max);
