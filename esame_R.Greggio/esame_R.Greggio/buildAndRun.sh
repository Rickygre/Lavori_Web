#!/bin/sh
mvn clean package && docker build -t com.mycompany/esame_R.Greggio .
docker rm -f esame_R.Greggio || true && docker run -d -p 8080:8080 -p 4848:4848 --name esame_R.Greggio com.mycompany/esame_R.Greggio 
