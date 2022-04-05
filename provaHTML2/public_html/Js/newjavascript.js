function caricadati(){

let saluto = document.getElementById("nome");
saluto.innerHTML = "Riccardo,";

let file = "https://catfact.ninja/fact";

fetch(file) //chiamata al serve che ritorna un json
        .then(x => { // dichiaro variabile x
            let res = x.json(); //implemento metodo json alla variabile x e ritorno un risultato con la var res
            return res;
        }
        )
        .then(y => saluto.innerHTML =
                    y.fact + "<br>" + y.length);


}