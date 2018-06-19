function getParam(url) {
    var queryString = url ? url.split('?')[1] :
        window.location.search.slice(1);
    var obj = {};
    if (queryString) {
        queryString = queryString.split('#')[0];
        var arr = queryString.split('&');
        for (var i = 0; i < arr.length; i++) {
            var a = arr[i].split('=');
            var paramNum = undefined;
            var paramName = a[0].replace(/\[\d*\]/, function (v) {
                paramNum = v.slice(1, -1);
                return '';
            });
            var paramValue = typeof (a[1]) === 'undefined' ? true : a[1];
            paramName = paramName.toLowerCase();
            paramValue = paramValue.toLowerCase();
            if (obj[paramName]) {
                if (typeof obj[paramName] === 'string') {
                    obj[paramName] = [obj[paramName]];
                }
                if (typeof paramNum === 'undefined') {
                    obj[paramName].push(paramValue);
                } else {
                    obj[paramName][paramNum] = paramValue;
                }
            } else {
                obj[paramName] = paramValue;
            }
        }
    }

    return obj;
}

fetch("/restservices/planners/" + getParam().id, {
        method: 'GET'
    }).then(response => response.json())
    .then(function (myJson) {
        myJson.forEach(function (row) {
            document.getElementById("titelGroot").innerHTML = row.titel;
            document.getElementById("beschrijvingGroot").innerHTML = row.beschrijving;
            document.getElementById("einddatumGroot").innerHTML = row.einddatum;
        })
    });

fetch("/restservices/idee/" + getParam().id, {
        method: 'GET'
    }).then(response => response.json())
    .then(function (myJson) {
        myJson.forEach(function (row) {
            var tr = document.createElement("TR");
            tr.setAttribute("id", "tr" + row.idee_id);
            var idee_id = document.createElement("TD");
            document.getElementById("mijnCadeausTable").appendChild(tr).appendChild(idee_id).innerHTML = row.idee_id;
            var titel = document.createElement("TD");
            document.getElementById("tr" + row.idee_id).appendChild(titel).innerHTML = row.titel;
            var tijdvereist = document.createElement("TD");
            document.getElementById("tr" + row.idee_id).appendChild(tijdvereist).innerHTML = row.tijdvereist;
            var prijs = document.createElement("TD");
            document.getElementById("tr" + row.idee_id).appendChild(prijs).innerHTML = '€ ' + row.prijs;
            var opmerking = document.createElement("TD");
            document.getElementById("tr" + row.idee_id).appendChild(opmerking).innerHTML = row.opmerking;
            var stemmen = document.createElement("TD");
            if (row.aantalstemmen > 3) {
                document.getElementById("tr" + row.idee_id).appendChild(stemmen).innerHTML = '‍👨‍👩‍👧‍👦 ' + row.aantalstemmen;
            } else if (row.aantalstemmen == 3) {
                document.getElementById("tr" + row.idee_id).appendChild(stemmen).innerHTML = '👨‍👨‍👧 ' + row.aantalstemmen;
            } else if (row.aantalstemmen == 2) {
                document.getElementById("tr" + row.idee_id).appendChild(stemmen).innerHTML = '‍👨‍👦 ' + row.aantalstemmen;
            } else if (row.aantalstemmen == 1) {
                document.getElementById("tr" + row.idee_id).appendChild(stemmen).innerHTML = '🙃‍️ ' + row.aantalstemmen;
            } else {
                document.getElementById("tr" + row.idee_id).appendChild(stemmen).innerHTML = '🙁 ' + row.aantalstemmen;
            }
            var stemKnop = document.createElement("TD");
            document.getElementById("tr" + row.idee_id).appendChild(stemKnop).innerHTML = "<button id='"+ row.idee_id + "' class='btn btn-success'>Stemmen</button>";
            document.getElementById(row.idee_id).addEventListener("click", stem);
        })
    });

function stem(event) {
    var id = event.target.id;
    fetch("/restservices/idee/" + id, {
            method: 'PUT'
        }).then(function (response) {
            if (response.ok) { // response-status = 200 OK
                console.log("Gestemd!");
            } else console.log("Kan niet stemmen!");
        }).then(location.reload());

}

document.getElementById("planner_idInput").value = getParam().id;

document.getElementById("ideeToevoegenKnop").addEventListener("click", function () {
    var formData = new FormData(document.querySelector("#ideeToevoegen"));
    formData.append('id', '1');
    var encData = new URLSearchParams(formData.entries());
    fetch("/restservices/idee", {
            method: 'POST',
            body: encData
        })
        .then(location.reload());

});




//alert(getParam().id);
