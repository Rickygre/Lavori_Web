# Build
mvn clean package && docker build -t com.mycompany/esame_R.Greggio .

# RUN

docker rm -f esame_R.Greggio || true && docker run -d -p 8080:8080 -p 4848:4848 --name esame_R.Greggio com.mycompany/esame_R.Greggio 

# System Test

Switch to the "-st" module and perform:

mvn compile failsafe:integration-test