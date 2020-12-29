# Sample Employee Management MicroService Using H2 memory mode , Spring boot , Jpa , Kafka pub-sub

## Development

### Prerequisites
- [Docker](https://docs.docker.com/engine/install/)
- [Docker-Compose]
- [Maven](https://maven.apache.org/download.cgi)


### To Run 



```
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=peopleflw/ems

```

then 

```
docker-compose -f ./docker-compose.yml up -d
```

### API Documentations 

```
http://localhost:8080/v3/api-docs/
```


# Create Employee endpoint sample request

```
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"name":"MohamedAli" ,"email":"test2@gmail.com","mobileNumber":"0100228999","age":"30","address":"WorldCity"}' \
  http://localhost:8080/api/employees
  ```

