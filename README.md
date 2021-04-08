
# Distributed Matrix Multiplication

## References: 
https://grpc.io/

https://grpc.io/docs/languages/java/basics/

https://spring.io/guides/tutorials/rest/

## Setup:
At least 9 servers 
1. Clone this repo into each server
2. run ``` sudo apt install default-jdk maven ``` on each server
3. on client server ```cd``` into grpc-client and run ```./mvnw clean spring-boot:run``` 
4. on the rest of the servers ```cd``` into grpc-server and run ```./mvnw clean spring-boot:run -Dmaven.test.skip=true```

To upload matrix file use ```<server_public_ip>:8082/upload```. 
The call accepts a body form data with 2 keys:
1. file - txt file containing 2 matrices - check provided file (MatrixFormatFile.txt) for matrix format
2. deadline - in seconds


## NOTE:
This version is implemented using ```ServiceNameBlockingStub``` for better performance and scalling an asynchronous stub can be used:
https://grpc.io/docs/languages/java/generated-code/#asynchronous-stub
