# Build
mvn clean package && docker build -t com.mycompany/segnalibro .

# RUN

docker rm -f segnalibro || true && docker run -d -p 8080:8080 -p 4848:4848 --name segnalibro com.mycompany/segnalibro 

# System Test

Switch to the "-st" module and perform:

mvn compile failsafe:integration-test