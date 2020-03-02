
let getButtons = document.querySelectorAll(".btn-get");
let getContent = document.getElementById("get-content");

let brand = document.querySelector("#brand-insert");
let model = document.querySelector("#model-insert");
let insertContent = document.getElementById("insert-content");
let insertButton = document.querySelector("#insert-btn");

let deleteButton = document.querySelector("#delete-btn");
let selected = document.querySelector("#toDelete");
let valueToDelete = document.querySelector("#value-delete");
let deletedContent = document.getElementById("deleted-content");

const loadCars = function (brand) {
    fetch('http://localhost:8083/cars?brand=' + brand, {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "mode": "cors"
        }
    }).then((response) => {
        if (response.ok) {
            return response.json();
        }
    }).then((myJson) => {
        if (myJson.length > 0) {
            getContent.innerHTML = "";
            for (let car of myJson) {
                getContent.innerHTML += "<div>" + car.brand + " - " + car.model + "</div>";
            }
        }
    }).catch((error) => {
        console.log(error);
    });
}
// Charger les voitures en fontion de leurs marques
for (let button of getButtons) {
    button.addEventListener("click", (event) => {
        event.preventDefault();
        let brand = button.innerHTML.trim();
        console.log(brand)
        if (brand.length > 0) {
            loadCars(brand)
        }
    });
}

// Ajouter une nouvelle voiture
insertButton.addEventListener("click", function (event) {
    event.preventDefault();
    const insertForm = $('.insert-form').serializeArray();
    if (insertForm[0].value.trim().length > 0 && insertForm[1].value.trim().length > 0) {
        const body = {
            brand: insertForm[0].value.trim(),
            model: insertForm[1].value.trim()
        };
        fetch('http://localhost:8083/cars/insert', {
            method: "POST",
            body: JSON.stringify(body),
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "mode": "cors"
            }
        }).then(response => response.text()
        ).then(response => { 
            insertContent.innerHTML = response;
            loadCars(body.brand) 
        }
        ).catch(error => console.log(error));
    } else {
        insertContent.innerHTML = "Veuillez remplir les champs vides..."
    }
});

// Effacer une ou plusieurs voitures
deleteButton.addEventListener("click", function (event) {
    event.preventDefault();
    const deleteForm = $('.delete-form').serializeArray();
    if ((deleteForm[0].value.trim() !== '') && (deleteForm[1].value.trim() !== '')) {
        const body = {
            column: deleteForm[0].value.trim(),
            value: deleteForm[1].value.trim()
        };
        fetch('http://localhost:8083/cars/delete', {
            method: "DELETE",
            body: JSON.stringify(body),
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "mode": "cors"
            }
        }).then(response => response.text()
        ).then(response => {
            deletedContent.innerHTML = response;
            loadCars(body.column)
        }
        ).catch(error => console.log(error));
    }
});
