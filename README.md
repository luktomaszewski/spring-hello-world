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

### Manually:


1) Run ActiveMQ (for example using Docker):
```
docker pull webcenter/activemq
docker run -p 8161:8161 -p 61616:61616 webcenter/activemq:latest
```
2) Run SpringHelloWorld app (in main project directory)
```
gradlew clean bootRun
```

### Automatically: 

**REQUIRED: Docker is running**

```
gradlew clean buildDocker
docker-compose up
```

## Goals
- [x] mvn -> gradle
- [x] swagger
- [x] ~~elasticsearch: index service~~
- [x] activemq: listener
- [x] test coverage: 100%
- [ ] docker-compose