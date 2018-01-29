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
elasticsearch
activemq

## Getting Started

### Manually:

1) Run Elasticsearch (for example using Docker):
```
docker pull elasticsearch:5.6.5
docker run -d -p 9200:9200 -p 9300:9300 <image_id>
```
2) Run ActiveMQ (for example using Docker):
```
docker pull webcenter/activemq
docker run -p 8161:8161 -p 61616:61616 <image_id>
```
3) Run SpringHelloWorld app (in main project directory)
```
gradlew clean bootRun
```

### Automatically: 
```
soon - in progress
```

## Goals
- [x] mvn -> gradle
- [x] swagger
- [x] elasticsearch: index service
- [x] activemq: listener
- [ ] test coverage: 100% (current: 82%)
- [ ] docker-compose