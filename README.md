# Sample Employee Management MicroService Using H2 memory mode , Spring boot , Jpa , Kafka pub-sub

In this tutorial example i created a simple Microservice that spring boot to save employee data in the embded H2 memroty database and to push and event message to kafka MQ  


## Development

### Prerequisites
- [Docker](https://docs.docker.com/engine/install/)
- [Docker-Compose](https://docs.docker.com/compose/install/)
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


## Create Employee endpoint sample request

```
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"name":"MohamedAtroush" ,"email":"test2@gmail.com","mobileNumber":"0100228999","age":"30","address":"WorldCity"}' \
  http://localhost:8080/api/employees
  ```

## To update an employee state to In-CHECK 

```
curl --header "Content-Type: application/json" --request POST http://localhost:8080/api/employees/1/state/incheck
```

## To update an employee state to Approved

```
curl --header "Content-Type: application/json" --request POST http://localhost:8080/api/employees/1/state/approve
```
active

## To update an employee state to Active

```
curl --header "Content-Type: application/json" --request POST http://localhost:8080/api/employees/1/state/active

```