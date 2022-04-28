function getBookmarks() {  //funzione carica il file jsonArray di /bookmarks

    let path = "http://localhost:8080/segnalibro/resources/bookmarks";
    fetch(path)
            .then(ris =>
                ris.json())
            .then(jsobj => {

                let f1 = "descrizione";
                let arbookmark = jsobj;
                let table = "<table>";
                for (bookmark of arbookmark) {
                    let row = "<tr><td>"
                            + bookmark[f1]
                            + "</td><td>"
                            + bookmark.link +
                            "</td></tr>";
                    table += row;
                }
                table += "</table>";
                document.querySelector("#divbkms").innerHTML = table;
            });
}


var globaljwt ="";                //oggetto json che dentro ci sarà jwt   //var variabile globale

function login() {

    let url = "http://localhost:8080/segnalibro/resources/utenti/login"; //path delle risorse 
    let usr = document.querySelector("#usr").value;  //seleziono campo usr
    let pwd = document.querySelector("#pwd").value; //seleziono campo pwd
    let postdata = {
        "usr": usr,
        "pwd": pwd
    };

    postdata = JSON.stringify(postdata); //oggetto body diventa da ogg javascript un ogg stringa json

    //struttura della chiamata:
    fetch(url,
            {
                method: "post",
                body: postdata,
                headers: {
                    'Accept': 'application/json',
                    'Content-type': 'application/json'
                }

            })
            .then(response =>          //come risposta richiamo la parte della risp json e la rispedisco indietro
                response.json())
                        .then(jsobj => {      //jsobj è un parametro che uso per tirare su risorse
                            globaljwt=jsobj.jwt;
                            console.log(globaljwt);
                })
                











}