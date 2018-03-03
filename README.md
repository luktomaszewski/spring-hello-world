# SpringHelloWorld

My first simple SpringBoot Project 

## Stack

java8
spring-boot
swagger2
assertj
mockito
junit
hibernate
h2
gradle
activemq

## Getting Started

## How to run
   
#### using docker-compose

**REQUIRED: Docker is running**

```
gradlew clean build
docker-compose up
```

#### using activemq in docker

**REQUIRED: Docker is running**

```
docker run -p 8161:8161 -p 61616:61616 webcenter/activemq:latest
gradlew clean bootRun -Dspring.profiles.active=local
```

#### using local activemq

**REQUIRED: ActiveMQ is running**

with user: `admin` & password: `admin`

```
gradlew clean bootRun -Dspring.profiles.active=local
```

## Goals
- [x] mvn -> gradle
- [x] swagger
- [x] ~~elasticsearch: index service~~
- [x] activemq: listener
- [x] unit test coverage: 100%
- [x] docker-compose
- [ ] integration tests