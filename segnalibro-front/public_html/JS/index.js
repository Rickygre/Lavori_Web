document.querySelector('#btnlogin').addEventListener('click', e => onLogin(e));
document.querySelector('#btnregistra').addEventListener('click', e => onRegistra(e));



const onLogin = (e) => {
    e.preventDefault();
    console.log("onLogin()code...", e);
}


const onRegistra = (e) => {
    e.preventDefault();
    console.log("onRegistra()code...", e);
    window.location = 'registration.html';  //collego altra pagina per visualizzare output
}

const doLogin = (usr, pwd) => {
    const resp = await
    fetch('http//localhost:8080/resources/utenti/login', {
        method: 'POST',
        mode: 'cors',
        cache: 'no-chache',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({usr, pwd})

    })
    
    
    if (!resp.ok) {
        throw new Error("problemi con fetch. response code:" + resp.status);
    }
    return resp.json();


}
