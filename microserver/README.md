# Microserver

Basic micronaut server writing data to database via REST API and sending data to Kafka.

## Notes

1. Server url: localhost:8040
2. Login url: localhost:8040/login
Credentials body
   ```
   {
   "username":"admin",
   "password":"microserver"
   }
    ```

3. Swagger url: localhost:8040/swagger.
4. Swagger requires JWT token
5. Auth header requires bearer.


## Build and run

1. Jar run
```
./mvnw clean install

java -jar <jar-location>/microserver-0.1.jar 

```

2. Docker image build with graal-vm
```
./mvnw package -Dpackaging=docker-native

docker run -p 8040:8040 microserver-0.1
```
