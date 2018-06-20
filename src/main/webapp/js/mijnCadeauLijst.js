var deleteId = 0;
document.getElementById("modalDelete").addEventListener("click",verwijderen);

fetch("/restservices/planners", {
        method: 'GET'
    })
    .then(response => response.json())
    .then(function (myJson) {
        myJson.forEach(function (row) {
            var tr = document.createElement("TR");
            tr.setAttribute("id", "tr" + row.planner_id);
            var planner_id = document.createElement("TD");
            document.getElementById("mijnCadeausTable").appendChild(tr);
            var titel = document.createElement("TD");
            document.getElementById("tr" + row.planner_id).appendChild(titel).innerHTML = row.titel;
            var beschrijving = document.createElement("TD");
            document.getElementById("tr" + row.planner_id).appendChild(beschrijving).innerHTML = row.beschrijving;
            var einddatum = document.createElement("TD");
            document.getElementById("tr" + row.planner_id).appendChild(einddatum).innerHTML = row.einddatum;
            var knop = document.createElement("TD")
            document.getElementById("tr" + row.planner_id).appendChild(knop).innerHTML = "<a class='btn btn-success' href='/planner.html?id="+row.planner_id+"'>Bekijken</a>";
            var verwijderKnop = document.createElement("TD");
            document.getElementById("tr" + row.planner_id).appendChild(verwijderKnop).innerHTML = "<button id='"+row.planner_id+"' class='btn btn-danger'>Verwijderen</button>";
            //document.getElementById(row.planner_id).addEventListener("click", verwijderen);
            $(document).ready(function () {
                $("#"+row.planner_id).click(function () {
                    deleteId = row.planner_id;
                    $("#verwijderModal").modal();
                    document.getElementById("plannerInfoDelete").innerHTML = "Wil je planner: " + "'" + row.titel + "'" + " verwijderen?";
                });
            });
        })
    });

function verwijderen() {
    fetch("/restservices/planners/" + deleteId, {
            method: 'DELETE'
        })
        .then(function (response) {
            if (response.ok) { // response-status = 200 OK
                console.log("Planner verwijderd!");
            } else if (response.status == 404)
            console.log("Planner niet gevonden!");
            else console.log("Planner kan niet worden verwijderd!");
        })
        .then(location.reload());
}
