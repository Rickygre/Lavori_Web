/*document.querySelector('#btnlogin').addEventListener('click', e => onLogin(e));
 document.querySelector('#btnregistra').addEventListener('click', e => onRegistra(e));
 const onLogin = (e) => {
 e.preventDefault();
 console.log("onLogin()code...", e);
 }
 
 const onRegistra = (e) => {
 e.preventDefault();
 console.log("onRegistra()code...", e);
 window.location = 'registration.html'; //collego altra pagina per visualizzare output
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
 }).then(response => response.json())
 return response.json();
 }
 
 const doLogin = (usr, pwd) =>
 fetch('http://localhost:8080/resources/utenti/login', {
 method: 'POST',
 headers: {
 'Content-Type': 'application/json',
 },
 body: JSON.stringify({
 // your expected POST request payload goes here
 title: "My post title",
 body: "My post content."
 })
 })
 .then(res => res.json())
 .then(data => {
 // enter you logic when the fetch is successful
 console.log(data)
 })
 .catch(error => {
 // enter your logic for when there is an error (ex. error toast)
 console.log(error)
 })*/




