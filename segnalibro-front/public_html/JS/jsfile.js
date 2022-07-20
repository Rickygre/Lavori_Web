
function getBookmarks() {  //funzione carica il file jsonArray di /bookmarks
   let path = "http://localhost:8080/segnalibro/resources/bookmarks";
    fetch(path)
            .then(ris =>
                ris.json())
            .then(jsobj => {

                //let f1 = "descr";
                let arbookmark = jsobj;
                let table = "<table>";
                for (bookmark of arbookmark) {
                    let row = "<tr>" + 
                            "<td>"
                            + bookmark.id +
                            "</td>"+
                            "<td>"
                            + bookmark.descr + 
                            "</td>"+ 

                            "<td>"
                            + bookmark.link +
                            "</td>" + 

                            "<td>"
                            + bookmark.utente.email +
                            "</td>" + 

                            "</tr>";
                    table += row;
                }
                table += "</table>";
                document.querySelector("#divbkms").innerHTML = table;
            });
}

function init() {
    let usr = sessionStorage.getItem("myusr");
    if (usr)
    {
        document.getElementById("loggeduser").innerHTML = usr;
    } else //non loggato  
    {
        document.getElementById("loggeduser").innerHTML = " nessun utente loggato";
    }
}

    function logout() {
        sessionStorage.removeItem("globaljwt"); //pulisce solo una proprietà
        sessionStorage.clear(); //pulisce tutte le sessioni di questo dominio
        let jwt = sessionStorage.getItem("globaljwt");
        window.location.href = "/segnalibro-front/index.html";
    }

    function login() {
        let url = "http://localhost:8080/segnalibro/resources/utenti/login"; //path delle risorse 
        let usr = document.querySelector("#usr").value; //seleziono campo usr
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

                .then(response => {            //come risposta richiamo la parte della risp json e la rispedisco indietro
                    if (response.status == 401) {
                        alert("username errato!! inserire le credenziali corrette.");
                    } else
                        return response.json();
                })

                .then(jsobj => {              //jsobj è un parametro che uso per tirare su risorse
                    if (jsobj != undefined || true) {
                        sessionStorage.setItem("globaljwt", jsobj.jwt);
                        let decoded = jwt_decode(jsobj.jwt);
                        sessionStorage.setItem("myid", decoded.sub);
                        sessionStorage.setItem("myusr", decoded.upn);
                        document.querySelector("#loggeduser").innerHTML = sessionStorage.getItem("myusr");

                    }
                })
                .catch(error => {
                    console.log(error)
                    document.querySelector("#loggeduser").innerHTML = "Utente non trovato!";
                    });

}

function postBookmarks() {
        let url = "http://localhost:8080/segnalibro/resources/bookmarks"; //path delle risorse 
        let c = document.querySelector("#condiviso").value; //seleziono campo usr
        let l = document.querySelector("#link").value;    //seleziono campo pwd
        let d = document.querySelector("#descr").value;
        if(c=='true')
            c=true;
        else
            c=false;
                 
    let postuser = {
        "id": parseInt(sessionStorage.getItem("myid"))
    }
        
    let postdata = {
            "condiviso": c,
            "link": l,
            "descr":d,
            "utente": postuser
        }
        
        let auth = "Bearer " + sessionStorage.getItem("globaljwt");
        postdata = JSON.stringify(postdata); //oggetto body diventa da ogg javascript un ogg stringa json
        console.log(postdata);     
        console.log(JSON.stringify(postdata)); 
        //struttura della chiamata:
        
    fetch(url,
                {
                    method: "post",
                    body: postdata,
                    
                    headers: {
                        "Accept": 'application/json',
                        "Content-type": 'application/json',
                        "Authorization": auth
                        
                    }
                })

                .then(response => {            //come risposta richiamo la parte della risp json e la rispedisco indietro
                    if (response.status == 401) {
                        alert("username errato!! inserire le credenziali corrette.");
                    } else
                        return response.json();
                })

                .then(jsobj => {              //jsobj è un parametro che uso per tirare su risorse
                    if (jsobj != undefined) {
                        console.log(jsobj.toString());
                   
                    }
                })
                .catch(error => {
                    console.log(error)
                    document.querySelector("#loggeduser").innerHTML = "bookmarks caricati correttamente!";
                    });

}


    function deleteBookmarks(){
        let txtnumbook = document.querySelector("#numbook");
        let url = "http://localhost:8080/segnalibro/resources/bookmarks/" + txtnumbook.value;
        
        console.log(url);
        
        /*"id": parseInt(sessionStorage.getItem("myid"))
         }
        */
         fetch(url, {
            "method":'delete',
             //"body": postuser,
            headers: {
                "Accept":'application/json',
                "Content-type":'application/json'
            }})
                
              
                .catch(error => {
                    console.log(error)
                    document.querySelector("#delBook").innerHTML = "bookmarks non eliminati!";
                    });                   
    
}