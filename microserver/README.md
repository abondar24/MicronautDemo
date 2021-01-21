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

3. Swagger yml: localhost:8040/swagger/microserver-0.1.yml
4. Swagger UI: localhost:8040/swagger/views/swagger-ui/
5. Rapidoc: localhost:8040/swagger/views/rapidoc/
6. Auth header requires bearer.


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
