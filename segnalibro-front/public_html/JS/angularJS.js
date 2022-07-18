

var app = angular.module('myApp', []);
app.controller('loginCtrl',
        function ($scope, $http) {
            $scope.usr = '';
            $scope.pwd = '';
            $scope.logged = false;
            $scope.login = function () {
                //genero variabile (param) che conterrà parametri per questa fetch
                let req = {
                    url: "http://localhost:8080/segnalibro/resources/utenti/login",
                    method: "POST",
                    data: {usr: $scope.usr, pwd: $scope.pwd},
                    headers: {'Accept': 'application/json',
                        'Content-type': 'application/json'}
                };
                $http(req).then(
                        function (response) {  //caso successo
                            jsobj = response.data;
                            sessionStorage.setItem("myjwt", jsobj.jwt);
                            let decoded = jwt_decode(jsobj.jwt);
                            sessionStorage.setItem("myid", decoded.sub);
                            sessionStorage.setItem("myusr", decoded.upn);
                            $scope.loggedusr = sessionStorage.getItem("myusr");
                            $scope.logged = true;
                            $scope.getMyBkms();
                        },
                        function (response) {  //caso non successo
                            sessionStorage.clear();
                            $scope.loggedusr = "nessun utente trovato";
                            $scope.logged = false;
                        });
            };

            $scope.getMyBkms = function () {
                let  myurl = "http://localhost:8080/segnalibro/resources/bookmarks/" + sessionStorage.getItem("myid");
                let req = {
                    url: myurl,
                    method: "GET",
                    headers: {
                        "Authorization": "Bearer" + sessionStorage.getItem("myjwt"),
                        "Accept": 'application/json',
                        "Content-type": 'application/json'
                    }
                };
                $http(req).then(
                        function (response) {  //caso successo
                            console.log(response);
                            $scope.bookmarks = response.data;
                        },
                        function (response) {  //caso errore login
                            sessionStorage.clear();
                            $scope.loggedusr = "Utente NON Trovato!!";
                            $scope.logged = false;
                        });
            };

            $scope.creaBkm = function () {
                let  myurl = "http://localhost:8080/segnalibro/resources/bookmarks/";
                let sh = false;
                if ($scope.sh === "true")
                    sh = true;
                let c = $scope.condiviso;
                let d = $scope.descr;
                let l = $scope.link;
                let postuser = {
                    "id": parseInt(sessionStorage.getItem("myid"))
                };
                let req = {
                    url: myurl,
                    method: "POST",
                    data: {
                        condiviso: c,
                        descr: d,
                        link: l,
                        utente: postuser
                    },
                    headers: {
                        "Authorization": "Bearer" + sessionStorage.getItem("myjwt"),
                        "Accept": 'application/json',
                        "Content-type": 'application/json'
                    }

                };
                $http(req).then(
                        function (response) { //caso successo   
                            console.log(response);
                            $scope.msginsbkm = "Bookmark inserito correttamente!!";
                            $scope.getMyBkms();

                        },
                        function (response) {
                            $scope.msginsbkm = "ATTENZIONE book non creato!!";
                        });
            };


            $scope.logout = function () {
                sessionStorage.removeItem("globaljwt"); //pulisce solo una proprietà
                sessionStorage.clear(); //pulisce tutte le sessioni di questo dominio
                let jwt = sessionStorage.getItem("globaljwt");
                window.location.href = "/segnalibro-front/es_angularjs.html";
            };

            $scope.EliminaBK = function () {
                let  myurl = "http://localhost:8080/segnalibro/resources/bookmarks/"+ $scope.iddelete;
                console.log(myurl);

                let req = {
                    url: myurl,
                    method: "delete",
                    headers: {
                        "Accept": 'application/json',
                        "Content-type": 'application/json'
                    }

                };
                $http(req).then(
                        function (response) { //caso successo   
                            console.log(response);
                            $scope.msginsbkm = "bookmark eliminato con successo!!!";
                             $scope.getMyBkms();
                            

                        },
                        function (response) {
                            $scope.msginsbkm = "Eliminazione bookmark non riuscita!! bookmark non presente!!";
                        });


            }


        });

