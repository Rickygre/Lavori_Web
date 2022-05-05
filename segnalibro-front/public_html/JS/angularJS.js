


var app = angular.module('myApp', []);
app.controller('loginCtrl',
        function login ($scope, $http) {
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
                        $scope.bookmarks = response.data.data;
                        },
                        function (response) {  //caso errore login
                        sessionStorage.clear();
                                $scope.loggedusr = "nessun utente trovato";
                                $scope.logged = false;
                        });
                };
        });

