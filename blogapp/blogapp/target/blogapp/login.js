console.log("script start ok..");
let txtUsr = document.getElementById("Usr");
let txtPwd = document.getElementById("Pwd");
let btnLogin = document.getElementById("btnLogin");

btnLogin.addEventListener("click", v => {
    v.preventDefault();
    const credential = {
        usr: txtUsr.value,
        pwd: txtPwd.value
    };
    fetch("http:/localhost:8080/blogapp/resources/users/login", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credential)
    }).then(response => response.json())
            .then(data => console.log(data));

});




fetch("http:/localhost:8080/blogapp/resources/users", {
    method: 'GET',
    headers: {
        'Authorization': 'Bearer' + window.localStorage.getItem("Token")
                //'Content-Type': 'application/json'
    }
})
        .then(response => {
            if (response.ok === false) {

                console.log("errore caricamento utenti");
                console.log(response);
            } else {
                console.log('risposta OK')
            }

        }).then(data => data.forEach(v => console.log(v)));













