document.querySelector("#nieuwCadeauForm").addEventListener("submit",function(){
        var formData = new FormData(document.querySelector("#nieuwCadeauForm"));
        var encData = new URLSearchParams(formData.entries());
        fetch("/webapp/restservices/planners", {method: 'POST', body: encData})
        .then(response => response.json())
        .then(function () {
            alert(myJson);
        })

   }
);